package math.geometry.model;

import equations.Equations;
import math.gcode.model.GCodeEdge;
import math.gcode.model.GCodeLayer;
import math.gcode.model.GCodeModel;

// Code Tells You How, Comments Tell You Why
public class ModelBuilder {

    private double elementSize;
    int nodeId = 1;
    int elementId = 1;

    public ModelBuilder(double elementSize) {
	super();
	this.elementSize = elementSize;
    }

    public double getElementSize() {
	return elementSize;
    }

    public void setElementSize(double elementSize) {
	this.elementSize = elementSize;
    }

    public Model buildCloud(GCodeModel gCodeModel) {
	Model model = new Model();
	generateVerticesCloud(gCodeModel, model);
	generateEdgesCloud(gCodeModel, model);
	return model;
    }

    public Model buildGCodeBased(GCodeModel gCodeModel) {
	Model model = new Model();
	generateVerticesGCodeBased(gCodeModel, model);
	generateEdgesGCodeBased(gCodeModel, model);
	return model;
    }

    public void generateVerticesCloud(GCodeModel gCodeModel, Model model) {
	// TODO: elementSize should be User choince, but with reasonable bounds

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

    public void generateEdgesCloud(GCodeModel gCodeModel, Model model) {

	for (Vertex currentVertex : model.getVertices()) {

	    for (Vertex vertex : model.getVertices()) {
		if (currentVertex.getZ() == vertex.getZ() || currentVertex.getZ() == vertex.getZ() + 1) {

		    if (currentVertex != vertex) {
			// metoda w abaqus
			double vertexDistance = Equations.computeVertexDistance(currentVertex, vertex);

			// if (currentAbaqusVertex.getDistanceTo(abaqusVertex)
			// <=
			// this.elementSize){

			if (vertexDistance <= this.elementSize + 0.01) {

			    if (model.findEdge(currentVertex, vertex) != null) {
				break;
			    }

			    if (model.findEdge(vertex, currentVertex) != null) {
				break;
			    }
			    model.addEdge(elementId++, vertex, currentVertex);

			}
		    }
		}
	    }
	}
    }

    public void generateVerticesGCodeBased(GCodeModel gCodeModel, Model model) {

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

		    double incrX = incrementX + (leftoverX / numberOfWholeSegments);
		    double incrY = incrementY + (leftoverY / numberOfWholeSegments);

		    nextX = x1 + incrX;
		    nextY = y1 + incrY;

		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, nextX, nextY, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);

		    for (int k = 0; k < numberOfWholeSegments - 2; k++) {
			vertex1 = model.addVertex(nodeId++, nextX, nextY, z, gCodeEdge);
			vertex2 = model.addVertex(nodeId++, nextX = nextX + incrX, nextY = nextY + incrY, z, gCodeEdge);
			model.addEdge(elementId++, vertex1, vertex2);
		    }

		    vertex3 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex2, vertex3);
		}
	    }
	}
    }

    public void generateEdgesGCodeBased(GCodeModel gCodeModel, Model model) {
	for (Vertex vertex : model.getVertices()) {

	    for (Vertex nextVertex : model.getVertices()) {

		if (vertex.getGCodeEdge() != nextVertex.getGCodeEdge()) {

		    if (vertex.getZ() == nextVertex.getZ() || vertex.getZ() == nextVertex.getZ() + nextVertex.getZ()) {
			double d = Equations.computeVertexDistance(vertex, nextVertex);
			if (d <= 1) {
			    model.addInPlaneJoint(elementId++, vertex, nextVertex);
			}
			if (vertex.getZ() == nextVertex.getZ() + nextVertex.getZ()) {
			    d = Equations.computeLayerDistance(vertex, nextVertex);
			    if (d <= 1) {
				model.addInterLayerJoint(elementId++, vertex, nextVertex);
			    }
			}
		    }
		}
	    }
	}
    }
}
