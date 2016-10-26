package math.writer;

import math.geometry.model.Edge;
import math.geometry.model.InPlaneJoint;
import math.geometry.model.Vertex;

public interface WriterVisitor {

    public String generateHeader();

    public String generateNodes(Vertex vertex);

    public String generateMiddle();

    public String generateInPlaneJoint(InPlaneJoint inPlaneJoint);

    public String generateElements(Edge edges);

    public String generateBottom();

}
