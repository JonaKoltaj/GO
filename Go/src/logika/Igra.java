package logika;

import java.util.HashSet;
import java.util.Map;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import splosno.Poteza;

public class Igra {
	
	public Plosca plosca;
	protected boolean stanje; // true, če igra ni končana in false sicer
	protected Set<Poteza> moznePoteze; // beležimo poteze, ki so na voljo
	protected String naVrsti; // kateri izmed igralcev mora narediti potezo
	protected DisjointSet skupineBelih; // skupina zetonov iste barve, ki tvorijo celoto
	protected DisjointSet skupineCrnih;
	

	public Igra() {
		plosca = new Plosca(9);
		stanje = true;
		moznePoteze = seznamMoznihPotez();
		naVrsti = "Black";
		skupineBelih = new DisjointSet("White");
		skupineCrnih = new DisjointSet("Black");
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
	public static String drugi(String barva) {
		if (barva == "White") return "Black";
		else return "White";
	}
	
	// ne vem, ali bo ta metoda sploh uporabna
	public Zeton[] vrniSosede(Zeton z) {
		int n = plosca.velikost;
		Zeton[] seznamSosedov = {null, null, null, null};
		if (z == null) return seznamSosedov;
		else {
		if (z.i > 0 & z.i < n - 1 & z.j > 0 & z.j < n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.i == 0 & z.j == 0) {
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i - 1][z.j];
		}
		else if (z.i == n - 1 & z.j == n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.i == 0 & z.j == n - 1) {
			seznamSosedov[2] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.i == n - 1 & z.j == 0) {
			seznamSosedov[0] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
		}
		else if (z.i == 0 & z.j != 0 & z.j != n - 1) {
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		} 
		else if (z.i == n - 1 & z.j != 0 & z.j != n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		else if (z.j == 0 & z.i != 0 & z.i != n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[1] = plosca.mreza[z.i][z.j + 1];
			seznamSosedov[2] = plosca.mreza[z.i - 1][z.j];
		}
		else if (z.j == n - 1 & z.i != 0 & z.i != n - 1) {
			seznamSosedov[0] = plosca.mreza[z.i + 1][z.j];
			seznamSosedov[2] = plosca.mreza[z.i - 1][z.j];
			seznamSosedov[3] = plosca.mreza[z.i][z.j - 1];
		}
		return seznamSosedov;
		}
	}
	
	// z uporabo razreda DisjoinSet, žeton z priključimo skupini svoje barve
	// tukaj predpostavimo, da ta korak naredimo natanko enkrat, torej ne more se zgoditi, da bi žeton že bil v množici
	public void prikljuciSkupini(Zeton z) {
		Zeton[] sosedi = vrniSosede(z);
		boolean aliImaSoseda = false;
		for (Zeton s : sosedi) {if (s != null) { if (s.barva == z.barva) aliImaSoseda = true; }
		}
		
		// če nima soseda iste barve, bomo uporabili makeSet in ustvarili enojec
		if (aliImaSoseda == false) {
			if (z.barva == "White") skupineBelih.makeSet(z);
			else skupineCrnih.makeSet(z);
			}
		
		// sicer najdemo prvega soseda iste barve in naredimo unijo
		else {
			int i = 0;
			boolean nadaljuj = true;
			while (nadaljuj & i != 4) {
				if (sosedi[i] != null) {
					// če se barvi ujemata
					if (sosedi[i].barva == z.barva) {
						if (z.barva == "White") {skupineBelih.makeSet(z); skupineBelih.union(z, sosedi[i]);}
						else {skupineCrnih.makeSet(z); skupineCrnih.union(z, sosedi[i]);}
						nadaljuj = false;
						}
					
					// če se barvi ne ujemata, gremo naprej
					else ++i;
				}
				else ++i;
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
			plosca.postaviZeton(novZeton);
			// ko postavimo žeton, preverimo njegove sosede. Če je na meji s kakšnim, ga damo v pripadajočo skupino,
			// sicer ustvarimo novo skupino
			// to naredi pomožna metoda prikljuciSkupini
			prikljuciSkupini(novZeton);
			moznePoteze.remove(poteza);
			naVrsti = drugi(naVrsti);
			return true;
		}
		else return false;
	}
	
	// metoda za izpis stanja igre - pomembna samo med testiranjem, kasneje izbrišem
	public  void izpisStanja() {
		if (stanje == false) {System.out.println("Igre je konec. Zmagal je: " + drugi(naVrsti));
		plosca.izpis();}
		else {
		System.out.println("Na vrsti je igralec: " + naVrsti);
		plosca.izpis();
		System.out.println("Disjunktne unije belih so");
		skupineBelih.izpisStarsev();
		skupineBelih.izpisRanka();
		System.out.println("Disjunktne unije črnih so");
		skupineCrnih.izpisStarsev();
		skupineCrnih.izpisRanka();
		}
	}
	
	
	// metoda, ki preveri ali je igre konec
	public static void aliJeKonec() {
		// metoda se bo sprehodila po "robu" od vsake skupine in preverila ali je skupina ujeta
	}
}
