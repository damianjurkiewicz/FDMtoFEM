package geometry.builder;

import gcode.model.GCodeModel;
import geometry.model.Model;

public class Builder {

    private double elementSize;

    private double inLayerJoinDistance;
    private double interLayerJoinDistance;

    public Builder(double elementSize, double inLayerJoinDistance, double interLayerJoinDistance) {
	this.elementSize = elementSize;
	this.inLayerJoinDistance = inLayerJoinDistance;
	this.interLayerJoinDistance = interLayerJoinDistance;
    }

    public Model buildClassifier(GCodeModel gCodeModel) {
	ClassifierGenerator classifier = new ClassifierGenerator(this.elementSize, this.inLayerJoinDistance,
		this.interLayerJoinDistance);
	return classifier.build(gCodeModel);
    }

    public Model buildPointer(GCodeModel gCodeModel) {
	PointerGenerator pointer = new PointerGenerator(this.elementSize);
	return pointer.build(gCodeModel);
    }

}
