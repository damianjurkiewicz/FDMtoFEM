package math.geometry.builder;

import math.gcode.model.GCodeModel;
import math.geometry.model.Model;

public class Builder {

    private double elementSize;
    int nodeId = 1;
    int elementId = 1;

    public Builder(double elementSize) {
	super();
	this.elementSize = elementSize;
    }

    public double getElementSize() {
	return elementSize;
    }

    public void setElementSize(double elementSize) {
	this.elementSize = elementSize;
    }

    public Model buildCloud(GCodeModel gCodeModel) {
	Model model = new Model();
	CloudVertices v = new CloudVertices(elementSize);
	CloudEdges e = new CloudEdges(elementSize);
	v.generateVertices(gCodeModel, model);
	e.generateEdges(gCodeModel, model);
	return model;
    }

    public Model buildBased(GCodeModel gCodeModel) {
	Model model = new Model();
	BasedVertices v = new BasedVertices(elementSize);
	BasedEdges e = new BasedEdges(elementSize);
	v.generateVertices(gCodeModel, model);
	e.generateEdges(gCodeModel, model);
	return model;

    }

}
