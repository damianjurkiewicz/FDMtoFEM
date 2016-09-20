package math.abaqus;

import java.util.ArrayList;
import java.util.List;

import math.model.GCodeEdge;

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

    public AbaqusVertex addVertex(double x, double y, double z) {
	AbaqusVertex vertex = new AbaqusVertex(x, y, z);
	this.vertices.add(vertex);
	return vertex;
    }

    public void removeVertex(AbaqusVertex vertex) {
	this.vertices.remove(vertex);
    }

    public AbaqusEdge addEdge(AbaqusVertex vertex1, AbaqusVertex vertex2, GCodeEdge gCodeEdge) {
	AbaqusEdge edge = new AbaqusEdge(vertex1, vertex2, gCodeEdge);
	this.edges.add(edge);
	return edge;
    }

    public void removeEdge(AbaqusEdge edge) {
	this.edges.remove(edge);
    }

}
