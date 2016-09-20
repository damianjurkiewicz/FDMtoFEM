package math.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.abaqus.AbaqusModel;
import math.abaqus.AbaqusModelBuilder;
import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;

public class AbaqusModelBuilderTest {

    @Test
    public void abaqusModelBuilderTest() {
	GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\AbaqusBuilderTest.txt");
	GCodeModel gCodeModel = gCodeBuilder.build(file);
	AbaqusModelBuilder abaqusBuilder = new AbaqusModelBuilder();
	AbaqusModel abaqusModel = abaqusBuilder.build(gCodeModel);
	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0);
    }

}
