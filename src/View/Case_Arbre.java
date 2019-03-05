package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Model.Node;

public class Case_Arbre extends Case {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Case_Arbre(Panel_Grid g, Node node) {
		super(g, node);
		this.node.setWalkable(false);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Case_Arbre.class.getResource("/arbre.png")));
		label.setBounds(0, 0, 40, 40);
		add(label);
		this.type = "Arbre";
	}

}
