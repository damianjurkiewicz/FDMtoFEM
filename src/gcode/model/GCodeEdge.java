package gcode.model;

public class GCodeEdge {

    private GCodeVertex vertex1;
    private GCodeVertex vertex2;
    private GCodeLayer layer;

    protected GCodeEdge(GCodeLayer layer, GCodeVertex vertex1, GCodeVertex vertex2) {
	this.vertex1 = vertex1;
	this.vertex2 = vertex2;
	this.layer = layer;
    }

    public GCodeVertex getVertex1() {
	return vertex1;
    }

    public void setVertex1(GCodeVertex vertex1) {
	this.vertex1 = vertex1;
    }

    public GCodeVertex getVertex2() {
	return vertex2;
    }

    public void setVertex2(GCodeVertex vertex2) {
	this.vertex2 = vertex2;
    }

    public GCodeLayer getLayer() {
	return layer;
    }

    public double getLength() {
	double length;
	length = Math
		.sqrt(Math.pow(vertex2.getX() - vertex1.getX(), 2d) + Math.pow(vertex2.getY() - vertex1.getY(), 2d));

	return length;
    }

    public boolean isZeroLength() {
	boolean isZeroLength;
	if (getLength() == 0d) {
	    isZeroLength = true;
	} else {
	    isZeroLength = false;
	}
	return isZeroLength;
    }
}
