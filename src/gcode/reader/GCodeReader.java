package gcode.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GCodeReader {

    public GCodeFile readFile(String filePath) {

	GCodeFile file = new GCodeFile();
	String row;

	try {

	    BufferedReader reader = new BufferedReader(new FileReader(filePath));

	    while ((row = reader.readLine()) != null) {

		String gCode = "";

		if (row.contains(";")) {
		    String[] rowParts = row.split(";");
		    gCode = rowParts[0];
		} else {
		    gCode = row;
		}

		if (!gCode.isEmpty()) {

		    String[] gCodeParts = gCode.split(" ");
		    String g = "", x = "", y = "", z = "", e = "";

		    for (int i = 0; i < gCodeParts.length; i++) {

			switch (gCodeParts[i].toUpperCase().charAt(0)) {
			case 'G':
			    g = gCodeParts[i].substring(1);
			    break;
			case 'X':
			    x = gCodeParts[i].substring(1);

			    break;
			case 'Y':
			    y = gCodeParts[i].substring(1);
			    break;
			case 'Z':
			    z = gCodeParts[i].substring(1);
			    break;
			case 'E':
			    e = gCodeParts[i].substring(1);
			    break;
			default:
			    break;
			}
		    }

		    GCodeRow gCodeRow = new GCodeRow(g, x, y, z, e);
		    file.getRows().add(gCodeRow);
		}
	    }

	    reader.close();

	} catch (

	FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return file;
    }
}
