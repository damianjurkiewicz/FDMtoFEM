package math.abaqus;

import math.model.GCodeEdge;

public class AbaqusEdge {

    private AbaqusVertex vertex1;
    private AbaqusVertex vertex2;
    private GCodeEdge gCodeEdge;

    public AbaqusEdge(AbaqusVertex vertex1, AbaqusVertex vertex2, GCodeEdge gCodeEdge) {
	super();
	this.vertex1 = vertex1;
	this.vertex2 = vertex2;
	this.gCodeEdge = gCodeEdge;
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

    public GCodeEdge getgCodeEdge() {
	return gCodeEdge;
    }

    public void setgCodeEdge(GCodeEdge gCodeEdge) {
	this.gCodeEdge = gCodeEdge;
    }

}
