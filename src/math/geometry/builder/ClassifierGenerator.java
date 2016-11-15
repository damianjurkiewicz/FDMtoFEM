package math.geometry.builder;

import equations.Equations;
import math.gcode.model.GCodeEdge;
import math.gcode.model.GCodeLayer;
import math.gcode.model.GCodeModel;
import math.geometry.model.Model;
import math.geometry.model.Vertex;

public class ClassifierGenerator implements Generator {

    int elementId = 1;
    private int nodeId = 1;
    private double elementSize;

    public ClassifierGenerator(double elementSize) {
	this.elementSize = elementSize;
    }

    @Override
    public Model build(GCodeModel gCodeModel) {
	Model model = new Model();
	generateVertices(gCodeModel, model);
	generateEdges(gCodeModel, model);
	return model;
    }

    private void generateVertices(GCodeModel gCodeModel, Model model) {

	double x1, y1, x2, y2, nextX = 0, nextY = 0, z;
	double incrementX, incrementY;
	double leftoverX, leftoverY;
	double edgeLengthX, edgeLengthY;
	double edgeLength;
	int numberOfWholeSegments;

	for (GCodeLayer gCodeLayer : gCodeModel.getLayers()) {

	    for (GCodeEdge gCodeEdge : gCodeLayer.getEdges()) {

		x1 = gCodeEdge.getVertex1().getX();
		y1 = gCodeEdge.getVertex1().getY();
		x2 = gCodeEdge.getVertex2().getX();
		y2 = gCodeEdge.getVertex2().getY();
		z = gCodeEdge.getLayer().getZ();
		Vertex vertex1;
		Vertex vertex2;
		Vertex vertex3;

		edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
		numberOfWholeSegments = Equations.computeNumberOfWholeSegments(edgeLength, elementSize);

		if (numberOfWholeSegments < 1) {
		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);
		}

		if (numberOfWholeSegments == 1) {
		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, (x1 + x2) / 2, (y1 + y2) / 2, z, gCodeEdge);
		    vertex3 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);
		    model.addEdge(elementId++, vertex2, vertex3);
		}

		if (numberOfWholeSegments > 1) {
		    edgeLengthX = Equations.computeEdgeLength(x1, x2);
		    edgeLengthY = Equations.computeEdgeLength(y1, y2);
		    incrementX = Equations.computeIncrement(edgeLengthX, edgeLength, elementSize);
		    incrementY = Equations.computeIncrement(edgeLengthY, edgeLength, elementSize);
		    leftoverX = Equations.computeLeftover(edgeLengthX, incrementX, numberOfWholeSegments);
		    leftoverY = Equations.computeLeftover(edgeLengthY, incrementY, numberOfWholeSegments);

		    nextX = x1 + leftoverX / 2;
		    nextY = y1 + leftoverY / 2;

		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, nextX, nextY, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);

		    for (int k = 0; k < numberOfWholeSegments; k++) {
			vertex1 = model.addVertex(nodeId++, nextX, nextY, z, gCodeEdge);
			vertex2 = model.addVertex(nodeId++, nextX = nextX + incrementX, nextY = nextY + incrementY, z,
				gCodeEdge);
			model.addEdge(elementId++, vertex1, vertex2);
		    }

		    vertex3 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex2, vertex3);
		}
	    }
	}
    }

    boolean isNotDuplicate;

    private void generateEdges(GCodeModel gCodeModel, Model model) {
	for (Vertex vertex : model.getVertices()) {
	    isNotDuplicate = false;
	    for (Vertex nextVertex : model.getVertices()) {

		if (vertex.getGCodeEdge() != nextVertex.getGCodeEdge()) {

		    if (vertex.getZ() == nextVertex.getZ()) {
			double d = Equations.computeInLayerDistance(vertex, nextVertex);
			if (d <= 0.9) {

			    if (model.findEdge(vertex, nextVertex) != null) {
				isNotDuplicate = true;
			    }

			    if (model.findEdge(nextVertex, vertex) != null) {
				isNotDuplicate = true;
			    }

			    if (isNotDuplicate = true) {
				model.addInLayerJoint(elementId++, vertex, nextVertex);
			    }
			}
		    }
		}

		if (vertex.getZ() == nextVertex.getZ() + nextVertex.getZ()) {

		    double d = Equations.computeInterLayerDistance(vertex, nextVertex);
		    if (d <= 1) {

			if (model.findEdge(vertex, nextVertex) != null) {
			    isNotDuplicate = true;
			}

			if (model.findEdge(nextVertex, vertex) != null) {
			    isNotDuplicate = true;
			}

			if (isNotDuplicate = true) {
			    model.addInterLayerJoint(elementId++, vertex, nextVertex);
			}

		    }
		}

	    }
	}
    }
}
