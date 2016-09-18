package math.gcode.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;

public class GCodeFileTest {

    @Test
    public void fileReadingTest() {
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\maddj\\Desktop\\11.txt");
	assertEquals("1", file.getRows().get(3).getG());
    }
}
