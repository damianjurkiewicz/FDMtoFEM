package geometry.builder;

import equations.Equations;
import gcode.model.GCodeEdge;
import gcode.model.GCodeLayer;
import gcode.model.GCodeModel;
import geometry.model.Model;
import geometry.model.Vertex;

public class PointerGenerator implements Generator {

	private int nodeId = 1;
	private int elementId = 1;
	private double elementSize;
	private double inLayerJoinDistance;
	private double interLayerJoinDistance;

	public PointerGenerator(double elementSize, double inLayerJoinDistance, double interLayerJoinDistance) {
		this.elementSize = elementSize;
		this.inLayerJoinDistance = inLayerJoinDistance;
		this.interLayerJoinDistance = interLayerJoinDistance;
	}

	@Override
	public Model build(GCodeModel gCodeModel) {
		Model model = new Model();
		generateNodes(gCodeModel, model);
		generateElements(gCodeModel, model);
		return model;
	}

	public void generateNodes(GCodeModel gCodeModel, Model model) {
		double x1, y1, x2, y2, nextX, nextY, z;
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

				edgeLength = Equations.computeEdgeLength(x1, x2, y1, y2);
				numberOfWholeSegments = Equations.computeNumberOfWholeSegments(edgeLength, elementSize);

				if (numberOfWholeSegments == 0) {
					model.addVertex(nodeId++, (x1 + x2) / 2, (y1 + y2) / 2, z);
				}

				if (numberOfWholeSegments > 0) {
					edgeLengthX = Equations.computeEdgeLength(x1, x2);
					edgeLengthY = Equations.computeEdgeLength(y1, y2);
					incrementX = Equations.computeIncrement(edgeLengthX, edgeLength, elementSize);
					incrementY = Equations.computeIncrement(edgeLengthY, edgeLength, elementSize);
					leftoverX = Equations.computeLeftover(edgeLengthX, incrementX, numberOfWholeSegments);
					leftoverY = Equations.computeLeftover(edgeLengthY, incrementY, numberOfWholeSegments);

					nextX = x1 + leftoverX / 2;
					nextY = y1 + leftoverY / 2;
					model.addVertex(nodeId++, nextX, nextY, z);

					for (int k = 0; k < numberOfWholeSegments; k++) {
						nextX = nextX + incrementX;
						nextY = nextY + incrementY;
						model.addVertex(nodeId++, nextX, nextY, z);
					}
				}
			}
		}
	}

	boolean isNotDuplicate;

	public void generateElements(GCodeModel gCodeModel, Model model) {
		for (Vertex currentVertex : model.getVertices()) {

			for (Vertex vertex : model.getVertices()) {
				isNotDuplicate = false;

				if (currentVertex.getZ() == vertex.getZ()) {
					if (currentVertex != vertex) {
						double vertexDistance = Equations.computeInLayerDistance(currentVertex, vertex);

						if (vertexDistance <= this.inLayerJoinDistance) {

							if (model.findEdge(currentVertex, vertex) != null) {
								isNotDuplicate = true;
							}

							if (model.findEdge(vertex, currentVertex) != null) {
								isNotDuplicate = true;
							}

							if (isNotDuplicate = true) {
								model.addEdge(elementId++, vertex, currentVertex);
							}
						}
					}
				}

				if (currentVertex.getZ() != vertex.getZ()) {
					if (currentVertex != vertex) {
						double vertexDistance = Equations.computeInterLayerDistance(currentVertex, vertex);

						if (vertexDistance <= this.interLayerJoinDistance) {

							if (model.findEdge(currentVertex, vertex) != null) {
								isNotDuplicate = true;
							}

							if (model.findEdge(vertex, currentVertex) != null) {
								isNotDuplicate = true;
							}

							if (isNotDuplicate = true) {
								model.addInterLayerJoint(elementId++, vertex, currentVertex);
							}
						}
					}
				}
			}
		}
	}

}
