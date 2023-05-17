//package vodja;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.SwingWorker;
//
//import GUI.Okno;
//import inteligenca.Alphabeta;
//import inteligenca.Inteligenca;
//import inteligenca.RandomIzbira;
////import inteligenca.RandomIzbira;
//import logika.Igra;
//import logika.Igralec;
//import logika.Par;
//import splosno.KdoIgra;
//import splosno.Poteza;
//
//public class Vodja {
//	
//	
//	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
//	public static Map<Igralec, KdoIgra> kdoIgra;
//	public static Okno okno;
//	public static Igra igra = null;
//	public static boolean clovekNaVrsti = false;
//	
//	public static void igramoNovoIgro () {
//		igra = new Igra();
//		igramo();
//	}
//	
//	public static void igramo() {
//		okno.osveziGUI();
//		switch (igra.stanje) {
//		
//		case ZMAGA_CRNI, ZMAGA_BELI, NEODLOCENO: 
//			System.out.println(igra.zmagovalec());
//			return;
//		
////		case ZMAGA_CRNI: 
////		case ZMAGA_BELI: 
//		case V_TEKU: 
//			Igralec igralec = igra.naVrsti;
//			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
//			switch (vrstaNaPotezi) {
//			case C: 
//				clovekNaVrsti = true;
//				break;
//			case R:
//				igrajRacunalnikovoPotezo();
//				break;
//			}
//		}
//		
//	}
//	
////	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(2);
////	public static Inteligenca racunalnikovaInteligenca = new Minimax(2);
//	public static Inteligenca racunalnikovaInteligenca = new RandomIzbira("random");
//	
//	
//	
//	// isto kot pri Capture.go.2
//	public static void igrajRacunalnikovoPotezo() {
//		Igra zacetnaIgra = igra;
//		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
//			@Override
//			protected Void doInBackground() {
//				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};	// za testiranje je boljše če je hitrješi
//				return null;
//			}
//			@Override
//			protected void done () {
//				if (igra != zacetnaIgra) return;
//				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
//				igra.odigraj(poteza);
//				igramo();	
//			}
//		};
//		worker.execute();	
//	}
//	
//	
//	
//	
//	// TAKO KOT PRI PROFESORJU
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetnaIgra = igra;
////		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
////			@Override
////			protected Poteza doInBackground() {
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
////				return poteza;
////			}
////			@Override
////			protected void done () {
////				Poteza poteza = null;
////				try {poteza = get();} catch (Exception e) {};
////				if (igra == zacetnaIgra) {
////					igra.odigraj(poteza, igra.naVrsti);
////					igramo ();
////				}
////			}
////		};
////		worker.execute();
////	}
////	
//	
////	public static void igrajClovekovoPotezo(Poteza poteza) {
////		if (clovekNaVrsti && igra.moznePoteze.contains(poteza)) {
////			igra.odigraj(poteza, igra.naVrsti);
////			clovekNaVrsti = false;
////			igra.sprintajIgro();
////			igramo();
////		}
////	}
//
//	
//		// moje prejšnje
//	public static void igrajClovekovoPotezo(Poteza poteza) {
//		boolean vrednost = igra.odigraj(poteza);
//		if (vrednost) clovekNaVrsti = false;
//		igramo ();
//	}
//}

