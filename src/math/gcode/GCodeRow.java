package math.gcode;

public class GCodeRow {
    private String g;
    private String x;
    private String y;
    private String z;
    private String e;

    public GCodeRow() {
	this.g = "1";
	this.x = "0.0";
	this.y = "0.0";
	this.z = "0.0";
	this.e = "0.0";
    }

    // konstruktor
    public GCodeRow(String g, String x, String y, String z, String e) {
	this.g = g;
	this.x = x;
	this.y = y;
	this.z = z;
	this.e = e;
    }

    // wlasciwosci
    public String getG() {
	return g;
    }

    // zabiezpieczenia mo¿na umieszczaæ w bloku set, np g>0
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

    public String toString() {
	String opis = "";
	opis = opis + "g: " + this.g;
	opis = opis + "x: " + this.x;
	opis = opis + "y: " + this.y;
	opis = opis + "z: " + this.z;
	opis = opis + "e: " + this.e;
	return opis;
    }

}
