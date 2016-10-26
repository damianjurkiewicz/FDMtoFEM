package math.geometry.model;

import math.gcode.model.GCodeEdge;
import math.writer.WriterVisitor;

public class Vertex implements GeometryElements {

    private double x;
    private double y;
    private double z;
    private int id;
    private GCodeEdge gCodeEdge;

    public Vertex(int id, double x, double y, double z) {
	this.id = id;
	this.x = x;
	this.y = y;
	this.z = z;
    }

    public Vertex(int id, double x, double y, double z, GCodeEdge gCodeEdge) {
	super();
	this.gCodeEdge = gCodeEdge;
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

    public GCodeEdge getgCodeEdge() {
	return gCodeEdge;
    }

    public void setgCodeEdge(GCodeEdge gCodeEdge) {
	this.gCodeEdge = gCodeEdge;
    }

    @Override
    public void receive(WriterVisitor writer) {
	// TODO Auto-generated method stub

    }

}
