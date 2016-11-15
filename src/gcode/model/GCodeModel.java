package gcode.model;

import java.util.ArrayList;
import java.util.List;

public class GCodeModel {

    private List<GCodeLayer> layers;

    public GCodeModel() {
	this.layers = new ArrayList<GCodeLayer>();
    }

    public List<GCodeLayer> getLayers() {
	return layers;
    }

    public GCodeLayer addLayer(double z) {
	GCodeLayer layer = new GCodeLayer(this, z);
	this.layers.add(layer);
	return layer;
    }

    public void removeLayer(GCodeLayer layer) {
	this.layers.remove(layer);
    }

}
