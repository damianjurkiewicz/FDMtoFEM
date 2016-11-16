package writer;

import geometry.model.Edge;
import geometry.model.InLayerJoint;
import geometry.model.InterLayerJoint;
import geometry.model.Vertex;

public class FormatModelElementsAbaqus implements FormatModelElements {

    @Override
    public String formatVertices(Vertex vertex) {
	String vertices = "";
	double x = vertex.getX();
	double y = vertex.getY();
	double z = vertex.getZ();
	int id = vertex.getId();
	return vertices + id + ", " + x + ", " + y + ", " + z;
    }

    @Override
    public String formatEdges(Edge edge) {
	int edgeId = edge.getEdgeId();
	int v1Id = edge.getVertex1().getId();
	int v2Id = edge.getVertex2().getId();
	return edgeId + ", " + v1Id + ", " + v2Id;
    }

    @Override
    public String formatInPlaneJoint(InLayerJoint inPlaneJoint) {
	int edgeId = inPlaneJoint.getEdgeId();
	int v1Id = inPlaneJoint.getVertex1().getId();
	int v2Id = inPlaneJoint.getVertex2().getId();
	return edgeId + ", " + v1Id + ", " + v2Id;
    }

    @Override
    public String formatInterLayerJoint(InterLayerJoint interLayerJoint) {
	int edgeId = interLayerJoint.getEdgeId();
	int v1Id = interLayerJoint.getVertex1().getId();
	int v2Id = interLayerJoint.getVertex2().getId();
	return edgeId + ", " + v1Id + ", " + v2Id;
    }

}
