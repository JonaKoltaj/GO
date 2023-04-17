import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