//////////////////////////7
//
//package vodja;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.SwingWorker;
//
//import GUI.Okno;
//import inteligenca.Alphabeta;
//import inteligenca.Inteligenca;
//import inteligenca.Minimax;
//import inteligenca.RandomIzbira;
////import inteligenca.RandomIzbira;
//import logika.Igra;
//import logika.Igralec;
//import logika.Par;
//import splosno.KdoIgra;
//import splosno.Poteza;
//
//public class Vodja {
//	
//	
//	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
//	public static Map<Igralec, KdoIgra> kdoIgra; // *************
//	public static Okno okno;
//	public static Igra igra = null;
//	public static boolean clovekNaVrsti = false;
//	
//	public static void igramoNovoIgro() {
//		igra = new Igra();
//		igramo();
//	}
//	
//	// MOJ predzadnji !!!!
//	public static void igramo() {
//		okno.osveziGUI();
//		switch (igra.stanje) {
//		case ZMAGA_CRNI: 
//		case ZMAGA_BELI: 
//		case NEODLOCENO: 
//			igra.sprintajIgro();
//			return; 
//		case V_TEKU: 
//			Igralec igralec = igra.naVrsti;
//			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
//			switch (vrstaNaPotezi) {
//			// če je na vrsti človek čakamo na njegovo potezo
//			case C: 
//				clovekNaVrsti = true;
//				break;
//			case R:
//				// sicer igramo računalnikovo potezo
//				igrajRacunalnikovoPotezo();
//				break;
//			}
//		}
//	}
//	
////	// MOJ predzadnji !!!!
////	public static void igramo() {
////		okno.osveziGUI();
////		switch (igra.stanje) {
////		case ZMAGA_CRNI, ZMAGA_BELI, NEODLOCENO: 
////			igra.sprintajIgro();
////			return;
////		case V_TEKU: 
////			Igralec igralec = igra.naVrsti;
////			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
////			switch (vrstaNaPotezi) {
////			// če je na vrsti človek čakamo na njegovo potezo
////			case C: 
////				clovekNaVrsti = true;
////				break;
////			case R:
////				// sicer igramo računalnikovo potezo
////				igrajRacunalnikovoPotezo();
////				break;
////			}
////		}
////	}
//	
////	public static Inteligenca racunalnikovaInteligenca = new Minimax(2);
////	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(1);
//	public static Inteligenca racunalnikovaInteligenca = new RandomIzbira("random");
//	
//	
//	// isto kot pri Capture.go.2
//	public static void igrajRacunalnikovoPotezo() {
//		Igra zacetnaIgra = igra;
//		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
//			@Override
//			protected Void doInBackground() {
//				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};	// za testiranje je boljše če je hitrješi
//				return null;
//			}
//			@Override
//			protected void done () {
//				if (igra != zacetnaIgra) return;
//				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
//				igra.odigraj(poteza);
//				igramo();	
//			}
//		};
//		worker.execute();	
//	}
//	
//	
////	// MOJE ISTO KOT PROFESORJEVO
////		public static void igrajRacunalnikovoPotezo() {
////			Igra zacetnaIgra = igra;
////			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
////				@Override
////				protected Void doInBackground() {
////					try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};
////					return null;
////				}
////				@Override
////				protected void done () {
////					if (igra != zacetnaIgra) return;
////					Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////					igra.odigraj(poteza, igra.naVrsti);
////					igra.sprintajIgro();
////					igramo ();
////					}
////			};
////			worker.execute();
////		}
////	
//	// MOJE ISTO KOT PROFESORJEVO
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetkaIgra = igra;
////		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
////			@Override
////			protected Poteza doInBackground() {
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};
////				return poteza;
////			}
////			@Override
////			protected void done () {
////				Poteza poteza = null;
////				try {poteza = get();} catch (Exception e) {};
////				if (igra == zacetkaIgra) {
////					igra.odigraj(poteza, igra.naVrsti);
////					igramo ();
////				}
////			}
////		};
////		worker.execute();
////	}
//	
//	
//	public static void igrajClovekovoPotezo(Poteza poteza) {
//		if (clovekNaVrsti && igra.moznePoteze.contains(poteza)) {
//			igra.odigraj(poteza);
//			clovekNaVrsti = false;
//			igra.sprintajIgro();
//			igramo();
//		}
//	}
//	
//	// PREDZADNJE MOJE
////	public static void igrajClovekovoPotezo(Poteza poteza) {
////		if (igra.odigraj(poteza, igra.naVrsti)) clovekNaVrsti = false;
////		igra.sprintajIgro();
////		igramo ();
////	}
//
//	
//	
//	/// MOJE 1 
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetnaIgra = igra;
////		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () { // ************* ima Void, Void
//////		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () { // ************* ima Void, Void
////			@Override
////			protected Poteza doInBackground() {
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				System.out.println("Racunalnik je zgral potezo" + poteza);
////				// try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}; 
////				try {TimeUnit.MILLISECONDS.sleep(10);} catch (Exception e) {}; // ko testiram
////				return poteza;
////			}
////			@Override
////			protected void done () {
////				Poteza poteza = null;
////				try {poteza = get();} catch (Exception e) {};
////				if (igra == zacetnaIgra) {
////					igra.odigraj(poteza);
////					igramo();
////				}
////			}
////		};
////		worker.execute();
////	}
////	
//	
////	/// PREKOPIRANO
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetnaIgra = igra;
////		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () { // ************* ima Void, Void
////			@Override
////			protected Void doInBackground() {
////				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}; // ko testiram
////				return null;
////			}
////			@Override
////			protected void done () {
////				if (igra != zacetnaIgra) return;
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				igra.odigraj(poteza, igra.naVrsti);
////				igramo();
////			}
////		};
////		worker.execute();
////	}
////		
////	public static void igrajClovekovoPotezo(Poteza poteza) {
//////		boolean vrednost = igra.odigraj(poteza);
//////		if (vrednost) clovekNaVrsti = false;
////		igramo ();
////	}
//}
//

