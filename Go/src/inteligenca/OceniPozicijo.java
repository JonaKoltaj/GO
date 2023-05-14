package inteligenca;


import logika.DisjointSet;
import logika.Igra;
import logika.Igralec;
import logika.Zeton;

public class OceniPozicijo {

	// metoda, ki oceni potezo za igralca	
	
	// za igralca neke barve pogledamo koliko prostih mest imajo njegove posamezne skupine
	// seštejemo prosta mesta
	// manj kot je prostih mest, več verjetnosti ima nasprotnik za zmago
	// torej manj kot imamo prostih mest, slabše je za nas
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int ocena = 0;
		DisjointSet raziskujemoSkupino = igra.skupineBelih;
		if (jaz == Igralec.CRNI) {raziskujemoSkupino = igra.skupineCrnih;}
		// sedaj gremo po predstavnikih in seštejemo sosede
		for (Zeton z: raziskujemoSkupino.predstavniki) {
			ocena += Igra.prestejProsteSosede(z);
		}
		return ocena;
	}
	
	
	
	
	
}
