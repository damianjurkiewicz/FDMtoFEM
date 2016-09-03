package math.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import math.model.GCodeEdge;
import math.model.GCodeVertex;


public class GCodeEdgeTest {

	@Test
	public void getLengthTest() {
		GCodeVertex v1 = new GCodeVertex(2d, 2d, 2d);
		GCodeVertex v2 = new GCodeVertex(0d, 0d, 0d);
		GCodeEdge edge = new GCodeEdge(v1, v2);
		assertEquals(3.46d, edge.getLength(), 0.01d);
	}

	@Test
	public void isZeroLengthIsTrueTest() {
		GCodeVertex v1 = new GCodeVertex(0d, 0d, 0d);
		GCodeVertex v2 = new GCodeVertex(0d, 0d, 0d);
		GCodeEdge edge = new GCodeEdge(v1, v2);
		assertTrue(edge.isZeroLength());
	}
	
	@Test
	public void isZeroLengthIsFalseTest() {
		GCodeVertex v1 = new GCodeVertex(2d, 2d, 2d);
		GCodeVertex v2 = new GCodeVertex(0d, 0d, 0d);
		GCodeEdge edge = new GCodeEdge(v1, v2);
		assertFalse(edge.isZeroLength());
	}
}