//////////////////////////7 TA PRAVIIII
//
//package vodja;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.SwingWorker;
//
//import GUI.Okno;
//import inteligenca.Alphabeta;
//import inteligenca.Inteligenca;
//import inteligenca.Minimax;
//import inteligenca.RandomIzbira;
////import inteligenca.RandomIzbira;
//import logika.Igra;
//import logika.Igralec;
//import logika.Par;
//import splosno.KdoIgra;
//import splosno.Poteza;
//
//public class Vodja {
//	
//	
//	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
//	public static Map<Igralec, KdoIgra> kdoIgra; // *************
//	public static Okno okno;
//	public static Igra igra = null;
//	public static boolean clovekNaVrsti = false;
//	
//	public static void igramoNovoIgro() {
//		igra = new Igra();
//		igramo();
//	}
//	
//	// MOJ predzadnji !!!!
//	public static void igramo() {
//		okno.osveziGUI();
//		switch (igra.stanje) {
//		case ZMAGA_CRNI: 
//		case ZMAGA_BELI: 
//		case NEODLOCENO: 
//			igra.sprintajIgro();
//			return; 
//		case V_TEKU: 
//			Igralec igralec = igra.naVrsti;
//			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
//			switch (vrstaNaPotezi) {
//			// če je na vrsti človek čakamo na njegovo potezo
//			case C: 
//				clovekNaVrsti = true;
//				break;
//			case R:
//				// sicer igramo računalnikovo potezo
//				igrajRacunalnikovoPotezo();
//				break;
//			}
//		}
//	}
//	
////	// MOJ predzadnji !!!!
////	public static void igramo() {
////		okno.osveziGUI();
////		switch (igra.stanje) {
////		case ZMAGA_CRNI, ZMAGA_BELI, NEODLOCENO: 
////			igra.sprintajIgro();
////			return;
////		case V_TEKU: 
////			Igralec igralec = igra.naVrsti;
////			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
////			switch (vrstaNaPotezi) {
////			// če je na vrsti človek čakamo na njegovo potezo
////			case C: 
////				clovekNaVrsti = true;
////				break;
////			case R:
////				// sicer igramo računalnikovo potezo
////				igrajRacunalnikovoPotezo();
////				break;
////			}
////		}
////	}
//	
////	public static Inteligenca racunalnikovaInteligenca = new Minimax(2);
////	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(1);
//	public static Inteligenca racunalnikovaInteligenca = new RandomIzbira("random");
//	
//	
//	// isto kot pri Capture.go.2
//	public static void igrajRacunalnikovoPotezo() {
//		Igra zacetnaIgra = igra;
//		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
//			@Override
//			protected Void doInBackground() {
//				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};	// za testiranje je boljše če je hitrješi
//				return null;
//			}
//			@Override
//			protected void done () {
//				if (igra != zacetnaIgra) return;
//				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
//				igra.odigraj(poteza);
//				igramo();	
//			}
//		};
//		worker.execute();	
//	}
//	
//	
////	// MOJE ISTO KOT PROFESORJEVO
////		public static void igrajRacunalnikovoPotezo() {
////			Igra zacetnaIgra = igra;
////			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
////				@Override
////				protected Void doInBackground() {
////					try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};
////					return null;
////				}
////				@Override
////				protected void done () {
////					if (igra != zacetnaIgra) return;
////					Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////					igra.odigraj(poteza, igra.naVrsti);
////					igra.sprintajIgro();
////					igramo ();
////					}
////			};
////			worker.execute();
////		}
////	
//	// MOJE ISTO KOT PROFESORJEVO
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetkaIgra = igra;
////		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
////			@Override
////			protected Poteza doInBackground() {
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};
////				return poteza;
////			}
////			@Override
////			protected void done () {
////				Poteza poteza = null;
////				try {poteza = get();} catch (Exception e) {};
////				if (igra == zacetkaIgra) {
////					igra.odigraj(poteza, igra.naVrsti);
////					igramo ();
////				}
////			}
////		};
////		worker.execute();
////	}
//	
//	
//	public static void igrajClovekovoPotezo(Poteza poteza) {
//		if (clovekNaVrsti && igra.moznePoteze.contains(poteza)) {
//			igra.odigraj(poteza);
//			clovekNaVrsti = false;
//			igra.sprintajIgro();
//			igramo();
//		}
//	}
//	
//	// PREDZADNJE MOJE
////	public static void igrajClovekovoPotezo(Poteza poteza) {
////		if (igra.odigraj(poteza, igra.naVrsti)) clovekNaVrsti = false;
////		igra.sprintajIgro();
////		igramo ();
////	}
//
//	
//	
//	/// MOJE 1 
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetnaIgra = igra;
////		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () { // ************* ima Void, Void
//////		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () { // ************* ima Void, Void
////			@Override
////			protected Poteza doInBackground() {
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				System.out.println("Racunalnik je zgral potezo" + poteza);
////				// try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}; 
////				try {TimeUnit.MILLISECONDS.sleep(10);} catch (Exception e) {}; // ko testiram
////				return poteza;
////			}
////			@Override
////			protected void done () {
////				Poteza poteza = null;
////				try {poteza = get();} catch (Exception e) {};
////				if (igra == zacetnaIgra) {
////					igra.odigraj(poteza);
////					igramo();
////				}
////			}
////		};
////		worker.execute();
////	}
////	
//	
////	/// PREKOPIRANO
////	public static void igrajRacunalnikovoPotezo() {
////		Igra zacetnaIgra = igra;
////		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () { // ************* ima Void, Void
////			@Override
////			protected Void doInBackground() {
////				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}; // ko testiram
////				return null;
////			}
////			@Override
////			protected void done () {
////				if (igra != zacetnaIgra) return;
////				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////				igra.odigraj(poteza, igra.naVrsti);
////				igramo();
////			}
////		};
////		worker.execute();
////	}
////		
////	public static void igrajClovekovoPotezo(Poteza poteza) {
//////		boolean vrednost = igra.odigraj(poteza);
//////		if (vrednost) clovekNaVrsti = false;
////		igramo ();
////	}
//}
//
/////////////////////////////////////////////////////
//


