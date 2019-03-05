package View;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.Coordinate;
import Model.Node;


public class Case_Teleporteur extends Case{
	
	private Coordinate Destination;
	private static final long serialVersionUID = 1L;

	public Case_Teleporteur(Panel_Grid grid, Node node) {
		super(grid, node);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Case_Teleporteur.class.getResource("/teleporteur.png")));
		label.setBounds(0, 0, 40, 40);
		add(label);
		this.node.setWalkable(false);
		this.type = "Teleporteur";
		setDestination();
	}
	public void setDestination() {
		Random r1 = new Random();
		Random r2 = new Random();		
		boolean done = false;
		while(!done) {
			int x = r1.nextInt(this.grid.getNum_square() - 2); //choisir aleatoirement coordonnee 0 =< x & y <= num_square - 2
			int y = r2.nextInt(this.grid.getNum_square() - 2);
			if( x != this.coor.getX() && y != this.coor.getY()) {
				Destination = new Coordinate(x, y);
				done = true;
			} else {
				done = false;
			}
		}	 
	}
	
	public Coordinate getDestination() {
		return this.Destination;		
	}
}
