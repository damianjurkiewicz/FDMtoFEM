package math.gcode.start;

public class Naukowiec implements Osoba {

    private String stopien;

    private String imie;

    private String nazwisko;

    public String getStopien() {
	return stopien;
    }

    public void setStopien(String stopien) {
	this.stopien = stopien;
    }

    public String getImie() {
	return imie;
    }

    public void setImie(String imie) {
	this.imie = imie;
    }

    public String getNazwisko() {
	return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
	this.nazwisko = nazwisko;
    }

    @Override
    public String przedstawSie() {
	return this.stopien + " " + this.imie + " " + this.nazwisko;
    }
}
