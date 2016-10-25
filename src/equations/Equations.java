package equations;

import math.abaqus.AbaqusVertex;

public class Equations {

    /**
     * @param x1
     *            X coordinate of initial vertex of edge
     * @param y1
     *            Y coordinate of initial vertex of edge
     * @param x2
     *            X coordinate of ending vertex of edge
     * @param y2
     *            Y coordinate of ending vertex of edge
     * @param coordinate1
     *            x1 or y1
     * 
     * @param coordinate2
     *            x2 or y2
     * @return
     */

    public static double computeEdgeLength(double x1, double x2, double y1, double y2) {
	double edgeLength = Math.sqrt(Math.pow(x2 - x1, 2d) + Math.pow(y2 - y1, 2d));
	return edgeLength;
    }

    public static double computeEdgeLength(double coordinate1, double coordinate2) {
	double edgeLength = coordinate2 - coordinate1;
	return edgeLength;
    }

    public static int computeNumberOfWholeSegments(double edgeLength, double elementSize) {
	int wholeSegmentsNumber = (int) (edgeLength / elementSize);
	return wholeSegmentsNumber;
    }

    public static double computeIncrement(double edgeLengthXorY, double edgeLength, double elementSize) {
	double increment = (edgeLengthXorY) / edgeLength * elementSize;
	return increment;
    }

    public static double computeLeftover(double edgeLengthXorY, double increment, double wholeSegmentsNumber) {
	double edgeLeftover = ((edgeLengthXorY) - increment * wholeSegmentsNumber);
	return edgeLeftover;
    }

    public static double computeVertexDistance(AbaqusVertex vertex1, AbaqusVertex vertex2) {
	double vertexDistance = Math
		.sqrt(Math.pow(vertex2.getX() - vertex1.getX(), 2d) + Math.pow(vertex2.getY() - vertex1.getY(), 2d));
	return vertexDistance;
    }

    public static double computeVector(double coordinate1, double coordinate2) {
	double vector = coordinate2 - coordinate1;
	return vector;
    }

    public static double computeDotProduct(double vector1X, double vector2X, double vector1Y, double vector2Y) {
	double dotProduct = vector1X * vector2X + vector1Y + vector2Y;
	return dotProduct;
    }

    public static double computeCos(double dotProduct, double length1, double length2) {
	double cos = dotProduct / (length1 * length2);
	cos = (180 / (Math.PI)) * cos;
	return cos;
    }

    public static double computeSlope(double x1, double x2, double y1, double y2) {
	double slope = 0;
	if (x2 - x1 != 0) {
	    slope = (y2 - y1) / (x2 - x1);
	}
	return slope;

    }

}
