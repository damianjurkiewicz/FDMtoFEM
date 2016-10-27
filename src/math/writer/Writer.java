package math.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import math.geometry.Edge;
import math.geometry.Model;
import math.geometry.ModelBuilder;
import math.geometry.Vertex;

public class Writer {

    public void writeAbaqus(Model model, ModelBuilder builder) {

	PrintWriter printWriter = null;

	try {
	    printWriter = new PrintWriter("FDM_Model.inp", "UTF-8");
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	WriterVisitorAbaqus visitor = new WriterVisitorAbaqus();

	printWriter.println(visitor.generateHeader());

	for (Vertex abaqusVertex : model.getVertices()) {
	    printWriter.println(abaqusVertex.receive(visitor));
	}

	printWriter.println(visitor.generateMiddle());

	for (Edge abaqusEdge : model.getEdges()) {
	    printWriter.println(abaqusEdge.receive(visitor));
	}

	printWriter.println(visitor.generateBottom());

	printWriter.close();
    }

}
