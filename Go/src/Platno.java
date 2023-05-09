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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logika.Igra;
import logika.Plosca;
import logika.Zeton;
import splosno.Poteza;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	protected Igra igra;
	protected String prviIgralec;
	protected String drugiIgralec;
	
	public Platno(int sirina, int visina) {
		super();
		igra = new Igra();
		nastaviPlosco(igra.plosca);
		setPreferredSize(new Dimension(sirina, visina));
		
		prviIgralec = "Človek";
		drugiIgralec = "Računalnik";
		
		addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
	}
	
	public void nastaviPlosco(Plosca p) {
		igra.plosca = p;
		repaint();
	}
	
	//to je samo pomozna funkcija, da se lahko ureja kdo igra prvi/kako igra racunalnik proti racunalniku
	public void spremeniIgralca() {
		if (prviIgralec == "Računalnik" && drugiIgralec == "Človek") {
			igra.igrajRacunalnik();
			konecIgre();
			prviIgralec = "Človek";
			drugiIgralec = "Računalnik";
		}
		while (prviIgralec == "Računalnik" && drugiIgralec == "Računalnik" && igra.stanje) {
			igra.igrajRacunalnik();
			konecIgre();
		}
		repaint();
	}
	
	public void konecIgre() {
		if (!igra.aliJeKonec()) {
			JOptionPane.showMessageDialog(null, "ZMAGAL JE IGRALEC BARVE: " + igra.zmagovalec, "IGRE JE KONEC!", JOptionPane.PLAIN_MESSAGE);
			int odgovor = JOptionPane.showConfirmDialog(null, "ŽELITE ZAČETI NOVO IGRO?");
			if (odgovor == JOptionPane.YES_OPTION) {
				this.igra = new Igra();
				repaint();
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// izrisan go board tako da se prilagaja oknu ki ga odpremo
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
	    for (int i = 0; i < igra.plosca.velikost; ++i) {
	    	for (int j = 0; j < igra.plosca.velikost; ++j) {
	    		if (igra.plosca.mreza[i][j] != null) {
	    			String barva = igra.plosca.mreza[i][j].barva;
	    			if (barva == "White") g.setColor(Color.WHITE);
	    			else g.setColor(Color.BLACK);
	    			g.fillOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
	    			g.setColor(Color.BLACK);
	    			g.drawOval(marginSirina - polmerZetona + i*((velikost)/8), marginVisina - polmerZetona + j*((velikost)/8), polmerZetona*2, polmerZetona*2);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
        
        int sirina = getWidth();
	    int visina = getHeight();
	    int velikost = Math.min(sirina, visina) - 200;
	    int marginSirina = sirina/2 - velikost/2;
	    int marginVisina = visina/2 - velikost/2;
	    int najmanjsaRazdalja = velikost/16;
        
        for (int i = 0; i < igra.plosca.velikost; ++i) {
	    	for (int j = 0; j < igra.plosca.velikost; ++j) {
	    		int poljex = marginSirina + i*((velikost)/8);
	    		int poljey = marginVisina + j*((velikost)/8);
	    		double razdalja = Math.sqrt((poljex - x)*(poljex - x) + (poljey - y)*(poljey - y));
	    		
	    		if (razdalja < najmanjsaRazdalja) {
	    			if (prviIgralec == "Človek" && drugiIgralec == "Računalnik") {
	    				boolean moznoClovek = igra.odigraj(new Poteza(i, j));
	    				if (!moznoClovek) {
	    					JOptionPane.showMessageDialog(null, "Poteza ni mogoča, izberi drugo polje", "Polje ni prosto", JOptionPane.ERROR_MESSAGE);
	    					continue;
	    				}
	    				konecIgre();
		    			boolean moznoRac = igra.igrajRacunalnik();
		    			while (!moznoRac) {
		    				moznoRac = igra.igrajRacunalnik();
		    			}
		    			konecIgre();
	    			}
	    			else if (prviIgralec == "Človek" && drugiIgralec == "Človek") {
	    				boolean moznoClovek = igra.odigraj(new Poteza(i, j));
	    				if (!moznoClovek) {
	    					JOptionPane.showMessageDialog(null, "Polje je zapolnjeno, izberi drugega", "Zapolnjeno polje", JOptionPane.ERROR_MESSAGE);
	    					continue;
	    				}
	    				konecIgre();
	    			}
	    			else continue;
	    		}
	    	}
        }
        repaint();
		
	}

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
