package logika;

import java.util.ArrayList;
import java.util.LinkedList;

import splosno.Poteza;

public class Igra {
	
	public int velikost;
	private Zeton[][] plosca;
	public Stanje stanje; 
	public LinkedList<Poteza> moznePoteze;
	public ArrayList<Zeton> zetoniPoVrsti; // za kopiranje igre beležimo vrstni red postavljenih žetonov
	public Igralec naVrsti; // kateri izmed igralcev mora narediti potezo
	public DisjointSet skupineBelih; // beležimo disjunktne množice belih žetonov
	public DisjointSet skupineCrnih; // beležimo disjunktne množice belih žetonov
	public Poteza obveznaPoteza; // obvezna poteza, za  tistega, ki je na vrsti
	protected LinkedList<Zeton> zajetaSkupina;
	
	
	public Zeton[][] ustvariMrezo(int n) {
		Zeton[][] plosca = new Zeton[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				plosca[i][j] = null;
			}
		}
		return plosca;
	}

	
	public Igra() {
		velikost = 9;
		plosca = ustvariMrezo(this.velikost);
		stanje = Stanje.V_TEKU;
		moznePoteze = seznamMoznihPotez();
		zetoniPoVrsti = new ArrayList<Zeton>();
		naVrsti = Igralec.CRNI;
		skupineBelih = new DisjointSet(Igralec.BELI);
		skupineCrnih = new DisjointSet(Igralec.CRNI);
		obveznaPoteza = null;
		zajetaSkupina = null;
	}

	
	public Zeton[][] getPlosca() {
		return plosca;
	}
	
	
	public LinkedList<Zeton> zajeti() {
		return zajetaSkupina;
	}
	
	
	// naredimo deep kopijo igre
	public Igra(Igra staraIgra) {
		velikost = 9;
		plosca = ustvariMrezo(this.velikost);
		stanje = Stanje.V_TEKU;
		moznePoteze = seznamMoznihPotez();
		zetoniPoVrsti = new ArrayList<Zeton>();
		naVrsti = Igralec.CRNI;
		skupineBelih = new DisjointSet(Igralec.BELI);
		skupineCrnih = new DisjointSet(Igralec.CRNI);
		obveznaPoteza = null;
		zajetaSkupina = null;
		for (Zeton z: staraIgra.zetoniPoVrsti) {
			int i = z.i;
			int j = z.j;
			this.odigraj(new Poteza(i, j));
		}
	}
		
	
	// Samo pomožna metoda za izpis igre - kasneje izbrišem	
	public void izpis(Zeton[][] mreza) {
		if (mreza == null) System.out.println("Prazna plošča");
		else {System.out.println("____________________________________________________________________");
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (mreza[i][j] == null) System.out.print(mreza[i][j] + "\t");
				else System.out.print(mreza[i][j].barva + "\t");
			}
			System.out.println();
		}
		System.out.println("____________________________________________________________________");
		System.out.println("");
		}
	}
	
	
	// metoda na mrežo postavi žeton
	public void postaviZeton(Zeton z) {
		plosca[z.i][z.j] = z;
	}
			
	
	public Zeton vrniZeton(int i, int j) {
		return plosca[i][j];
	}
	
			
	public void sprintajIgro() {
		System.out.println("IGRA"); 
		izpis(plosca);
		System.out.println("Vrstni red žetonov: ");
		for (Zeton z: zetoniPoVrsti) {
			System.out.print(z + " , ");
		}
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
		System.out.println("Obvezna poteza je :  " + "    " + obveznaPoteza + "     ");
		System.out.println("_________________________________________________________");
		System.out.println("_________________________________________________________");
	}	
	
			
	// uporabimo samo na začetku - vrne seznam vseh možnih potez
	public LinkedList<Poteza> seznamMoznihPotez() {
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		int n = 9;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (plosca[i][j] == null) moznePoteze.add(new Poteza(i, j));
			}
		}
		return moznePoteze;
	}
	
	
	// metoda, ki vrne barvo nasprotnega igralca 
	public Igralec drugi(Igralec barva) {
		Igralec nasprotnik = Igralec.BELI; 
		if (barva == Igralec.BELI) nasprotnik = Igralec.CRNI;
			return nasprotnik;
	}
	
	
	// vrne seznam velikosti 4, ki v smeri urinega kazalca beleži sosede
	// element z indeksom 0 je tisti s koordinatami (i + 1, j)
	public Zeton[] vrniSosede(Zeton z) {
		int n = plosca.length;
		Zeton[] seznamSosedov = {null, null, null, null};
		if (z == null) return seznamSosedov;
		else {
			if (z.i > 0 & z.i < n - 1 & z.j > 0 & z.j < n - 1) {
				seznamSosedov[0] = plosca[z.i - 1][z.j];
				seznamSosedov[1] = plosca[z.i][z.j + 1];
				seznamSosedov[2] = plosca[z.i + 1][z.j];
				seznamSosedov[3] = plosca[z.i][z.j - 1];
			}
			else if (z.i == 0 & z.j == 0) {
				seznamSosedov[1] = plosca[z.i][z.j + 1];
				seznamSosedov[2] = plosca[z.i + 1][z.j];
			}
			else if (z.i == n - 1 & z.j == n - 1) {
				seznamSosedov[0] = plosca[z.i - 1][z.j];
				seznamSosedov[3] = plosca[z.i][z.j - 1];
			}
			else if (z.i == 0 & z.j == n - 1) {
				seznamSosedov[2] = plosca[z.i + 1][z.j];
				seznamSosedov[3] = plosca[z.i][z.j - 1];
			}
			else if (z.i == n - 1 & z.j == 0) {
				seznamSosedov[0] = plosca[z.i - 1][z.j];
				seznamSosedov[1] = plosca[z.i][z.j + 1];
			}
			else if (z.i == 0 & z.j != 0 & z.j != n - 1) {
				seznamSosedov[1] = plosca[z.i][z.j + 1];
				seznamSosedov[2] = plosca[z.i + 1][z.j];
				seznamSosedov[3] = plosca[z.i][z.j - 1];
			} 
			else if (z.i == n - 1 & z.j != 0 & z.j != n - 1) {
				seznamSosedov[0] = plosca[z.i - 1][z.j];
				seznamSosedov[1] = plosca[z.i][z.j + 1];
				seznamSosedov[3] = plosca[z.i][z.j - 1];
			}
			else if (z.j == 0 & z.i != 0 & z.i != n - 1) {
				seznamSosedov[0] = plosca[z.i - 1][z.j];
				seznamSosedov[1] = plosca[z.i][z.j + 1];
				seznamSosedov[2] = plosca[z.i + 1][z.j];
			}
			else if (z.j == n - 1 & z.i != 0 & z.i != n - 1) {
				seznamSosedov[0] = plosca[z.i - 1][z.j];
				seznamSosedov[2] = plosca[z.i + 1][z.j];
				seznamSosedov[3] = plosca[z.i][z.j - 1];
			}
		return seznamSosedov;
		}
	}
	
	
	// STARA
	// ta metoda nič ne vrača, ampak updejta stanje v pripadajoči disjunktni množici, 
	// ko na polje postavimo žeton
	// z uporabo razreda DisjointSet, ustavimo enojec in naredimo unijo s sosedi iste barve
	public void prikljuciSkupini(Zeton z) {
		Zeton[] sosedi = vrniSosede(z);
		// ustvarimo enojec
		if (z.barva == Igralec.BELI) skupineBelih.makeSet(z);
		else if (z.barva == Igralec.CRNI) skupineCrnih.makeSet(z);
		// združimo s sosedi
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
		}
	}
	
	
	// metoda, ki vzame željeno potezo, jo odigra, če je to mogoče, ter vrne true,
	// če to ni mogoče, vrne false
	public boolean odigraj(Poteza poteza) {
		int i = poteza.x();
		int j = poteza.y();
		if (moznePoteze.contains(poteza)) {
			Zeton novZeton = new Zeton(i, j, naVrsti); 
			zetoniPoVrsti.add(novZeton);
			postaviZeton(novZeton);
			prikljuciSkupini(novZeton);
			moznePoteze.remove(poteza);
			naVrsti = drugi(naVrsti);
			konec();
			obveznaPoteza();
			return true;
		}
		else return false;	
	}
	
	
	// metoda, ki sprejme seznam skupine žetonov in vrne prazen rob
	// t.j. vrne liberties
	public LinkedList<Par> vrniProsta(LinkedList<Zeton> seznam) {
		LinkedList<Par> sez = new LinkedList<Par>();
		int n = plosca.length;
			for (Zeton z : seznam) {
				Zeton[] sosediZetona = vrniSosede(z);
				if (sosediZetona[0] == null & z.i - 1 >= 0) sez.add(new Par(z.i - 1, z.j));
				if (sosediZetona[1] == null & z.j + 1 < n) sez.add(new Par(z.i, z.j + 1));
				if (sosediZetona[2] == null & z.i + 1 < n) sez.add(new Par(z.i + 1, z.j));
				if (sosediZetona[3] == null & z.j - 1 >= 0) sez.add(new Par(z.i, z.j - 1));
			}
		return sez;
	}
	
	
	public Integer prestejProsteSosede(Zeton s) {
		LinkedList<Zeton> sosedi = new LinkedList<Zeton>();
		if (s.barva == Igralec.BELI) {
			sosedi = skupineBelih.vrniSkupino(s);
		}
		else if (s.barva == Igralec.CRNI) sosedi = skupineCrnih.vrniSkupino(s);
		LinkedList<Par> prostaSosescina = vrniProsta(sosedi);
		return prostaSosescina.size();
	}
		
		
	
	// metoda, ki updata stanje igre oz. spremeni stanje, ko se igra konča
	public void konec() {
		for (Zeton z: skupineBelih.predstavniki) {
			LinkedList<Par> sosedi = vrniProsta(skupineBelih.vrniSkupino(z));
			if (sosedi.isEmpty()) {
				stanje = Stanje.ZMAGA_CRNI; zajetaSkupina = skupineBelih.vrniSkupino(z);
			}
		}
		for (Zeton z: skupineCrnih.predstavniki) {
			LinkedList<Par> sosedi = vrniProsta(skupineCrnih.vrniSkupino(z));
			if (sosedi.isEmpty()) {
				zajetaSkupina = skupineCrnih.vrniSkupino(z);
				stanje = Stanje.ZMAGA_BELI;
			}
		}
		if (stanje == Stanje.V_TEKU && moznePoteze.isEmpty()) stanje = Stanje.NEODLOCENO;
	} 
		
	
	// metoda, ki preveri ali mora igralec, ki je na vrsti odigrati kakšno obvezno potezo, da se zaščiti ali da zmaga
	public void obveznaPoteza() {
		Par p = null;
		DisjointSet skupina1 = skupineCrnih;
		if (naVrsti == Igralec.BELI) skupina1 = skupineBelih;
		// najprej preveri ali se mora v naslednji potezi zaščititi
		for (Zeton z: skupina1.predstavniki) {
			if (prestejProsteSosede(z) == 1) {
				p = vrniProsta(skupina1.vrniSkupino(z)).get(0);
			}
		}
		
		// potem preveri ali lahko v naslednji potezi zmaga
		DisjointSet skupina2 = skupineBelih;
		if (naVrsti == Igralec.BELI) skupina2 = skupineCrnih;
		for (Zeton z: skupina2.predstavniki) {
			if (prestejProsteSosede(z) == 1) {
				p = vrniProsta(skupina2.vrniSkupino(z)).get(0);
			}
		}
		
		Poteza pot = null;
		if (p != null) pot = new Poteza(p.i, p.j);
		obveznaPoteza = pot;
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
				System.out.print("]");
				System.out.println("");
			}
		}
	}

}
