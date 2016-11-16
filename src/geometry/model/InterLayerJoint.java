package geometry.model;

import writer.FormatModelElements;

public class InterLayerJoint extends Edge  {

    public InterLayerJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	super(edgeId, vertex1, vertex2);
    }

    @Override
    public String receive(FormatModelElements modelElement) {
	return modelElement.formatInterLayerJoint(this);
    }

}
