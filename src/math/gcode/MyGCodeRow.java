package math.gcode;
//przyklad rozszerzenia klasy (typy generyczne, dziedziczenie, polimorfizm)
public class MyGCodeRow extends GCodeRow {

	private String f;
	
	public MyGCodeRow(String g, String x, String y, String z, String e, String f) {
		super(g, x, y, z, e);
		this.f = f;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}
}
