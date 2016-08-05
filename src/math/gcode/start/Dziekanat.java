package math.gcode.start;

import java.util.ArrayList;
import java.util.List;

public class Dziekanat {

    private List<Osoba> osoby;

    public Dziekanat() {
	this.osoby = new ArrayList<Osoba>();
    }

    public List<Osoba> getOsoby() {
	return this.osoby;
    }

    public String toString() {
	String opis = "";

	for (Osoba osoba : osoby) {
	    opis = opis + osoba.przedstawSie() + "\n";
	}

	return opis;
    }
}
