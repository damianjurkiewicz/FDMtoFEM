package gcode.reader;

public class GCodeRow {
	private String g;
	private String x;
	private String y;
	private String z;
	private String e;

	public GCodeRow(String g, String x, String y, String z, String e) {
		this.g = g;
		this.x = x;
		this.y = y;
		this.z = z;
		this.e = e;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

}
