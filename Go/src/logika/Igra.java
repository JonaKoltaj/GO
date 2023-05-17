package logika;

import java.util.HashSet;
import java.util.LinkedList;

import splosno.Poteza;

public class Igra {
	
	public Plosca plosca;
	public Stanje stanje; 
	
	// TO SPREMENIM V EN SAM SEZNAM LINKED LIST
//	public Set<Poteza> moznePoteze; // beležimo poteze, ki so na voljo
//	public static ArrayList<Poteza> moznePotezeSeznam;
	public LinkedList<Poteza> moznePoteze;
	public LinkedList<Zeton> zetoniPoVrsti;
	public Igralec naVrsti; // kateri izmed igralcev mora narediti potezo
	public  DisjointSet skupineBelih; // beležimo disjunktne množice belih žetonov
	public  DisjointSet skupineCrnih; // popravi na protected
	protected LinkedList<Zeton> zajetaSkupina;
	

	public Igra() {
		plosca = new Plosca(9);
		stanje = Stanje.V_TEKU;
		moznePoteze = seznamMoznihPotez();
		zetoniPoVrsti = new LinkedList<Zeton>();
		naVrsti = Igralec.CRNI;
		skupineBelih = new DisjointSet(Igralec.BELI);
		skupineCrnih = new DisjointSet(Igralec.CRNI);
		zajetaSkupina = null;
	}

	
	
	// naredimo deep kopijo, popravi, da bo igralo v pravem vrstnem redu
	public Igra(Igra staraIgra) {
		plosca = new Plosca(9);
		stanje = Stanje.V_TEKU;
		moznePoteze = seznamMoznihPotez();
		zetoniPoVrsti = new LinkedList<Zeton>();
		naVrsti = Igralec.CRNI;
		skupineBelih = new DisjointSet(Igralec.BELI);
		skupineCrnih = new DisjointSet(Igralec.CRNI);
		zajetaSkupina = null;
		for (Zeton z: staraIgra.zetoniPoVrsti) {
			int i = z.i;
			int j = z.j;
			this.odigraj(new Poteza(i, j));
		}
		
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
	
	
	// metoda, ki vrne barvo nasprotnega igralca 
	public  Igralec drugi(Igralec barva) {
		Igralec nasprotnik = Igralec.BELI; // v primeru, ko je sedaj igralec črni, bo nasprotnik beli
		if (barva == Igralec.BELI) nasprotnik = Igralec.CRNI;
			return nasprotnik;
		}
	
	
	// vrne seznam velikosti 4, ki v smeri urinega kazalca beleži sosede
	// element z indeksom 0 je tisti s koordinatami (i + 1, j)
	public  Zeton[] vrniSosede(Zeton z) {
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
//		boolean aliImaSoseda = false;
//		for (Zeton s : sosedi) {if (s != null) { if (s.barva == z.barva) aliImaSoseda = true; }
//		}
		
		// če nima nobenega soseda iste barve, bomo samo ustvarili enojec
		// sicer združimo še s sosedi
		
		// ustvarimo enojec
		if (z.barva == Igralec.BELI) {skupineBelih.makeSet(z);}
		else if (z.barva == Igralec.CRNI) {skupineCrnih.makeSet(z);};
		
		
		// družimo s sosedi
//		if (aliImaSoseda == true) {
			for (Zeton s : sosedi) {
				if (s != null) { // preskočimo prazna polja
					if (s.barva == z.barva) {  
						if (z.barva == Igralec.BELI) {
							skupineBelih.union(z, s);
							}
						else if (z.barva == Igralec.CRNI)  {
							skupineCrnih.union(z, s);
						}
					}
				}
//			}
		}
	}
	
	

	
	// NOVA
	// metoda, ki vzame željeno potezo, jo odigra, če je to mogoče, ter vrne true,
	// če to ni mogoče, vrne false
	public boolean odigraj(Poteza poteza) {
		int i = poteza.x();
		int j = poteza.y();
		if (moznePoteze.contains(poteza)) {
			Zeton novZeton = new Zeton(i, j, naVrsti); 
			zetoniPoVrsti.add(novZeton);
			plosca.postaviZeton(novZeton);
			prikljuciSkupini(novZeton);
			moznePoteze.remove(poteza);
			naVrsti = drugi(naVrsti);
			konec();
			return true;
		}
		else return false;
		
	}
	


//	
	// metoda, ki sprejme seznam skupine žetonov in vrne prazen rob
	// t.j. mesta, ki so še prosta na robu skupine
	public   LinkedList<Par> vrniProsta(LinkedList<Zeton> seznam) {
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
	

	
	// NOVA
	public  Integer prestejProsteSosede(Zeton s) {
		LinkedList<Zeton> sosedi = new LinkedList<Zeton>();
		if (s.barva == Igralec.BELI) {
			sosedi = skupineBelih.vrniSkupino(s);
		}
		else if (s.barva == Igralec.CRNI) {sosedi = skupineCrnih.vrniSkupino(s);}
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
		
	
			
			// samo pomožna metoda za izpis, kasneje zbrišem
			public  void sprintajZ(LinkedList<Zeton> sez) {
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
