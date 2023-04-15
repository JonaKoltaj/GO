import java.util.ArrayList;

import logika.Igra;
import logika.Plosca;
import logika.Zeton;
import splosno.Poteza;

public class CaptureGo {

	public static void main(String[] args) {
		
		Okno okno = new Okno();
		okno.pack();
		okno.setVisible(true);
		// Plosca plosca = new Plosca(10);
		// plosca.izpis();
		Igra igra = new Igra();
		igra.izpisStanja();
		igra.odigraj(new Poteza(1,1));
		igra.izpisStanja();
		igra.odigraj(new Poteza(2,2));
		igra.izpisStanja();
		igra.odigraj(new Poteza(3,3));
		igra.izpisStanja();
		igra.odigraj(new Poteza(3,2));
		igra.izpisStanja();
		
	}

}
