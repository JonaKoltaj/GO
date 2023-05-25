package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

public class Inteligenca extends KdoIgra {
	
	public Inteligenca () {
		super("Milka & Jona");
	}
	
	public static Inteligenca racunalnikovaInteligenca = new Alphabeta(3);
	
	public Poteza izberiPotezo (Igra igra) {
		int n = 3;
		Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
		return poteza;
	}

}