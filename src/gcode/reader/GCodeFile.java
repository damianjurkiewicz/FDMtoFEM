package gcode.reader;

import java.util.ArrayList;
import java.util.List;

public class GCodeFile {

	private List<GCodeRow> rows;

	public GCodeFile() {
		this.rows = new ArrayList<GCodeRow>();
	}

	public List<GCodeRow> getRows() {
		return rows;
	}

	public void setRows(List<GCodeRow> rows) {
		this.rows = rows;
	}

}
