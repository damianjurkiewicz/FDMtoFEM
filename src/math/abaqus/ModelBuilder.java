package math.abaqus;

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
	Model abaqusModel = new Model();
	generateVertices(gCodeModel, abaqusModel);
	generateEdges(gCodeModel, abaqusModel);
	return abaqusModel;
    }

    public void generateVertices(GCodeModel gCodeModel, Model abaqusModel) {
	// TODO: elementSize should be User choince, but with reasonable bounds

	double x1, y1, x2, y2, nextX, nextY, z;
	double incrementX, incrementY;
	double leftoverX, leftoverY;
	double edgeLengthX, edgeLengthY;
	double edgeLength;
	int numberOfWholeSegments;
	int id = 1;

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
		    abaqusModel.addVertex(id++, (x1 + x2) / 2, (y1 + y2) / 2, z);
		}

		if (numberOfWholeSegments > 0) {
		    edgeLengthX = Equations.computeEdgeLength(x1, x2);
		    edgeLengthY = Equations.computeEdgeLength(y1, y2);
		    incrementX = Equations.computeIncrement(edgeLengthX, edgeLength, elementSize);
		    incrementY = Equations.computeIncrement(edgeLengthY, edgeLength, elementSize);
		    leftoverX = Equations.computeLeftover(edgeLengthX, incrementX, numberOfWholeSegments);
		    leftoverY = Equations.computeLeftover(edgeLengthY, incrementY, numberOfWholeSegments);

		    // abaqusModel.addVertex(x1, y1, z);
		    nextX = x1 + leftoverX / 2;
		    nextY = y1 + leftoverY / 2;
		    abaqusModel.addVertex(id++, nextX, nextY, z);

		    for (int k = 0; k < numberOfWholeSegments; k++) {
			nextX = nextX + incrementX;
			nextY = nextY + incrementY;
			abaqusModel.addVertex(id++, nextX, nextY, z);
		    }
		    // abaqusModel.addVertex(x2, y2, z);

		}
	    }

	}

    }

    public void generateEdges(GCodeModel gCodeModel, Model abaqusModel) {

	int edgeId = 1;

	for (Vertex currentAbaqusVertex : abaqusModel.getVertices()) {

	    for (Vertex abaqusVertex : abaqusModel.getVertices()) {

		if (currentAbaqusVertex.getZ() == abaqusVertex.getZ()
			|| currentAbaqusVertex.getZ() == abaqusVertex.getZ() + 1) {

		    if (currentAbaqusVertex != abaqusVertex) {
			// metoda w abaqus
			double vertexDistance = Equations.computeVertexDistance(currentAbaqusVertex, abaqusVertex);

			// if (currentAbaqusVertex.getDistanceTo(abaqusVertex)
			// <=
			// this.elementSize){

			if (vertexDistance <= this.elementSize + 0.000001) {

			    if (abaqusModel.findEdge(currentAbaqusVertex, abaqusVertex) != null) {
				break;
			    }

			    if (abaqusModel.findEdge(abaqusVertex, currentAbaqusVertex) != null) {
				break;
			    }

			    abaqusModel.addEdge(edgeId++, abaqusVertex, currentAbaqusVertex);
			}
		    }
		}
	    }
	}
    }
}
