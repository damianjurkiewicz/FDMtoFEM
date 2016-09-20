package math.gcode.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;

public class GCodeFileTest {

    @Test
    public void fileReadingTest() {
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\11.txt");
	assertEquals("1", file.getRows().get(3).getG());
    }

    @Test(expected = FileNotFoundException.class)
    public void fileNotFoundExceptionTest() {
	GCodeReader reader = new GCodeReader();
	reader.read("C:\\Users\\Damian\\Desktop\\12.txt");
    }

    @Test(expected = IOException.class)
    public void test() {
	GCodeReader reader = new GCodeReader();
	reader.read("C:\\Users\\Damian\\Desktop\\11.txt");
    }

}
