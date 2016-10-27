package math.geometry;

import equations.Equations;
import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;

// Code Tells You How, Comments Tell You Why
public class ModelBuilder {

    private double elementSize;

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

    public Model build(GCodeModel gCodeModel) {
	Model model = new Model();
	generateVertices(gCodeModel, model);
	generateEdges(gCodeModel, model);
	return model;
    }

    public void generateVertices(GCodeModel gCodeModel, Model model) {
	// TODO: elementSize should be User choince, but with reasonable bounds

	double x1, y1, x2, y2, nextX, nextY, z;
	double incrementX, incrementY;
	double leftoverX, leftoverY;
	double edgeLengthX, edgeLengthY;
	double edgeLength;
	int numberOfWholeSegments;
	int vertexId = 1;

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
		    model.addVertex(vertexId++, (x1 + x2) / 2, (y1 + y2) / 2, z);
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
		    model.addVertex(vertexId++, nextX, nextY, z);

		    for (int segmentNumber = 0; segmentNumber < numberOfWholeSegments; segmentNumber++) {
			nextX = nextX + incrementX;
			nextY = nextY + incrementY;
			model.addVertex(vertexId++, nextX, nextY, z);
		    }

		}
	    }

	}

    }

    public void generateEdges(GCodeModel gCodeModel, Model model) {

	int edgeId = 1;

	for (Vertex currentVertex : model.getVertices()) {

	    for (Vertex vertex : model.getVertices()) {

		if (currentVertex != vertex) {
		    double vertexDistance = Equations.computeVertexDistance(currentVertex, vertex);

		    // if (currentAbaqusVertex.getDistanceTo(abaqusVertex)
		    // <= this.elementSize){

		    if (vertexDistance <= this.elementSize + 0.000001) {

			if (model.findEdge(currentVertex, vertex) != null) {
			    break;
			}

			if (model.findEdge(vertex, currentVertex) != null) {
			    break;
			}

			model.addEdge(edgeId++, vertex, currentVertex);
		    }
		}
	    }
	}
    }
}
