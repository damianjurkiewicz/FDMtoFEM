package geometry.model;

import writer.FormatModelElements;

public class InLayerJoint extends Edge implements ModelElements {

    public InLayerJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	super(edgeId, vertex1, vertex2);
    }

    @Override
    public String receive(FormatModelElements writer) {
	return writer.formatInPlaneJoint(this);
    }

}
