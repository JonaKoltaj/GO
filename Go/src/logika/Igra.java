package logika;

import java.util.HashSet;
import java.util.LinkedList;

import splosno.Poteza;

public class Igra {
	
	public static Plosca plosca;
	public Stanje stanje; 
	
	// TO SPREMENIM V EN SAM SEZNAM LINKED LIST
//	public Set<Poteza> moznePoteze; // beležimo poteze, ki so na voljo
//	public static ArrayList<Poteza> moznePotezeSeznam;
	public LinkedList<Poteza> moznePoteze;
	public LinkedList<Zeton> zetoniPoVrsti;
	public Igralec naVrsti; // kateri izmed igralcev mora narediti potezo
	public DisjointSet skupineBelih; // beležimo disjunktne množice belih žetonov
	public DisjointSet skupineCrnih; // popravi na protected
	protected LinkedList<Zeton> zajetaSkupina;
	


	public Igra() {
		plosca = new Plosca(9);
		stanje = Stanje.V_TEKU;
		moznePoteze = seznamMoznihPotez();
		zetoniPoVrsti = new LinkedList<Zeton>();
		// morda kasneje zbrišem
//		moznePotezeSeznam = arrayMoznihPotez();
		naVrsti = Igralec.CRNI;
		skupineBelih = new DisjointSet(Igralec.BELI);
		skupineCrnih = new DisjointSet(Igralec.CRNI);
		zajetaSkupina = null;
	}
//	
	// metoda, ki prekopira igro
		public static Igra kopirajIgro(Igra staraIgra) {
			Igra novaIgra = new Igra();
			int velikost = staraIgra.plosca.velikost;
//			novaIgra.naVrsti = igra.naVrsti;
//			novaIgra.stanje = igra.stanje;
//			novaIgra.skupineBelih = new DisjointSet(Igralec.BELI);
//			novaIgra.skupineCrnih = new DisjointSet(Igralec.CRNI);
			System.out.println("Kopiramo igro");
			// sedaj najprej na ploščo postavljamo žetone, ki so na stari plošči 
			// žetone bomo ustvarjali na novo in jih dodajali v disjunktne množice
			for (Zeton z : staraIgra.zetoniPoVrsti) {
				int x = z.i;
				int y = z.j;
				Igralec barvaZ = z.barva;
				novaIgra.odigraj(new Poteza(x, y), barvaZ);
			}
			
			novaIgra.jeKonec();
			return novaIgra;
			}
			
		public void sprintajIgro() {
			System.out.println("IGRA"); 
			plosca.izpis();
			System.out.println("Vrstni red žetonov: ");
			sprintajZ(zetoniPoVrsti);
			System.out.println("_________________________________________________________"); 
			System.out.println("Stanje igre je: " + stanje); 
			System.out.println("Na vrsti je: " + naVrsti); 
			System.out.println("----------------BELI------------------"); 
			skupineBelih.sprintajMnozico();
			System.out.println("----------------ČRNI------------------"); 
			skupineCrnih.sprintajMnozico();
			System.out.println("_________________________________________________________");
			System.out.println("Zajeta skupina je: ");
			sprintajZ(zajetaSkupina);
			System.out.println("_________________________________________________________");
			System.out.println("_________________________________________________________");
			
		}	
			
			
		
//			// pomožna funkcija za kopiranje
//			ArrayList<Zeton> kopijaZajetaSkupina = null;
//			if (igra.zajetaSkupina != null) {
//				kopijaZajetaSkupina = new ArrayList<Zeton>();
//				for (Zeton z: igra.zajetaSkupina) {kopijaZajetaSkupina.add(z);};
//			}
//			return novaIgra;
//		}
		
	

	// STARA
//	// metoda, ki prekopira igro
//	// TO JE NAROBE, MOREM POPRAVIT !!!
//	public Igra(Igra igra) {
//		this.plosca = Plosca.kopiraj(igra.plosca);
//		this.stanje = igra.stanje;
//		Set<Poteza> kopijaMoznePoteze = new HashSet<Poteza>();
//		kopijaMoznePoteze.addAll(igra.moznePoteze);
//		this.moznePoteze = kopijaMoznePoteze;
//		ArrayList<Poteza> kopijaMoznePotezeSeznam = new ArrayList<Poteza>();
//		for (Poteza p: igra.moznePotezeSeznam) {kopijaMoznePotezeSeznam.add(p);};
//		this.moznePotezeSeznam = kopijaMoznePotezeSeznam;
//		this.naVrsti = igra.naVrsti;
//		
//		DisjointSet kopijaSkupineBelih = DisjointSet.kopirajMnozico(igra.skupineBelih);
//		this.skupineBelih = kopijaSkupineBelih;
//		DisjointSet kopijaSkupineCrnih = DisjointSet.kopirajMnozico(igra.skupineCrnih);
//		this.skupineCrnih = kopijaSkupineCrnih;
//		
//		
//	
//		// pomožna funkcija za kopiranje
//		ArrayList<Zeton> kopijaZajetaSkupina = null;
//		if (igra.zajetaSkupina != null) {
//			kopijaZajetaSkupina = new ArrayList<Zeton>();
//			for (Zeton z: igra.zajetaSkupina) {kopijaZajetaSkupina.add(z);};
//		}
//	}
//	
	
