package math.abaqus;

import math.model.GCodeEdge;
import math.model.GCodeModel;

public class AbaqusModelBuilder {

    public AbaqusModel build(GCodeModel gCodeModel) {

	double p = 2;
	int i, j;

	AbaqusModel abaqusModel = new AbaqusModel();

	for (i = 0; i < gCodeModel.getLayers().size(); i++) {

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

		// Odcinek krotszy niz wielkosc elemenetu
		if (edgeLength <= p) {

		    AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
		    AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
		    abaqusModel.addEdge(vertex1, vertex2, gCodeEdge);

		}

		// Polowa odcinka krotsza ni¿ element, ale caly odcinek
		// dluzszy niz element
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
			a = (y1 - y2) / (x1 - x2);
			b = y1 - a * x1;
			x3 = (x2 - x1) / 2;
			y3 = a * x3 + b;
		    }

		    AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
		    AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
		    AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
		    // tworza sie dwa odcinki
		    abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);
		    abaqusModel.addEdge(vertex3, vertex2, gCodeEdge);

		}

		// Odcinek duzo dluzszy niz element
		if (edgeLength > p) {
		    int edgeSegments = 0;
		    double rest = 0;

		    // Odcinek pionowy
		    if (x1 - x2 == 0) {
			edgeSegments = (int) (edgeLength / p);
			rest = (edgeLength % edgeSegments);
			x3 = x1;
			y3 = y1 + p + rest / 2;

			AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
			AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
			AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
			abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);

			AbaqusVertex vertex = null;
			AbaqusVertex vertexNext = null;
			for (int k = 0; k < edgeSegments - 2; k++) {
			    vertex = abaqusModel.addVertex(x3, y3, z);
			    vertexNext = abaqusModel.addVertex(x3, y3 = y3 + p, z);
			    abaqusModel.addEdge(vertex, vertexNext, gCodeEdge);
			}
			abaqusModel.addEdge(vertexNext, vertex2, gCodeEdge);
		    }

		    // Odcinek poziomy
		    if (y1 - y2 == 0) {
			edgeSegments = (int) (edgeLength / p);
			rest = (edgeLength % edgeSegments);
			x3 = x1 + p + rest / 2;
			y3 = y1;

			AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
			AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
			AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
			abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);

			AbaqusVertex vertex = null;
			AbaqusVertex vertexNext = null;
			for (int k = 0; k < edgeSegments - 2; k++) {
			    vertex = abaqusModel.addVertex(x3, y3, z);
			    x3 = x3 + p;
			    vertexNext = abaqusModel.addVertex(x3, y3, z);
			    abaqusModel.addEdge(vertex, vertexNext, gCodeEdge);
			}
			abaqusModel.addEdge(vertexNext, vertex2, gCodeEdge);

		    }

		    // Odcinek ukoœny
		    if (y1 - y2 != 0 && x1 - x2 != 0) {
			double a = (y1 - y2) / (x1 - x2);
			double b = y1 - a * x1;
			double pX = ((x2 - x1) / edgeLength) * p;
			edgeSegments = (int) ((x2 - x1) / pX);
			rest = ((x2 - x1) % edgeSegments);
			x3 = x1 + pX + rest / 2;
			y3 = a * x3 + b;
			AbaqusVertex vertex1 = abaqusModel.addVertex(x1, y1, z);
			AbaqusVertex vertex3 = abaqusModel.addVertex(x3, y3, z);
			abaqusModel.addEdge(vertex1, vertex3, gCodeEdge);

			AbaqusVertex vertex = null;
			AbaqusVertex vertexNext = null;
			for (int k = 0; k < edgeSegments - 2; k++) {

			    vertex = abaqusModel.addVertex(x3, y3, z);
			    x3 = x3 + pX;
			    y3 = a * x3 + b;
			    vertexNext = abaqusModel.addVertex(x3, y3, z);
			    abaqusModel.addEdge(vertex, vertexNext, gCodeEdge);
			}

			AbaqusVertex vertex2 = abaqusModel.addVertex(x2, y2, z);
			abaqusModel.addEdge(vertexNext, vertex2, gCodeEdge);
		    }

		}

	    }
	}

	return abaqusModel;
    }

}
