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
import logika.Plosca;

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
		
		menuOdpri = dodajMenuItem(menuDatoteka, "Odpri ...");
		menuShrani = dodajMenuItem(menuDatoteka, "Shrani ...");
		menuRestart = dodajMenuItem(menuDatoteka, "Začni ponovno ...");
		menuIme = dodajMenuItem(menuNastavitve, "Izberi ime ...");
		menuIgralec = dodajMenuItem(menuNastavitve, "Izberi igralca ...");
		menuAlgoritem = dodajMenuItem(menuNastavitve, "Izberi algoritem ...");
		
		
		
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
		else if (objekt == menuIgralec) {
			String[] buttons = {"Igralec", "Računalnik"};
			int prviIgralec = JOptionPane.showOptionDialog(null, "Izberi ali boš igral ti ali računalnik:", "Človek ali računalnik prvi", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
			int drugiIgralec = JOptionPane.showOptionDialog(null, "Izberi ali boš igral ti ali računalnik:", "Človek ali računalnik drugi", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
			if (prviIgralec == 0) platno.prviIgralec = "Človek";
			else platno.prviIgralec = "Računalnik";
			if (drugiIgralec == 0) platno.drugiIgralec = "Človek";
			else platno.drugiIgralec = "Računalnik";
			platno.spremeniIgralca();
		}
		else if (objekt == menuAlgoritem) {
			// kateri algoritem igras (to bom pol bl pr algoritmih
			
		}
	}
	
	

}
