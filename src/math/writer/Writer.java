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

	WriterVisitorAbaqus abaqusWriter = new WriterVisitorAbaqus();

	printWriter.println(abaqusWriter.generateHeader());

	for (Vertex abaqusVertex : model.getVertices()) {
	    printWriter.println(abaqusVertex.receive(abaqusWriter));
	}

	printWriter.println(abaqusWriter.generateMiddle());

	for (Edge abaqusEdge : model.getEdges()) {
	    printWriter.println(abaqusEdge.receive(abaqusWriter));
	}

	// for (InPlaneJoint inPlaneJoint : model.getInPlaneJoints()) {
	// printWriter.println(inPlaneJoint.receive(abaqusWriter));
	// }

	printWriter.println(abaqusWriter.generateBottom());

	printWriter.close();
    }

}
