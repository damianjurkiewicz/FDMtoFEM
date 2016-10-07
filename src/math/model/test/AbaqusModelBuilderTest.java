package math.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import math.abaqus.AbaqusModel;
import math.abaqus.AbaqusModelBuilder;
import math.gcode.GCodeFile;
import math.gcode.GCodeReader;
import math.model.GCodeModel;
import math.model.GCodeModelBuilder;

public class AbaqusModelBuilderTest {

    GCodeModelBuilder gCodeBuilder = new GCodeModelBuilder();
    GCodeReader reader = new GCodeReader();
    GCodeFile file = reader.read("C:\\Users\\Damian\\Desktop\\AbaqusBuilderTest.txt");
    GCodeModel gCodeModel = gCodeBuilder.build(file);
    AbaqusModelBuilder abaqusBuilder = new AbaqusModelBuilder();
    AbaqusModel abaqusModel = abaqusBuilder.fragmentEdges(gCodeModel);

    @Test
    public void krotszyOdP() {

	assertEquals(10, abaqusModel.getVertices().get(0).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(0).getY(), 0.0001);
	assertEquals(1, abaqusModel.getVertices().get(0).getZ(), 0);
	assertEquals(11, abaqusModel.getVertices().get(1).getX(), 0.0001);
	assertEquals(11, abaqusModel.getVertices().get(1).getY(), 0.0001);
	assertEquals(1, abaqusModel.getVertices().get(1).getZ(), 0);
    }

    @Test
    public void dluzszyOdPKrotszyOd2PPionowy() {
	assertEquals(10, abaqusModel.getVertices().get(2).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(2).getY(), 0.0001);
	assertEquals(2.0, abaqusModel.getVertices().get(2).getZ(), 0);
	assertEquals(10, abaqusModel.getVertices().get(3).getX(), 0.0001);
	assertEquals(11, abaqusModel.getVertices().get(3).getY(), 0.0001);
	assertEquals(2.0, abaqusModel.getVertices().get(3).getZ(), 0);
	assertEquals(10, abaqusModel.getVertices().get(4).getX(), 0.0001);
	assertEquals(12, abaqusModel.getVertices().get(4).getY(), 0.0001);
	assertEquals(2.0, abaqusModel.getVertices().get(4).getZ(), 0);
    }

    @Test
    public void dluzszyOdPKrotszyOd2PPoziomy() {
	assertEquals(10, abaqusModel.getVertices().get(5).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(5).getY(), 0.0001);
	assertEquals(3.0, abaqusModel.getVertices().get(5).getZ(), 0);
	assertEquals(11, abaqusModel.getVertices().get(6).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(6).getY(), 0.0001);
	assertEquals(3.0, abaqusModel.getVertices().get(6).getZ(), 0);
	assertEquals(12, abaqusModel.getVertices().get(7).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(7).getY(), 0.0001);
	assertEquals(3.0, abaqusModel.getVertices().get(7).getZ(), 0);
    }

    @Test
    public void dluzszyOdPKrotszyOd2PSkosny() {
	assertEquals(10, abaqusModel.getVertices().get(8).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(8).getY(), 0.0001);
	assertEquals(4.0, abaqusModel.getVertices().get(8).getZ(), 0);
	assertEquals(11, abaqusModel.getVertices().get(9).getX(), 0.0001);
	assertEquals(11, abaqusModel.getVertices().get(9).getY(), 0.0001);
	assertEquals(4.0, abaqusModel.getVertices().get(9).getZ(), 0);
	assertEquals(12, abaqusModel.getVertices().get(10).getX(), 0.0001);
	assertEquals(12, abaqusModel.getVertices().get(10).getY(), 0.0001);
	assertEquals(4.0, abaqusModel.getVertices().get(10).getZ(), 0);
    }

