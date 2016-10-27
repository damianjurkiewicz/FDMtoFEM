package equations;

import math.geometry.Vertex;

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

    public static double computeVertexDistance(Vertex vertex1, Vertex vertex2) {
	double vertexDistance = Math
		.sqrt(Math.pow(vertex2.getX() - vertex1.getX(), 2d) + Math.pow(vertex2.getY() - vertex1.getY(), 2d));
	return vertexDistance;

    }

}
