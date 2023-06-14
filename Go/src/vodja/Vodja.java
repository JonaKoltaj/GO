package vodja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import GUI.Okno;
import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igralec;
import logika.Zeton;
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
		igramo();
	}
	
	public static void igramo() {
		okno.osveziGUI();
		switch (igra.stanje) {
		case ZMAGA_CRNI: 
		case ZMAGA_BELI:
		case NEODLOCENO: 
			return; // odhajamo iz metode igramo
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
	

	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(4);
	
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
		igramo();
	}
	
	//metoda za shranjevanje igre
	public static void shrani(String ime) {
		try {
			PrintWriter dat = new PrintWriter(new FileWriter(ime));
			dat.print("Žetoni: ");
			for (Zeton z: igra.zetoniPoVrsti) {
				dat.print(z + " ");
			}
			dat.println();
			dat.println("NaVrsti: " + igra.naVrsti);
			dat.println("Beli: " + vrstaIgralca.get(Igralec.BELI));
			dat.println("Črni: " + vrstaIgralca.get(Igralec.CRNI));
			dat.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//metoda ki prebere igro nazaj v obliki "stZetonov(n) z1 ... zn navrsti beli crni"
	public Igra preberi(String ime) {
        try {
            BufferedReader dat = new BufferedReader(new FileReader(ime));
            int n = 0;
            String podatki = "";
            while (dat.ready()) {
                String vrstica = dat.readLine();
                if (!vrstica.equals("")) {
                	String[] besede = vrstica.trim().split(" ");
                	if (besede[0].equals("Žetoni:")) {
                		//v besede je n+1 elementov ("zetoni" , z1, ..., zn)
	                	n +=  besede.length - 1;
	                	podatki += Integer.toString(n) + " ";
                		for (int i = 1; i < n+1; i++) {
                			podatki += besede[i] + " ";
                		}
                	}
                	else if (besede[0].equals("NaVrsti:")) {
                		podatki += besede[1];
                	}
                	else if (besede[0].equals("Beli:")) {
                		podatki += besede[1];
                	}
                	else if (besede[0].equals("Črni:")) {
                		podatki += besede[1];
                	}
                }
            }
            //okej povleci zdej kar si mela v okno sem not in poglej ce deluje, da spremenis v igro pac
            dat.close();
            return podatki;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



