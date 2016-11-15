package math.geometry.builder;

import math.gcode.model.GCodeModel;
import math.geometry.model.Model;

public class Builder {

    private double elementSize;

    public Builder(double elementSize) {
	this.elementSize = elementSize;
    }

    public Model buildClassifier(GCodeModel gCodeModel) {
	ClassifierGenerator classifier = new ClassifierGenerator(this.elementSize);
	return classifier.build(gCodeModel);
    }

    public Model buildPointer(GCodeModel gCodeModel) {
	PointerGenerator pointer = new PointerGenerator(this.elementSize);
	return pointer.build(gCodeModel);
    }

}
