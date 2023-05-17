
package inteligenca;


import java.util.LinkedList;
import java.util.List;

import logika.Igra;
import logika.Igralec;
import splosno.Poteza;

public class Alphabeta extends Inteligenca {
	
	private int globina;
	
	public Alphabeta (int globina) {
		super("alphabeta globina " + globina);
		this.globina = globina;
	}
	
	
	// vrne najboljšo potezo za igralca, ki je na vrsti
	// črni igralec maksimizira, beli pa minimalizira
	@Override
	public Poteza izberiPotezo (Igra igra) {
//		Igra kopijaOriginalneIge = Igra.kopirajIgro(igra);
		// Na začetku alpha = ZGUBA in beta = ZMAGA
		System.out.println("Našli smo najboljšo potezo za naštega igralca");
		Poteza najboljsa = alphabetaPoteze(igra, this.globina, Integer.MIN_VALUE, Integer.MAX_VALUE, igra.naVrsti == Igralec.CRNI).poteza;
		System.out.println(najboljsa);
		return najboljsa;
	}
	
	// vrne najbolje ocenjeno potezo za IGRALCA JAZ
	public OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, boolean max) {
		// originalno igro želimo pustiti pri miru
		List<Poteza> poteze = igra.moznePoteze;
		switch (igra.stanje) {
		case NEODLOCENO:
			return new OcenjenaPoteza(null, 0);
		case ZMAGA_BELI:
			return new OcenjenaPoteza(null, Integer.MIN_VALUE);
		case ZMAGA_CRNI:
			return new OcenjenaPoteza(null, Integer.MAX_VALUE);
		default:
			if (globina == 0) return new OcenjenaPoteza(null, OceniPozicijo.oceniPozicijo(igra, igra.naVrsti));
			// če maksimiziramo črnega
			if (max) {
				int maxEval = Integer.MIN_VALUE;
				Poteza o = poteze.get(0);
				for (Poteza p : poteze) {
					Igra kopijaIgre = new Igra(igra);
					kopijaIgre.odigraj(p);
					int eval = alphabetaPoteze(kopijaIgre, globina - 1, alpha, beta, false).ocena;
					if (eval > maxEval) {
						o = p;
						maxEval = eval;
					}
					alpha = Integer.max(alpha, eval);
					if (beta <= alpha) break;
				}
				return new OcenjenaPoteza(o, maxEval);
			} else {
				int minEval = Integer.MAX_VALUE;
				Poteza o = poteze.get(0);
				for (Poteza p : poteze) {
					Igra kopijaIgre = new Igra(igra);
					kopijaIgre.odigraj(p);
					int eval = alphabetaPoteze(kopijaIgre, globina - 1, alpha, beta, true).ocena;
					if (eval < minEval) {
						o = p;
						minEval = eval;
					}
					beta = Integer.min(beta, eval);
					if (beta <= alpha) break;
				}
				return new OcenjenaPoteza(o, minEval);
			}
			
			
		}
		
		
	
	}
}


//package inteligenca;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//
//import logika.Igra;
//import logika.Igralec;
//import splosno.Poteza;
//
//public class Alphabeta extends Inteligenca {
//	
//	private static final int ZMAGA = 100; // vrednost zmage
//	private static final int ZGUBA = -ZMAGA;  // vrednost izgube
//	private static final int NEODLOC = 0;  // vrednost neodločene igre	
//	
//	private int globina;
//	
//	public Alphabeta (int globina) {
//		super("alphabeta globina " + globina);
//		this.globina = globina;
//	}
//	
//	
//	// vrne najboljšo potezo za igralca, ki je na vrsti
//	@Override
//	public Poteza izberiPotezo (Igra igra) {
//		// Na začetku alpha = ZGUBA in beta = ZMAGA
//		return alphabetaPoteze(igra, this.globina, ZGUBA, ZMAGA, igra.naVrsti).poteza;
//	}
//	
//	// vrne najbolje ocenjeno potezo za IGRALCA JAZ
//	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
//		int ocena;
//		// če je na vrsti tisti, za katerega ocenjujemo oceno
//		if (igra.naVrsti == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
//		LinkedList<Poteza> poteze = igra.moznePoteze;
//		Poteza kandidat = poteze.getFirst();; // Možno je, da se ne spremini vrednost kanditata. Zato ne more biti null.
//		for (Poteza p: poteze) {
//			Igra kopijaIgre = Igra.kopirajIgro(igra);
//			System.out.println("Poskušamo igrati potezo:" + p.x() + p.y());
//			kopijaIgre.odigraj(p, kopijaIgre.naVrsti);
//			int ocenap;
//			switch (kopijaIgre.stanje) {
//			case ZMAGA_CRNI: ocenap = (jaz == Igralec.CRNI ? ZMAGA : ZGUBA); break;
//			case ZMAGA_BELI: ocenap = (jaz == Igralec.BELI ? ZMAGA : ZGUBA); break;
//			case NEODLOCENO: ocenap = NEODLOC; break;
//			default:
//				// Nekdo je na potezi
//				if (globina == 1) ocenap = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
//				else ocenap = alphabetaPoteze (kopijaIgre, globina-1, alpha, beta, jaz).ocena;
//			}
//			if (igra.naVrsti == jaz) { // Maksimiramo oceno
//				if (ocenap > ocena) { // mora biti > namesto >=
//					ocena = ocenap;
//					kandidat = p;
//					alpha = Math.max(alpha,ocena);
//				}
//			} else { // igra.naPotezi() != jaz, torej minimiziramo oceno
//				if (ocenap < ocena) { // mora biti < namesto <=
//					ocena = ocenap;
//					kandidat = p;
//					beta = Math.min(beta, ocena);					
//				}	
//			}
//			if (alpha >= beta) // Izstopimo iz "for loop", saj ostale poteze ne pomagajo
//				break;
//		}
//		return new OcenjenaPoteza (kandidat, ocena);
//
//	
//}
//}


