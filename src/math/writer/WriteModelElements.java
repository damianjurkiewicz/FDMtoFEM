package math.writer;

import math.geometry.model.Edge;
import math.geometry.model.InLayerJoint;
import math.geometry.model.InterLayerJoint;
import math.geometry.model.Vertex;

public interface WriteModelElements {

    public String formatNodes(Vertex vertex);

    public String formatInPlaneJoint(InLayerJoint inPlaneJoint);

    public String formatInterLayerJoint(InterLayerJoint interLayerJoint);

    public String formatElements(Edge edges);

}
