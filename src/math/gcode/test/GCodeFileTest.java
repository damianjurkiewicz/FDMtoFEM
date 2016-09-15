package math.gcode.test;

import static org.junit.Assert.*;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.gcode.GCodeRow;


public class GCodeFileTest {

	@Test
	public void test() {
		GCodeFile file = new GCodeFile();

		GCodeRow row = new GCodeRow("G1", "0.500", "60.500", "0.0", "0.0");
		file.getRows().add(row);
		assertEquals(file.getRows().get(0).getG(), "G1");
	//	assertEquals(file.getRows().get(1).getG(), "G1");
	}

	@Test
	public void fileReadingTest() {
		GCodeReader reader = new GCodeReader();
		GCodeFile file = reader.read("C:\\Users\\maddj\\Desktop\\11.txt");
		assertEquals("1", file.getRows().get(3).getG());

	}
	
}
