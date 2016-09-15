package math.model;

public class GCodeVertex {

	private double x;

	private double y;

	private GCodeLayer layer;

	protected GCodeVertex(GCodeLayer layer, double x, double y) {
		this.layer = layer;
		this.x = x;
		this.y = y;

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public GCodeLayer getLayer() {
		return layer;
	}

	public void setLayer(GCodeLayer layer) {
		this.layer = layer;
	}



}
