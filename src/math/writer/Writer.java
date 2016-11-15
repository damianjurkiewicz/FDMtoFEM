package math.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import math.geometry.model.Model;

public class Writer {

    private String writeDirectory;

    public Writer(String directory) {
	super();
	this.writeDirectory = directory;
    }

    public void writeFile(Model model) {

	try {
	    PrintWriter printWriter = new PrintWriter(this.writeDirectory);
	    FileTemplate templateAbaqus = new FileTemplateAbaqus();
	    printWriter.println(templateAbaqus.writeFile(model));
	    printWriter.close();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