    @Test
    public void dluzszyOd2PPionowy() {
	assertEquals(10, abaqusModel.getVertices().get(11).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(11).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(11).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(12).getX(), 0.0001);
	assertEquals(10.25, abaqusModel.getVertices().get(12).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(12).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(13).getX(), 0.0001);
	assertEquals(11.75, abaqusModel.getVertices().get(13).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(13).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(14).getX(), 0.0001);
	assertEquals(13.25, abaqusModel.getVertices().get(14).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(14).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(15).getX(), 0.0001);
	assertEquals(14.75, abaqusModel.getVertices().get(15).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(15).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(16).getX(), 0.0001);
	assertEquals(16.25, abaqusModel.getVertices().get(16).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(16).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(17).getX(), 0.0001);
	assertEquals(17.75, abaqusModel.getVertices().get(17).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(17).getZ(), 0);

	assertEquals(10, abaqusModel.getVertices().get(18).getX(), 0.0001);
	assertEquals(18, abaqusModel.getVertices().get(18).getY(), 0.0001);
	assertEquals(5.0, abaqusModel.getVertices().get(18).getZ(), 0);
    }

    @Test
    public void dluzszyOd2PPoziomy() {
	assertEquals(10, abaqusModel.getVertices().get(19).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(19).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(19).getZ(), 0);

	assertEquals(10.25, abaqusModel.getVertices().get(20).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(20).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(20).getZ(), 0);

	assertEquals(11.75, abaqusModel.getVertices().get(21).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(21).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(21).getZ(), 0);

	assertEquals(13.25, abaqusModel.getVertices().get(22).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(22).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(22).getZ(), 0);

	assertEquals(14.75, abaqusModel.getVertices().get(23).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(23).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(23).getZ(), 0);

	assertEquals(16.25, abaqusModel.getVertices().get(24).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(24).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(24).getZ(), 0);

	assertEquals(17.75, abaqusModel.getVertices().get(25).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(25).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(25).getZ(), 0);

	assertEquals(18, abaqusModel.getVertices().get(26).getX(), 0.0001);
	assertEquals(10, abaqusModel.getVertices().get(26).getY(), 0.0001);
	assertEquals(6.0, abaqusModel.getVertices().get(26).getZ(), 0);
    }

    @Test
    public void dluzszyOd2PSkosny() {
	assertEquals(0.65, abaqusModel.getVertices().get(27).getX(), 0.0001);
	assertEquals(6, abaqusModel.getVertices().get(27).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(27).getZ(), 0);

	assertEquals(0.9099577, abaqusModel.getVertices().get(28).getX(), 0.0001);
	assertEquals(6.0562965, abaqusModel.getVertices().get(28).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(28).getZ(), 0);

	assertEquals(2.3759746, abaqusModel.getVertices().get(29).getX(), 0.0001);
	assertEquals(6.3737779, abaqusModel.getVertices().get(29).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(29).getZ(), 0);

	assertEquals(3.842, abaqusModel.getVertices().get(30).getX(), 0.001);
	assertEquals(6.691, abaqusModel.getVertices().get(30).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(30).getZ(), 0);

	assertEquals(5.308, abaqusModel.getVertices().get(31).getX(), 0.001);
	assertEquals(7.009, abaqusModel.getVertices().get(31).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(31).getZ(), 0);

	assertEquals(6.774, abaqusModel.getVertices().get(32).getX(), 0.001);
	assertEquals(7.326, abaqusModel.getVertices().get(32).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(32).getZ(), 0);

	assertEquals(8.2400422, abaqusModel.getVertices().get(33).getX(), 0.001);
	assertEquals(7.6437034, abaqusModel.getVertices().get(33).getY(), 0.001);
	assertEquals(7.0, abaqusModel.getVertices().get(33).getZ(), 0);

	assertEquals(8.5, abaqusModel.getVertices().get(34).getX(), 0.0001);
	assertEquals(7.7, abaqusModel.getVertices().get(34).getY(), 0.0001);
	assertEquals(7.0, abaqusModel.getVertices().get(34).getZ(), 0);

    }

}
