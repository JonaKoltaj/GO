package logika;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import splosno.Poteza;

public class Igra {
	
	public Plosca plosca;
	protected boolean stanje; // true, če igra ni končana in false sicer
	protected Set<Poteza> moznePoteze; // beležimo poteze, ki so na voljo
	protected String naVrsti; // kateri izmed igralcev mora narediti potezo
	protected ArrayList<ArrayList<Zeton>> skupineBelih;
	protected ArrayList<ArrayList<Zeton>> skupineCrnih;
	
	

	public Igra() {
		plosca = new Plosca(9);
		stanje = true;
		moznePoteze = seznamMoznihPotez();
		naVrsti = "Black";
		skupineBelih = new ArrayList<ArrayList<Zeton>>();
		skupineCrnih = new ArrayList<ArrayList<Zeton>>();
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
	
	// metoda, ki vzame željeno potezo, jo odigra, če je to mogoče, ter vrne true,
	// če to ni mogoče, vrne false
	public boolean odigraj(Poteza poteza) {
		int i = poteza.x();
		int j = poteza.y();
		if (moznePoteze.contains(poteza)) {
			Zeton novZeton = new Zeton(i, j, naVrsti);
			plosca.postaviZeton(novZeton);
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
		}
	}
	
	// metoda, ki vrne skupine določene barve
	
	
	// metoda, ki vrne "Liberty" vsake od skupin določene barve
	
	// metoda, ki preveri ali je igre konec
	public static void jeKonec() {
	}
}
