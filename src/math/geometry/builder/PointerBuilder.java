package math.geometry.builder;

import equations.Equations;
import math.gcode.model.GCodeEdge;
import math.gcode.model.GCodeLayer;
import math.gcode.model.GCodeModel;
import math.geometry.model.Model;
import math.geometry.model.Vertex;

public class PointerBuilder implements Generator {

    int elementId = 1;
    private double elementSize;

    int nodeId = 1;

    public PointerBuilder(double elementSize) {
	super();
	this.elementSize = elementSize;
    }

    @Override
    public Model build(GCodeModel gCodeModel) {
	Model model = new Model();
	generateVertices(gCodeModel, model);
	generateEdges(gCodeModel, model);
	return model;
    }

    public void generateVertices(GCodeModel gCodeModel, Model model) {
	double x1, y1, x2, y2, nextX, nextY, z;
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

		edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
		numberOfWholeSegments = Equations.computeNumberOfWholeSegments(edgeLength, elementSize);

		if (numberOfWholeSegments == 0) {
		    model.addVertex(nodeId++, (x1 + x2) / 2, (y1 + y2) / 2, z);
		}

		if (numberOfWholeSegments > 0) {
		    edgeLengthX = Equations.computeEdgeLength(x1, x2);
		    edgeLengthY = Equations.computeEdgeLength(y1, y2);
		    incrementX = Equations.computeIncrement(edgeLengthX, edgeLength, elementSize);
		    incrementY = Equations.computeIncrement(edgeLengthY, edgeLength, elementSize);
		    leftoverX = Equations.computeLeftover(edgeLengthX, incrementX, numberOfWholeSegments);
		    leftoverY = Equations.computeLeftover(edgeLengthY, incrementY, numberOfWholeSegments);

		    nextX = x1 + leftoverX / 2;
		    nextY = y1 + leftoverY / 2;
		    model.addVertex(nodeId++, nextX, nextY, z);

		    for (int k = 0; k < numberOfWholeSegments; k++) {
			nextX = nextX + incrementX;
			nextY = nextY + incrementY;
			model.addVertex(nodeId++, nextX, nextY, z);
		    }
		}
	    }
	}
    }

    boolean notDuplicate;

    public void generateEdges(GCodeModel gCodeModel, Model model) {
	for (Vertex currentVertex : model.getVertices()) {

	    for (Vertex vertex : model.getVertices()) {
		notDuplicate = false;

		if (currentVertex.getZ() == vertex.getZ()) {
		    if (currentVertex != vertex) {
			double vertexDistance = Equations.computeInLayerDistance(currentVertex, vertex);

			if (vertexDistance <= this.elementSize + 0.01) {

			    if (model.findEdge(currentVertex, vertex) != null) {
				notDuplicate = true;
			    }

			    if (model.findEdge(vertex, currentVertex) != null) {
				notDuplicate = true;
			    }

			    if (notDuplicate = true) {
				model.addEdge(elementId++, vertex, currentVertex);
			    }
			}
		    }
		}

		if (currentVertex.getZ() != vertex.getZ()) {
		    if (currentVertex != vertex) {
			double vertexDistance = Equations.computeInterLayerDistance(currentVertex, vertex);

			if (vertexDistance <= 1.01) {

			    if (model.findEdge(currentVertex, vertex) != null) {
				notDuplicate = true;
			    }

			    if (model.findEdge(vertex, currentVertex) != null) {
				notDuplicate = true;
			    }

			    if (notDuplicate = true) {
				model.addInterLayerJoint(elementId++, vertex, currentVertex);
			    }
			}
		    }
		}
	    }
	}
    }

}
