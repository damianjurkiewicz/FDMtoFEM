package math.gcode;

import java.util.ArrayList;
import java.util.List;

public class GCodeFile {
    // lista z typem generycznym GCodeRow
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

    
    public String toString(){
    	return " " + rows.size();
    }

}
