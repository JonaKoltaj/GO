package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

public abstract class Inteligenca extends KdoIgra {
	
	public Inteligenca (String ime) {
		super(ime);
	}
	
	public abstract Poteza izberiPotezo (Igra igra);

}