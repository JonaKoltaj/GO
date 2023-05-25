

package inteligenca;

import java.util.LinkedList;

import logika.Igra;
import logika.Igralec;

import splosno.Poteza;


public class Alphabeta {
	
	private static final int ZMAGA = 1000; // vrednost zmage
	private static final int ZGUBA = -ZMAGA;  // vrednost izgube
	private static final int NEODLOC = 0;  // vrednost neodločene igre	
	
	private int globina;
	
	public Alphabeta (int globina) {
		super("alphabeta globina " + globina);
		this.globina = globina;
	}
	

	public Poteza izberiPotezo (Igra igra) {
		if (igra.obveznaPoteza != null) return igra.obveznaPoteza;
		else return alphabetaPoteze(igra, this.globina, ZGUBA, ZMAGA, igra.naVrsti).poteza;
//		return alphabetaPoteze(igra, this.globina, ZGUBA, ZMAGA, igra.naVrsti).poteza;
	}
	
	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
		int ocena;
		// Če sem računalnik, maksimiramo oceno z začetno oceno ZGUBA
		// Če sem pa človek, minimiziramo oceno z začetno oceno ZMAGA
		if (igra.naVrsti == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
		LinkedList<Poteza> moznePoteze = igra.moznePoteze;
		Poteza kandidat = moznePoteze.get(0); // Možno je, da se ne spremini vrednost kanditata. Zato ne more biti null.
		for (Poteza p: moznePoteze) {
			Igra kopijaIgre = new Igra(igra);
			System.out.println("Poskušamo igrati potezo:" + p.x() + p.y());
			kopijaIgre.odigraj (p);
			int ocenap;
			switch (kopijaIgre.stanje) {
			case ZMAGA_CRNI: ocenap = (jaz == Igralec.CRNI ? ZMAGA : ZGUBA); break;
			case ZMAGA_BELI: ocenap = (jaz == Igralec.BELI ? ZMAGA : ZGUBA); break;
			case NEODLOCENO: ocenap = NEODLOC; break;
			default:
				// Nekdo je na potezi
				if (globina == 1) ocenap = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				else ocenap = alphabetaPoteze (kopijaIgre, globina-1, alpha, beta, jaz).ocena;
			}
			if (igra.naVrsti == jaz) { // Maksimiramo oceno
				if (ocenap > ocena) { // mora biti > namesto >=
					ocena = ocenap;
					kandidat = p;
					alpha = Math.max(alpha,ocena);
				}
			} else { // igra.naPotezi() != jaz, torej minimiziramo oceno
				if (ocenap < ocena) { // mora biti < namesto <=
					ocena = ocenap;
					kandidat = p;
					beta = Math.min(beta, ocena);					
				}	
			}
			if (alpha >= beta) // Izstopimo iz "for loop", saj ostale poteze ne pomagajo
				break;
		}
		return new OcenjenaPoteza (kandidat, ocena);
	}

}
