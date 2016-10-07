package math.abaqus;

import equations.Equations;
import math.model.GCodeEdge;
import math.model.GCodeModel;

// Code Tells You How, Comments Tell You Why
public class AbaqusModelBuilder {

    public AbaqusModel fragmentEdges(GCodeModel gCodeModel) {
	int layerNumber, edgeNumber;
	double elementSize = 1.5;
	double x1, y1, x2, y2, nextX = 0, nextY = 0;
	double z;
	GCodeEdge gCodeEdge = null;
	double edgeLength;
	double linearFunctionSlope;
	double linearFunctionParam;
	int wholeSegmentsNumber = 0;
	double edgeLeftovers = 0;

	AbaqusModel abaqusModel = new AbaqusModel();

	for (layerNumber = 0; layerNumber < gCodeModel.getLayers().size(); layerNumber++) {

	    for (edgeNumber = 0; edgeNumber < gCodeModel.getLayers().get(layerNumber).getEdges().size(); edgeNumber++) {

		gCodeEdge = gCodeModel.getLayers().get(layerNumber).getEdges().get(edgeNumber);

		x1 = gCodeEdge.getVertex1().getX();
		y1 = gCodeEdge.getVertex1().getY();
		x2 = gCodeEdge.getVertex2().getX();
		y2 = gCodeEdge.getVertex2().getY();
		z = gCodeEdge.getLayer().getZ();

		edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);

		if (Equations.edgeIsShort(edgeLength, elementSize)) {
		    abaqusModel.addVertex(x1, y1, z);
		    abaqusModel.addVertex(x2, y2, z);
		}

		if (Equations.edgeIsMediumLong(edgeLength, elementSize)) {

		    if (Equations.edgeIsVertical(x1, x2)) {
			nextX = x1;
			nextY = (y1 + y2) / 2; // for medium long edge, edge is
					       // divide in half
		    }

		    if (Equations.edgeIsHorizontal(y1, y2)) {
			nextY = y1;
			nextX = (x1 + x2) / 2; // for medium long edge, edge is
					       // divide in half
		    }

		    if (Equations.edgeIsSlanted(x1, x2, y1, y2)) {
			linearFunctionSlope = Equations.computeSlope(x1, x2, y1, y2);
			linearFunctionParam = Equations.computeParam(x1, y1, linearFunctionSlope);
			nextX = (x2 + x1) / 2;
			nextY = linearFunctionSlope * nextX + linearFunctionParam;
		    }

		    abaqusModel.addVertex(x1, y1, z);
		    abaqusModel.addVertex(nextX, nextY, z);
		    abaqusModel.addVertex(x2, y2, z);

		}

		if (Equations.edgeIsLong(edgeLength, elementSize)) {

		    if (Equations.edgeIsVertical(x1, x2)) {
			wholeSegmentsNumber = (int) (edgeLength / elementSize);
			edgeLeftovers = (edgeLength - elementSize * wholeSegmentsNumber);
			nextX = x1;
			nextY = y1 + edgeLeftovers / 2;

			abaqusModel.addVertex(x1, y1, z);

			abaqusModel.addVertex(nextX, nextY, z);

			for (int segmentNumber = 0; segmentNumber < wholeSegmentsNumber; segmentNumber++) {
			    nextY = nextY + elementSize;
			    abaqusModel.addVertex(nextX, nextY, z);
			}

			abaqusModel.addVertex(x2, y2, z);
		    }

		    if (Equations.edgeIsHorizontal(y1, y2)) {
			wholeSegmentsNumber = (int) (edgeLength / elementSize);
			edgeLeftovers = (edgeLength - elementSize * wholeSegmentsNumber);
			nextX = x1 + edgeLeftovers / 2;
			nextY = y1;

			abaqusModel.addVertex(x1, y1, z);
			abaqusModel.addVertex(x1 + (edgeLeftovers / 2), y1, z);

			for (int k = 0; k < wholeSegmentsNumber; k++) {
			    abaqusModel.addVertex(nextX = nextX + elementSize, nextY, z);
			}
			abaqusModel.addVertex(x2, y2, z);

		    }

		    if (Equations.edgeIsSlanted(x1, x2, y1, y2)) {
			double a = (y1 - y2) / (x1 - x2);
			double b = y1 - a * x1;
			double pX = ((x2 - x1) / edgeLength) * elementSize;
			wholeSegmentsNumber = (int) ((x2 - x1) / pX);
			edgeLeftovers = (x2 - x1) - pX * wholeSegmentsNumber;

			nextX = x1 + edgeLeftovers / 2;
			nextY = a * nextX + b;

			abaqusModel.addVertex(x1, y1, z);
			abaqusModel.addVertex(nextX, nextY, z);

			for (int k = 0; k < wholeSegmentsNumber; k++) {
			    abaqusModel.addVertex(nextX = nextX + pX, nextY = a * nextX + b, z);

			}

			abaqusModel.addVertex(x2, y2, z);

		    }

		}

	    }
	}

	return abaqusModel;
    }

    /**
     * @param wspolrzedna
     *            x poczatku edge'a
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    // private double obliczWspolKierProstej(double x1, double y1, double x2,
    // double y2) {
    // return (y1 - y2) / (x1 - x2);
    // }

}
