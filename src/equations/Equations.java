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
	 * @param coordinate1 x1 or y1
	 * 			
	 * @param coordinate2 x2 or y2
	 * @return
	 */

	public static double computeEdgeLength(double x1, double x2, double y1, double y2) {
		double edgeLength = Math.sqrt(Math.pow(x2 - x1, 2d) + Math.pow(y2 - y1, 2d));
		return edgeLength;
	}
	
	public static int computeWholeSegmentsNumber(double edgeLength, double elementSize) {
		int wholeSegmentsNumber = (int) (edgeLength / elementSize);
		return wholeSegmentsNumber;
	}


	public static double computeIncrement(double coordinate1, double coordinate2, double edgeLength, double elementSize) {
		double increment = (coordinate2 - coordinate1) / edgeLength * elementSize;
		return increment;
	}

	public static double computeLeftover(double coordinate1, double coordinate2, double increment, double wholeSegmentsNumber) {
		double edgeLeftover = ((coordinate2 - coordinate1) - increment * wholeSegmentsNumber) / 2;
		return edgeLeftover;
	}

	public static double computeVertexDistance(AbaqusVertex vertex1, AbaqusVertex vertex2) {
		double vertexDistance = Math
				.sqrt(Math.pow(vertex2.getX() - vertex1.getX(), 2d) + Math.pow(vertex2.getY() - vertex1.getY(), 2d));
		return vertexDistance;

	}

}
