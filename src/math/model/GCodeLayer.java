package math.model;

import java.util.ArrayList;
import java.util.List;

public class GCodeLayer {

	private double z;
	private GCodeModel model;
	private List<GCodeEdge> edges;
	private List<GCodeVertex> vertices;

	protected GCodeLayer(GCodeModel model, double z) {
		this.z = z;
		this.model = model;
		this.edges = new ArrayList<GCodeEdge>();
		this.vertices = new ArrayList<GCodeVertex>();
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public GCodeModel getModel() {
		return model;
	}

	public void setModel(GCodeModel model) {
		this.model = model;
	}

	public List<GCodeEdge> getEdges() {
		return edges;
	}

	public void setEdges(List<GCodeEdge> edges) {
		this.edges = edges;
	}

	public List<GCodeVertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<GCodeVertex> vertices) {
		this.vertices = vertices;
	}

	public GCodeVertex addVertex(double x, double y) {
		GCodeVertex vertex = new GCodeVertex(this, x, y);
		this.vertices.add(vertex);
		return vertex;
	}

	public void removeVertex(GCodeVertex vertex) {
		this.vertices.remove(vertex);
	}

	public GCodeEdge addEdge(GCodeVertex vertex1, GCodeVertex vertex2) {
		GCodeEdge edge = new GCodeEdge(this, vertex1, vertex2);
		this.edges.add(edge);
		return edge;
	}

	public void removeEdge(GCodeEdge edge) {
		this.edges.remove(edge);
	}

	public String toString() {
		String opis = "";
		opis = "" + this.getModel().getLayers().get(0).getZ();
		return opis;
	}

}
