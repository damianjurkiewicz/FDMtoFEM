package math.test.gcode.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.gcode.model.GCodeLayer;
import math.gcode.model.GCodeModel;
import math.gcode.model.GCodeVertex;

public class GCodeVertexTest {

    @Test
    public void vertexTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(0, 0);
	v1.setX(1d);
	v1.setY(2d);
	assertEquals(1d, v1.getX(), 0d);
	assertEquals(2d, v1.getY(), 0d);
    }

    @Test
    public void getterTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(0, 0);
	v1.getLayer().addVertex(0d, 0d);
	assertEquals(0d, v1.getLayer().getVertices().get(0).getX(), 0);
    }

}
