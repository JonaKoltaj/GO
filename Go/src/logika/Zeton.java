package logika;

public class Zeton {
	// Razred Zeton beleži samo tiste kamenčke, ki se trenutno nahajajo na plošči
	protected int i;
	protected int j;
	public Igralec barva;
	
	public Zeton (int indexVrsta, int indexStolpec, Igralec barva) {
		this.i = indexVrsta;
		this.j = indexStolpec;
		this.barva = barva; 
	}
	
	public boolean enaka(Zeton z) {
		if (z.i == this.i & z.j == this.j & this.barva == z.barva) return true;
		else return false;
		
	}

}
