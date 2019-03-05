package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import View.Case;
import View.Case_Teleporteur;
import View.Panel_Grid;
import View.Panel_Unite;


public class Unite {
	private Coordinate coor;
	private Panel_Grid grille;
	private Panel_Unite panel_unite;
	private Astar astar;
	private List<Node> path;
	private int speed = 300;
	private int comptePath = 0;
	private State state;
	
	public Unite(Panel_Grid grille, Coordinate coor) {
		this.coor = coor; 
		this.grille = grille;
	}
	
	public Panel_Unite getPanel_unite() {
		return this.panel_unite;
	}
	
	public void setPanel_unite(Panel_Unite panel) {
		this.panel_unite=panel;
	}
	
	public Coordinate getCoor() {
		return this.coor;
	}

	public void setPath(List<Node> path) {
		this.path = path;
	}
	
	public void setComptePath(int k) {
		this.comptePath = k;
	}
	
	public void setChar(Panel_Grid g, int x, int y){
		JPanel char_case = grille.getSquare()[x][y];
		Panel_Unite panel = new Panel_Unite(grille, this);
		char_case.add(panel);
		panel.set_square(grille.getSquare()[x][y]);		
		char_case.repaint();
		grille.repaint();
		grille.revalidate();
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Timer timer_boueu = new Timer(speed*2, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int new_x = path.get(comptePath).getCoordinate().getX();
			int new_y = path.get(comptePath).getCoordinate().getY();
			getPanel_unite().get_square().remove(getPanel_unite());
			coor.setX(new_x);
			coor.setY(new_y);
			grille.setVisible(coor.getX(), coor.getY());
			setChar(grille, coor.getX(), coor.getY());
		//	k++;
		}
	});
	
	public Timer timer = new Timer(speed, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(comptePath < path.size()) {
				if (path.get(comptePath).getCase().getType() == "Boueuse") {
					speed = speed *2;
				}	
				int new_x = path.get(comptePath).getCoordinate().getX();
				int new_y = path.get(comptePath).getCoordinate().getY();
				getPanel_unite().get_square().remove(getPanel_unite());
				coor.setX(new_x);
				coor.setY(new_y);
				if (path.get(comptePath).getCase().getType()=="Teleporteur") {
					int des_x = ((Case_Teleporteur)path.get(comptePath).getCase()).getDestination().getX();
				    int des_y = ((Case_Teleporteur)path.get(comptePath).getCase()).getDestination().getY();
			        Node node_des= grille.getNode(des_x, des_y);
			        if (node_des.isWalkable()) {
			        path.add(node_des);
			        }else {
			        	((Case_Teleporteur) path.get(comptePath).getCase()).setDestination();
			        	comptePath--;
			        }		
				} 
				if (path.get(comptePath).getCase().getType() == "Or") { /*check gold*/
					Case case_depart = path.get(comptePath).getCase();
					Case case_arrive = grille.getSquare()[1][2];
					astar= new Astar(grille);
					path = astar.findPath(case_depart, case_arrive);
					setPath(path);
					comptePath = -1;
				}
				System.out.println("( "+ new_x + ", " + new_y +" )");
				if( grille.getSquare()[new_x][new_y] == grille.getSquare()[1][2]) { /*save a gold in the house*/
					Panel_Grid.GOLD +=10;
					System.out.println("Total gold: " + Panel_Grid.GOLD);
				}				
				grille.setVisible(coor.getX(), coor.getY());
				setChar(grille, coor.getX(), coor.getY());
				comptePath++;
					}
				}
			});
}
