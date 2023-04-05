package logika;

import java.util.HashSet;

public class Plosca {
	// ploščo predstavimo z n x n matriko
		protected int velikost;
		protected String[][] mreza;
		
		
		public Plosca(int n) {
			velikost = n;
			mreza = new String[n][n];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					mreza[i][j] = null;
				}
			}
		}
		
		public void izpis() {
			if (mreza == null) System.out.println("Prazna plošča");
			else {
			for (int i = 0; i < velikost; ++i) {
						for (int j = 0; j < velikost; ++j) System.out.print(mreza[i][j] + "\t");
						System.out.println();
					}
				}
		}
		
		public HashSet<Poteza> indeksiBelih(Plosca p) {
			
			
		}
		
		
		
}
