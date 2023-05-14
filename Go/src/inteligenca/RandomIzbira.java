package inteligenca;

import java.util.ArrayList;
import java.util.Random;

import logika.Igra;
import splosno.Poteza;

public class RandomIzbira extends Inteligenca {

	public RandomIzbira(String ime) {
		super(ime);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
	}
	
	// izbere naključno potezo
	@Override
	public Poteza izberiPotezo(Igra igra) {
		ArrayList<Poteza> seznam = igra.moznePotezeSeznam;
		int n = seznam.size();
		Poteza p = seznam.get(izberiNakljucniIndeks(n));
		System.out.println("Izbira računalnika je: " + p.x() + " , " + p.y());
		return p;
	}
	
	public static Poteza izberiP() {
		ArrayList<Poteza> seznam = Igra.moznePotezeSeznam;
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
