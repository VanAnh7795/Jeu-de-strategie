package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Model.Node;
public class Case_Or extends Case{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int valeur = 20;
	
	public Case_Or(Panel_Grid g, Node node) {
		super(g, node);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Case_Arbre.class.getResource("/Star_Coin.png")));
		label.setBounds(0, 0, 40, 40);
		add(label);
		this.node.setWalkable(false);
		this.type = "Or";
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public void removeOr() {
		
	}
}