	public LinkedList<Poteza> seznamMoznihPotez() {
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		int n = 9;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (plosca.mreza[i][j] == null) moznePoteze.add(new Poteza(i, j));
			}
		}
		return moznePoteze;
	}
	
//	// TI DVE METODI GRESTA STRAN ***************************
//	// metoda, ki iz množice možnih potez vrne seznam možnih potez 
//		public ArrayList<Poteza> arrayMoznihPotez() {
//			ArrayList<Poteza> seznam = new ArrayList<Poteza>();
//			seznam.addAll(seznamMoznihPotez());
//			return seznam;
//		}
//	
//	// metoda, ki vrne množico vseh možnih potez 
//	public Set<Poteza> seznamMoznihPotez() {
//		Set<Poteza> seznam = new HashSet<Poteza>();
//		for (int i = 0; i < 9; ++i) {
//			for (int j = 0; j < 9; ++j) {
//				if (plosca.mreza[i][j] == null) seznam.add(new Poteza(i, j));
//			}
//		}
//		return seznam;
//	}
//	//***************************
	
	
	
	// metoda, ki vrne barvo nasprotnega igralca - Sedaj to naredi nasprotnik()
	public static Igralec drugi(Igralec barva) {
		Igralec nasprotnik = Igralec.BELI; // v primeru, ko je sedaj igralec črni, bo nasprotnik beli
		if (barva == Igralec.BELI) nasprotnik = Igralec.CRNI;
			return nasprotnik;
		}
	
	// vrne seznam velikosti 4, ki v smeri urinega kazalca beleži sosede
	// element z indeksom 0 je tisti s koordinatami (i + 1, j)
	public static Zeton[] vrniSosede(Zeton z) {
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
	
	// ta metoda nič ne vrača, ampak updejta stanje v pripadajoči disjunktni množici
	// z uporabo razreda DisjointSet, ustavimo enojec in naredimo unijo s sosedi iste barve
	public void prikljuciSkupini(Zeton z) {
		Zeton[] sosedi = vrniSosede(z);
		boolean aliImaSoseda = false;
		for (Zeton s : sosedi) {if (s != null) { if (s.barva == z.barva) aliImaSoseda = true; }
		}
		// če nima nobenega soseda iste barve, bomo samo ustvarili enojec
		if (aliImaSoseda == false) {
			if (z.barva == Igralec.BELI) {
				skupineBelih.makeSet(z); 
				// to raje naredimo drugje
				//skupineBelih.predstavniki.add(z);
				}
			else {
				skupineCrnih.makeSet(z); 
				//skupineCrnih.predstavniki.add(z);
				}
			}
		// sicer naredimo unijo z vsemi sosedi iste barve
		else {
			for (Zeton s : sosedi) {
				if (s != null) { // preskočimo prazna polja
					if (s.barva == z.barva) {  
						if (z.barva == Igralec.BELI) {
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
	
	

	// STARA
		// metoda, ki vzame željeno potezo, jo odigra, če je to mogoče, ter vrne true,
		// če to ni mogoče, vrne false
//		public boolean odigraj(Poteza poteza) {
//			int i = poteza.x();
//			int j = poteza.y();
//			if (moznePoteze.contains(poteza)) {
//				Zeton novZeton = new Zeton(i, j, naVrsti); // ustvarimo nov žeton
//				System.out.println("Barva " + naVrsti + " na mesto: " +  "(" + novZeton.i + "," + novZeton.j + ")");
//				plosca.postaviZeton(novZeton);
//				prikljuciSkupini(novZeton);
//				moznePoteze.remove(poteza);
//				naVrsti = drugi(naVrsti);
//				return true;
//			}
//			else return false;
//		}
	
	// NOVA
	// metoda, ki vzame željeno potezo, jo odigra, če je to mogoče, ter vrne true,
	// če to ni mogoče, vrne false
	public boolean odigraj(Poteza poteza, Igralec barva) {
		int i = poteza.x();
		int j = poteza.y();
		if (moznePoteze.contains(poteza)) {
			Zeton novZeton = new Zeton(i, j, barva); 
			zetoniPoVrsti.add(novZeton);
			postavitevZetona(novZeton);
			moznePoteze.remove(poteza);
			naVrsti = drugi(barva);
			jeKonec();
			return true;
		}
		else return false;
		
	}
	
	// pomožna metoda, ki Zeton priključi pravi skupini in updejta Disjunktno množico
	public void postavitevZetona(Zeton z) {
		System.out.println("Barva " + naVrsti + " na mesto: " +  "(" + z.i + "," + z.j + ")");
		plosca.postaviZeton(z);
		prikljuciSkupini(z);
	}
	
	// STARA
	// metoda, ki sprejme seznam skupine žetonov in vrne prazen rob
	// t.j. mesta, ki so še prosta na robu skupine
//	public  HashSet<Par> vrniProsta(ArrayList<Zeton> seznam) {
//		HashSet<Par> sez = new HashSet<Par>();
//		int n = plosca.velikost;
//			for (Zeton z : seznam) {
//				Zeton[] sosediZetona = vrniSosede(z);
//				if (sosediZetona[0] == null & z.i - 1 >= 0) sez.add(new Par(z.i - 1, z.j));
//				if (sosediZetona[1] == null & z.j + 1 < n) sez.add(new Par(z.i, z.j + 1));
//				if (sosediZetona[2] == null & z.i + 1 < n) sez.add(new Par(z.i + 1, z.j));
//				// tu sem popravila >= ,prej ni bilo = ampak nisem opazila napake??
//				if (sosediZetona[3] == null & z.j - 1 >= 0) sez.add(new Par(z.i, z.j - 1));
//				}
//			return sez;
//	}
//	

	
//	
	// metoda, ki sprejme seznam skupine žetonov in vrne prazen rob
	// t.j. mesta, ki so še prosta na robu skupine
	public static  LinkedList<Par> vrniProsta(LinkedList<Zeton> seznam) {
		LinkedList<Par> sez = new LinkedList<Par>();
		int n = plosca.velikost;
			for (Zeton z : seznam) {
				Zeton[] sosediZetona = vrniSosede(z);
				if (sosediZetona[0] == null & z.i - 1 >= 0) sez.add(new Par(z.i - 1, z.j));
				if (sosediZetona[1] == null & z.j + 1 < n) sez.add(new Par(z.i, z.j + 1));
				if (sosediZetona[2] == null & z.i + 1 < n) sez.add(new Par(z.i + 1, z.j));
				// tu sem popravila >= ,prej ni bilo = ampak nisem opazila napake??
				if (sosediZetona[3] == null & z.j - 1 >= 0) sez.add(new Par(z.i, z.j - 1));
				}
			return sez;
	}
	
//	// STARA
//	public static Integer prestejProsteSosede(Zeton s) {
//		int v = 0;
//		int n = plosca.velikost;
//		ArrayList<Zeton> sosedi = DisjointSet.vrniSkupino(s);
//		for (Zeton z : sosedi) {
//			Zeton[] sosediZetona = vrniSosede(z);
//			if (sosediZetona[0] == null & z.i - 1 >= 0) v += 1;
//			if (sosediZetona[1] == null & z.j + 1 < n) v += 1;
//			if (sosediZetona[2] == null & z.i + 1 < n) v += 1;
//			if (sosediZetona[3] == null & z.j - 1 >= 0) v += 1;
//			}
//		return v;
//		}
	
	// NOVA
	public Integer prestejProsteSosede(Zeton s) {
		LinkedList<Zeton> sosedi = new LinkedList<Zeton>();
		if (s.barva == Igralec.BELI) {
			sosedi = skupineBelih.vrniSkupino(s);
		}
		else sosedi = skupineCrnih.vrniSkupino(s);
		LinkedList<Par> prostaSosescina = vrniProsta(sosedi);
		return prostaSosescina.size();
		}
		
	
	// metoda, ki updata stanje igre oz. spremeni stanje, ko se igra konča
	public void konec() {
		if (stanje == Stanje.V_TEKU) {
			// zapeljemo se po belih predstavnikih in pogledamo, če je kakšna skupina ujeta
			for (Zeton z: skupineBelih.predstavniki) {
				LinkedList<Par> sosedi = vrniProsta(skupineBelih.vrniSkupino(z));
				if (sosedi.isEmpty()) {
					stanje = Stanje.ZMAGA_CRNI;}
					zajetaSkupina = skupineBelih.vrniSkupino(z);}
			}
			for (Zeton z: skupineCrnih.predstavniki) {
				LinkedList<Par> sosedi = vrniProsta(skupineCrnih.vrniSkupino(z));
				if (sosedi.isEmpty()) {
				zajetaSkupina = skupineCrnih.vrniSkupino(z);
				stanje = Stanje.ZMAGA_BELI;}
			}
		}
		
	
		
	
	// vrne true ali false glede na to, ali igra še poteka
	// če je true, tudi sprinta rezultat - zaenkrat
	public boolean jeKonec() {
		boolean vrednost = false;
		if (stanje == Stanje.V_TEKU) vrednost = true;
		else System.out.println(zmagovalec());
		return vrednost;
	}
	
	// pomožna metoda, vrne string zmagovalca
			public String zmagovalec() {
				String zmagovalec = "";
				if (stanje == Stanje.ZMAGA_CRNI) zmagovalec = "Zmagovalec je Črni";
				if (stanje == Stanje.ZMAGA_BELI) zmagovalec = "Zmagovalec je Beli";
				if (stanje == Stanje.NEODLOCENO) zmagovalec = "Neodločeno";
				return zmagovalec;
			}
		
	// STARA
	// metoda, ki preveri ali je igre konec
	// šli bomo po vseh predstavnikih in pogledali, ali imajo vse skupine še kakšno prosto mesto
	// če kakšna skupina nima prostega roba, to pomeni, da jo je nasprotnik zajel, torej je igre konec
//	public boolean aliJeKonec() {
//		boolean igraVTeku = true;
//		for (Zeton z: skupineBelih.predstavniki) {
//			HashSet<Par> sosedi = vrniProsta(skupineBelih.vrniSkupino(z));
//			if (sosedi.isEmpty()) {
//				igraVTeku = false;
//				zajetaSkupina = skupineBelih.vrniSkupino(z);
//				stanje = Stanje.ZMAGA_CRNI;}
//		}
//		for (Zeton z: skupineCrnih.predstavniki) {
//			HashSet<Par> sosedi = vrniProsta(skupineCrnih.vrniSkupino(z));
//			if (sosedi.isEmpty()) {igraVTeku = false;
//			zajetaSkupina = skupineCrnih.vrniSkupino(z);
//			stanje = Stanje.ZMAGA_BELI;}
//		}
//		if (igraVTeku == false) konecIgre(); // samo izpis, kasneje izbrišem
//		return igraVTeku;
//	}
//	
//		// pomožna metoda, ki pove kdo je zmagal
//		public String zmagovalec() {
//			String zmagovalec = "";
//			if (stanje == Stanje.ZMAGA_CRNI) zmagovalec = "Črni";
//			if (stanje == Stanje.ZMAGA_BELI) zmagovalec = "Beli";
//			return zmagovalec;
//		}
//			
	
	
//		// metoda za izpis stanja igre - pomembna samo med testiranjem, kasneje izbrišem
//		public  void izpisStanja() {
//			plosca.izpis();
//			System.out.println("********************************************************************");
//			aliJeKonec();
//			System.out.println("********************************************************************");
//		}
	
//		// pomožna metoda za izpis rezultata, kasneje izbrišem
//		public static void sprintaj(ArrayList<Zeton> sez) {
//			for (Zeton z : sez) System.out.print(" " + z.barva + "(" + z.i+ " , " + z.j + ") ");
//			System.out.println("");
//		}
//		
//		// metoda, ki izpiše rezultat igre, kasneje izbrišem
//		public void konecIgre() {
//			System.out.println("IGRE JE KONEC!");
//			System.out.println("ZMAGAL JE IGRALEC BARVE: " + zmagovalec());
//			System.out.println("ZAJEL JE SKUPINO: ");
//			sprintaj(zajetaSkupina);
//		}
			
			// samo pomožna metoda za izpis, kasneje zbrišem
			public static void sprintajZ(LinkedList<Zeton> sez) {
				if (sez != null) {
					if (sez.isEmpty() == false) {
						System.out.print("[");
						for (Zeton z : sez) System.out.print(z);
						//for (Zeton z : sez) System.out.print(" " + z.barva + "(" + z.i+ " , " + z.j + ") ");
						System.out.print("]");
						System.out.println("");
					}
				}
			}
			
	}
