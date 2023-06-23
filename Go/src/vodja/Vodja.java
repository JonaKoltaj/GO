package vodja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	//spodnji dve metodi sta morali biti v vodja, ker zapisujeta se kdo je racunalnik in kdo clovek
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
			dat.print("Črni: " + vrstaIgralca.get(Igralec.CRNI));
			dat.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//metoda ki prebere igro najprej nazaj v obliki "stZetonov(n) z1 ... zn navrsti beli crni"
	public static void preberi(String ime) {
        try {
            BufferedReader dat = new BufferedReader(new FileReader(ime));
            int n = 0;
            String podatkiNiz = "";
            while (dat.ready()) {
                String vrstica = dat.readLine();
                if (!vrstica.equals("")) {
                	String[] besede = vrstica.trim().split(" ");
                	if (besede[0].equals("Žetoni:")) {
                		//v besede je n+1 elementov ("zetoni" , z1, ..., zn)
	                	n +=  besede.length - 1;
	                	podatkiNiz += Integer.toString(n) + " ";
                		for (int i = 1; i < n+1; i++) podatkiNiz += besede[i] + " ";
                	}
                	else if (besede[0].equals("NaVrsti:")) podatkiNiz += besede[1] + " ";
                	else if (besede[0].equals("Beli:")) podatkiNiz += besede[1] + " ";
                	else if (besede[0].equals("Črni:")) podatkiNiz += besede[1];
                }
            }
            System.out.println(podatkiNiz);
            dat.close();
            //potem pa podatke spremenimo v obliko Igra
            //spodnje so podatki oblike [n, z1, ..., zn, navrsti, beli, crni]
            igra = new Igra();
            String[] podatki = podatkiNiz.split(" ");
			if (podatki.length != 0) {
				//tukaj pisem st namesto n, ker imam n ze
				int st = Integer.parseInt(podatki[0]);
				for (int k = 1; k < st+1; k++) {
					String zeton = podatki[k];
					String pattern = "(.)\\((.),(.)";
					Pattern r = Pattern.compile(pattern);
					Matcher m = r.matcher(zeton);
					if (m.find()) {
						String barva = m.group(1);
						if (barva.equals("B")) igra.naVrsti = Igralec.BELI;
						if (barva.equals("C")) igra.naVrsti = Igralec.CRNI;
						int i = Integer.parseInt(m.group(2));
						int j = Integer.parseInt(m.group(3));
						igra.odigraj(new Poteza(i, j));
					}
				}
				String naVrsti = podatki[st+1];
				if (naVrsti.equals("Black")) igra.naVrsti = Igralec.CRNI;
				else if (naVrsti.equals("White")) igra.naVrsti = Igralec.BELI;
				vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
				kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
				String beli = podatki[st+2];
				if (beli.equals("človek")) {
					vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C);
					kdoIgra.put(Igralec.BELI, new KdoIgra("UserBeli")); 
				}
				else if (beli.equals("řačunalnik")) {
					vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R);
					kdoIgra.put(Igralec.BELI, racunalnikovaInteligenca); 
				}
				String crni = podatki[st+3];
				if (crni.equals("človek")) {
					vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C);
					kdoIgra.put(Igralec.CRNI, new KdoIgra("UserCrni"));
				}
				else if (crni.equals("řačunalnik")) {
					vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R);
					kdoIgra.put(Igralec.CRNI, racunalnikovaInteligenca);
				}
				igramo();
			}
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



