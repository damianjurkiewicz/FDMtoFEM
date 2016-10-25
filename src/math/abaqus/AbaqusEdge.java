package math.abaqus;

import math.writer.WriterVisitor;

public class AbaqusEdge implements WriterSource {

    private AbaqusVertex vertex1;
    private AbaqusVertex vertex2;
    private int edgeId;

    public AbaqusEdge(int edgeId, AbaqusVertex vertex1, AbaqusVertex vertex2) {
	super();
	this.edgeId = edgeId;
	this.vertex1 = vertex1;
	this.vertex2 = vertex2;

    }

    public AbaqusVertex getVertex1() {
	return vertex1;
    }

    public void setVertex1(AbaqusVertex vertex1) {
	this.vertex1 = vertex1;
    }

    public AbaqusVertex getVertex2() {
	return vertex2;
    }

    public void setVertex2(AbaqusVertex vertex2) {
	this.vertex2 = vertex2;
    }

    public int getEdgeId() {
	return edgeId;
    }

    public void setEdgeId(int edgeId) {
	this.edgeId = edgeId;
    }

    @Override
    public void przyjmij(WriterVisitor writer) {
	// TODO Auto-generated method stub

    }

}
