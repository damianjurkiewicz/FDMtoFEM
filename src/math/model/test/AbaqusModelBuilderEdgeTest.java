package math.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.abaqus.AbaqusModel;
import math.abaqus.AbaqusModelBuilder;
import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;
import math.model.GCodeVertex;

public class AbaqusModelBuilderEdgeTest {

	AbaqusModelBuilder abaqusBuilder = new AbaqusModelBuilder(1.5);
	GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
	GCodeModel gCodeModel = new GCodeModel();



	@Test
	public void testBuild() {
		GCodeLayer gCodeLayer = gCodeModel.addLayer(1.0);
		GCodeVertex v1 = gCodeLayer.addVertex(2, 6);
		GCodeVertex v2 = gCodeLayer.addVertex(5, 1);
		GCodeVertex v3 = gCodeLayer.addVertex(9, 7);
		GCodeVertex v4 = gCodeLayer.addVertex(16, 2);
		GCodeEdge e1 = gCodeLayer.addEdge(v1, v2);
		GCodeEdge e2 = gCodeLayer.addEdge(v2, v3);
		GCodeEdge e3 = gCodeLayer.addEdge(v3, v4);
		

		AbaqusModel abaqusModel = abaqusBuilder.build(gCodeModel);


		assertEquals(2, abaqusModel.getEdges().get(0).getVertex1().getX(), 0);
		assertEquals(6, abaqusModel.getEdges().get(1).getVertex1().getY(), 0);
		assertEquals(2.342, abaqusModel.getEdges().get(2).getVertex1().getX(), 0);
		assertEquals(5.429, abaqusModel.getEdges().get(3).getVertex1().getY(), 0);
		assertEquals(3.114, abaqusModel.getEdges().get(4).getVertex1().getX(), 0);
		assertEquals(4.143, abaqusModel.getEdges().get(5).getVertex1().getY(), 0);
		assertEquals(3.886, abaqusModel.getEdges().get(6).getVertex1().getX(), 0);
		assertEquals(2.857, abaqusModel.getEdges().get(7).getVertex1().getY(), 0);
		assertEquals(4.658, abaqusModel.getEdges().get(8).getVertex1().getX(), 0);
		assertEquals(1.571, abaqusModel.getEdges().get(9).getVertex1().getY(), 0);
		assertEquals(5, abaqusModel.getEdges().get(10).getVertex1().getX(), 0);
		assertEquals(1, abaqusModel.getEdges().get(11).getVertex1().getY(), 0);
//		
//		assertEquals(5, abaqusModel.getEdges().get(0).getVertex1().getX(), 0);
//		assertEquals(1, abaqusModel.getEdges().get(1).getVertex1().getY(), 0);
//		assertEquals(5.335, abaqusModel.getEdges().get(2).getVertex1().getX(), 0);
//		assertEquals(1.504, abaqusModel.getEdges().get(3).getVertex1().getY(), 0);
//		assertEquals(6.168, abaqusModel.getEdges().get(4).getVertex1().getX(), 0);
//		assertEquals(2.752, abaqusModel.getEdges().get(5).getVertex1().getY(), 0);
//		assertEquals(7.0, abaqusModel.getEdges().get(6).getVertex1().getX(), 0);
//		assertEquals(4.0, abaqusModel.getEdges().get(7).getVertex1().getY(), 0);
//		assertEquals(7.832, abaqusModel.getEdges().get(8).getVertex1().getX(), 0);
//		assertEquals(5.248, abaqusModel.getEdges().get(9).getVertex1().getY(), 0);
//		assertEquals(8.664, abaqusModel.getEdges().get(10).getVertex1().getX(), 0);
//		assertEquals(6.496, abaqusModel.getEdges().get(11).getVertex1().getY(), 0);
//		assertEquals(9.0, abaqusModel.getEdges().get(10).getVertex1().getX(), 0);
//		assertEquals(7.0, abaqusModel.getEdges().get(11).getVertex1().getY(), 0);
//		
//		assertEquals(9, abaqusModel.getEdges().get(0).getVertex1().getX(), 0);
//		assertEquals(7, abaqusModel.getEdges().get(1).getVertex1().getY(), 0);
//		assertEquals(9.448, abaqusModel.getEdges().get(2).getVertex1().getX(), 0);
//		assertEquals(6.68, abaqusModel.getEdges().get(3).getVertex1().getY(), 0);
//		assertEquals(10.669, abaqusModel.getEdges().get(4).getVertex1().getX(), 0);
//		assertEquals(5.808, abaqusModel.getEdges().get(5).getVertex1().getY(), 0);
//		assertEquals(11.89, abaqusModel.getEdges().get(6).getVertex1().getX(), 0);
//		assertEquals(4.936, abaqusModel.getEdges().get(7).getVertex1().getY(), 0);
//		assertEquals(13.11, abaqusModel.getEdges().get(8).getVertex1().getX(), 0);
//		assertEquals(4.064, abaqusModel.getEdges().get(9).getVertex1().getY(), 0);
//		assertEquals(14.331, abaqusModel.getEdges().get(10).getVertex1().getX(), 0);
//		assertEquals(3.192, abaqusModel.getEdges().get(11).getVertex1().getY(), 0);
//		assertEquals(15.552, abaqusModel.getEdges().get(10).getVertex1().getX(), 0);
//		assertEquals(2.32, abaqusModel.getEdges().get(11).getVertex1().getY(), 0);
//		assertEquals(16, abaqusModel.getEdges().get(10).getVertex1().getX(), 0);
//		assertEquals(2, abaqusModel.getEdges().get(11).getVertex1().getY(), 0);
	}
	
	
	
	
}