package vodja;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import GUI.Okno;
import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
import inteligenca.Minimax;
import inteligenca.RandomIzbira;
//import inteligenca.RandomIzbira;
import logika.Igra;
import logika.Igralec;
import logika.Par;
import splosno.KdoIgra;
import splosno.Poteza;

public class Vodja {
	
	
	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
	public static Map<Igralec, KdoIgra> kdoIgra;
	public static Okno okno;
	public static Igra igra = null;
	public static boolean clovekNaVrsti = false;
	
	public static void igramoNovoIgro () {
		igra = new Igra();
		igramo ();
	}
	
	public static void igramo() {
		okno.osveziGUI();
		switch (igra.stanje) {
		case ZMAGA_CRNI: 
		case ZMAGA_BELI: 
		case V_TEKU: 
			Igralec igralec = igra.naVrsti;
			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
			switch (vrstaNaPotezi) {
			case C: 
				clovekNaVrsti = true;
				break;
			case R:
				igrajRacunalnikovoPotezo();
				break;
			}
		}
		
	}
	

//	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(3);
	public static Inteligenca racunalnikovaInteligenca = new RandomIzbira("random");
	
	public static void igrajRacunalnikovoPotezo() {
		Igra zacetnaIgra = igra;
		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
			@Override
			protected Poteza doInBackground() {
				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};
				return poteza;
			}
			@Override
			protected void done () {
				Poteza poteza = null;
				try {poteza = get();} catch (Exception e) {};
				if (igra == zacetnaIgra) {
					igra.odigraj(poteza);
					igramo ();
				}
			}
		};
		worker.execute();
	}
		
	public static void igrajClovekovoPotezo(Poteza poteza) {
//		boolean vrednost = igra.odigraj(poteza);
//		if (vrednost) clovekNaVrsti = false;
		igramo ();
	}
}



