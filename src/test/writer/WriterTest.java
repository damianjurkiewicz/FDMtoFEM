package test.writer;

import org.junit.Test;

import gcode.model.GCodeModel;
import gcode.model.GCodeModelBuilder;
import gcode.reader.GCodeFile;
import gcode.reader.GCodeReader;
import geometry.builder.Builder;
import geometry.model.Model;
import writer.Writer;

public class WriterTest {

    @Test
    public void writerTest() {

	Double elementSize = 0.789;
	Double inLayerJoinDistance = 0.8;
	Double interLayerJoinDistance = 0.8;
	String readDirectory = "C:\\Users\\maddj\\Desktop\\aba.txt";
	String writeDirectory = "C:\\Users\\maddj\\Desktop\\FDM_Model.inp";

	GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
	GCodeReader reader = new GCodeReader();
	GCodeFile file = reader.readFile(readDirectory);
	GCodeModel gCodeModel = gCodeBuilder.build(file);
	Builder builder = new Builder(elementSize, inLayerJoinDistance, interLayerJoinDistance);
	Model model = builder.buildClassifier(gCodeModel);
	Writer write = new Writer(writeDirectory);
	write.writeFile(model);

    }

}
