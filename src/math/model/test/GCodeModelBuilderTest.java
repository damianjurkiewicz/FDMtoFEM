package math.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;

public class GCodeModelBuilderTest {

    @Test
    public void gCodeModelBuilderTest() {

	GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\12.txt");
	GCodeModel gCodeModel = gCodeBuilder.build(file);
	assertEquals(1.0, gCodeModel.getLayers().get(0).getZ(), 0d);
    }

}
