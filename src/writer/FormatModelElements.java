package writer;

import geometry.model.Edge;
import geometry.model.InLayerJoint;
import geometry.model.InterLayerJoint;
import geometry.model.Vertex;

public interface FormatModelElements {

    public String formatNodes(Vertex vertex);

    public String formatInPlaneJoint(InLayerJoint inPlaneJoint);

    public String formatInterLayerJoint(InterLayerJoint interLayerJoint);

    public String formatElements(Edge edges);

}
