package math.gcode.start.test;

import org.junit.Test;

import math.gcode.start.Dziekanat;
import math.gcode.start.Naukowiec;
import math.gcode.start.Student;

public class DziekanatTest {

    @Test
    public void test() {
	Dziekanat dziekanat = new Dziekanat();

	Student student1 = new Student();
	student1.setImie("Imie1");
	student1.setNazwisko("Nazwisko1");
	dziekanat.getOsoby().add(student1);

	Student student2 = new Student();
	student2.setImie("Imie2");
	student2.setNazwisko("Nazwisko2");
	dziekanat.getOsoby().add(student2);

	Naukowiec naukowiec = new Naukowiec();
	naukowiec.setStopien("stopien1");
	naukowiec.setImie("Imie2");
	naukowiec.setNazwisko("Nazwisko2");
	dziekanat.getOsoby().add(naukowiec);

	System.out.println(dziekanat);
    }

}
