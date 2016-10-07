package equations;

public class Equations {

    public static double obliczWspolKierProst(double x1, double x2, double y1, double y2) {

	double wspolKierProst = (y1 - y2) / (x1 - x2);
	return wspolKierProst;

    }

    public static double obliczParamPrzesuniaciaProst(double x1, double y1, double wspolKierProst) {
	double paramPrzesunieciaProst = y1 - (wspolKierProst * x1);
	return paramPrzesunieciaProst;
    }

}
