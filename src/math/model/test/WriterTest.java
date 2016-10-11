package math.model.test;

import org.junit.Test;

import math.abaqus.AbaqusModel;
import math.abaqus.AbaqusModelBuilder;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;
import math.writer.Writer;

public class WriterTest {

    AbaqusModelBuilder abaqusBuilder = new AbaqusModelBuilder();
    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeModel gCodeModel = new GCodeModel();
    AbaqusModel abaqusModel = abaqusBuilder.generateEdges((abaqusBuilder.generateVertices(gCodeModel)));

    @Test
    public void writerTest() {
	Writer zapisz = new Writer();
	zapisz.write(abaqusModel);
    }

}
