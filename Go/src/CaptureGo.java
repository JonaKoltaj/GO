import logika.Plosca;

public class CaptureGo {

	public static void main(String[] args) {
		
		Okno okno = new Okno();
		okno.pack();
		okno.setVisible(true);
		Plosca plosca = new Plosca(10);
		plosca.izpis();
	}

}
