package math.test.gcode.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.gcode.model.GCodeModel;
import math.gcode.model.GCodeModelBuilder;
import math.gcode.reader.GCodeFile;
import math.gcode.reader.GCodeReader;

public class GCodeModelBuilderTest {

    @Test
    public void gCodeModelBuilderTest() {

	GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\AbaqusBuilderTest.txt");
	GCodeModel gCodeModel = gCodeBuilder.build(file);
	assertEquals(10, gCodeModel.getLayers().get(0).getVertices().get(0).getX(), 0d);
    }

}
