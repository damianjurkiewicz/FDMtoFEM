package math.geometry.builder;

import math.gcode.model.GCodeModel;
import math.geometry.model.Model;

public interface EdgesGenerator {
    public void generateEdges(GCodeModel gCodeModel, Model model);
}
