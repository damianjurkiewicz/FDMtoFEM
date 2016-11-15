package geometry.model;

import writer.FormatModelElements;

public class InterLayerJoint extends Edge implements ModelElements {

    public InterLayerJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	super(edgeId, vertex1, vertex2);
    }

    @Override
    public String receive(FormatModelElements writer) {
	return writer.formatInterLayerJoint(this);
    }

}
