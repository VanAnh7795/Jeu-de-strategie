package View;

import java.awt.Color;

import Model.Node;



public class Case_Normale extends Case {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Case_Normale(Panel_Grid g, Node node) {
		super(g, node);
		this.node.setWalkable(true);
		setBackground(new Color(0, 100, 0));
		setOpaque(true);
		this.type= "Normale";
	}
}
