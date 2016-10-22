package math.abaqus;

import equations.Equations;
import math.model.GCodeEdge;
import math.model.GCodeLayer;
import math.model.GCodeModel;

// Code Tells You How, Comments Tell You Why
public class AbaqusModelBuilder {

	private double elementSize;

	public AbaqusModelBuilder(double elementSize) {
		super();
		this.elementSize = elementSize;
	}

	public double getElementSize() {
		return elementSize;
	}

	public void setElementSize(double elementSize) {
		this.elementSize = elementSize;
	}

	public AbaqusModel build(GCodeModel gCodeModel) {
		AbaqusModel abaqusModel = new AbaqusModel();
		generateVertices(gCodeModel, abaqusModel);
		generateEdges(gCodeModel, abaqusModel);
		return abaqusModel;
	}

	public void generateVertices(GCodeModel gCodeModel, AbaqusModel abaqusModel) {
		// TODO: elementSize should be User choince, but with reasonable bounds

		double x1, y1, x2, y2, nextX, nextY, z;
		double incrementX, incrementY;
		double leftoverX, leftoverY;
		double edgeLength;
		int wholeSegments;

		for (GCodeLayer gCodeLayer : gCodeModel.getLayers()) {

			for (GCodeEdge gCodeEdge : gCodeLayer.getEdges()) {

				x1 = gCodeEdge.getVertex1().getX();
				y1 = gCodeEdge.getVertex1().getY();
				x2 = gCodeEdge.getVertex2().getX();
				y2 = gCodeEdge.getVertex2().getY();
				z = gCodeEdge.getLayer().getZ();

				edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
				wholeSegments = Equations.computeWholeSegmentsNumber(edgeLength, elementSize);

				if (wholeSegments == 0) {
					abaqusModel.addVertex(x1, y1, z);
					abaqusModel.addVertex(x2, y2, z);
				}

				if (wholeSegments == 1) {
					abaqusModel.addVertex(x1, y1, z);
					abaqusModel.addVertex((x2 + x1) / 2, (y2 + y1) / 2, z);
					abaqusModel.addVertex(x2, y2, z);
				}

				if (wholeSegments > 1) {
					incrementX = Equations.computeIncrement(x1, x2, edgeLength, elementSize);
					incrementY = Equations.computeIncrement(y1, y2, edgeLength, elementSize);
					leftoverX = Equations.computeLeftover(x1, x2, incrementX, wholeSegments);
					leftoverY = Equations.computeLeftover(y1, y2, incrementY, wholeSegments);

					// abaqusModel.addVertex(x1, y1, z);
					nextX = x1 + leftoverX;
					nextY = y1 + leftoverY;
					abaqusModel.addVertex(nextX, nextY, z);

					for (int k = 0; k < wholeSegments; k++) {
						nextX = nextX + incrementX;
						nextY = nextY + incrementY;
						abaqusModel.addVertex(nextX, nextY, z);
					}
					// abaqusModel.addVertex(x2, y2, z);

				}
			}

		}

	}

	public void generateEdges(GCodeModel gCodeModel, AbaqusModel abaqusModel) {

		for (AbaqusVertex currentAbaqusVertex : abaqusModel.getVertices()) {

			for (AbaqusVertex abaqusVertex : abaqusModel.getVertices()) {

				if (currentAbaqusVertex != abaqusVertex) {
					//metoda w abaqus
					double vertexDistance = Equations.computeVertexDistance(currentAbaqusVertex, abaqusVertex);

					// if (currentAbaqusVertex.getDistanceTo(abaqusVertex) <= this.elementSize){
					
					
					if (vertexDistance <= this.elementSize) {

						if (abaqusModel.findEdge(currentAbaqusVertex, abaqusVertex) != null) {
							break;
						}

						if (abaqusModel.findEdge(abaqusVertex, currentAbaqusVertex) != null) {
							break;
						}
						
						abaqusModel.addEdge(currentAbaqusVertex, abaqusVertex);
					}
				}
			}
		}
	}
}
