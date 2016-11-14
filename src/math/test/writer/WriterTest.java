package math.test.writer;

import org.junit.Test;

import math.gcode.model.GCodeModel;
import math.gcode.model.GCodeModelBuilder;
import math.gcode.reader.GCodeFile;
import math.gcode.reader.GCodeReader;
import math.geometry.builder.Builder;
import math.geometry.model.Model;
import math.writer.Writer;

public class WriterTest {

    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeReader reader = new GCodeReader();
    GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\aba.txt");
    GCodeModel gCodeModel = gCodeBuilder.build(file);
    Builder builder = new Builder(0.5);
    Model model = builder.buildClassifer(gCodeModel);
    // Model model = builder.buildPointer(gCodeModel);

    @Test
    public void writerTest() {
	Writer zapisz = new Writer();
	zapisz.writeAbaqus(model);
    }

}
