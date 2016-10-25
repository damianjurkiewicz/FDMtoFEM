package math.abaqus;

import java.util.ArrayList;
import java.util.List;

import math.model.GCodeEdge;

public class AbaqusModel {

    private List<AbaqusVertex> vertices;

    public List<AbaqusEdge> edges;

    public List<AbaqusEdge> inPlaneJoints;

    public AbaqusModel() {
	this.vertices = new ArrayList<AbaqusVertex>();
	this.edges = new ArrayList<AbaqusEdge>();
	this.inPlaneJoints = new ArrayList<AbaqusEdge>();
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

    public List<AbaqusEdge> getInPlaneJoints() {
	return inPlaneJoints;
    }

    public void setInPlaneJoints(List<AbaqusEdge> inPlaneJoints) {
	this.inPlaneJoints = inPlaneJoints;
    }

    public AbaqusVertex addVertex(int id, double x, double y, double z, GCodeEdge gCodeEdge) {
	AbaqusVertex vertex = new AbaqusVertex(id, x, y, z, gCodeEdge);
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

    public AbaqusEdge addInPlaneJoint(int edgeId, AbaqusVertex vertex1, AbaqusVertex vertex2) {
	AbaqusEdge edge = new AbaqusEdge(edgeId, vertex1, vertex2);
	this.inPlaneJoints.add(edge);
	return edge;
    }

    public void removeInPlaneJoint(AbaqusEdge edge) {
	this.inPlaneJoints.remove(edge);
    }

    public String toString() {
	String vertices = "";
	String edges = "";
	String inPlaneJoints = "";

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
	    edges = edges + edgeId + ", " + v1Id + ", " + v2Id + "\n";
	}

	for (AbaqusEdge abaqusInPlaneJoint : this.inPlaneJoints) {
	    int edgeId = abaqusInPlaneJoint.getEdgeId();
	    int v1Id = abaqusInPlaneJoint.getVertex1().getId();
	    int v2Id = abaqusInPlaneJoint.getVertex2().getId();
	    inPlaneJoints = inPlaneJoints + edgeId + ", " + v1Id + ", " + v2Id + "\n";
	}

	return vertices + "*Element, type=B31" + "\n" + edges + inPlaneJoints;
    }

}
