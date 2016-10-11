package math.abaqus;

import equations.Equations;
import math.model.GCodeEdge;
import math.model.GCodeModel;

// Code Tells You How, Comments Tell You Why
public class AbaqusModelBuilder {

    AbaqusModel abaqusModel;

    public AbaqusModel generateVertices(GCodeModel gCodeModel) {

	abaqusModel = new AbaqusModel();
	int layerNumber, edgeNumber;
	double elementSize = 1.5;
	double x1, y1, x2, y2, z, nextX, nextY;
	double edgeLength;
	double incrementX;
	double incrementY;
	double leftoverX;
	double leftoverY;

	int wholeSegments = 0;
	GCodeEdge gCodeEdge = null;

	for (layerNumber = 0; layerNumber < gCodeModel.getLayers().size(); layerNumber++) {

	    for (edgeNumber = 0; edgeNumber < gCodeModel.getLayers().get(layerNumber).getEdges().size(); edgeNumber++) {

		gCodeEdge = gCodeModel.getLayers().get(layerNumber).getEdges().get(edgeNumber);

		x1 = gCodeEdge.getVertex1().getX();
		y1 = gCodeEdge.getVertex1().getY();
		x2 = gCodeEdge.getVertex2().getX();
		y2 = gCodeEdge.getVertex2().getY();
		z = gCodeEdge.getLayer().getZ();

		edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
		wholeSegments = Equations.computeWholeSegmentsNumber(edgeLength, elementSize);

		if (wholeSegments == 0) {
		    abaqusModel.addVertex(x1, y1, z);
		    abaqusModel.addVertex(x2, y2, z);
		}

		if (wholeSegments == 1) {
		    abaqusModel.addVertex(x1, y1, z);
		    abaqusModel.addVertex((x2 + x1) / 2, (y2 + y1) / 2, z);
		    abaqusModel.addVertex(x2, y2, z);
		}

		if (wholeSegments > 1) {
		    incrementX = Equations.computeIncrement(x1, x2, edgeLength, elementSize);
		    incrementY = Equations.computeIncrement(y1, y2, edgeLength, elementSize);
		    leftoverX = Equations.computeLeftover(x1, x2, incrementX, wholeSegments);
		    leftoverY = Equations.computeLeftover(y1, y2, incrementY, wholeSegments);

		    abaqusModel.addVertex(x1, y1, z);
		    nextX = x1 + leftoverX;
		    nextY = y1 + leftoverY;
		    abaqusModel.addVertex(nextX, nextY, z);

		    for (int k = 0; k < wholeSegments; k++) {
			nextX = nextX + incrementX;
			nextY = nextY + incrementY;
			abaqusModel.addVertex(nextX, nextY, z);
		    }
		    abaqusModel.addVertex(x2, y2, z);

		}
	    }
	}

	return abaqusModel;

    }

    public AbaqusModel generateEdges(AbaqusModel abaqusModel) {

	int verticesNumber;
	for (verticesNumber = 0; verticesNumber < abaqusModel.getVertices().size(); verticesNumber++) {

	    AbaqusVertex vertex1 = abaqusModel.getVertices().get(verticesNumber);
	    AbaqusVertex vertex2 = abaqusModel.getVertices().get(verticesNumber + 1);

	    double odleglosc = Math.sqrt(
		    Math.pow(vertex2.getX() - vertex1.getX(), 2d) + Math.pow(vertex2.getY() - vertex1.getY(), 2d));

	    if (vertex1.getZ() == vertex2.getZ()) {

		if (odleglosc <= 1.51) {
		    abaqusModel.addEdge(vertex1, vertex2);
		}

	    }

	}

	return abaqusModel;
    }

}
