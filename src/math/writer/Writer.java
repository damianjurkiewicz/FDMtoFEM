package math.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import math.geometry.model.Edge;
import math.geometry.model.Model;
import math.geometry.model.ModelBuilder;
import math.geometry.model.Vertex;

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

	WriterVisitorAbaqus sdk = new WriterVisitorAbaqus();

	printWriter.println(sdk.generateHeader());

	for (Vertex abaqusVertex : model.getVertices()) {
	    printWriter.println(sdk.generateNodes(abaqusVertex));
	}

	for (Edge abaqusEdge : model.getEdges()) {
	    printWriter.println(sdk.generateElements(abaqusEdge));
	}

	printWriter.println(sdk.generateBottom());

	printWriter.close();
    }

}
