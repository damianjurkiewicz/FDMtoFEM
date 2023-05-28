package math.abaqus;

import java.util.ArrayList;
import java.util.List;

public class AbaqusModel {

    private List<AbaqusVertex> vertices;

    private List<AbaqusEdge> edges;

    public AbaqusModel() {
	this.vertices = new ArrayList<AbaqusVertex>();
	this.edges = new ArrayList<AbaqusEdge>();
    }

    public List<AbaqusVertex> getVertices() {
	return vertices;
    }

    public void setVertices(List<AbaqusVertex> vertices) {
	this.vertices = vertices;
    }

    public List<AbaqusEdge> getEdges() {
	return edges;
    }

    public void setEdges(List<AbaqusEdge> edges) {
	this.edges = edges;
    }

    public AbaqusVertex addVertex(int id, double x, double y, double z) {
	AbaqusVertex vertex = new AbaqusVertex(id, x, y, z);
	this.vertices.add(vertex);
	return vertex;
    }

    public void removeVertex(AbaqusVertex vertex) {
	this.vertices.remove(vertex);
    }

    public AbaqusEdge addEdge(int edgeId, AbaqusVertex vertex1, AbaqusVertex vertex2) {
	AbaqusEdge edge = new AbaqusEdge(edgeId, vertex1, vertex2);
	this.edges.add(edge);
	return edge;
    }

    public void removeEdge(AbaqusEdge edge) {
	this.edges.remove(edge);
    }

    public AbaqusEdge findEdge(AbaqusVertex vertex1, AbaqusVertex vertex2) {
	AbaqusEdge abaqusEdge = null;

	for (AbaqusEdge currentAbaqusEdge : this.edges) {

	    if (currentAbaqusEdge.getVertex1() == vertex1 && currentAbaqusEdge.getVertex2() == vertex2) {
		abaqusEdge = currentAbaqusEdge;
	    }
	}

	return abaqusEdge;
    }

    public String toString() {
	String vertices = "";
	String elements = "";

	for (AbaqusVertex abaqusVertex : this.vertices) {
	    double x = abaqusVertex.getX();
	    double y = abaqusVertex.getY();
	    double z = abaqusVertex.getZ();
	    int id = abaqusVertex.getId();
	    vertices = vertices + id + ", " + x + ", " + y + ", " + z + "\n";
	}

	for (AbaqusEdge abaqusEdge : this.edges) {
	    int edgeId = abaqusEdge.getEdgeId();
	    int v1Id = abaqusEdge.getVertex1().getId();
	    int v2Id = abaqusEdge.getVertex2().getId();
	    elements = elements + edgeId + ", " + v1Id + ", " + v2Id + "\n";
	}

	return vertices + "*Element, type=B31" + "\n" + elements;
    }

}
