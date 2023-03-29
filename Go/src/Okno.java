import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Okno extends JFrame implements ActionListener {
	
	protected Platno platno;
	
	private JMenuItem menuOdpri, menuShrani, menuKoncaj;
	
	public Okno() {
		super();
		setTitle("Capture Go");
//		platno = new Platno(800, 800);
//		add(platno);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuDatoteka = dodajMenu(menubar, "Datoteka");
		
		menuOdpri = dodajMenuItem(menuDatoteka, "Odpri ...");
		menuShrani = dodajMenuItem(menuDatoteka, "Shrani ...");
		menuKoncaj = dodajMenuItem(menuDatoteka, "Končaj ...");
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
			
		}
		else if (objekt == menuShrani) {
			
		}
		else if (objekt == menuKoncaj) {
			
		}
	}
	
	

}
