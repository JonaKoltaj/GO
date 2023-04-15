import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		igra.odigraj(new Poteza(1,1)); // črni
		igra.izpisStanja();
		igra.odigraj(new Poteza(2,2)); // beli
		igra.izpisStanja();
		igra.odigraj(new Poteza(3,3)); // črni
		igra.izpisStanja();
		igra.odigraj(new Poteza(3,2)); // beli
		igra.izpisStanja();
		igra.odigraj(new Poteza(1,2)); // črni
		igra.izpisStanja();
		
		Igra igra2 = new Igra();
		igra2.odigraj(new Poteza(3,4));
		igra2.izpisStanja();
		igra2.odigraj(new Poteza(4,4));
		igra2.izpisStanja();
		igra2.odigraj(new Poteza(3,5));
		igra2.izpisStanja();
		igra2.odigraj(new Poteza(5,4));
		igra2.izpisStanja();
		igra2.odigraj(new Poteza(3,6));
		igra2.izpisStanja();
		igra2.odigraj(new Poteza(6,4));
		igra2.izpisStanja();
		
	}

	
}
