package logika;

public enum Igralec {
	CRNI, BELI;
	
	public Igralec nasprotnik() {
		return (this == CRNI ? BELI : CRNI);
		}
	
	@Override
	public String toString() {
		return (this == CRNI ? "Black" : "White");
	}
	
	public static Igralec pretvoriVIgralca(String niz)  {
		Igralec barva = BELI;
		if (niz == "Black") {barva = CRNI;}
		return barva;
	}
}
