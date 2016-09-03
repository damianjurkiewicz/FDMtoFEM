package math.model;

import java.util.ArrayList;
import java.util.List;

public class GCodeLayer {

    private List<GCodeEdge> edges;
    private List<GCodeVertex> vertices;

    public GCodeLayer() {
	this.edges = new ArrayList<GCodeEdge>();
	this.vertices = new ArrayList<GCodeVertex>();
    }

    public List<GCodeEdge> getEdges() {
	return edges;
    }

    public List<GCodeVertex> getVertices() {
	return vertices;
    }

}
