package math.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import math.geometry.model.Edge;
import math.geometry.model.InPlaneJoint;
import math.geometry.model.InterLayerJoint;
import math.geometry.model.Model;
import math.geometry.model.Vertex;

public class Writer {

    public void writeAbaqus(Model model) {

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

	WriterVisitor writer = new WriterVisitorAbaqus();

	printWriter.println(writer.generateHeader());

	printWriter.println("**Wezly");

	for (Vertex abaqusVertex : model.getVertices()) {
	    printWriter.println(abaqusVertex.receive(writer));
	}

	printWriter.println(writer.generateMiddle());

	printWriter.println("*Element, type=B31, elset = wydruk");

	for (Edge abaqusEdge : model.getEdges()) {
	    printWriter.println(abaqusEdge.receive(writer));
	}

	printWriter.println("*Element, type=B31, elset = wwarstwie");

	for (InPlaneJoint inPlaneJoint : model.getInPlaneJoints()) {
	    printWriter.println(inPlaneJoint.receive(writer));
	}

	printWriter.println("*Element, type=B31, elset = miedzy");

	for (InterLayerJoint interLayerJoint : model.getInterLayerJoints()) {
	    printWriter.println(interLayerJoint.receive(writer));
	}

	printWriter.println(writer.generateBottom());

	printWriter.close();
    }

}
