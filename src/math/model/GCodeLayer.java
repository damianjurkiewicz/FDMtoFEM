package math.model;

import java.util.ArrayList;
import java.util.List;

public class Layer {

    private List<Edge> edges;
    private List<Vertex> vertices;

    public Layer() {
	this.edges = new ArrayList<Edge>();
	this.vertices = new ArrayList<Vertex>();
    }

    public List<Edge> getEdges() {
	return edges;
    }

    public List<Vertex> getVertices() {
	return vertices;
    }

}
