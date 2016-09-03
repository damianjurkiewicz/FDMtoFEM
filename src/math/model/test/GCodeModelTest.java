package math.model.test;

import org.junit.Test;

import math.model.Edge;
import math.model.Layer;
import math.model.Model;
import math.model.Vertex;

public class ModelTest {

    @Test
    public void test() {
	Model model = new Model();
	Vertex vertex1 = new Vertex(0, 0, 0);
	Vertex vertex2 = new Vertex(0, 0, 0);
	Edge edge = new Edge(vertex1, vertex2);

	Layer layer = new Layer();
	layer.getEdges().add(edge);
	layer.getVertices().add(vertex1);
	layer.getVertices().add(vertex2);
	model.getLayers().add(layer);

    }

}
