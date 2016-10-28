package math.model.test;

import org.junit.Test;

import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.geometry.Model;
import math.geometry.ModelBuilder;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;
import math.writer.Writer;

public class WriterTest {

    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeReader reader = new GCodeReader();
    GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\aba.txt");
    GCodeModel gCodeModel = gCodeBuilder.build(file);
    ModelBuilder abaqusBuilder = new ModelBuilder(0.789129);
    Model abaqusModel = abaqusBuilder.build(gCodeModel);

    @Test
    public void writerTest() {
	Writer writer = new Writer();
	writer.writeAbaqus(abaqusModel, abaqusBuilder);
    }

}
