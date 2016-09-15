package math.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;


public class GCodeModelTest {
	
	@Test
	public void test() {

		GCodeModel model = new GCodeModel();
		GCodeReader reader = new GCodeReader();
		GCodeFile file = reader.read("C:\\Users\\maddj\\Desktop\\11.txt");
		GCodeModelBuilder builder = new GCodeModelBuilder();
		builder.build(file);
		assertEquals(1.0, model.getLayers().get(0).getZ(), 0d);
	}
	

}
