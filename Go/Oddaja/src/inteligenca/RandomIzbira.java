package inteligenca;

import java.util.LinkedList;
import java.util.Random;

import logika.Igra;
import splosno.Poteza;

public class RandomIzbira extends Inteligenca {

	public RandomIzbira(String ime) {
		super("random izbira");
	}
	

	// izbere naključno potezo
	@Override
	public Poteza izberiPotezo(Igra igra) {
		LinkedList<Poteza> seznam = igra.moznePoteze;
		int n = seznam.size();
		Poteza p = seznam.get(izberiNakljucniIndeks(n));
		System.out.println("Izbira računalnika je: " + p.x() + " , " + p.y());
		return p;
	}
	

	
	// pomožna funkcija
	// od števila 0 do vključno n - 1 izbere neko naključno število
	public static Integer izberiNakljucniIndeks(int n) {
		Random r = new Random();
		int min = 0;
		int max = n;
		int indeks = r.nextInt(max - min) + min;
		return indeks;
	}

	
	

}
