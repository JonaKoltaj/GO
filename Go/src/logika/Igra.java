package logika;

import java.util.HashSet;
import java.util.Set;

import splosno.Poteza;

public class Igra {
	
	protected Plosca plosca;
	
	public Igra() {
		plosca = new Plosca(9);
	}
	
	public Set<Poteza> seznamMoznihPotez() {
		Set<Poteza> seznam = new HashSet<Poteza>();
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (plosca.mreza[i][j] == null) seznam.add(new Poteza(i, j));
			}
		}
		return seznam;
	}
	
	public boolean odigraj(Poteza poteza) {
		Set<Poteza> seznam = seznamMoznihPotez();
		return seznam.contains(poteza);
	}
}
