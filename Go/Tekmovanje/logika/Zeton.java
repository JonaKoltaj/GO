package logika;

public class Zeton {
	// Razred Zeton beleži samo tiste kamenčke, ki se trenutno nahajajo na plošči
	public int i;
	public int j;
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
	
	@Override
	public String toString() {
		String b = "B";
		if (barva == Igralec.CRNI) b = "C";
		return b + "(" + i + " , " + j + ") ";
	}

}
