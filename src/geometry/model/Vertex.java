package geometry.model;

import gcode.model.GCodeEdge;
import writer.FormatModelElements;

public class Vertex implements ModelElements {

	private int vertexId;
    private double x;
    private double y;
    private double z;
    private GCodeEdge gCodeEdge;

    public Vertex(int id, double x, double y, double z) {
	this.vertexId = id;
	this.x = x;
	this.y = y;
	this.z = z;
    }

    public Vertex(int id, double x, double y, double z, GCodeEdge gCodeEdge) {
	this(id, x, y, z);
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
	return vertexId;
    }

    public void setId(int id) {
	this.vertexId = id;
    }

    public GCodeEdge getGCodeEdge() {
	return gCodeEdge;
    }

    public void setGCodeEdge(GCodeEdge gCodeEdge) {
	this.gCodeEdge = gCodeEdge;
    }

    @Override
    public String receive(FormatModelElements modelElement) {
	return modelElement.formatVertices(this);
    }

}
