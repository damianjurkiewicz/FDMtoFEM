package math.model.test;

import org.junit.Test;

import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;
import math.model.GCodeVertex;


public class GCodeModelTest {

    @Test
    public void test() {
	GCodeModel model = new GCodeModel();
	GCodeVertex vertex1 = new GCodeVertex(0, 0, 0);
	GCodeVertex vertex2 = new GCodeVertex(0, 0, 0);
	GCodeEdge edge = new GCodeEdge(vertex1, vertex2);

	GCodeLayer layer = new GCodeLayer();
	layer.getEdges().add(edge);
	layer.getVertices().add(vertex1);
	layer.getVertices().add(vertex2);
	model.getLayers().add(layer);

    }

}
