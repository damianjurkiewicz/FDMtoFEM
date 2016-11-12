package math.geometry.builder;

import equations.Equations;
import math.gcode.model.GCodeModel;
import math.geometry.model.Model;
import math.geometry.model.Vertex;

public class BasedEdges implements EdgesGenerator {

    int nodeId = 1;
    int elementId = 1;
    private double elementSize;

    public BasedEdges(double elementSize) {
	super();
	this.elementSize = elementSize;
    }

    @Override
    public void generateEdges(GCodeModel gCodeModel, Model model) {
	for (Vertex currentVertex : model.getVertices()) {

	    for (Vertex vertex : model.getVertices()) {
		if (currentVertex.getZ() == vertex.getZ() || currentVertex.getZ() == vertex.getZ() + 1) {

		    if (currentVertex != vertex) {
			// metoda w abaqus
			double vertexDistance = Equations.computeVertexDistance(currentVertex, vertex);

			// if (currentAbaqusVertex.getDistanceTo(abaqusVertex)
			// <=
			// this.elementSize){

			if (vertexDistance <= this.elementSize + 0.01) {

			    if (model.findEdge(currentVertex, vertex) != null) {
				break;
			    }

			    if (model.findEdge(vertex, currentVertex) != null) {
				break;
			    }
			    model.addEdge(elementId++, vertex, currentVertex);

			}
		    }
		}
	    }
	}
    }
}
