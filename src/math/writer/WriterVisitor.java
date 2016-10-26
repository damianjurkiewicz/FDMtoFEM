package math.writer;

import math.abaqus.Edge;
import math.abaqus.Vertex;

public interface WriterVisitor {

    public String generateHeader();

    public String generateNodes(Vertex vertex);

    public String generateMiddle();

    public String generateElements(Edge edges);

    public String generateBottom();

}
