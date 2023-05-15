import java.util.HashMap;
import java.util.Map;

import GUI.Okno;
import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
import inteligenca.Minimax;
import inteligenca.OceniPozicijo;
import inteligenca.RandomIzbira;
import logika.Igra;
import logika.Igralec;
import logika.Par;
import logika.Plosca;
import logika.Zeton;
import splosno.Poteza;
import vodja.Vodja;

public class CaptureGo {

	public static void main(String[] args) {
		
		// zaenkrat igro lahko igrata dva igralca tako, da izmenjajoče vnašata koordinate
		// vedno začne črni
//		Inteligenca r1 = new RandomIzbira("random");
//		Inteligenca r2 = new Minimax(9);
//		Inteligenca r3 = new Alphabeta(9);
		Okno okno = new Okno();
		okno.pack();
		okno.setVisible(true);
		Vodja.okno = okno;
////		
//		Igra igra = new Igra();
//		System.out.println("ZAČNEMO IGRO");
//		igra.odigraj(new Poteza(0,0), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(5,0), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(5,1), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(0,1), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(6,0), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(4,5), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(1,1), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(4,1), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(1,2), igra.naVrsti);
//		igra.sprintajIgro();
//		igra.odigraj(new Poteza(6,1), igra.naVrsti);
//		igra.sprintajIgro();
//		Igra kopijaIgre = Igra.kopirajIgro(igra);
//		System.out.println("SKOPIRANA IGRA JE");
//		kopijaIgre.sprintajIgro();
////		Poteza potencialna1 = new Poteza(1,1);
////		Poteza potencialna2 = new Poteza(5,6);
//		int ocena1 = OceniPozicijo.oceniPozicijo(igra, Igralec.CRNI);
//		int ocena2 = OceniPozicijo.oceniPozicijo(igra, Igralec.BELI);
//		System.out.println("Ocena pozicije črnega je: " + " " + ocena1 + ", Ocena poziicje belega je: " + ocena2);
//		
		
	
	}

}
