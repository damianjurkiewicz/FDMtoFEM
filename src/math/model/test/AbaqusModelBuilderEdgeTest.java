package math.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.abaqus.AbaqusModel;
import math.abaqus.AbaqusModelBuilder;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;

public class AbaqusModelBuilderEdgeTest {

    AbaqusModelBuilder abaqusBuilder = new AbaqusModelBuilder();
    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeModel gCodeModel = new GCodeModel();

    AbaqusModel abaqusModel = abaqusBuilder.generateEdges((abaqusBuilder.generateVertices(gCodeModel)));

    @Test
    public void testEdge() {
	assertEquals(10, abaqusModel.getEdges().get(0).getVertex1().getX(), 0);
	assertEquals(10, abaqusModel.getEdges().get(0).getVertex1().getY(), 0);
    }

}
