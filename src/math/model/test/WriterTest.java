package math.model.test;

import org.junit.Test;

import math.abaqus.AbaqusModel;
import math.abaqus.AbaqusModelBuilder;
import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;
import math.writer.Writer;

public class WriterTest {

    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeReader reader = new GCodeReader();
    GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\aba.txt");
    GCodeModel gCodeModel = gCodeBuilder.build(file);
    AbaqusModelBuilder abaqusBuilder = new AbaqusModelBuilder(1);
    AbaqusModel abaqusModel = abaqusBuilder.build(gCodeModel);

    @Test
    public void writerTest() {
	Writer zapisz = new Writer();
	zapisz.write(abaqusModel, abaqusBuilder);
    }

}
