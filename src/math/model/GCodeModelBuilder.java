package math.model;

import math.gcode.GCodeFile;

public class GCodeModelBuilder {

	// Metoda majaca za zadanie utworzyc odpowienia strukture, warstw (layer),
	// odcinkow (edge), punktow (vertex)
	// Struktura ta odpowiada wyrobowi drukowanemu, skadajacego sie z warstw i
	// nitek (odcinkow, edge'ow), nitka ma punkt poczatkowy i koncowy, zawarty w
	// GCodzie
	public GCodeModel build(GCodeFile gCodeFile) {

		GCodeVertex vertex1 = null;
		GCodeVertex vertex2 = null;
		GCodeLayer gCodeLayer = null;
		GCodeModel gCodeModel = new GCodeModel();

		// Przeszukanie calego gCodeFile, linijka po linijce
		for (int i = 0; i < gCodeFile.getRows().size(); i++) {

			// Jesli napotka Z ustawi nowa warstwe, String przy Z (w naszym
			// przypadku liczba) musi miec kropke, eliminuje to problem
			// wczytywania wartosci rychow pomocniczych, ktore wedlug pliku
			// GCode ze Slic3ra nie zawieraja kropki, mozna pomyslec o lepszym
			// zabezpieczeniu
			if (gCodeFile.getRows().get(i).getZ().contains(".")) {
				Double z = Double.parseDouble(gCodeFile.getRows().get(i).getZ());
				gCodeLayer = gCodeModel.addLayer(z);
			}

			// Wczytanie linijki w ktorej:
			// X niepusty, Y niepusty, E niepusty,
			// Wystepowanie tych wsporzednych gwarantuje, ze nastapil
			// rzeczywiscie wydruk, a nie ruch pomocniczy

			if (!gCodeFile.getRows().get(i).getX().isEmpty() && !gCodeFile.getRows().get(i).getY().isEmpty()
					&& !gCodeFile.getRows().get(i).getE().isEmpty()) {

				// Linijka zawierajaca X, Y, E daje wspolrzedne dla drugiego
				// vertexa (x2, y2), z tego wzledu, ze przed taka linijka moze
				// wystpic ruch pomocniczy
				Double x1 = 0d, x2 = 0d, y1 = 0d, y2 = 0d;
				x2 = Double.parseDouble(gCodeFile.getRows().get(i).getX());
				y2 = Double.parseDouble(gCodeFile.getRows().get(i).getY());

				// Przeszukiwanie wstecz, by znalezc ostatnia linijke ze
				// wspolrzednymi, ktora byla ruchem pomocniczym, ale to w tym
				// miejscu rozpoczyna sie wydruk nitki
				int k = 0;
				do {
					k++;

					if (!gCodeFile.getRows().get(i - k).getX().isEmpty()) {
						x1 = Double.parseDouble(gCodeFile.getRows().get(i - k).getX());
						y1 = Double.parseDouble(gCodeFile.getRows().get(i - k).getY());
					}

				} while (gCodeFile.getRows().get(i - k).getX().isEmpty());

				// Po znalezieniu wspolrzednych X Y nastepuje utworzenie
				// vertexow i stworzenia edga z ich wykorzystaniem
				vertex1 = gCodeLayer.addVertex(x1, y1);
				vertex2 = gCodeLayer.addVertex(x2, y2);

				gCodeLayer.addEdge(vertex1, vertex2);

			}
		}

		return gCodeModel;

	}
}
