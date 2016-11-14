package math.geometry.builder;

import math.gcode.model.GCodeModel;
import math.geometry.model.Model;

public class Builder {

    private double elementSize;

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

    public Model buildClassifer(GCodeModel gCodeModel) {
	Model model = new Model();
	ClassiferBuilder e = new ClassiferBuilder(0.789);
	e.generateVertices(gCodeModel, model);
	e.generateEdges(gCodeModel, model);
	return model;
    }

    public Model buildPointer(GCodeModel gCodeModel) {
	Model model = new Model();
	PointerBuilder e = new PointerBuilder(0.789);
	e.generateVertices(gCodeModel, model);
	e.generateEdges(gCodeModel, model);
	return model;
    }

}
