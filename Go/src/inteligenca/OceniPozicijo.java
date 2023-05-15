package inteligenca;


import java.util.HashMap;
import java.util.Map;

import logika.DisjointSet;
import logika.Igra;
import logika.Igralec;
import logika.Zeton;

public class OceniPozicijo {

	// metoda, ki oceni potezo za igralca	
	
	// več kot ima igralec ogroženih polj, slabše je zanj
	// najbolj ogrožena polja, so tista, katerih skupina ima samo še 1 prosto polje
	// naredimo oceno za vsakega izgralca posebej
	// ocena bo razlika
	// torej, če bomo mi imeli manj slabih polj kot naš nasprotnik, bomo dobili pozitivno oceno
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int ocenaJaz = oceniPozicijoBarve(igra, jaz);
		int ocenaNasprotnik = oceniPozicijoBarve(igra, Igra.drugi(jaz));
//		System.out.println("Ocena igralca je " + (ocenaJaz - ocenaNasprotnik));
		return ocenaJaz - ocenaNasprotnik;
		
	}
	
	public static int oceniPozicijoBarve(Igra igra, Igralec barva) {
		// v slovar si shranimo "ogroženost" skupin
		// ključ: število prostih sosedov neke skupine
		// vrednost: koliko takšnih skupin imamo
		Map<Integer, Integer> ogrozenost = new HashMap<>();
		
		DisjointSet raziskujemoSkupino = igra.skupineBelih;
		if (barva == Igralec.CRNI) {raziskujemoSkupino = igra.skupineCrnih;}
		// sedaj gremo po predstavnikih in preštejemo sosede
		int sosedi = 0;
		for (Zeton z: raziskujemoSkupino.predstavniki) {
			sosedi = Igra.prestejProsteSosede(z);
//			System.out.println("Zeton " +  z.i + "," +  z.j + " ima " +  sosedi + " prostih sosedov");
			if (ogrozenost.containsKey(sosedi)) {ogrozenost.put(sosedi, ogrozenost.get(sosedi) + 1);}
			else ogrozenost.put(sosedi, 1);

		}
		int ocena = 0;
		for (int x : ogrozenost.keySet()) {
//			System.out.println(x);
			if (x == 1) {ocena -= 20 * ogrozenost.get(x); System.out.println(ocena);
		}
			else if (x == 2) {ocena -= 5 * ogrozenost.get(x); System.out.println(ocena);}
			else if (x == 3) {ocena -= 1 * ogrozenost.get(x); System.out.println(ocena);}
		}
			
		
//		System.out.println("Slovar ogroženosti je");
//		System.out.println(ogrozenost);
		return ocena;
	}
	
	
	
	
	
}
