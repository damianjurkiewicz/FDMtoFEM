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

	public List<GCodeEdge> getEdges() {
		return edges;
	}

	public List<GCodeVertex> getVertices() {
		return vertices;
	}

	public GCodeModel getModel() {
		return model;
	}

	public GCodeVertex addVertex(double x, double y) {
		GCodeVertex vertex = new GCodeVertex(this, x, y);
		this.vertices.add(vertex);
		return vertex;
	}

	public void removeVertex(GCodeVertex vertex) {
		this.vertices.remove(vertex);
	}

	
	
	public GCodeEdge addEdge(GCodeVertex vertex1, GCodeVertex vertex2){
		GCodeEdge edge = new GCodeEdge(this, vertex1, vertex2);
		this.edges.add(edge);
		return edge;
	}

	public void removeEdge(GCodeEdge edge) {
		this.edges.remove(edge);
	}

	public String toString() {
		String opis = " ";
		int nrNoda = 1;
		for (GCodeEdge edge : edges) {

			// rzutowanie z double na Double, by moc skorzystac z toString
			Double x1 = edge.getVertex1().getX();
			Double y1 = edge.getVertex1().getY();

			Double x2 = edge.getVertex2().getX();
			Double y2 = edge.getVertex2().getY();

			opis = "Edge " + edges.size() + " " + "Vertex " + vertices.size() + "\n" + nrNoda++ + " V1 " + x1.toString()
					+ " " + y1.toString() + " " + "\n" + nrNoda++ + " V2 " + x2.toString() + " " + y2.toString();
		}

		return opis;
	}

}

