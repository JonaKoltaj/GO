//package GUI;
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Stroke;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.awt.event.WindowEvent;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import inteligenca.Alphabeta;
//import inteligenca.Inteligenca;
//import inteligenca.RandomIzbira;
//import logika.Igra;
//import logika.Igralec;
//import logika.Plosca;
//import logika.Stanje;
//import logika.Zeton;
//import splosno.Poteza;
//import vodja.Vodja;
//
//@SuppressWarnings("serial")
//public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
//	protected Igra igra;
//	protected String prviIgralec;
//	protected String drugiIgralec;
//	
//	public Platno(int sirina, int visina) {
//		super();
//		igra = new Igra();
//		nastaviPlosco(igra.plosca);
//		setPreferredSize(new Dimension(sirina, visina));
//		
//		prviIgralec = "Človek";
//		drugiIgralec = "Računalnik";
//		
//		addMouseListener(this);
//        addMouseMotionListener(this);
//        addKeyListener(this);
//        setFocusable(true);
//	}
//	
//	public void nastaviPlosco(Plosca p) {
//		igra.plosca = p;
//		repaint();
//	}
//	
//	// **************************************************
//	// verjetno bi ta del moral biti v razredu VODJA (tako je mel prof. narjeno na primeru)
//	// mogoče spremenima kasneje, če bo treba
//	
//	
//	// public static Inteligenca racunalnikovaInteligenca = new Alphabeta(9);
////	public static Inteligenca racunalnikovaInteligenca = new RandomIzbira("random");
////	
////	public boolean igrajRacunalnikovoPotezo() {
////		Poteza poteza = racunalnikovaInteligenca.izberiPotezo(igra);
////		try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
////		boolean vrednost = igra.odigraj(poteza);
////		return vrednost;
////	}
////	
//	//*********************************************************************
//	
//	
//	// !!!! to bom prestavila v drug razred. zaenkrat samo zakomentiram - milka
//	
//	//to je samo pomozna funkcija, da se lahko ureja kdo igra prvi/kako igra racunalnik proti racunalniku
////	public void spremeniIgralca() {
////		if (prviIgralec == "Računalnik" && drugiIgralec == "Človek") {
////			igrajRacunalnikovoPotezo();
////			konecIgre();
////			prviIgralec = "Človek";
////			drugiIgralec = "Računalnik";
////		}
////		while (prviIgralec == "Računalnik" && drugiIgralec == "Računalnik" && (igra.stanje != Stanje.V_TEKU)) {
////			igrajRacunalnikovoPotezo();
////			konecIgre();
////		}
////		repaint();
////	}
////	
////	public void konecIgre() {
////		if (!igra.aliJeKonec()) {
////			JOptionPane.showMessageDialog(null, "ZMAGAL JE IGRALEC BARVE: " + igra.zmagovalec(), "IGRE JE KONEC!", JOptionPane.PLAIN_MESSAGE);
////			int odgovor = JOptionPane.showConfirmDialog(null, "ŽELITE ZAČETI NOVO IGRO?");
////			if (odgovor == JOptionPane.YES_OPTION) {
////				this.igra = new Igra();
////				repaint();
////			}
////		}
////	}
//	
//	@Override
//	protected void paintComponent(Graphics g) {
//		// izrisan go board tako da se prilagaja oknu ki ga odpremo
//		int sirina = getWidth();
//	    int visina = getHeight();
//	    int velikost = Math.min(sirina, visina) - 200;
//	    int marginSirina = sirina/2 - velikost/2;
//	    int marginVisina = visina/2 - velikost/2;
//	    super.paintComponent(g);
//	    g.drawRect(marginSirina, marginVisina, velikost, velikost);
//	    for(int i = 1; i <= 7; i++) {
//	        g.drawLine(marginSirina + i*((velikost)/8), marginVisina, marginSirina + i*((velikost)/8), velikost + marginVisina);
//	    }
//	    for(int j = 1; j <= 7; j++) {
//	    	g.drawLine(marginSirina, marginVisina + j*((velikost)/8), velikost + marginSirina, marginVisina + j*((velikost)/8));
//	    }
//	    //tukej je izrisanih tistih pet pikic na sredini
//	    int polmerPikice = velikost/96;
//	    g.fillOval(marginSirina - polmerPikice + 2*((velikost)/8), marginVisina - polmerPikice + 2*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 6*((velikost)/8), marginVisina - polmerPikice + 2*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 2*((velikost)/8), marginVisina - polmerPikice + 6*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 6*((velikost)/8), marginVisina - polmerPikice + 6*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 4*((velikost)/8), marginVisina - polmerPikice + 4*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    
//	    //izrisemo se zetone
//	    int polmerZetona = velikost/16;
//	    for (int i = 0; i < igra.plosca.velikost; ++i) {
//	    	for (int j = 0; j < igra.plosca.velikost; ++j) {
//	    		if (igra.plosca.mreza[i][j] != null) {
//	    			Igralec barva = igra.plosca.mreza[i][j].barva;
//	    			if (barva == Igralec.BELI) g.setColor(Color.WHITE);
//	    			else g.setColor(Color.BLACK);
//	    			g.fillOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
//	    			g.setColor(Color.BLACK);
//	    			g.drawOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
//	    		}
//	    	}
//	    }
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	// nekaj se zgodi, samo če je bil na vrsti človek
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (Vodja.clovekNaVrsti) {
//			int x = e.getX();
//	        int y = e.getY();
//	        
//	        int sirina = getWidth();
//		    int visina = getHeight();
//		    int velikost = Math.min(sirina, visina) - 200;
//		    int marginSirina = sirina/2 - velikost/2;
//		    int marginVisina = visina/2 - velikost/2;
//		    int najmanjsaRazdalja = velikost/16;
//	        
//	        for (int i = 0; i < igra.plosca.velikost; ++i) {
//		    	for (int j = 0; j < igra.plosca.velikost; ++j) {
//		    		int poljex = marginSirina + i*((velikost)/8);
//		    		int poljey = marginVisina + j*((velikost)/8);
//		    		double razdalja = Math.sqrt((poljex - x)*(poljex - x) + (poljey - y)*(poljey - y));
//		    		
//		    		if (razdalja < najmanjsaRazdalja) {
//		    			boolean mozno = Vodja.igra.odigraj(new Poteza(i, j));
//		    			if (!mozno) {
//	    					JOptionPane.showMessageDialog(null, "Poteza ni mogoča, izberi drugo polje", "Polje ni prosto", JOptionPane.ERROR_MESSAGE);
//	    					continue;}
//		    			else {Vodja.clovekNaVrsti = false;
//		    				Vodja.igrajClovekovoPotezo(new Poteza(i, j));};
//		    		}
//		    	}
//	        }
//		}
//	}
//	
//	    		
//	    		
//	// jona
////	@Override
////	public void mouseClicked(MouseEvent e) {
////		int x = e.getX();
////        int y = e.getY();
////        
////        int sirina = getWidth();
////	    int visina = getHeight();
////	    int velikost = Math.min(sirina, visina) - 200;
////	    int marginSirina = sirina/2 - velikost/2;
////	    int marginVisina = visina/2 - velikost/2;
////	    int najmanjsaRazdalja = velikost/16;
////        
////        for (int i = 0; i < igra.plosca.velikost; ++i) {
////	    	for (int j = 0; j < igra.plosca.velikost; ++j) {
////	    		int poljex = marginSirina + i*((velikost)/8);
////	    		int poljey = marginVisina + j*((velikost)/8);
////	    		double razdalja = Math.sqrt((poljex - x)*(poljex - x) + (poljey - y)*(poljey - y));
////	    		
////	    		if (razdalja < najmanjsaRazdalja) {
////	    			if (prviIgralec == "Človek" && drugiIgralec == "Računalnik") {
////	    				boolean moznoClovek = igra.odigraj(new Poteza(i, j));
////	    				if (!moznoClovek) {
////	    					JOptionPane.showMessageDialog(null, "Poteza ni mogoča, izberi drugo polje", "Polje ni prosto", JOptionPane.ERROR_MESSAGE);
////	    					continue;
////	    				}
////	    				konecIgre();
////		    			boolean moznoRac = igrajRacunalnikovoPotezo();
////		    			while (!moznoRac) {
////		    				moznoRac = igrajRacunalnikovoPotezo();
////		    			}
////		    			konecIgre();
////	    			}
////	    			else if (prviIgralec == "Človek" && drugiIgralec == "Človek") {
////	    				boolean moznoClovek = igra.odigraj(new Poteza(i, j));
////	    				if (!moznoClovek) {
////	    					JOptionPane.showMessageDialog(null, "Polje je zapolnjeno, izberi drugega", "Zapolnjeno polje", JOptionPane.ERROR_MESSAGE);
////	    					continue;
////	    				}
////	    				konecIgre();
////	    			}
////	    			else continue;
////	    		}
////	    	}
////        }
////        repaint();
//		
////	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}


////////////////////////////////////////////////////

package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inteligenca.Alphabeta;
import inteligenca.Inteligenca;
import inteligenca.RandomIzbira;
import logika.Igra;
import logika.Igralec;
//import logika.Plosca;
//import logika.Polje;
import logika.Stanje;
import logika.Zeton;
import splosno.Poteza;
import vodja.Vodja;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	protected Igra igra;
	protected String prviIgralec;
	protected String drugiIgralec;
	
	public Platno(int sirina, int visina) {
		super();
		igra = new Igra();
		setPreferredSize(new Dimension(sirina, visina));
		
		prviIgralec = "Človek";
		drugiIgralec = "Računalnik";
		
		addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
	}
	
	

	
	// !!!! to bom prestavila v drug razred. zaenkrat samo zakomentiram - milka
	
	//to je samo pomozna funkcija, da se lahko ureja kdo igra prvi/kako igra racunalnik proti racunalniku
//	public void spremeniIgralca() {
//		if (prviIgralec == "Računalnik" && drugiIgralec == "Človek") {
//			igrajRacunalnikovoPotezo();
//			konecIgre();
//			prviIgralec = "Človek";
//			drugiIgralec = "Računalnik";
//		}
//		while (prviIgralec == "Računalnik" && drugiIgralec == "Računalnik" && (igra.stanje != Stanje.V_TEKU)) {
//			igrajRacunalnikovoPotezo();
//			konecIgre();
//		}
//		repaint();
//	}
//	
//	public void konecIgre() {
//		if (!igra.aliJeKonec()) {
//			JOptionPane.showMessageDialog(null, "ZMAGAL JE IGRALEC BARVE: " + igra.zmagovalec(), "IGRE JE KONEC!", JOptionPane.PLAIN_MESSAGE);
//			int odgovor = JOptionPane.showConfirmDialog(null, "ŽELITE ZAČETI NOVO IGRO?");
//			if (odgovor == JOptionPane.YES_OPTION) {
//				this.igra = new Igra();
//				repaint();
//			}
//		}
//	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		// izrisan go board tako da se prilagaja oknu ki ga odpremo
//		int n = 9;
//		int sirina = getWidth();
//	    int visina = getHeight();
//	    int velikost = Math.min(sirina, visina) - 200;
//	    int marginSirina = sirina/2 - velikost/2;
//	    int marginVisina = visina/2 - velikost/2;
//	    super.paintComponent(g);
//	    g.drawRect(marginSirina, marginVisina, velikost, velikost);
//	    for(int i = 1; i <= 7; i++) {
//	        g.drawLine(marginSirina + i*((velikost)/8), marginVisina, marginSirina + i*((velikost)/8), velikost + marginVisina);
//	    }
//	    for(int j = 1; j <= 7; j++) {
//	    	g.drawLine(marginSirina, marginVisina + j*((velikost)/8), velikost + marginSirina, marginVisina + j*((velikost)/8));
//	    }
//	    //tukej je izrisanih tistih pet pikic na sredini
//	    int polmerPikice = velikost/96;
//	    g.fillOval(marginSirina - polmerPikice + 2*((velikost)/8), marginVisina - polmerPikice + 2*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 6*((velikost)/8), marginVisina - polmerPikice + 2*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 2*((velikost)/8), marginVisina - polmerPikice + 6*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 6*((velikost)/8), marginVisina - polmerPikice + 6*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    g.fillOval(marginSirina - polmerPikice + 4*((velikost)/8), marginVisina - polmerPikice + 4*((velikost)/8), polmerPikice*2, polmerPikice*2);
//	    
//	    //izrisemo se zetone
//	    int polmerZetona = velikost/16;
//	    Zeton[][] plosca;
//	    plosca = igra.getPlosca();
//	    for (int i = 0; i < n; ++i) {
//	    	for (int j = 0; j < n; ++j) {
//	    		if (plosca[i][j] != null) {
//	    			Igralec barva = plosca[i][j].barva;
//	    			if (barva == Igralec.BELI) g.setColor(Color.WHITE);
//	    			else g.setColor(Color.BLACK);
//	    			g.fillOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
//	    			g.setColor(Color.BLACK);
//	    			g.drawOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
//	    		}
//	    	}
//	    }
//	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// izrisan go board tako da se prilagaja oknu ki ga odpremo
		int n = 9;
		int sirina = getWidth();
	    int visina = getHeight();
	    int velikost = Math.min(sirina, visina) - 200;
	    int marginSirina = sirina/2 - velikost/2;
	    int marginVisina = visina/2 - velikost/2;
	    super.paintComponent(g);
	    g.drawRect(marginSirina, marginVisina, velikost, velikost);
	    for(int i = 1; i <= 7; i++) {
	        g.drawLine(marginSirina + i*((velikost)/8), marginVisina, marginSirina + i*((velikost)/8), velikost + marginVisina);
	    }
	    for(int j = 1; j <= 7; j++) {
	    	g.drawLine(marginSirina, marginVisina + j*((velikost)/8), velikost + marginSirina, marginVisina + j*((velikost)/8));
	    }
	    //tukej je izrisanih tistih pet pikic na sredini
	    int polmerPikice = velikost/96;
	    g.fillOval(marginSirina - polmerPikice + 2*((velikost)/8), marginVisina - polmerPikice + 2*((velikost)/8), polmerPikice*2, polmerPikice*2);
	    g.fillOval(marginSirina - polmerPikice + 6*((velikost)/8), marginVisina - polmerPikice + 2*((velikost)/8), polmerPikice*2, polmerPikice*2);
	    g.fillOval(marginSirina - polmerPikice + 2*((velikost)/8), marginVisina - polmerPikice + 6*((velikost)/8), polmerPikice*2, polmerPikice*2);
	    g.fillOval(marginSirina - polmerPikice + 6*((velikost)/8), marginVisina - polmerPikice + 6*((velikost)/8), polmerPikice*2, polmerPikice*2);
	    g.fillOval(marginSirina - polmerPikice + 4*((velikost)/8), marginVisina - polmerPikice + 4*((velikost)/8), polmerPikice*2, polmerPikice*2);
	    
	    //izrisemo se zetone
	    int polmerZetona = velikost/16;
	    
	   
	    if (Vodja.igra != null) {
	    	Zeton[][] plosca;
		    plosca = Vodja.igra.getPlosca();
	    	for (int i = 0; i < n; ++i) {
		    	for (int j = 0; j < n; ++j) {
		    		if (plosca[i][j] != null) {
		    			Igralec barva = plosca[i][j].barva;
		    			if (barva == Igralec.BELI) g.setColor(Color.WHITE);
		    			else g.setColor(Color.BLACK);
		    			g.fillOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
		    			g.setColor(Color.BLACK);
		    			g.drawOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
		    		}
		    	}
		    }
	    }
	    
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	// nekaj se zgodi, samo če je bil na vrsti človek
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Vodja.clovekNaVrsti) {
			int x = e.getX();
	        int y = e.getY();
	        int n = 9;
	        
	        int sirina = getWidth();
		    int visina = getHeight();
		    int velikost = Math.min(sirina, visina) - 200;
		    int marginSirina = sirina/2 - velikost/2;
		    int marginVisina = visina/2 - velikost/2;
		    int najmanjsaRazdalja = velikost/16;
	        
	        for (int i = 0; i < n; ++i) {
		    	for (int j = 0; j < n; ++j) {
		    		int poljex = marginSirina + i*((velikost)/8);
		    		int poljey = marginVisina + j*((velikost)/8);
		    		double razdalja = Math.sqrt((poljex - x)*(poljex - x) + (poljey - y)*(poljey - y));
		    		
		    		if (razdalja < najmanjsaRazdalja) {
		    			boolean mozno = Vodja.igra.odigraj(new Poteza(i, j));
		    			if (!mozno) {
	    					JOptionPane.showMessageDialog(null, "Poteza ni mogoča, izberi drugo polje", "Polje ni prosto", JOptionPane.ERROR_MESSAGE);
	    					continue;}
		    			else {Vodja.clovekNaVrsti = false;
		    				Vodja.igrajClovekovoPotezo(new Poteza(i, j));};
		    		}
		    	}
	        }
		}
	}
	
	    		
	    		
	// jona
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		int x = e.getX();
//        int y = e.getY();
//        
//        int sirina = getWidth();
//	    int visina = getHeight();
//	    int velikost = Math.min(sirina, visina) - 200;
//	    int marginSirina = sirina/2 - velikost/2;
//	    int marginVisina = visina/2 - velikost/2;
//	    int najmanjsaRazdalja = velikost/16;
//        
//        for (int i = 0; i < igra.plosca.velikost; ++i) {
//	    	for (int j = 0; j < igra.plosca.velikost; ++j) {
//	    		int poljex = marginSirina + i*((velikost)/8);
//	    		int poljey = marginVisina + j*((velikost)/8);
//	    		double razdalja = Math.sqrt((poljex - x)*(poljex - x) + (poljey - y)*(poljey - y));
//	    		
//	    		if (razdalja < najmanjsaRazdalja) {
//	    			if (prviIgralec == "Človek" && drugiIgralec == "Računalnik") {
//	    				boolean moznoClovek = igra.odigraj(new Poteza(i, j));
//	    				if (!moznoClovek) {
//	    					JOptionPane.showMessageDialog(null, "Poteza ni mogoča, izberi drugo polje", "Polje ni prosto", JOptionPane.ERROR_MESSAGE);
//	    					continue;
//	    				}
//	    				konecIgre();
//		    			boolean moznoRac = igrajRacunalnikovoPotezo();
//		    			while (!moznoRac) {
//		    				moznoRac = igrajRacunalnikovoPotezo();
//		    			}
//		    			konecIgre();
//	    			}
//	    			else if (prviIgralec == "Človek" && drugiIgralec == "Človek") {
//	    				boolean moznoClovek = igra.odigraj(new Poteza(i, j));
//	    				if (!moznoClovek) {
//	    					JOptionPane.showMessageDialog(null, "Polje je zapolnjeno, izberi drugega", "Zapolnjeno polje", JOptionPane.ERROR_MESSAGE);
//	    					continue;
//	    				}
//	    				konecIgre();
//	    			}
//	    			else continue;
//	    		}
//	    	}
//        }
//        repaint();
		
//	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

