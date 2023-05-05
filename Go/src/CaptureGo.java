import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logika.Igra;
import logika.Par;
import logika.Plosca;
import logika.Zeton;
import splosno.Poteza;

public class CaptureGo {

	public static void main(String[] args) {
		
		// zaenkrat igro lahko igrata dva igralca tako, da izmenjajoče vnašata koordinate
		// vedno začne črni
		
		//Okno okno = new Okno();
		//okno.pack();
		//okno.setVisible(true);
		
		Igra igra = new Igra();
		System.out.println("ZAČNEMO IGRO");
		igra.odigraj(new Poteza(0,0));
		igra.izpisStanja();
		igra.igrajRacunalnik();
		igra.izpisStanja();
		igra.odigraj(new Poteza(6,1));
		igra.izpisStanja();
//		igra.igrajRacunalnik();
//		igra.izpisStanja();
//		igra.odigraj(new Poteza(0,2));
//		igra.izpisStanja();
//		igra.igrajRacunalnik();
//		igra.izpisStanja();
//		igra.odigraj(new Poteza(1,2));
//		igra.izpisStanja();
//		igra.igrajRacunalnik();
//		igra.izpisStanja();
//		igra.odigraj(new Poteza(0,3));
//		igra.izpisStanja();
//		igra.igrajRacunalnik();
//		igra.izpisStanja();
//		igra.odigraj(new Poteza(2,1));
//		igra.izpisStanja();
	}
	
	

	
}
