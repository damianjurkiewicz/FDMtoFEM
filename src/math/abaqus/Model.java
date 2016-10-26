package math.abaqus;

import java.util.ArrayList;
import java.util.List;

import math.model.GCodeEdge;

public class Model {

    private List<Vertex> vertices;

    private List<Edge> edges;

    public List<Edge> inPlaneJoints;

    public Model() {
	this.vertices = new ArrayList<Vertex>();
	this.edges = new ArrayList<Edge>();
	this.inPlaneJoints = new ArrayList<Edge>();
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

    public Edge addInPlaneJoint(int edgeId, Vertex vertex1, Vertex vertex2) {
	Edge edge = new Edge(edgeId, vertex1, vertex2);
	this.inPlaneJoints.add(edge);
	return edge;
    }

    public void removeInPlaneJoint(Edge edge) {
	this.inPlaneJoints.remove(edge);
    }

    public String toString() {
	String vertices = "";
	String elements = "";

	for (Vertex abaqusVertex : this.vertices) {
	    double x = abaqusVertex.getX();
	    double y = abaqusVertex.getY();
	    double z = abaqusVertex.getZ();
	    int id = abaqusVertex.getId();
	    vertices = vertices + id + ", " + x + ", " + y + ", " + z + "\n";
	}

	for (Edge abaqusEdge : this.edges) {
	    int edgeId = abaqusEdge.getEdgeId();
	    int v1Id = abaqusEdge.getVertex1().getId();
	    int v2Id = abaqusEdge.getVertex2().getId();
	    elements = elements + edgeId + ", " + v1Id + ", " + v2Id + "\n";
	}

	return vertices + "*Element, type=B31" + "\n" + elements;
    }

}
