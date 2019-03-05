package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import View.Panel_Grid;

public class GrilleKeyListener implements  KeyListener {
	
	private Panel_Grid g;
	
	public GrilleKeyListener(Panel_Grid g) {
		this.g = g;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='c') {
			g.creationUnite();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
