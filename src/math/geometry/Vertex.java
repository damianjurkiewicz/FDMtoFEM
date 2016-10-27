package math.geometry;

import math.writer.WriterVisitor;

public class Vertex implements GeometryElements {

    private double x;
    private double y;
    private double z;
    private int id;

    public Vertex(int id, double x, double y, double z) {
	super();
	this.x = x;
	this.y = y;
	this.z = z;
	this.id = id;
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getY() {
	return y;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getZ() {
	return z;
    }

    public void setZ(double z) {
	this.z = z;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @Override
    public String receive(WriterVisitor writer) {
	return writer.generateNodes(this);
    }

}
