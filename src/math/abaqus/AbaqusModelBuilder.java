package math.abaqus;

import math.model.GCodeEdge;
import math.model.GCodeModel;

public class AbaqusModelBuilder {

	// Metoda majaca za zadanie podzielic wczytany edge (odcinek) ze wzgledu na
	// jego dlugosc, w analizie MES nazywa sie to dyskretyzacja, utworzeniem
	// siatki elementow
	public AbaqusModel build(GCodeModel gCodeModel) {
		// zadany parametr p, podaje uzytkownik
		// w perspektywie jest uzaleznienie tego parametru od wymiaru wlokna i
		// sposobu wstawiania polaczen
		// nalezy zwrocic na to uwage przy tworzeniu klasy AbaqusJoiner
		double p = 1.5;

		int i, j;

		AbaqusModel abaqusModel = new AbaqusModel();

		// Wczytywanie warstwa po warstwie
		for (i = 0; i < gCodeModel.getLayers().size(); i++) {
			// Wczytywanie edge po edge'u,
			for (j = 0; j < gCodeModel.getLayers().get(i).getEdges().size(); j++) {

				GCodeEdge gCodeEdge = gCodeModel.getLayers().get(i).getEdges().get(j);
				double x1 = gCodeEdge.getVertex1().getX();
				double y1 = gCodeEdge.getVertex1().getY();
				double x2 = gCodeEdge.getVertex2().getX();
				double y2 = gCodeEdge.getVertex2().getY();
				double x3 = 0;
				double y3 = 0;
				double z = gCodeEdge.getLayer().getZ();
				double edgeLength = gCodeModel.getLayers().get(i).getEdges().get(j).getLength();

				// Odcinek krotszy niz wielkosc elemenetu, tak krotki odcinek
				// nie jest dzielony
				if (edgeLength <= p) {

					AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
					AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
//					abaqusModel.addEdge(vertex1, vertex2);

				}

				// Polowa odcinka krotsza ni¿ zadania dlugosc elementu, ale caly
				// odcinek
				// dluzszy niz element
				// Taki odcinek jest dzielony na pol, powstaja dwa elementy
				if (edgeLength / 2 < p && edgeLength > p) {
					double a;
					double b;
					// Odcinek pionowy
					if (x1 - x2 == 0) {
						x3 = x1;
						y3 = (y1 + y2) / 2;
					}

					// Odcinek poziomy
					if (y1 - y2 == 0) {
						y3 = y1;
						x3 = (x1 + x2) / 2;
					}

					// Odcinek ukoœny
					if (y1 - y2 != 0 && x1 - x2 != 0) {
						a = obliczWspolKierProstej(x1, y1, x2, y2);
						b = y1 - a * x1;
						x3 = (x2 + x1) / 2;
						y3 = a * x3 + b;
					}

					// Definiowanie nowych vertexow
					AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
					AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
					AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
					// Tworzenie nowych odcinkow z utworzonych vertexow
//					abaqusModel.addEdge(vertex1, vertex3);
//					abaqusModel.addEdge(vertex3, vertex2);

				}

				// Odcinek duzo dluzszy niz zadana wielkosc elementu
				// Najczesciej wystepujacy podzial odcinka, pierwszy i ostatni
				// element to odcinek o polowie dlugosci reszty podzielonej na
				// dwa, jaka zostaje z podzialu
				if (edgeLength > 2 * p) {
					int edgeSegments = 0;
					double rest = 0;

					// Odcinek pionowy
					if (x1 - x2 == 0) {
						edgeSegments = (int) (edgeLength / p);
						rest = (edgeLength - p * edgeSegments); // reszta jaka
																// pozostaje z
																// podzialu o
																// parametrze p
						// pomysl na przyszlosc: dodaj reszte do kazdego z
						// odcinkow lub dzielenie wedlug zadanej ilosci
						// elementow na odcinek

						// Obliczenie wspolrzednych pierwszego z kolei odcinka,
						// ten odcinek ma dlugosc reszta/2.
						x3 = x1;
						y3 = y1 + rest / 2;

						// Stworzenie i dodanie do modelu pierwszego elementu
						AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
						AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
//						abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);

						AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);

						AbaqusVertex vertex = null;
						AbaqusVertex vertexNext = null;

						// Petla majaca za zadanie utworzyc elementy o
						// wspolrzednych przesunietych o zadana wielkosc
						// elementu p
						for (int k = 0; k < edgeSegments; k++) {
							vertex = abaqusModel.addVertex(x3, y3, z);
							vertexNext = abaqusModel.addVertex(x3, y3 = y3 + p, z);
//							abaqusModel.addEdge(vertex, vertexNext, gCodeEdge);
						}
//						abaqusModel.addEdge(vertexNext, vertex2, gCodeEdge);
					}

					// Odcinek poziomy, podzial analogicznie do pionowego.
					if (y1 - y2 == 0) {
						edgeSegments = (int) (edgeLength / p);
						rest = (edgeLength - p * edgeSegments);
						x3 = x1 + rest / 2;
						y3 = y1;

						AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
						AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
						AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
//						abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);

						AbaqusVertex vertex = null;
						AbaqusVertex vertexNext = null;
						for (int k = 0; k < edgeSegments; k++) {
							vertex = abaqusModel.addVertex(x3, y3, z);
							x3 = x3 + p;
							vertexNext = abaqusModel.addVertex(x3, y3, z);
//							abaqusModel.addEdge(vertex, vertexNext, gCodeEdge);
						}
//						abaqusModel.addEdge(vertexNext, vertex2, gCodeEdge);

					}

					// Odcinek ukoœny, podzial podobny do tego wczesniej, z
					// malymi roznicami.
					if (y1 - y2 != 0 && x1 - x2 != 0) {
						//Obliczam kierunkowy prostej
						//Lepiej zmienic nazwe na wspolKierunkProstej - nazwa od 8 do 22 znakow jest bardzo dobre
						double a = obliczWspolKierProstej(x1, y1, x2, y2);
						//Obliczam parametr przesuniecia 
						double b = y1 - a * x1;
						// Przy skosnym odcinku nie mozna po prostu dodawac
						// parametru p, do wsporzednych, nalezy obliczyc dlugosc
						// tego parametru np w dlugosci poziomej (X)
						// Uzyskany parametr pX wykorzystuje sie do obliczen - przesuniecie po x
						// kolejnych wspolrzednych, wiecej w dokumentacji
						//deklaracja zmiennnej na poczatku metody (typ)
						double pX = ((x2 - x1) / edgeLength) * p;
						edgeSegments = (int) ((x2 - x1) / pX);
						rest = (x2 - x1) - pX * edgeSegments;
						//deklaracja zmiennych na poczatku algorytmu, podkreslenie logiki
						//mozna stosowac metody, jesli cos sie powtarza, pozniej wystarczy poprawic w jednym miejscu
						x3 = x1 + rest / 2;
						y3 = a * x3 + b;
						
						AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
						AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
//						abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);

						AbaqusVertex vertex = null;
						AbaqusVertex vertexNext = null;
						//zamiast k "licznik"
						for (int k = 0; k < edgeSegments; k++) {

							vertex = abaqusModel.addVertex(x3, y3, z);
							x3 = x3 + pX;
							y3 = a * x3 + b;
							vertexNext = abaqusModel.addVertex(x3, y3, z);
//							abaqusModel.addEdge(vertex, vertexNext, gCodeEdge);
						}

						AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
//						abaqusModel.addEdge(vertexNext, vertex2, gCodeEdge);
					}

				}

			}
		}

		return abaqusModel;
	}

	/**
	 * @param wspolrzedna x poczatku edge'a
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private double obliczWspolKierProstej(double x1, double y1, double x2, double y2) {
		return (y1 - y2) / (x1 - x2);
	}

}
