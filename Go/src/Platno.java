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

import javax.swing.JButton;
import javax.swing.JPanel;

import logika.Igra;
import logika.Plosca;
import logika.Zeton;
import splosno.Poteza;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	protected Plosca plosca;
	protected Color barvaPrviIgralec;
	protected Color barvaDrugiIgralec;
	
	public Platno(int sirina, int visina) {
		super();
		Plosca plosca = new Plosca(9);
		nastaviPlosco(plosca);
		setPreferredSize(new Dimension(sirina, visina));
		
		barvaPrviIgralec = Color.BLACK;
		barvaDrugiIgralec = Color.WHITE;
		
		addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
	}
	
	public void nastaviPlosco(Plosca p) {
		plosca = p;
		repaint();
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
	    for (int i = 0; i < plosca.velikost; ++i) {
	    	for (int j = 0; j < plosca.velikost; ++j) {
	    		if (plosca.mreza[i][j] != null) {
	    			String barva = plosca.mreza[i][j].barva;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
        
        int sirina = getWidth();
	    int visina = getHeight();
	    int velikost = Math.min(sirina, visina) - 200;
	    int marginSirina = sirina/2 - velikost/2;
	    int marginVisina = visina/2 - velikost/2;
	    int najmanjsaRazdalja = velikost/16;
        
        for (int i = 0; i < plosca.velikost; ++i) {
	    	for (int j = 0; j < plosca.velikost; ++j) {
	    		int poljex = marginSirina + i*((velikost)/8);
	    		int poljey = marginVisina + j*((velikost)/8);
	    		double razdalja = Math.sqrt((poljex - x)*(poljex - x) + (poljey - y)*(poljey - y));
	    		
	    		if (razdalja < najmanjsaRazdalja) {
	    			Igra.odigraj(new Poteza(i, j));
	    		}
	    	}
        }
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
