package geometry.builder;

import gcode.model.GCodeModel;
import geometry.model.Model;

public interface Generator {
    public Model build(GCodeModel gCodeModel);
}
