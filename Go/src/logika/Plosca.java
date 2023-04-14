package logika;

import java.util.HashSet;

// class plošča ima podatke o velikosti in trenutnem stanju plošče, kje je kak žeton
// razred ima tudi metodo, ki izpiše trenutno stanje na plošči
// na vsakem mestu matrike se nahaja ena izmed naslednjih vrednosti: "White", "Black" ali null

public class Plosca {
		protected int velikost;
		protected Zeton[][] mreza;
		
		// konstruiramo prazno ploščo velikosti n x n
		public Plosca(int n) {
			velikost = n;
			mreza = new Zeton[n][n];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					mreza[i][j] = null;
				}
			}
		}
		
		// metoda, ki izpiše stanje plošče
		public void izpis() {
			if (mreza == null) System.out.println("Prazna plošča");
			else {
			for (int i = 0; i < velikost; ++i) {
						for (int j = 0; j < velikost; ++j) {
							if (mreza[i][j] == null) System.out.print(mreza[i][j] + "\t");
							else System.out.print(mreza[i][j].barva + "\t");}
						System.out.println();
					}
				}
		}
		
		public void postaviZeton(Zeton z) {
			mreza[z.i][z.j] = z;
		}
		//public void spremeni(int i, int j, String niz) {
		//	mreza[i][j] = niz;
		//}
		
		
		
		
}
