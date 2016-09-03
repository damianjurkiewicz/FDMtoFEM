package math.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Layer> layers;

    public Model() {
	this.layers = new ArrayList<Layer>();
    }

    public List<Layer> getLayers() {
	return layers;
    }

}
