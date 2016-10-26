package math.test.geometry.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.gcode.model.GCodeModel;
import math.gcode.model.GCodeModelBuilder;
import math.gcode.model.GCodeVertex;
import math.geometry.model.Model;
import math.geometry.model.ModelBuilder;

public class AbaqusModelBuilderTest {

    ModelBuilder abaqusBuilder = new ModelBuilder(1.5);
    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeModel gCodeModel = new GCodeModel();

    @Test
    public void krotki() {

	GCodeVertex vertex1 = gCodeModel.addLayer(1).addVertex(10, 10);
	GCodeVertex vertex2 = gCodeModel.addLayer(1).addVertex(11, 11);
	gCodeModel.addLayer(1.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(1, abaqusModel.getVertices().get(0).getZ(), 0);
	assertEquals(11, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(11, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(1, abaqusModel.getVertices().get(1).getZ(), 0);
    }

    @Test
    public void sredniPionowy() {
	GCodeVertex vertex1 = gCodeModel.addLayer(2).addVertex(10, 10);
	GCodeVertex vertex2 = gCodeModel.addLayer(2).addVertex(10, 12);
	gCodeModel.addLayer(2.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(2.0, abaqusModel.getVertices().get(0).getZ(), 0);
	assertEquals(10, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(11, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(2.0, abaqusModel.getVertices().get(1).getZ(), 0);
	assertEquals(10, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(12, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(2.0, abaqusModel.getVertices().get(2).getZ(), 0);
    }

    @Test
    public void sredniPoziomy() {
	GCodeVertex vertex1 = gCodeModel.addLayer(2).addVertex(10, 10);
	GCodeVertex vertex2 = gCodeModel.addLayer(2).addVertex(12, 10);
	gCodeModel.addLayer(3.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(3.0, abaqusModel.getVertices().get(0).getZ(), 0);
	assertEquals(11, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(3.0, abaqusModel.getVertices().get(1).getZ(), 0);
	assertEquals(12, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(3.0, abaqusModel.getVertices().get(2).getZ(), 0);
    }

    @Test
    public void sredniSkosny() {
	GCodeVertex vertex1 = gCodeModel.addLayer(4.0).addVertex(10, 10);
	GCodeVertex vertex2 = gCodeModel.addLayer(4.0).addVertex(12, 12);
	gCodeModel.addLayer(4.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(4.0, abaqusModel.getVertices().get(0).getZ(), 0);
	assertEquals(11, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(11, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(4.0, abaqusModel.getVertices().get(1).getZ(), 0);
	assertEquals(12, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(12, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(4.0, abaqusModel.getVertices().get(2).getZ(), 0);
    }

    @Test
    public void dlugiPionowy() {
	GCodeVertex vertex1 = gCodeModel.addLayer(5.0).addVertex(10, 10);
	GCodeVertex vertex2 = gCodeModel.addLayer(5.0).addVertex(10, 18);
	gCodeModel.addLayer(5.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(0).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(10.25, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(1).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(11.75, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(2).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(3).getX(), 0.0001);
	assertEquals(13.25, abaqusModel.getVertices().get(3).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(3).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(4).getX(), 0.0001);
	assertEquals(14.75, abaqusModel.getVertices().get(4).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(4).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(5).getX(), 0.0001);
	assertEquals(16.25, abaqusModel.getVertices().get(5).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(5).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(6).getX(), 0.0001);
	assertEquals(17.75, abaqusModel.getVertices().get(6).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(6).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(7).getX(), 0.0001);
	assertEquals(18, abaqusModel.getVertices().get(7).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(7).getZ(), 0);
    }

    @Test
    public void dlugiPoziomy() {
	GCodeVertex vertex1 = gCodeModel.addLayer(6.0).addVertex(10, 10);
	GCodeVertex vertex2 = gCodeModel.addLayer(6.0).addVertex(18, 10);
	gCodeModel.addLayer(6.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(0).getZ(), 0);

	assertEquals(10.25, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(1).getZ(), 0);

	assertEquals(11.75, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(2).getZ(), 0);

	assertEquals(13.25, abaqusModel.getVertices().get(3).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(3).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(3).getZ(), 0);

	assertEquals(14.75, abaqusModel.getVertices().get(4).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(4).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(4).getZ(), 0);

	assertEquals(16.25, abaqusModel.getVertices().get(5).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(5).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(5).getZ(), 0);

	assertEquals(17.75, abaqusModel.getVertices().get(6).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(6).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(6).getZ(), 0);

	assertEquals(18, abaqusModel.getVertices().get(7).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(7).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(7).getZ(), 0);
    }

    @Test
    public void dlugiSkosny() {
	GCodeVertex vertex1 = gCodeModel.addLayer(7.0).addVertex(0.65, 6);
	GCodeVertex vertex2 = gCodeModel.addLayer(7.0).addVertex(8.5, 7.7);
	gCodeModel.addLayer(7.0).addEdge(vertex1, vertex2);
	Model abaqusModel = new Model();
	abaqusBuilder.generateVerticesCloud(gCodeModel, abaqusModel);

	assertEquals(0.65, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(6, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(0).getZ(), 0);

	assertEquals(0.9099577, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(6.0562965, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(1).getZ(), 0);

	assertEquals(2.3759746, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(6.3737779, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(2).getZ(), 0);

	assertEquals(3.842, abaqusModel.getVertices().get(3).getX(), 0.001);
	assertEquals(6.691, abaqusModel.getVertices().get(3).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(3).getZ(), 0);

	assertEquals(5.308, abaqusModel.getVertices().get(4).getX(), 0.001);
	assertEquals(7.009, abaqusModel.getVertices().get(4).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(4).getZ(), 0);

	assertEquals(6.774, abaqusModel.getVertices().get(5).getX(), 0.001);
	assertEquals(7.326, abaqusModel.getVertices().get(5).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(5).getZ(), 0);

	assertEquals(8.2400422, abaqusModel.getVertices().get(6).getX(), 0.001);
	assertEquals(7.6437034, abaqusModel.getVertices().get(6).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(6).getZ(), 0);

	assertEquals(8.5, abaqusModel.getVertices().get(7).getX(), 0.0001);
	assertEquals(7.7, abaqusModel.getVertices().get(7).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(7).getZ(), 0);

    }

}
