package logika;

public class Zeton {
	// Razred Zeton beleži samo tiste kamenčke, ki se trenutno nahajajo na plošči
	protected int i;
	protected int j;
	protected String barva;
	
	public Zeton (int indexVrsta, int indexStolpec, String barva) {
		this.i = indexVrsta;
		this.j = indexStolpec;
		this.barva = barva; 
	}

}
