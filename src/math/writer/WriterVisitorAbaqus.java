package math.writer;

import math.geometry.model.Edge;
import math.geometry.model.Vertex;

public class WriterVisitorAbaqus implements WriterVisitor {

    @Override
    public String generateHeader() {
	return "kurwa";
    }

    @Override
    public String generateNodes(Vertex vertex) {
	Double x = vertex.getX();
	return x.toString();
    }

    @Override
    public String generateElements(Edge edges) {
	int k = edges.getEdgeId();
	return "" + k;
    }

    @Override
    public String generateBottom() {
	return "chuj";
    }

}
