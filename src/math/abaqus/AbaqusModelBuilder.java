package math.abaqus;

import equations.Equations;
import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;

// Code Tells You How, Comments Tell You Why
public class AbaqusModelBuilder {

    private double elementSize;

    public AbaqusModelBuilder(double elementSize) {
	super();
	this.elementSize = elementSize;
    }

    public double getElementSize() {
	return elementSize;
    }

    public void setElementSize(double elementSize) {
	this.elementSize = elementSize;
    }

    public AbaqusModel build(GCodeModel gCodeModel) {
	AbaqusModel abaqusModel = new AbaqusModel();
	mesh(gCodeModel, abaqusModel);
	join(gCodeModel, abaqusModel);
	// remove(gCodeModel, abaqusModel);
	return abaqusModel;
    }

    int id = 1;
    int edgeId = 1;

    public void mesh(GCodeModel gCodeModel, AbaqusModel abaqusModel) {
	// TODO: elementSize should be User choince, but with reasonable bounds

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
		AbaqusVertex vertex1;
		AbaqusVertex vertex2 = null;
		AbaqusVertex vertex3;

		edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
		numberOfWholeSegments = Equations.computeNumberOfWholeSegments(edgeLength, elementSize);

		if (numberOfWholeSegments < 1) {
		    vertex1 = abaqusModel.addVertex(id++, x1, y1, z, gCodeEdge);
		    vertex2 = abaqusModel.addVertex(id++, x2, y2, z, gCodeEdge);
		    abaqusModel.addEdge(edgeId++, vertex1, vertex2);
		}

		if (numberOfWholeSegments == 1) {
		    vertex1 = abaqusModel.addVertex(id++, x1, y1, z, gCodeEdge);
		    vertex2 = abaqusModel.addVertex(id++, (x1 + x2) / 2, (y1 + y2) / 2, z, gCodeEdge);
		    vertex3 = abaqusModel.addVertex(id++, x2, y2, z, gCodeEdge);
		    abaqusModel.addEdge(edgeId++, vertex1, vertex2);
		    abaqusModel.addEdge(edgeId++, vertex2, vertex3);
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

		    vertex1 = abaqusModel.addVertex(id++, x1, y1, z, gCodeEdge);
		    vertex2 = abaqusModel.addVertex(id++, nextX, nextY, z, gCodeEdge);
		    abaqusModel.addEdge(edgeId++, vertex1, vertex2);

		    for (int k = 0; k < numberOfWholeSegments - 2; k++) {
			vertex1 = abaqusModel.addVertex(id++, nextX, nextY, z, gCodeEdge);
			vertex2 = abaqusModel.addVertex(id++, nextX = nextX + incrX, nextY = nextY + incrY, z,
				gCodeEdge);
			abaqusModel.addEdge(edgeId++, vertex1, vertex2);
		    }

		    vertex3 = abaqusModel.addVertex(id++, x2, y2, z, gCodeEdge);
		    abaqusModel.addEdge(edgeId++, vertex2, vertex3);

		}
	    }
	}

    }

    public void join(GCodeModel gCodeModel, AbaqusModel abaqusModel) {

	for (AbaqusVertex abaqusVertex : abaqusModel.getVertices()) {

	    for (AbaqusVertex nextAbaqusVertex : abaqusModel.getVertices()) {

		if (abaqusVertex.getgCodeEdge() != nextAbaqusVertex.getgCodeEdge()) {

		    if (abaqusVertex.getZ() == nextAbaqusVertex.getZ()
			    || abaqusVertex.getZ() == nextAbaqusVertex.getZ() + 1) {
			double d = Equations.computeVertexDistance(abaqusVertex, nextAbaqusVertex);
			if (d < 1.4) {
			    abaqusModel.addEdge(edgeId++, abaqusVertex, nextAbaqusVertex);
			}
		    }
		}
	    }

	}

    }

    public void remove(GCodeModel gCodeModel, AbaqusModel abaqusModel) {

	for (AbaqusEdge abaqusEdge : abaqusModel.getEdges()) {

	    for (AbaqusEdge nextAbaqusEdge : abaqusModel.getEdges()) {

		if (abaqusEdge.getVertex1() == nextAbaqusEdge.getVertex1()
			|| abaqusEdge.getVertex2() == nextAbaqusEdge.getVertex2()) {

		    double x1 = abaqusEdge.getVertex1().getX();
		    double y1 = abaqusEdge.getVertex1().getY();
		    double x2 = abaqusEdge.getVertex2().getX();
		    double y2 = abaqusEdge.getVertex2().getY();

		    double xx1 = nextAbaqusEdge.getVertex1().getX();
		    double yy1 = nextAbaqusEdge.getVertex1().getY();
		    double xx2 = nextAbaqusEdge.getVertex2().getX();
		    double yy2 = nextAbaqusEdge.getVertex2().getY();

		    double a1 = (y2 - y1) / (x2 - x1);
		    double a2 = (yy2 - yy1) / (xx2 - xx1);

		    double tan = Math.abs((a1 - a2) / (1 + a1 * a2));

		    if (tan > 58) {
			abaqusModel.removeEdge(abaqusEdge);
		    }

		}

	    }

	}

    }

}
