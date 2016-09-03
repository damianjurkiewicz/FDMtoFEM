package math.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import math.model.Edge;
import math.model.Vertex;

public class EdgeTest {

	@Test
	public void getLengthTest() {
		Vertex v1 = new Vertex(2d, 2d, 2d);
		Vertex v2 = new Vertex(0d, 0d, 0d);
		Edge edge = new Edge(v1, v2);
		assertEquals(3.46d, edge.getLength(), 0.01d);
	}

	@Test
	public void isZeroLengthIsTrueTest() {
		Vertex v1 = new Vertex(0d, 0d, 0d);
		Vertex v2 = new Vertex(0d, 0d, 0d);
		Edge edge = new Edge(v1, v2);
		assertTrue(edge.isZeroLength());
	}
	
	@Test
	public void isZeroLengthIsFalseTest() {
		Vertex v1 = new Vertex(2d, 2d, 2d);
		Vertex v2 = new Vertex(0d, 0d, 0d);
		Edge edge = new Edge(v1, v2);
		assertFalse(edge.isZeroLength());
	}
}
