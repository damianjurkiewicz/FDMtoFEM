package math.model;

import math.gcode.GCodeFile;

public class GCodeModelBuilder {

	public GCodeModel build(GCodeFile gCodeFile) {

		Double x1D = 0d;
		Double y1D = 0d;
		Double x2D = 0d;
		Double y2D = 0d;
		Double zD = 0d;
		GCodeVertex vertex = null;
		GCodeVertex vertex2 = null;
		GCodeLayer layer = null;
		GCodeEdge edge = null;
		GCodeModel model = new GCodeModel();
		for (int i = 0; i < 13; i++) {

			String z = gCodeFile.getRows().get(i).getZ();

			if (z.contains(".")) {
				zD = Double.parseDouble(gCodeFile.getRows().get(i).getZ());
				layer = new GCodeLayer(model, zD);
				model.getLayers().add(layer);
				System.out.print(model);
			}

			// x nie pusty, y nie pusty, z pusty, e nie pusty, poprzedni Z
			// pusty
			if (!gCodeFile.getRows().get(i).getX().isEmpty() && !gCodeFile.getRows().get(i).getY().isEmpty()
					&& gCodeFile.getRows().get(i).getZ().isEmpty() && !gCodeFile.getRows().get(i).getE().isEmpty()
					&& gCodeFile.getRows().get(i - 1).getZ().isEmpty()
					&& !gCodeFile.getRows().get(i - 1).getX().isEmpty()) {

				x1D = Double.parseDouble(gCodeFile.getRows().get(i - 1).getX());
				y1D = Double.parseDouble(gCodeFile.getRows().get(i - 1).getY());
				x2D = Double.parseDouble(gCodeFile.getRows().get(i).getX());
				y2D = Double.parseDouble(gCodeFile.getRows().get(i).getY());

				vertex = new GCodeVertex(layer, x1D, y1D);
				vertex2 = new GCodeVertex(layer, x2D, y2D);

				layer.getVertices().add(vertex);
				layer.getVertices().add(vertex2);

				edge = new GCodeEdge(layer, vertex, vertex2);
				layer.getEdges().add(edge);
			
			}

		}
		System.out.print(model);
		return model;

	}

}
