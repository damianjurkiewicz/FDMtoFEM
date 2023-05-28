package math.abaqus;

public class AbaqusVertex {

	private double x;
	private double y;
	private double z;
	private AbaqusModel model;

	public AbaqusVertex(AbaqusModel model, double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.model = model;
	}

	public AbaqusModel getModel() {
		return model;
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

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
