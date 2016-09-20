package math.model.test;

import org.junit.Test;

import math.model.GCodeLayer;
import math.model.GCodeModel;

public class GCodeModelTest {

    @Test
    public void getLayerTest() {
	GCodeModel model = new GCodeModel();
	model.addLayer(1d);
	model.getLayers().get(0).getZ();
    }

    @Test
    public void removeLayerTest() {
	GCodeModel model = new GCodeModel();
	GCodeLayer layer = model.addLayer(1d);
	model.getLayers().get(0).getZ();
	model.removeLayer(layer);
    }
}
