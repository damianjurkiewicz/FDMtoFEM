package equations;

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
     * @return
     */

    public static int computeWholeSegmentsNumber(double edgeLength, double elementSize) {
	int wholeSegmentsNumber = (int) (edgeLength / elementSize);
	return wholeSegmentsNumber;
    }

    public static double computeEdgeLength(double x1, double x2, double y1, double y2) {
	double edgeLength = Math.sqrt(Math.pow(x2 - x1, 2d) + Math.pow(y2 - y1, 2d));
	return edgeLength;
    }

    public static double computeIncrement(double p1, double p2, double edgeLength, double elementSize) {
	double increment = (p2 - p1) / edgeLength * elementSize;
	return increment;
    }

    public static double computeLeftover(double p1, double p2, double increment, double wholeSegmentsNumber) {
	double edgeLeftover = ((p2 - p1) - increment * wholeSegmentsNumber) / 2;
	return edgeLeftover;
    }

}