// PREJŠNJA


//package inteligenca;
//
//import java.util.ArrayList;
//
//import logika.Igra;
//import logika.Igralec;
//import splosno.Poteza;
//
//public class Alphabeta extends Inteligenca {
//	
//	private static final int ZMAGA = 100; // vrednost zmage
//	private static final int ZGUBA = -ZMAGA;  // vrednost izgube
//	private static final int NEODLOC = 0;  // vrednost neodločene igre	
//	
//	private int globina;
//	
//	public Alphabeta (int globina) {
//		super("alphabeta globina " + globina);
//		this.globina = globina;
//	}
//	
//	@Override
//	public Poteza izberiPotezo (Igra igra) {
//		// Na začetku alpha = ZGUBA in beta = ZMAGA
//		return alphabetaPoteze(igra, this.globina, ZGUBA, ZMAGA, igra.naVrsti).poteza;
//	}
//	
//	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
//		int ocena;
//		// Če sem računalnik, maksimiramo oceno z začetno oceno ZGUBA
//		// Če sem pa človek, minimiziramo oceno z začetno oceno ZMAGA
//		if (igra.naVrsti == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
//		ArrayList<Poteza> moznePoteze = igra.moznePotezeSeznam;
//		Poteza kandidat = moznePoteze.get(0); // Možno je, da se ne spremini vrednost kanditata. Zato ne more biti null.
//		for (Poteza p: moznePoteze) {
//			// verjetno je nekaj narobe pri kopiranju
//			Igra kopijaIgre = new Igra(igra);
//			System.out.println("Poskušamo igrati potezo:" + p.x() + p.y());
//			kopijaIgre.odigraj(p);
//			kopijaIgre.plosca.izpis();
//			int ocenap;
//			switch (kopijaIgre.stanje) {
//			case ZMAGA_CRNI: ocenap = (jaz == Igralec.CRNI ? ZMAGA : ZGUBA); break;
//			case ZMAGA_BELI: ocenap = (jaz == Igralec.BELI ? ZMAGA : ZGUBA); break;
//			case V_TEKU: ocenap = NEODLOC; break;
//			default:
//				// Nekdo je na potezi
//				if (globina == 1) ocenap = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
//				else ocenap = alphabetaPoteze (kopijaIgre, globina-1, alpha, beta, jaz).ocena;
//			}
//			if (igra.naVrsti == jaz) { // Maksimiramo oceno
//				if (ocenap > ocena) { // mora biti > namesto >=
//					ocena = ocenap;
//					kandidat = p;
//					alpha = Math.max(alpha,ocena);
//				}
//			} else { // igra.naPotezi() != jaz, torej minimiziramo oceno
//				if (ocenap < ocena) { // mora biti < namesto <=
//					ocena = ocenap;
//					kandidat = p;
//					beta = Math.min(beta, ocena);					
//				}	
//			}
//			if (alpha >= beta) // Izstopimo iz "for loop", saj ostale poteze ne pomagajo
//				break;
//		}
//		return new OcenjenaPoteza (kandidat, ocena);
//	}
//
//	
//}
