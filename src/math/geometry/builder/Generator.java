package math.geometry.builder;

import math.gcode.model.GCodeModel;
import math.geometry.model.Model;

public interface Generator {
    public Model build(GCodeModel gCodeModel);
}
