package math.gcode.test;

import static org.junit.Assert.*;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.gcode.GCodeRow;
import math.gcode.MyGCodeRow;

public class GCodeFileTest {

	@Test
	public void test() {
		GCodeFile file = new GCodeFile();
		GCodeRow row = new GCodeRow("G1", "X60.500", "Y60.500", "", "");
		MyGCodeRow myRow = new MyGCodeRow("G1", "X60.500", "Y60.500", "", "", "F1800.000");
		file.getRows().add(row);
		file.getRows().add(myRow);
		assertEquals(file.getRows().get(0).getG(), "G1");
		assertEquals(file.getRows().get(1).getG(), "G1");
	}

	@Test
	public void fileReadingTest() {
		GCodeReader reader = new GCodeReader();
		GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\11.txt");
		assertEquals("1", file.getRows().get(2).getG());
	}
	
}
