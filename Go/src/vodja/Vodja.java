package vodja;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import GUI.Okno;
import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
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
		// okno.osveziGUI();
		switch (igra.stanje()) {
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
	
	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(9);
	
	public static void igrajRacunalnikovoPotezo() {
		Igra zacetnaIgra = igra;
		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
			@Override
			protected Poteza doInBackground() {
				Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
				try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
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
		if (igra.odigraj(poteza)) clovekNaVrsti = false;
		igramo ();
	}
}
