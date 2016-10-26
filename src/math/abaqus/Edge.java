package math.abaqus;

import math.writer.WriterVisitor;

public class Edge implements GeometryElements {

    private Vertex vertex1;
    private Vertex vertex2;
    private int edgeId;

    public Edge(int edgeId, Vertex vertex1, Vertex vertex2) {
	super();
	this.edgeId = edgeId;
	this.vertex1 = vertex1;
	this.vertex2 = vertex2;

    }

    public Vertex getVertex1() {
	return vertex1;
    }

    public void setVertex1(Vertex vertex1) {
	this.vertex1 = vertex1;
    }

    public Vertex getVertex2() {
	return vertex2;
    }

    public void setVertex2(Vertex vertex2) {
	this.vertex2 = vertex2;
    }

    public int getEdgeId() {
	return edgeId;
    }

    public void setEdgeId(int edgeId) {
	this.edgeId = edgeId;
    }

    @Override
    public String receive(WriterVisitor writer) {
	return writer.generateElements(this);
    }

}
