package math.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;
import math.model.GCodeVertex;

public class GCodeEdgeTest {

    @Test
    public void getLengthTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(0d, 0d);
	GCodeVertex v2 = layer.addVertex(1d, 1d);
	GCodeEdge edge = layer.addEdge(v1, v2);
	assertEquals(1.41d, edge.getLength(), 0.01d);
    }

    @Test
    public void isZeroLengthIsTrueTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(0d, 0d);
	GCodeVertex v2 = layer.addVertex(0d, 0d);
	GCodeEdge edge = layer.addEdge(v1, v2);
	assertTrue(edge.isZeroLength());
    }

    @Test
    public void isZeroLengthIsFalseTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(2d, 3d);
	GCodeVertex v2 = layer.addVertex(4d, 5d);
	GCodeEdge edge = layer.addEdge(v1, v2);
	assertFalse(edge.isZeroLength());
    }

    @Test
    public void gettersSettersTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(2d, 3d);
	GCodeVertex v2 = layer.addVertex(4d, 5d);
	GCodeEdge edge = layer.addEdge(v1, v2);
	edge.setVertex1(v1);
	edge.setVertex2(v2);
	assertEquals(2d, edge.getVertex1().getX(), 0d);
	assertEquals(4d, edge.getVertex2().getX(), 0d);
	assertEquals(1d, edge.getLayer().getZ(), 0d);
    }

}
