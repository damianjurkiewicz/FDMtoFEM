package math.geometry.model;

import java.util.ArrayList;
import java.util.List;

import math.gcode.model.GCodeEdge;

public class Model {

    private List<Vertex> vertices;

    private List<Edge> edges;

    public List<InPlaneJoint> inPlaneJoints;

    public Model() {
	this.vertices = new ArrayList<Vertex>();
	this.edges = new ArrayList<Edge>();
	this.inPlaneJoints = new ArrayList<InPlaneJoint>();
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

    public List<InPlaneJoint> getInPlaneJoints() {
	return inPlaneJoints;
    }

    public void setInPlaneJoints(List<InPlaneJoint> inPlaneJoints) {
	this.inPlaneJoints = inPlaneJoints;
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

    public Edge findEdge(Vertex vertex1, Vertex vertex2) {
	Edge abaqusEdge = null;

	for (Edge currentAbaqusEdge : this.edges) {

	    if (currentAbaqusEdge.getVertex1() == vertex1 && currentAbaqusEdge.getVertex2() == vertex2) {
		abaqusEdge = currentAbaqusEdge;
	    }
	}

	return abaqusEdge;
    }

    public InPlaneJoint addInPlaneJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	InPlaneJoint inPlaneJoint = new InPlaneJoint(edgeId, vertex1, vertex2);
	this.inPlaneJoints.add(inPlaneJoint);
	return inPlaneJoint;
    }

    public void removeInPlaneJoint(Edge edge) {
	this.inPlaneJoints.remove(edge);
    }

}
