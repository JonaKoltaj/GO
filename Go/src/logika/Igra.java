package logika;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import inteligenca.RandomIzbira;
import splosno.Poteza;

public class Igra {
	
	public Plosca plosca;
	public boolean stanje; // true, če igra ni končana in false sicer
	protected Set<Poteza> moznePoteze; // beležimo poteze, ki so na voljo
	public static ArrayList<Poteza> moznePotezeSeznam;
	public String naVrsti; // kateri izmed igralcev mora narediti potezo
	protected DisjointSet skupineBelih; // beležimo disjunktne množice belih žetonov
	protected DisjointSet skupineCrnih; // popravi na protected
	public String zmagovalec;
	protected ArrayList<Zeton> zajetaSkupina;
	

	public Igra() {
		plosca = new Plosca(9);
		stanje = true;
		moznePoteze = seznamMoznihPotez();
		moznePotezeSeznam = arrayMoznihPotez();
		naVrsti = "Black";
		skupineBelih = new DisjointSet("White");
		skupineCrnih = new DisjointSet("Black");
		zmagovalec = null;
		zajetaSkupina = null;
	}
	
	// metoda, ki iz množice možnih potez vrne seznam možnih potez 
		public ArrayList<Poteza> arrayMoznihPotez() {
			ArrayList<Poteza> seznam = new ArrayList<Poteza>();
			seznam.addAll(seznamMoznihPotez());
			return seznam;
		}
	
