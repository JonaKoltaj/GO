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

import logika.Plosca;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	protected Plosca plosca;
	protected Color barvaMreze;
	protected Color barvaPrviIgralec;
	protected Color barvaDrugiIgralec;
	protected Stroke debelinaMreze;
	protected double polmerZetona;
	
	public Platno(int sirina, int visina) {
		super();
		Plosca plosca = new Plosca(9);
		nastaviPlosco(plosca);
		setPreferredSize(new Dimension(sirina, visina));
		
		barvaPrviIgralec = Color.BLACK;
		barvaDrugiIgralec = Color.WHITE;
		barvaMreze = Color.black;
		debelinaMreze = new BasicStroke(2);
		polmerZetona = 5;
		
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
	    g.fillOval(marginSirina - 5 + 2*((velikost)/8), marginVisina - 5 + 2*((velikost)/8), 10, 10);
	    g.fillOval(marginSirina - 5 + 6*((velikost)/8), marginVisina - 5 + 2*((velikost)/8), 10, 10);
	    g.fillOval(marginSirina - 5 + 2*((velikost)/8), marginVisina - 5 + 6*((velikost)/8), 10, 10);
	    g.fillOval(marginSirina - 5 + 6*((velikost)/8), marginVisina - 5 + 6*((velikost)/8), 10, 10);
	    g.fillOval(marginSirina - 5 + 4*((velikost)/8), marginVisina - 5 + 4*((velikost)/8), 10, 10);
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
