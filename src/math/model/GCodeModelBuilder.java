package math.model;

import math.gcode.GCodeFile;

public class GCodeModelBuilder {

    public GCodeModel build(GCodeFile gCodeFile) {

	GCodeVertex vertex1 = null;
	GCodeVertex vertex2 = null;
	GCodeLayer layer = null;
	GCodeModel model = new GCodeModel();

	for (int i = 0; i < gCodeFile.getRows().size(); i++) {

	    if (gCodeFile.getRows().get(i).getZ().contains(".")) {
		Double z = Double.parseDouble(gCodeFile.getRows().get(i).getZ());
		layer = model.addLayer(z);
	    }

	    // x nie pusty, y nie pusty, z pusty, e nie pusty, poprzedni Z
	    // pusty
	    if (!gCodeFile.getRows().get(i).getX().isEmpty() && !gCodeFile.getRows().get(i).getY().isEmpty()
		    && gCodeFile.getRows().get(i).getZ().isEmpty() && !gCodeFile.getRows().get(i).getE().isEmpty()
		    && gCodeFile.getRows().get(i - 1).getZ().isEmpty()
		    && !gCodeFile.getRows().get(i - 1).getX().isEmpty()) {

		Double x1 = Double.parseDouble(gCodeFile.getRows().get(i - 1).getX());
		Double y1 = Double.parseDouble(gCodeFile.getRows().get(i - 1).getY());
		Double x2 = Double.parseDouble(gCodeFile.getRows().get(i).getX());
		Double y2 = Double.parseDouble(gCodeFile.getRows().get(i).getY());

		vertex1 = layer.addVertex(x1, y1);
		vertex2 = layer.addVertex(x2, y2);

		layer.addEdge(vertex1, vertex2);
	    }
	}

	return model;

    }
}
