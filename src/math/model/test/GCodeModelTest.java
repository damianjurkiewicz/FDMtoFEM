package math.model.test;



import static org.junit.Assert.*;

import org.junit.Test;

import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;
import math.model.GCodeVertex;

public class GCodeModelTest {

	@Test
	public void test() {

		GCodeModel model = new GCodeModel();
		GCodeLayer layer = model.addLayer(0d);
		GCodeVertex vertex1 = layer.addVertex(1d, 2d);
		GCodeVertex vertex2 = layer.addVertex(3d, 4d);
		GCodeEdge edge = layer.addEdge(vertex1, vertex2);

		assertEquals(1d, model.getLayers().get(0).getVertices().get(0).getX(), 0d);

	}


}
