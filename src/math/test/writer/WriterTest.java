package math.test.writer;

import org.junit.Test;

import math.gcode.model.GCodeModel;
import math.gcode.model.GCodeModelBuilder;
import math.gcode.reader.GCodeFile;
import math.gcode.reader.GCodeReader;
import math.geometry.model.Model;
import math.geometry.model.ModelBuilder;
import math.writer.Writer;

public class WriterTest {

    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeReader reader = new GCodeReader();
    GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\aba.txt");
    GCodeModel gCodeModel = gCodeBuilder.build(file);
    ModelBuilder builder = new ModelBuilder(1);
    Model modelCloud = builder.buildCloud(gCodeModel);
    Model modelGCodeBased = builder.buildGCodeBased(gCodeModel);

    @Test
    public void writerTest() {
	Writer zapisz = new Writer();
	zapisz.writeAbaqus(modelCloud, builder);
    }

}
