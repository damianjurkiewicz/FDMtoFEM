package math.geometry.model;

import math.writer.WriterVisitor;

public class InPlaneJoint extends Edge implements GeometryElements {
    public InPlaneJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	super(edgeId, vertex1, vertex2);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void receive(WriterVisitor writer) {
	writer.generateInPlaneJoint(this);
    }

}
