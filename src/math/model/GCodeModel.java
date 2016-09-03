package math.model;

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

}
