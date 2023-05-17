import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import GUI.Okno;
import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
import inteligenca.Minimax;
import inteligenca.OceniPozicijo;
import inteligenca.RandomIzbira;
import logika.DisjointSet;
import logika.Igra;
import logika.Igralec;
import logika.Par;
//import logika.Plosca;
import logika.Zeton;
import splosno.Poteza;
import vodja.Vodja;

public class CaptureGo {

	public static void main(String[] args) {
		
	
		Okno okno = new Okno();
		okno.pack();
		okno.setVisible(true);
		Vodja.okno = okno;

		
		
//		Igra igra = new Igra();
//		System.out.println("ZAČNEMO ORIGINALNO IGRO 1");
//		igra.odigraj(new Poteza(0,0));
////		igra.sprintajIgro();
//		igra.odigraj(new Poteza(6,0));
//		igra.sprintajIgro();
//		
//		
//		Igra igra2 = new Igra();
//		System.out.println("ZAČNEMO IGRO 2");
//		igra2.odigraj(new Poteza(4,4));
//		igra2.odigraj(new Poteza(8,8));
//		igra2.sprintajIgro();
//		
//		System.out.println("SPET PRVA IGRA");
//		igra.sprintajIgro();
//		
		
		
//		Igra igra = new Igra();
//		System.out.println("ZAČNEMO ORIGINALNO IGRO 1");
//		igra.odigraj(new Poteza(0,0));
////		igra.sprintajIgro();
//		igra.odigraj(new Poteza(6,0));
//		igra.sprintajIgro();
//		
//		
//		
////		
////		
////		
////		
////		
////		
//		System.out.println("KOPIJA 1");
//		Igra kopija = new Igra(igra);
//		System.out.println("ODIGRANA KOPIAJA");
//		kopija.odigraj(new Poteza(8,8));
//		kopija.sprintajIgro();
//		System.out.println("ZAČNEMO ORIGINALNO IGRO 1");
//		igra.sprintajIgro();
		
//		igra.sprintajIgro();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("KOPIJA IGRE");
//		System.out.println("");
//		System.out.println("");
//		Igra novaIgra = new Igra(igra);
//		novaIgra.sprintajIgro();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("KOPIJA NOVA POTEZA NA 8 , 8 IGRE");
//		System.out.println("");
//		System.out.println("");
//		novaIgra.odigraj(new Poteza(8,8));
//		novaIgra.sprintajIgro();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("STARA IGRA");
//		System.out.println("");
//		System.out.println("");
//		igra.sprintajIgro();
		
		
//		Igra igra = new Igra();
//		System.out.println("ZAČNEMO ORIGINALNO IGRO 1");
//		igra.odigraj(new Poteza(0,0));
////		igra.sprintajIgro();
//		igra.odigraj(new Poteza(6,0));
////		igra.sprintajIgro();
//		igra.odigraj(new Poteza(1,0));
////		igra.sprintajIgro();
//		igra.odigraj(new Poteza(5,1));
//		igra.odigraj(new Poteza(2,2));
////		igra.sprintajIgro();
//		igra.odigraj(new Poteza(6,1));
//		igra.sprintajIgro();
//		
//		Igra igra2 = new Igra();
//		System.out.println("ZAČNEMO IGRO 2");
////		igra.sprintajIgro();
//		igra2.sprintajIgro();
//		
//		System.out.println("SPET PRVA IGRA");
//		igra.sprintajIgro();
//		System.out.println("KOPIJA 1");
//		Igra kopija = new Igra(igra);
//		kopija.sprintajIgro();
//		System.out.println("ODIGRANA KOPIAJA");
//		kopija.odigraj(new Poteza(8,8));
//		kopija.sprintajIgro();
//		
//		igra.sprintajIgro();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("KOPIJA IGRE");
//		System.out.println("");
//		System.out.println("");
//		Igra novaIgra = new Igra(igra);
//		novaIgra.sprintajIgro();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("KOPIJA NOVA POTEZA NA 8 , 8 IGRE");
//		System.out.println("");
//		System.out.println("");
//		novaIgra.odigraj(new Poteza(8,8));
//		novaIgra.sprintajIgro();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("STARA IGRA");
//		System.out.println("");
//		System.out.println("");
//		igra.sprintajIgro();
//		
	}
	

	

}
