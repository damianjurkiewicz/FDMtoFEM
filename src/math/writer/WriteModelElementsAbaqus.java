package math.writer;

import math.geometry.model.Edge;
import math.geometry.model.InLayerJoint;
import math.geometry.model.InterLayerJoint;
import math.geometry.model.Vertex;

public class WriteModelElementsAbaqus implements WriteModelElements {

    @Override
    public String generateNodes(Vertex vertex) {
	String vertices = "";
	double x = vertex.getX();
	double y = vertex.getY();
	double z = vertex.getZ();
	int id = vertex.getId();
	return vertices + id + ", " + x + ", " + y + ", " + z;
    }

    @Override
    public String generateElements(Edge edge) {
	int edgeId = edge.getEdgeId();
	int v1Id = edge.getVertex1().getId();
	int v2Id = edge.getVertex2().getId();
	return edgeId + ", " + v1Id + ", " + v2Id;
    }

    @Override
    public String generateInPlaneJoint(InLayerJoint inPlaneJoint) {
	int edgeId = inPlaneJoint.getEdgeId();
	int v1Id = inPlaneJoint.getVertex1().getId();
	int v2Id = inPlaneJoint.getVertex2().getId();
	return edgeId + ", " + v1Id + ", " + v2Id;
    }

    @Override
    public String generateInterLayerJoint(InterLayerJoint interLayerJoint) {
	int edgeId = interLayerJoint.getEdgeId();
	int v1Id = interLayerJoint.getVertex1().getId();
	int v2Id = interLayerJoint.getVertex2().getId();
	return edgeId + ", " + v1Id + ", " + v2Id;
    }

}