	// metoda, ki vrne množico vseh možnih potez 
	public Set<Poteza> seznamMoznihPotez() {
		Set<Poteza> seznam = new HashSet<Poteza>();
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (plosca.mreza[i][j] == null) seznam.add(new Poteza(i, j));
			}
		}
		return seznam;
	}
	
	// metoda, ki vrne barvo nasprotnega igralca
	public String drugi(String barva) {
		if (barva == "White") return "Black";
		else return "White";
	}
	
	// vrne seznam velikosti 4, ki v smeri urinega kazalca beleži sosede
	// element z indeksom 0 je tisti s koordinatami (i + 1, j)
	public Zeton[] vrniSosede(Zeton z) {
		int n = plosca.velikost;
		Zeton[] seznamSosedov = {null, null, null, null};
		if (z == null) return seznamSosedov;
		else {
		if (z.i > 0 & z.i < n - 1 & z.j > 0 & z.j < n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.i == 0 & z.j == 0) {
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i + 1][z.j];
		}
		else if (z.i == n - 1 & z.j == n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.i == 0 & z.j == n - 1) {
			seznamSosedov[2] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.i == n - 1 & z.j == 0) {
			seznamSosedov[0] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
		}
		else if (z.i == 0 & z.j != 0 & z.j != n - 1) {
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		} 
		else if (z.i == n - 1 & z.j != 0 & z.j != n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.j == 0 & z.i != 0 & z.i != n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i + 1][z.j];
		}
		else if (z.j == n - 1 & z.i != 0 & z.i != n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[2] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		return seznamSosedov;
		}
	}
	
	
	// z uporabo razreda DisjointSet, ustavimo enojec in naredimo unijo s sosedi iste barve
	public void prikljuciSkupini(Zeton z) {
		Zeton[] sosedi = vrniSosede(z);
		boolean aliImaSoseda = false;
		for (Zeton s : sosedi) {if (s != null) { if (s.barva == z.barva) aliImaSoseda = true; }
		}
		// če nima nobenega soseda iste barve, bomo samo ustvarili enojec
		if (aliImaSoseda == false) {
			if (z.barva == "White") {
				skupineBelih.makeSet(z); 
				skupineBelih.predstavniki.add(z);
				}
			else {
				skupineCrnih.makeSet(z); 
				skupineCrnih.predstavniki.add(z);
				}
			}
		// sicer naredimo unijo z vsemi sosedi iste barve
		else {
			for (Zeton s : sosedi) {
				if (s != null) { // preskočimo prazna polja
					if (s.barva == z.barva) {  
						if (z.barva == "White") {
							skupineBelih.makeSet(z); 
							skupineBelih.union(z, s);
							}
						else {
							skupineCrnih.makeSet(z); 
							skupineCrnih.union(z, s);
						}
					}
				}
			}
		}
	}
	
	// pomožna metoda, ki bo odigrala naključno 
	// kasneje zbrišem
	// tukej sm ti samo spremenila da tudi racunalnik vrne boolean vrednost, kr rabim pri platnu! - Jona
	public boolean igrajRacunalnik() {
		Poteza p = RandomIzbira.izberiNakljucno();
		boolean mozno = odigraj(p);
		return mozno;
	}
	
	
	// metoda, ki vzame željeno potezo, jo odigra, če je to mogoče, ter vrne true,
	// če to ni mogoče, vrne false
	public boolean odigraj(Poteza poteza) {
		int i = poteza.x();
		int j = poteza.y();
		if (moznePoteze.contains(poteza)) {
			Zeton novZeton = new Zeton(i, j, naVrsti);
			System.out.println("Barva " + naVrsti + " na mesto: " +  "(" + novZeton.i + "," + novZeton.j + ")");
			plosca.postaviZeton(novZeton);
			prikljuciSkupini(novZeton);
			moznePoteze.remove(poteza);
			naVrsti = drugi(naVrsti);
			return true;
		}
		else return false;
	}
	
	
	
	// metoda, ki sprejme seznam skupine žetonov in vrne prazen rob
	// t.j. mesta, ki so še prosta na robu skupine
	public  HashSet<Par> vrniProsta(ArrayList<Zeton> seznam) {
		HashSet<Par> sez = new HashSet<Par>();
		int n = plosca.velikost;
			for (Zeton z : seznam) {
				Zeton[] sosediZetona = vrniSosede(z);
				if (sosediZetona[0] == null & z.i - 1 >= 0) sez.add(new Par(z.i - 1, z.j));
				if (sosediZetona[1] == null & z.j + 1 < n) sez.add(new Par(z.i, z.j + 1));
				if (sosediZetona[2] == null & z.i + 1 < n) sez.add(new Par(z.i + 1, z.j));
				if (sosediZetona[3] == null & z.j - 1 > 0) sez.add(new Par(z.i, z.j - 1));
				}
			return sez;
	}

	
	// metoda, ki preveri ali je igre konec
	// šli bomo po vseh predstavnikih in pogledali, ali imajo vse skupine še kakšno prosto mesto
	// če kakšna skupina nima prostega roba, to pomeni, da jo je nasprotnik zajel, torej je igre konec
	public boolean aliJeKonec() {
		boolean igraVTeku = true;
		for (Zeton z: skupineBelih.predstavniki) {
			HashSet<Par> sosedi = vrniProsta(skupineBelih.vrniSkupino(z));
			if (sosedi.isEmpty()) {
				igraVTeku = false;
				zmagovalec = drugi(z.barva);
				zajetaSkupina = skupineBelih.vrniSkupino(z);
				stanje = false;}
		}
		for (Zeton z: skupineCrnih.predstavniki) {
			HashSet<Par> sosedi = vrniProsta(skupineCrnih.vrniSkupino(z));
			if (sosedi.isEmpty()) {igraVTeku = false;
			zmagovalec = drugi(z.barva);
			zajetaSkupina = skupineCrnih.vrniSkupino(z);
			stanje = false;}
		}
		if (igraVTeku == false) konecIgre(); // samo izpis, kasneje izbrišem
		return igraVTeku;
	}
	
	
	
		// metoda za izpis stanja igre - pomembna samo med testiranjem, kasneje izbrišem
		public  void izpisStanja() {
			plosca.izpis();
			System.out.println("********************************************************************");
			aliJeKonec();
			System.out.println("********************************************************************");
		}
	
		// pomožna metoda za izpis rezultata, kasneje izbrišem
		public static void sprintaj(ArrayList<Zeton> sez) {
			for (Zeton z : sez) System.out.print(" " + z.barva + "(" + z.i+ " , " + z.j + ") ");
			System.out.println("");
		}
		
		// metoda, ki izpiše rezultat igre, kasneje izbrišem
		public void konecIgre() {
			System.out.println("IGRE JE KONEC!");
			System.out.println("ZMAGAL JE IGRALEC BARVE: " + zmagovalec);
			System.out.println("ZAJEL JE SKUPINO: ");
			sprintaj(zajetaSkupina);
		}
}
