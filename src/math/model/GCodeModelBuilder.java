package math.model;

import math.gcode.GCodeFile;

import math.gcode.GCodeReader;

public final class GCodeModelBuilder {
	
	//TODO: Przemyslec AbaqusBuildera, dzielenie odcinkow, zapis do INP

	public GCodeModel build(GCodeFile gCodefile) {

		GCodeReader reader = new GCodeReader();
		GCodeFile file = reader.read("C:\\Users\\maddj\\Desktop\\11.txt");
		// przenioslem to ponad, bo za kazdym razym tworzyl sie nowy obiekt
		GCodeModel model = new GCodeModel();
		GCodeLayer layer = new GCodeLayer();
		Double zD = 0d;
		for (int i = 1; i < 100; i++) {

			String z = file.getRows().get(i).getZ();
			if (z.contains(".")) {
				zD = Double.parseDouble(file.getRows().get(i).getZ());
				model.getLayers().add(layer);

			}

			if (file.getRows().get(i).getG().contains("1")) {
				Double x1D = 0d;
				Double y1D = 0d;
				Double x2D = 0d;
				Double y2D = 0d;
//x nie pusty, y nie pusty, z pusty, e nie pusty, poprzedni Z pusty
				if (!file.getRows().get(i).getX().isEmpty() && !file.getRows().get(i).getY().isEmpty()
						&& file.getRows().get(i).getZ().isEmpty() && !file.getRows().get(i).getE().isEmpty()
						&& file.getRows().get(i-1).getZ().isEmpty()
						&& !file.getRows().get(i-1).getX().isEmpty()
						) {

					if (!file.getRows().get(i - 1).getX().isEmpty()) {
						x1D = Double.parseDouble(file.getRows().get(i - 1).getX());
					}

					if (!file.getRows().get(i - 1).getY().isEmpty()) {
						y1D = Double.parseDouble(file.getRows().get(i - 1).getY());
					}

					if (!file.getRows().get(i).getX().isEmpty()) {
						x2D = Double.parseDouble(file.getRows().get(i).getX());
					}

					if (!file.getRows().get(i).getY().isEmpty()) {
						y2D = Double.parseDouble(file.getRows().get(i).getY());
					}

					GCodeVertex vertex = new GCodeVertex(x1D, y1D, zD);
					GCodeVertex vertex2 = new GCodeVertex(x2D, y2D, zD);

					// System.out.println(zz);
					GCodeEdge edge = new GCodeEdge(vertex, vertex2);
					layer.getVertices().add(vertex);
					layer.getVertices().add(vertex2);
					layer.getEdges().add(edge);
					System.out.println(model);
					
				}
			}

		}
		return model;

	}

	public static void main(String[] args) {

		GCodeModelBuilder test = new GCodeModelBuilder();
		test.build(null);
	}

}

