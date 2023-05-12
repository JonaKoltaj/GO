package logika;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

// class plošča ima podatke o velikosti in trenutnem stanju plošče
// razred ima tudi metodo, ki izpiše trenutno stanje na plošči
// na vsakem mestu matrike se nahaja ena izmed naslednjih vrednosti: "White", "Black" ali null

public class Plosca {
		public int velikost;
		public Zeton[][] mreza;
		
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
		
		// metoda na mrežo postavi žeton
		public void postaviZeton(Zeton z) {
			mreza[z.i][z.j] = z;
		}
		
		// metoda, ki izpiše stanje plošče, kasneje izbrišem
		public void izpis() {
			if (mreza == null) System.out.println("Prazna plošča");
			else {System.out.println("____________________________________________________________________");
			for (int i = 0; i < velikost; ++i) {
						for (int j = 0; j < velikost; ++j) {
							if (mreza[i][j] == null) System.out.print(mreza[i][j] + "\t");
							else System.out.print(mreza[i][j].barva + "\t");}
						System.out.println();
					}
			System.out.println("____________________________________________________________________");
			System.out.println("");
				}
		}
		
		// to sm dodala samo prepisano metodo izpis, ki shrani v datoteko - Jona
		public void shrani(String ime) {
			try {
				PrintWriter dat = new PrintWriter(new FileWriter(ime));
				if (mreza == null) dat.println("Prazna plošča");
				else {dat.println("____________________________________________________________________");
				for (int i = 0; i < velikost; ++i) {
							for (int j = 0; j < velikost; ++j) {
								if (mreza[i][j] == null) dat.print(mreza[i][j] + "\t");
								else dat.print(mreza[i][j].barva + "\t");}
							dat.println();
						}
				dat.println("____________________________________________________________________");
				}
				dat.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
		public static Plosca preberi(String ime) {
	        try {
	            BufferedReader dat = new BufferedReader(new FileReader(ime));
	            int stevec = 0;
	            int stevecVrstica = 0;
	            String line;
	            while ((line = dat.readLine()) != null) {
	               if (!line.equals("____________________________________________________________________") && !line.equals("")) {
	            	   stevec +=1;
	               }
	            }
	            Plosca plosca = new Plosca(stevec);
	            while (dat.ready()) {
	                String vrstica = dat.readLine().trim();
	                if (!vrstica.equals("____________________________________________________________________") && !vrstica.equals("")) {
	                	String[] besede = vrstica.split(" ");
	                	int stevecStolpec = 0;
	                	for (String beseda : besede) {
	                		if (beseda == null) plosca.mreza[stevecVrstica][stevecStolpec] = null;
	                		else {Igralec barva = Igralec.pretvoriVIgralca(beseda);
	                			plosca.postaviZeton(new Zeton(stevecVrstica, stevecStolpec, barva));};
	                		stevecStolpec +=1;
	                	}
	                	stevecVrstica += 1;
	                }
	            }
	            dat.close();
				return plosca;
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
