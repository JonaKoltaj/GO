package logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DisjointSet {
	// podatkovna struktura DisjointSet bo beležila skupine kamenčkov iste barve, ki se dotikajo
	// vsaka taka skupina bo imela svojega predstavnika
	protected static ArrayList<Zeton> vsebuje;
	protected Igralec barva;
	protected static Map<Zeton, Zeton> parent; // levo žeton, desno starš od žetona
	protected Map<Zeton, Integer> rank;
	public ArrayList<Zeton> predstavniki;
	public static Map<Zeton, Integer> steviloProstihSosedov;
	
	

	public DisjointSet(Igralec barva) {
		this.barva = barva;
		vsebuje = new ArrayList<>();
		parent = new HashMap<>();
		rank = new HashMap<>();
		predstavniki = new ArrayList<Zeton>();
		steviloProstihSosedov = new HashMap<>();
	}
	
	
	// ustvarimo enojec, ki še ne obstaja
	public void makeSet(Zeton z) {
		if (z.barva == this.barva) vsebuje.add(z);
		parent.put(z, z);
		rank.put(z, 0);
		
		}
	
	
	// metoda najde prednika od žetona z
	public static Zeton find(Zeton z) {
		if (parent.get(z) == z) return z;
		else return find(parent.get(z));
	}
	
	// združi obstoječi množici, ki jima pripadata žetona z in w,, če sta ti množici disjunktni 
	public void union(Zeton z, Zeton w) {
		Zeton x = find(z);
		Zeton y = find(w); // to sta predstavnika množic
		int rx = rank.get(x);
		int ry = rank.get(y);
		if (x.equals(y) == false) {
			// System.out.println("Združili smo:" + "(" + z.i + " , " + z.j + ") + (" + w.i + " , " + w.j + ")");
			if (rx < ry) parent.put(x, y);
			else if  (rx > ry) parent.put(y, x);
			else {
				parent.put(y, x);
				rank.put(x, rx + 1);
			} 
		}
	}
	
	
	// vrne seznam žetonov, ki so v isti skupini kot žeton z
	public static ArrayList<Zeton> vrniSkupino(Zeton z) {
		ArrayList<Zeton> sez = new ArrayList<Zeton>();
		sez.add(z);
		for (Zeton s : vsebuje) {
			if (s != z) {
				Zeton starsS = find(s);
				Zeton starsZ = find(z);
				if (starsS.enaka(starsZ)) sez.add(s); 
			}
		}
		// ta del bo kasneje nepotreben
		System.out.println("Disjuntkna množica, kateri pripada žeton" + "(" + z.i + " , " + z.j + ")" + " je: ");
		sprintaj2(sez);
		return sez;
		
	}
	
	// samo pomožna metoda za izpis, kasneje zbrišem
	public static void sprintaj2(ArrayList<Zeton> sez) {
		System.out.print("[");
		for (Zeton z : sez) System.out.print(" " + z.barva + "(" + z.i+ " , " + z.j + ") ");
		System.out.print("]");
		System.out.println("");
		System.out.println("");
	}
	

}
