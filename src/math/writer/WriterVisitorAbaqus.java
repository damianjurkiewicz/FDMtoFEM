package math.writer;

import math.abaqus.Edge;
import math.abaqus.Vertex;

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