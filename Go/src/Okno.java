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
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	protected Platno platno;
	
	private JMenuItem menuOdpri, menuShrani, menuKoncaj;
	private JMenuItem menuIme, menuIgralec, menuAlgoritem;
	
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
		menuKoncaj = dodajMenuItem(menuDatoteka, "Končaj ...");
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
//			JFileChooser dialog = new JFileChooser();
//			int izbira = dialog.showOpenDialog(this);
//			if (izbira == JFileChooser.APPROVE_OPTION) {
//				String ime = dialog.getSelectedFile().getPath();
//				Plosca plosca = Plosca.preberi(ime);
//				platno.nastaviGraf(graf);
//			}
		}
		else if (objekt == menuShrani) {
			JFileChooser dialog = new JFileChooser();
			int izbira = dialog.showSaveDialog(this);
			if (izbira == JFileChooser.APPROVE_OPTION) {
				String ime = dialog.getSelectedFile().getPath();
				platno.plosca.shrani(ime);
			}
		}
		else if (objekt == menuKoncaj) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	

}
