package logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DisjointSet {
	// podatkovna struktura DisjointSet bo beležila skupine kamenčkov iste barve, ki se dotikajo
	// vsaka taka skupina bo imela svojega predstavnika
	public  ArrayList<Zeton> vsebuje;
	public Igralec barva;
	public   Map<Zeton, Zeton> parent; // levo žeton, desno starš od žetona
	public Map<Zeton, Integer> rank;
	public ArrayList<Zeton> predstavniki;
	
	// pomožna funkcija, ki sprinta množico 
	public void sprintajMnozico() {
		System.out.println("Skupina barve " + barva + " vsebuje žetone: "); 
		sprintaj2(vsebuje);
		System.out.println("PREDSTAVNIKI skupine " + barva + " so: "); 
		sprintaj2(predstavniki);
		System.out.println("Tabela staršev " + barva + " je: "); 
		System.out.println(parent);
		System.out.println("Disjunktne množice so naslednje:  "); 
		for (Zeton z: predstavniki) {
			sprintaj(vrniSkupino(z));
		}
	}
	

	public DisjointSet(Igralec barva) {
		this.barva = barva;
		vsebuje = new ArrayList<>();
		parent = new HashMap<>();
		rank = new HashMap<>();
		predstavniki = new ArrayList<Zeton>();
	}
	

	
	// ustvarimo enojec, ki še ne obstaja
	public void makeSet(Zeton z) {
		if (z.barva == this.barva) {
			this.vsebuje.add(z);
			this.parent.put(z, z);
			this.rank.put(z, 0);
			this.predstavniki.add(z);}
		}
	
	

	// metoda najde prednika od žetona z
		public  Zeton find(Zeton z) {
			if (parent.get(z) == z) return z;
			else return find(parent.get(z));
		}
		
	
	public void union(Zeton z, Zeton w) {
		Zeton x = find(z);
		Zeton y = find(w); // to sta predstavnika množic
		int rx = rank.get(x);
		int ry = rank.get(y);
		if (x.equals(y) == false) {
			// System.out.println("Združili smo:" + "(" + z.i + " , " + z.j + ") + (" + w.i + " , " + w.j + ")");
			if (rx < ry) {parent.put(x, y);predstavniki.remove(x);}
			else if  (rx > ry) {parent.put(y, x); predstavniki.remove(y);}
			else {
				parent.put(y, x);
				predstavniki.remove(y);
				rank.put(x, rx + 1);
			} 
		}
	}
	
	
	
	// vrne seznam žetonov, ki so v isti skupini kot žeton z
		// popravim, da vrne skupino novih žetonov
	public LinkedList<Zeton> vrniSkupino(Zeton z) {
		LinkedList<Zeton> sez = new LinkedList<Zeton>();
		sez.add(z);
		for (Zeton s : vsebuje) {
				if (s != z) {
					Zeton starsS = find(s);
					Zeton starsZ = find(z);
					if (starsS.enaka(starsZ)) sez.add(s); 
			}
		}
		// ta del bo kasneje nepotreben
//		System.out.println("Disjuntkna množica, kateri pripada žeton" + "(" + z.i + " , " + z.j + ")" + " je: ");
//		sprintaj2(sez);
		return sez;

	}
	
	

		// samo pomožna metoda za izpis, kasneje zbrišem
		public static void sprintaj(LinkedList<Zeton> sez) {
			System.out.print("[");
			for (Zeton z : sez) System.out.print(z);
			//for (Zeton z : sez) System.out.print(" " + z.barva + "(" + z.i+ " , " + z.j + ") ");
			System.out.print("]");
			System.out.println("");
		}
		
	// samo pomožna metoda za izpis, kasneje zbrišem
	public static void sprintaj2(ArrayList<Zeton> sez) {
		System.out.print("[");
		for (Zeton z : sez) System.out.print(z);
		//for (Zeton z : sez) System.out.print(" " + z.barva + "(" + z.i+ " , " + z.j + ") ");
		System.out.print("]");
		System.out.println("");
		System.out.println("");
	}
	

}



