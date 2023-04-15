package logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DisjointSet {
	
	protected ArrayList<Zeton> vsebuje;
	protected String barva;
	protected Map<Zeton, Zeton> parent;
	protected Map<Zeton, Integer> rank;
	
	
	// konstruiramo strukturo disjunktnih množic za določeno barvo
	public DisjointSet(String barva) {
		this.barva = barva;
		vsebuje = new ArrayList<>();
		parent = new HashMap<>();
		rank = new HashMap<>();
	}
	
	// ustvarimo enojec, ki ga še ni nikjer
	public void makeSet(Zeton z) {
		if (z.barva == this.barva) {
		vsebuje.add(z);
		parent.put(z, z);
		rank.put(z, 0);
		}
	}
	
	// najde prednika od žetona z
	public Zeton find(Zeton z) {
		if (parent.get(z) == z) return z;
		else return find(parent.get(z));
	}
	
	// združi obstoječi množici, ki sta disjunktni in vsebujeta z in w
	public void union(Zeton z, Zeton w) {
		Zeton x = find(z);
		Zeton y = find(w); // to sta predstavnika množic
		int rx = rank.get(x);
		int ry = rank.get(y);
		if (x.equals(y) == false) {
			if (rx < ry) parent.put(x, y);
			else if  (rx > ry) parent.put(y, x);
			else {
				parent.put(y, x);
				rank.put(x, rx + 1);
			} 
		}
	}
	
	public void izpisStarsev() {
		System.out.println("Starši so: ");
		for (Zeton z: parent.keySet()) {
			Zeton s = parent.get(z);
			String k = z.barva + " (" + z.i + " , " + z.j + " )"; 
			if (s != null) {
			String v = s.barva + " (" + s.i + " , " + s.j + " )"; 
			System.out.println(k + ": " + v);
			}
		}
				
	}
	
	public void izpisRanka() {
		System.out.println("Rang je: ");
		for (Zeton z: rank.keySet()) {
			String k = z.barva + " (" + z.i + " , " + z.j + " )"; 
			int s = rank.get(z);
			System.out.println(k + ": " + s);
			
		}
				
	}
	

}
