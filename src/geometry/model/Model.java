package geometry.model;

import java.util.ArrayList;
import java.util.List;

import gcode.model.GCodeEdge;

public class Model {

    private List<Vertex> vertices;

    private List<Edge> edges;

    public List<InLayerJoint> inLayerJoints;

    public List<InterLayerJoint> interLayerJoints;

    public Model() {
	this.vertices = new ArrayList<Vertex>();
	this.edges = new ArrayList<Edge>();
	this.inLayerJoints = new ArrayList<InLayerJoint>();
	this.interLayerJoints = new ArrayList<InterLayerJoint>();
    }

    public List<Vertex> getVertices() {
	return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
	this.vertices = vertices;
    }

    public List<Edge> getEdges() {
	return edges;
    }

    public void setEdges(List<Edge> edges) {
	this.edges = edges;
    }

    public List<InLayerJoint> getInPlaneJoints() {
	return inLayerJoints;
    }

    public void setInPlaneJoints(List<InLayerJoint> inPlaneJoints) {
	this.inLayerJoints = inPlaneJoints;
    }

    public List<InterLayerJoint> getInterLayerJoints() {
	return interLayerJoints;
    }

    public void setInterLayerJoints(List<InterLayerJoint> interLayerJoints) {
	this.interLayerJoints = interLayerJoints;
    }

    public Vertex addVertex(int id, double x, double y, double z) {
	Vertex vertex = new Vertex(id, x, y, z);
	this.vertices.add(vertex);
	return vertex;
    }

    public Vertex addVertex(int id, double x, double y, double z, GCodeEdge gCodeEdge) {
	Vertex vertex = new Vertex(id, x, y, z, gCodeEdge);
	this.vertices.add(vertex);
	return vertex;
    }

    public void removeVertex(Vertex vertex) {
	this.vertices.remove(vertex);
    }

    public Edge addEdge(int edgeId, Vertex vertex1, Vertex vertex2) {
	Edge edge = new Edge(edgeId, vertex1, vertex2);
	this.edges.add(edge);
	return edge;
    }

    public void removeEdge(Edge edge) {
	this.edges.remove(edge);
    }

    public InLayerJoint addInLayerJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	InLayerJoint inLayerJoint = new InLayerJoint(edgeId, vertex1, vertex2);
	this.inLayerJoints.add(inLayerJoint);
	return inLayerJoint;
    }

    public void removeInPlaneJoint(InLayerJoint inLayerJoint) {
	this.inLayerJoints.remove(inLayerJoint);
    }

    public InterLayerJoint addInterLayerJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	InterLayerJoint interLayerJoint = new InterLayerJoint(edgeId, vertex1, vertex2);
	this.interLayerJoints.add(interLayerJoint);
	return interLayerJoint;
    }

    public void removeInterLayerJoint(InterLayerJoint interLayerJoint) {
	this.interLayerJoints.remove(interLayerJoint);
    }

    public Edge findEdge(Vertex vertex1, Vertex vertex2) {
	Edge currentEdge = null;

	for (Edge edge : this.edges) {

	    if (edge.getVertex1() == vertex1 && edge.getVertex2() == vertex2) {
		currentEdge = edge;
	    }
	}

	return currentEdge;
    }

}
