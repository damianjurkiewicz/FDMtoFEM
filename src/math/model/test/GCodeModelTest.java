package math.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;

public class GCodeModelTest {

    @Test
    public void test() {

	GCodeModelBuilder builder = new GCodeModelBuilder();
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\11.txt");
	GCodeModel model = builder.build(file);
	assertEquals(1.0, model.getLayers().get(0).getZ(), 0d);
    }

}
