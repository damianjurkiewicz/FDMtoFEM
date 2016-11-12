package math.geometry.builder;

import equations.Equations;
import math.gcode.model.GCodeEdge;
import math.gcode.model.GCodeLayer;
import math.gcode.model.GCodeModel;
import math.geometry.model.Model;
import math.geometry.model.Vertex;

public class BasedVertices implements VerticesGenerator {

    int nodeId = 1;
    int elementId = 1;
    private double elementSize;

    public BasedVertices(double elementSize) {
	super();
	this.elementSize = elementSize;
    }

    @Override
    public void generateVertices(GCodeModel gCodeModel, Model model) {
	double x1, y1, x2, y2, nextX = 0, nextY = 0, z;
	double incrementX, incrementY;
	double leftoverX, leftoverY;
	double edgeLengthX, edgeLengthY;
	double edgeLength;
	int numberOfWholeSegments;

	for (GCodeLayer gCodeLayer : gCodeModel.getLayers()) {

	    for (GCodeEdge gCodeEdge : gCodeLayer.getEdges()) {

		x1 = gCodeEdge.getVertex1().getX();
		y1 = gCodeEdge.getVertex1().getY();
		x2 = gCodeEdge.getVertex2().getX();
		y2 = gCodeEdge.getVertex2().getY();
		z = gCodeEdge.getLayer().getZ();
		Vertex vertex1;
		Vertex vertex2;
		Vertex vertex3;

		edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
		numberOfWholeSegments = Equations.computeNumberOfWholeSegments(edgeLength, elementSize);

		if (numberOfWholeSegments < 1) {
		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);
		}

		if (numberOfWholeSegments == 1) {
		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, (x1 + x2) / 2, (y1 + y2) / 2, z, gCodeEdge);
		    vertex3 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);
		    model.addEdge(elementId++, vertex2, vertex3);
		}

		if (numberOfWholeSegments > 1) {
		    edgeLengthX = Equations.computeEdgeLength(x1, x2);
		    edgeLengthY = Equations.computeEdgeLength(y1, y2);
		    incrementX = Equations.computeIncrement(edgeLengthX, edgeLength, elementSize);
		    incrementY = Equations.computeIncrement(edgeLengthY, edgeLength, elementSize);
		    leftoverX = Equations.computeLeftover(edgeLengthX, incrementX, numberOfWholeSegments);
		    leftoverY = Equations.computeLeftover(edgeLengthY, incrementY, numberOfWholeSegments);

		    double incrX = incrementX + (leftoverX / numberOfWholeSegments);
		    double incrY = incrementY + (leftoverY / numberOfWholeSegments);

		    nextX = x1 + incrX;
		    nextY = y1 + incrY;

		    vertex1 = model.addVertex(nodeId++, x1, y1, z, gCodeEdge);
		    vertex2 = model.addVertex(nodeId++, nextX, nextY, z, gCodeEdge);
		    model.addEdge(elementId++, vertex1, vertex2);

		    for (int k = 0; k < numberOfWholeSegments - 2; k++) {
			vertex1 = model.addVertex(nodeId++, nextX, nextY, z, gCodeEdge);
			vertex2 = model.addVertex(nodeId++, nextX = nextX + incrX, nextY = nextY + incrY, z, gCodeEdge);
			model.addEdge(elementId++, vertex1, vertex2);
		    }

		    vertex3 = model.addVertex(nodeId++, x2, y2, z, gCodeEdge);
		    model.addEdge(elementId++, vertex2, vertex3);
		}
	    }
	}

    }

}
