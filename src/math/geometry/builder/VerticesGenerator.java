package math.geometry.builder;

import math.gcode.model.GCodeModel;
import math.geometry.model.Model;

public interface VerticesGenerator {
    public void generateVertices(GCodeModel gCodeModel, Model model);
}
