package math.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;
import math.model.GCodeVertex;

public class GCodeLayerTest {

    @Test
    public void removeVertexTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(0d, 0d);
	GCodeVertex v2 = layer.addVertex(1d, 1d);
	layer.removeVertex(v1);
	layer.removeVertex(v2);
	assertTrue(layer.getVertices().isEmpty());
    }

    @Test
    public void removeEdgeTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	GCodeVertex v1 = layer.addVertex(0d, 0d);
	GCodeVertex v2 = layer.addVertex(1d, 1d);
	GCodeEdge edge = layer.addEdge(v1, v2);
	layer.removeEdge(edge);
	assertTrue(layer.getEdges().isEmpty());
    }

    @Test
    public void getterModelTest() {
	GCodeModel model = new GCodeModel();
	model.addLayer(0d);
	model.getLayers().get(0).setZ(2d);
	assertEquals(2d, model.getLayers().get(0).getModel().getLayers().get(0).getZ(), 0);
    }

}
