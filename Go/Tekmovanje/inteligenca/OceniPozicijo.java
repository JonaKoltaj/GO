package inteligenca;


import java.util.HashMap;
import java.util.Map;

import logika.DisjointSet;
import logika.Igra;
import logika.Igralec;
import logika.Zeton;

public class OceniPozicijo {

	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int ocenaJaz = oceniPozicijoBarve(igra, jaz);
		int ocenaNasprotnik = oceniPozicijoBarve(igra, igra.drugi(igra.naVrsti));
		return ocenaJaz - ocenaNasprotnik;
		
	}
	

	
	
	public static int oceniPozicijoBarve(Igra igra, Igralec barva) {
		// v slovar si shranimo "ogroženost" skupin
		// ključ: število prostih sosedov neke skupine
		// vrednost: koliko takšnih skupin imamo
		Map<Integer, Integer> ogrozenost = new HashMap<>();
		
		DisjointSet raziskujemoSkupino = igra.skupineBelih;
		if (barva == Igralec.CRNI) {raziskujemoSkupino = igra.skupineCrnih;}
		int sosedi = 0;
		for (Zeton z: raziskujemoSkupino.predstavniki) {
			sosedi = igra.prestejProsteSosede(z);
			if (ogrozenost.containsKey(sosedi)) {ogrozenost.put(sosedi, ogrozenost.get(sosedi) + 1);}
			else ogrozenost.put(sosedi, 1);

		}
		int ocena = 0;
		for (int x : ogrozenost.keySet()) {
			if (x == 1) {ocena -= 20 * ogrozenost.get(x); System.out.println(ocena);
		}
			else if (x == 2) {ocena -= 5 * ogrozenost.get(x); System.out.println(ocena);}
			else if (x == 3) {ocena -= 1 * ogrozenost.get(x); System.out.println(ocena);}
		}
			
		
		return ocena;
	}

	
	
	
	
	
}
