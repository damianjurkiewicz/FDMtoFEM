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

    @Test
    public void writerTest() {

	GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\aba.txt");
	GCodeModel gCodeModel = gCodeBuilder.build(file);
	Builder builder = new Builder(0.789);
	Model model = builder.buildClassifier(gCodeModel);
	Writer zapisz = new Writer("C:\\Users\\Damian\\Desktop\\FDM_Model.inp");
	zapisz.writeFile(model);

    }

}
