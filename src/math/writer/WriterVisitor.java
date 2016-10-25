package math.writer;

import math.abaqus.AbaqusEdge;
import math.abaqus.AbaqusVertex;

public interface WriterVisitor {

    public void generateNodes(AbaqusVertex vertex);

    public void generateElements(AbaqusEdge edges);

}
