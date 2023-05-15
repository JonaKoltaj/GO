package GUI;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import splosno.KdoIgra;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	protected Platno platno;
	
	private JMenuItem menuOdpri, menuShrani, menuRestart;
	private JMenuItem menuIme, menuIgralec, menuAlgoritem;
	
	// Izbire v menujih
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraClovekClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	
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
		menuRestart = dodajMenuItem(menuDatoteka, "Začni ponovno ...");
		menuIme = dodajMenuItem(menuNastavitve, "Izberi ime ...");
		menuIgralec = dodajMenuItem(menuNastavitve, "Izberi igralca ...");
		menuAlgoritem = dodajMenuItem(menuNastavitve, "Izberi algoritem ...");
		
		
		igraClovekRacunalnik = new JMenuItem("Človek – računalnik");
		menuNacinIgre.add(igraClovekRacunalnik);
		igraClovekRacunalnik.addActionListener(this);
		
		igraRacunalnikClovek = new JMenuItem("Računalnik – človek");
		menuNacinIgre.add(igraRacunalnikClovek);
		igraRacunalnikClovek.addActionListener(this);
		
		igraClovekClovek = new JMenuItem("Človek – človek");
		menuNacinIgre.add(igraClovekClovek);
		igraClovekClovek.addActionListener(this);
		
		igraRacunalnikRacunalnik = new JMenuItem("Računalnik – računalnik");
		menuNacinIgre.add(igraRacunalnikRacunalnik);
		igraRacunalnikRacunalnik.addActionListener(this);
		
		
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
				Plosca plosca = Plosca.preberi(ime);
				platno.nastaviPlosco(plosca);
			}
		}
		else if (objekt == menuShrani) {
			JFileChooser dialog = new JFileChooser();
			int izbira = dialog.showSaveDialog(this);
			if (izbira == JFileChooser.APPROVE_OPTION) {
				String ime = dialog.getSelectedFile().getPath();
				platno.igra.plosca.shrani(ime);
			}
		}
		else if (objekt == menuRestart) {
			platno.igra = new Igra();
			platno.repaint();
		}
		else if (objekt == menuIme) {
			//to se pol lah napise, samo kako se izpise ime pac
		}
		
		// do sem je metoda ista kot prej
		if (objekt == igraClovekRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, new KdoIgra("Človek")); 
			Vodja.kdoIgra.put(Igralec.BELI, Vodja.racunalnikovaInteligenca);
			Vodja.igramoNovoIgro();
		} else if (e.getSource() == igraRacunalnikClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, Vodja.racunalnikovaInteligenca);
			Vodja.kdoIgra.put(Igralec.BELI, new KdoIgra("Človek")); 
			Vodja.igramoNovoIgro();
		} else if (e.getSource() == igraClovekClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, new KdoIgra("Človek")); 
			Vodja.kdoIgra.put(Igralec.BELI, new KdoIgra("Človek"));
			Vodja.igramoNovoIgro();
		} else if (e.getSource() == igraRacunalnikRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.CRNI, Vodja.racunalnikovaInteligenca);
			Vodja.kdoIgra.put(Igralec.BELI, Vodja.racunalnikovaInteligenca); 
			Vodja.igramoNovoIgro();
		}
			
		}
	
	public void osveziGUI() {
		if (Vodja.igra == null) {
			status.setText("Igra ni v teku.");
		}
		
//		else {
//			switch(Vodja.igra.stanje) {
//			case V_TEKU: 
//				if (Vodja.igra.naVrsti != null) {
//					status.setText("Na potezi je " + Vodja.igra.naVrsti);
//							// " - " + Vodja.kdoIgra.get(Vodja.igra.naVrsti)); 
//				}
//				break;
//			case ZMAGA_CRNI: 
//				status.setText("Zmagal je Črni - ");
//						// + Vodja.kdoIgra.get(Vodja.igra.naVrsti.nasprotnik()));
//				break;
//			case ZMAGA_BELI: 
//				status.setText("Zmagal je BELI - ");
//						// + Vodja.kdoIgra.get(Vodja.igra.naVrsti.nasprotnik()));
//				break;
//			}
			
	//	}

		System.out.println("Ali igra še poteka" + Vodja.igra.aliJeKonec());
		platno.repaint();
	}
	
	
	}
	
	
	// jona
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object objekt = e.getSource();
//		if (objekt == menuOdpri) {
//			JFileChooser dialog = new JFileChooser();
//			int izbira = dialog.showOpenDialog(this);
//			if (izbira == JFileChooser.APPROVE_OPTION) {
//				String ime = dialog.getSelectedFile().getPath();
//				Plosca plosca = Plosca.preberi(ime);
//				platno.nastaviPlosco(plosca);
//			}
//		}
//		else if (objekt == menuShrani) {
//			JFileChooser dialog = new JFileChooser();
//			int izbira = dialog.showSaveDialog(this);
//			if (izbira == JFileChooser.APPROVE_OPTION) {
//				String ime = dialog.getSelectedFile().getPath();
//				platno.igra.plosca.shrani(ime);
//			}
//		}
//		else if (objekt == menuRestart) {
//			platno.igra = new Igra();
//			platno.repaint();
//		}
//		else if (objekt == menuIme) {
//			//to se pol lah napise, samo kako se izpise ime pac
//		}
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
//		else if (objekt == menuAlgoritem) {
//			// kateri algoritem igras (to bom pol bl pr algoritmih
//			
//		}
//	}
	
	


