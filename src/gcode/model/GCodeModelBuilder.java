package gcode.model;

import gcode.reader.GCodeFile;

public class GCodeModelBuilder {

	public GCodeModel build(GCodeFile gCodeFile) {

		GCodeVertex vertex1 = null;
		GCodeVertex vertex2 = null;
		GCodeLayer gCodeLayer = null;
		GCodeModel gCodeModel = new GCodeModel();

		for (int i = 0; i < gCodeFile.getRows().size(); i++) {

			if (gCodeFile.getRows().get(i).getZ().contains(".")) {
				Double z = Double.parseDouble(gCodeFile.getRows().get(i).getZ());
				gCodeLayer = gCodeModel.addLayer(z);
			}

			// X niepusty, Y niepusty, Z pusty, E niepusty,

			if (!gCodeFile.getRows().get(i).getX().isEmpty() && !gCodeFile.getRows().get(i).getY().isEmpty()
					&& !gCodeFile.getRows().get(i).getE().isEmpty()) {
				// poprzedni linijka wstecz X niepusty, weŸ wartoœci z linijki
				// wstecz
				Double x1 = 0d, x2 = 0d, y1 = 0d, y2 = 0d;
				x2 = Double.parseDouble(gCodeFile.getRows().get(i).getX());
				y2 = Double.parseDouble(gCodeFile.getRows().get(i).getY());

				int k = 0;
				do {
					k++;

					if (!gCodeFile.getRows().get(i - k).getX().isEmpty()) {
						x1 = Double.parseDouble(gCodeFile.getRows().get(i - k).getX());
						y1 = Double.parseDouble(gCodeFile.getRows().get(i - k).getY());
					}

				} while (gCodeFile.getRows().get(i - k).getX().isEmpty());

				// poprzedni linijka wstecz X pusty, weŸ wartoœci z linijki dwie
				// linijki wstecz

				vertex1 = gCodeLayer.addVertex(x1, y1);
				vertex2 = gCodeLayer.addVertex(x2, y2);

				gCodeLayer.addEdge(vertex1, vertex2);
			}
		}

		return gCodeModel;

	}
}
