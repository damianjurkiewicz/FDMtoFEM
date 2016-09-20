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
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\AbaqusBuilderTest.txt");
	GCodeModel gCodeModel = gCodeBuilder.build(file);
	assertEquals(10, gCodeModel.getLayers().get(0).getVertices().get(0).getX(), 0d);
    }

}
