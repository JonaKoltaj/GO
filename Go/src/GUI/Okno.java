
package GUI;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logika.Igra;
import logika.Igralec;
import logika.Stanje;
import logika.Zeton;
import splosno.KdoIgra;
import splosno.Poteza;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	private Platno platno;
	
	private JMenuItem menuOdpri, menuShrani, menuStop, menuStart;
	private JMenuItem menuIme;
	
	// Izbire v menujih
	private JMenuItem menuClovekRacunalnik;
	private JMenuItem menuRacunalnikClovek;
	private JMenuItem menuClovekClovek;
	private JMenuItem menuRacunalnikRacunalnik;
	
	// Statusna vrstica v spodnjem delu, ki izpiše stanje
	private JLabel status;
	
	public Okno() {
		super();
		setTitle("Capture Go");
		platno = new Platno(700, 700);
		platno.setLayout(new BorderLayout());
		add(platno);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuDatoteka = dodajMenu(menubar, "Datoteka");
		JMenu menuNastavitve = dodajMenu(menubar, "Nastavitve");
		JMenu menuNacinIgre = dodajMenu(menubar, "Način igre");
		
		menuOdpri = dodajMenuItem(menuDatoteka, "Odpri ...");
		menuShrani = dodajMenuItem(menuDatoteka, "Shrani ...");
		menuStop = dodajMenuItem(menuDatoteka, "Ustavi program ...");
		menuStart = dodajMenuItem(menuDatoteka, "Nadaljuj s programom ...");
		
		menuIme = dodajMenuItem(menuNastavitve, "Izberi ime ...");
		
		menuClovekRacunalnik = dodajMenuItem(menuNacinIgre, "Človek – računalnik");
		menuRacunalnikClovek = dodajMenuItem(menuNacinIgre, "Računalnik – človek");
		menuClovekClovek = dodajMenuItem(menuNacinIgre, "Človek – človek");
		menuRacunalnikRacunalnik = dodajMenuItem(menuNacinIgre, "Računalnik – računalnik");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}
	
	private JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem = new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objekt = e.getSource();
		if (objekt == menuOdpri) {
			JFileChooser dialog = new JFileChooser();
			int izbira = dialog.showOpenDialog(this);
			if (izbira == JFileChooser.APPROVE_OPTION) {
				String ime = dialog.getSelectedFile().getPath();
				Vodja.preberi(ime);
			}
		}
		else if (objekt == menuShrani) {
			if (Vodja.igra != null) {
				JFileChooser dialog = new JFileChooser();
				int izbira = dialog.showSaveDialog(this);
				if (izbira == JFileChooser.APPROVE_OPTION) {
					String ime = dialog.getSelectedFile().getPath();
					Vodja.shrani(ime);
				}
			}
		}
		else if (objekt == menuStop) {
			if (Vodja.vrstaIgralca.get(Igralec.BELI) == VrstaIgralca.R && Vodja.vrstaIgralca.get(Igralec.CRNI) == VrstaIgralca.R && Vodja.igra.stanje == Stanje.V_TEKU) {
				Vodja.igra.stanje = Stanje.NEODLOCENO;
			}
		}
		//TO DO poglej si mal se tocno kdaj so stvari v go neodlocene in kako to najin program gleda in ce bi se to pol sploh lah zgodil
		//ce se to ne zgodi je dobr indikator za tole, ce ne pa rabimo mal spremenit samo
		else if (objekt == menuStart) {
			if (Vodja.igra.stanje == Stanje.NEODLOCENO) {
				Vodja.igra.stanje = Stanje.V_TEKU;
				Vodja.igramo();
			}
		}
		else if (objekt == menuIme) {
			//TO DO pop up window kjer izberes ime, se zapise v KdoIgra
		}
		//TO DO ampak ni nujno! to use lahko magari das pod menu Nastavitve, kot menu item Nacin Igre
		//pa se ti ko to kliknes pokaze pop up window (glej cisto dno okno datoteke kako)
		//mas uprasanje kdo bo crni pa dva gumba, pa kdo bo beli pa dva gumba
		else if (objekt == menuClovekRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, new KdoIgra("Človek")); 
			Vodja.kdoIgra.put(Igralec.BELI, Vodja.racunalnikovaInteligenca);
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == menuRacunalnikClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, Vodja.racunalnikovaInteligenca);
			Vodja.kdoIgra.put(Igralec.BELI, new KdoIgra("Človek")); 
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == menuClovekClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, new KdoIgra("Človek")); 
			Vodja.kdoIgra.put(Igralec.BELI, new KdoIgra("Človek"));
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == menuRacunalnikRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, Vodja.racunalnikovaInteligenca);
			Vodja.kdoIgra.put(Igralec.BELI, Vodja.racunalnikovaInteligenca); 
			Vodja.igramoNovoIgro();
		}
	}
	
	//TO DO pogruntaj statuse za zmago poraz, neodloceno in da se vsakic v teku pokaze kdo je na vrti (ime in barva)
	//po koncu pa se poleg stanje izpise se kdo je zmagu in kaj je ujel (mogoce to highlightej nekako, lahko inkorporiras zajete zetone v platno)
	public void osveziGUI() {
		if (Vodja.igra == null) {
			//status.setText("Igra ni v teku.");
		}
		else {
			switch(Vodja.igra.stanje) {
			case NEODLOCENO: //status.setText("Neodločeno!"); break;
			case V_TEKU: 
				//status.setText("Na potezi je " + Vodja.igra.naVrsti + 
					//	" - " + Vodja.kdoIgra.get(Vodja.igra.naVrsti)); 
				break;
			case ZMAGA_CRNI: 
				// status.setText("Zmagal je O - " + 
					//	Vodja.kdoIgra.get(Vodja.igra.naVrsti.nasprotnik()));
				
				break;
			case ZMAGA_BELI: 
				// status.setText("Zmagal je X - " + 
					//	Vodja.kdoIgra.get(Vodja.igra.naVrsti.nasprotnik()));
				break;
			}
		}
		Vodja.igra.konec();
		System.out.println("Ali igra še poteka " + Vodja.igra.jeKonec());
		System.out.println("Vrsta: " + Vodja.vrstaIgralca);
		System.out.println("KdoIgra: " + Vodja.kdoIgra);
		Vodja.igra.sprintajIgro();
		platno.repaint();
	}
}


//		else if (objekt == menuIgralec) {
//			String[] buttons = {"Igralec", "Računalnik"};
//			int prviIgralec = JOptionPane.showOptionDialog(null, "Izberi ali boš igral ti ali računalnik:", "Človek ali računalnik prvi", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
//			int drugiIgralec = JOptionPane.showOptionDialog(null, "Izberi ali boš igral ti ali računalnik:", "Človek ali računalnik drugi", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
//			if (prviIgralec == 0) platno.prviIgralec = "Človek";
//			else platno.prviIgralec = "Računalnik";
//			if (drugiIgralec == 0) platno.drugiIgralec = "Človek";
//			else platno.drugiIgralec = "Računalnik";
//			platno.spremeniIgralca();
//		}
	
	


