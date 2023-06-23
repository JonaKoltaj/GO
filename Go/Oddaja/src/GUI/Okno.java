
package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import logika.Igralec;
import logika.Stanje;
import logika.Zeton;
import splosno.KdoIgra;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	private Platno platno;
	
	private JMenuItem menuOdpri, menuShrani, menuStop, menuStart;
	private JMenuItem menuIme, menuNacinIgre;
	
	private JPanel statusBar;
	private JLabel status;
	
	public Okno() {
		super();
		setTitle("Capture Go");
		//definirati rabimo skupno platno, da ga lahko potem razdelimo
		JPanel skupnoPlatno = new JPanel();
	    skupnoPlatno.setLayout(new BorderLayout());
	    skupnoPlatno.setPreferredSize(new Dimension(700, 700));
	    getContentPane().add(skupnoPlatno);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuDatoteka = dodajMenu(menubar, "Datoteka");
		JMenu menuNastavitve = dodajMenu(menubar, "Nastavitve");
		
		menuOdpri = dodajMenuItem(menuDatoteka, "Odpri ...");
		menuShrani = dodajMenuItem(menuDatoteka, "Shrani ...");
		menuStop = dodajMenuItem(menuDatoteka, "Ustavi program ...");
		menuStart = dodajMenuItem(menuDatoteka, "Nadaljuj s programom ...");
		
		menuNacinIgre = dodajMenuItem(menuNastavitve, "Izberi način igre");
		menuIme = dodajMenuItem(menuNastavitve, "Izberi ime ...");
		
		platno = new Platno(600, 600);
		platno.setLayout(new BorderLayout());
		
		statusBar = dodajStatusBar();
		status = napisiTekst("Igra se še ni začela!");
		statusBar.add(status);
		
		//razdelimo platno na dvoje, da je bolj pregledno
		JSplitPane razdelitev = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    skupnoPlatno.add(razdelitev, BorderLayout.CENTER);
	    razdelitev.setLeftComponent(platno);
	    razdelitev.setRightComponent(statusBar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//metoda ki nam doda statusno vrstico
	private JPanel dodajStatusBar() {
		JPanel statusBar = new JPanel();
	    statusBar.setLayout(new BorderLayout());
	    return statusBar;
	}
	
	//metoda ki nam doda jlabel v statusno vrstico
	private JLabel napisiTekst(String niz) {
		JLabel tekst = new JLabel(niz);
	    tekst.setHorizontalAlignment(JLabel.CENTER);
	    tekst.setVerticalAlignment(JLabel.CENTER);
	    return tekst;
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
	
	//metoda za izpis zajetih zetonov v statusni vrstici
	//podobno kot v Igra
	private String izpisiZajete(LinkedList<Zeton> sez) {
		if (sez != null) {
			String izpis = "";
			if (sez.isEmpty() == false) {
				izpis += "[";
				for (Zeton z : sez) izpis += z;
				izpis += "]";
			}
		return izpis;
		}
		else return null;
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
		//ce zelimo ustaviti igro v primeru ko igra racunalnik proti racunalniku
		else if (objekt == menuStop) {
			if (Vodja.vrstaIgralca.get(Igralec.BELI) == VrstaIgralca.R && Vodja.vrstaIgralca.get(Igralec.CRNI) == VrstaIgralca.R && Vodja.igra.stanje == Stanje.V_TEKU) {
				Vodja.igra.stanje = Stanje.NEODLOCENO;
			}
		}
		//ce smo igro ustavili (ko sta bila oba igralca racunalnika)
		//ker se neodloceno ne zgodi nikoli drugje v go, to lahko naredimo
		else if (objekt == menuStart) {
			if (Vodja.igra.stanje == Stanje.NEODLOCENO) {
				Vodja.igra.stanje = Stanje.V_TEKU;
				Vodja.igramo();
			}
		}
		//metoda ki spremeni ime belega in crnega igralca
		//ce kliknemo "cancel" ostanejo imena ista kot prej
		else if (objekt == menuIme) {
			if (Vodja.igra != null) {
				String ime1 = JOptionPane.showInputDialog("Izberi si ime za igralca s črnimi žetoni:");
				String ime2 = JOptionPane.showInputDialog("Izberi si ime za igralca z belimi žetoni:");
				KdoIgra stari1 = Vodja.kdoIgra.get(Igralec.CRNI);
				KdoIgra stari2 = Vodja.kdoIgra.get(Igralec.BELI);
				Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
				if (ime1 != null) Vodja.kdoIgra.put(Igralec.CRNI, new KdoIgra(ime1));
				else Vodja.kdoIgra.put(Igralec.CRNI, stari1);
				if (ime2 != null) Vodja.kdoIgra.put(Igralec.BELI, new KdoIgra(ime2));
				else Vodja.kdoIgra.put(Igralec.BELI, stari2);
			}
		}
		//metoda ki nam odpre dve zaporedni okenci, kjer izberemo vrsto igralca za bele in crne zetone
		//zgenerira imeni, ki ju potem lahko z menuIme sprememnimo
		else if (objekt == menuNacinIgre) {
			String[] buttons = {"Igralec", "Računalnik"};
			int crni = JOptionPane.showOptionDialog(null, "Izberi ali boš črne igral ti ali računalnik:", "ČRNI", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
			int beli = JOptionPane.showOptionDialog(null, "Izberi ali boš bele igral ti ali računalnik:", "BELI", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			if (crni == 0) {
				Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.C);
				Vodja.kdoIgra.put(Igralec.CRNI, new KdoIgra("UserCrni"));
			}
			else {
				Vodja.vrstaIgralca.put(Igralec.CRNI, VrstaIgralca.R);
				Vodja.kdoIgra.put(Igralec.CRNI, Vodja.racunalnikovaInteligenca);
			}
			if (beli == 0) {
				Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.C);
				Vodja.kdoIgra.put(Igralec.BELI, new KdoIgra("UserBeli"));
			}
			else {
				Vodja.vrstaIgralca.put(Igralec.BELI, VrstaIgralca.R);
				Vodja.kdoIgra.put(Igralec.BELI, Vodja.racunalnikovaInteligenca);
			}
			Vodja.igramoNovoIgro();
		}
	}
	
	//metoda ki nam tekom igranja izpisuje stanje igre
	public void osveziGUI() {
		if (Vodja.igra != null) {
			switch(Vodja.igra.stanje) {
				//neodloceno samo v primeru ko program ustavimo
				case NEODLOCENO: status.setText("Program ustavljen!"); break;
				//ce je igra se v teku se bo izpisevalo besedilo oblike "Na vrsti je crni/beli (vrstaIgralca ImeIgralca)"
				//v primeru ko obvezna poteza obstaja bo tudi to izpisalo v naslednji vrstici
				case V_TEKU:
					Igralec trenutni = Vodja.igra.naVrsti;
					String barva = "";
					if (trenutni == Igralec.BELI) barva += "beli";
					else if (trenutni == Igralec.CRNI) barva += "črni";
					String podatkiOIgralcu = "Na vrsti je "+barva+" ("+Vodja.vrstaIgralca.get(trenutni).toString()+" "+Vodja.kdoIgra.get(trenutni).ime()+").";
					status.setText(podatkiOIgralcu);
					if (Vodja.igra.obveznaPoteza != null) {
						String obveznaPoteza = "Obvezna poteza je ("+Integer.toString(Vodja.igra.obveznaPoteza.x())+","+Integer.toString(Vodja.igra.obveznaPoteza.y())+").";
						status.setText("<html>" + podatkiOIgralcu + "<br>" + obveznaPoteza + "</html>");
					}
					break;
				//ko nekdo zmaga se izpise kdo je zmagal ter katero skupino je zajel
				case ZMAGA_CRNI: 
					String zmagovalecCrni = "Čestitke! Zmagal je črni igralec " + Vodja.kdoIgra.get(Igralec.CRNI).ime() + "!";
					String zajetoCrni = "Zajel je skupino žetonov: " + izpisiZajete(Vodja.igra.zajetaSkupina);
					status.setText("<html>" + zmagovalecCrni + "<br>" + zajetoCrni + "</html>");
					break;
				case ZMAGA_BELI:
					String zmagovalecBeli = "Čestitke! Zmagal je beli igralec " + Vodja.kdoIgra.get(Igralec.BELI).ime() + "!";
					String zajetoBeli = "Zajel je skupino žetonov: " + izpisiZajete(Vodja.igra.zajetaSkupina);
					status.setText("<html>" + zmagovalecBeli + "<br>" + zajetoBeli + "</html>");
					break;
			}
		}
		Vodja.igra.konec();
		System.out.println("Ali igra še poteka " + Vodja.igra.jeKonec());
		System.out.println("Vrsta: " + Vodja.vrstaIgralca);
		System.out.println("KdoIgra: " + "Črni: " + Vodja.kdoIgra.get(Igralec.CRNI).ime() + ", Beli: " + Vodja.kdoIgra.get(Igralec.BELI).ime());
		Vodja.igra.sprintajIgro();
		platno.repaint();
		statusBar.repaint();
	}
}

