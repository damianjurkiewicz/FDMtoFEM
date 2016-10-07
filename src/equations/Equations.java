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

    public static double computeSlope(double x1, double x2, double y1, double y2) {

	double linearFunctionSlope = (y1 - y2) / (x1 - x2);
	return linearFunctionSlope;

    }

    public static double computeParam(double x1, double y1, double linearFunctionSlope) {
	double linearFunctionParam = y1 - (linearFunctionSlope * x1);
	return linearFunctionParam;
    }

    public static double computeEdgeLength(double x1, double x2, double y1, double y2) {
	double length = Math.sqrt(Math.pow(x2 - x1, 2d) + Math.pow(y2 - y1, 2d));
	return length;
    }

    public static boolean edgeIsVertical(double x1, double x2) {
	return x1 == x2;
    }

    public static boolean edgeIsHorizontal(double y1, double y2) {
	return y1 == y2;
    }

    public static boolean edgeIsSlanted(double x1, double x2, double y1, double y2) {
	return y1 != y2 && x1 != x2;
    }

    public static boolean edgeIsShort(double edgeLength, double elementSize) {
	return edgeLength <= elementSize;
    }

    public static boolean edgeIsMediumLong(double edgeLength, double elementSize) {
	return edgeLength < 2 * elementSize && edgeLength > elementSize;
    }

    public static boolean edgeIsLong(double edgeLength, double elementSize) {
	return edgeLength > 2 * elementSize;
    }

}
