package View;

import java.awt.Color;
import java.awt.FlowLayout;
import Model.Node;

public class Autour_Maison extends Case {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Autour_Maison(Panel_Grid g, Node node) {
		super(g, node);
		this.node.setWalkable(true);
		setBackground(new Color(255, 215, 0));
		setLayout(new FlowLayout());
		setOpaque(true);
	}

}
