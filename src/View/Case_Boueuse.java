package View;

import java.awt.Color;
import java.awt.FlowLayout;
import Model.Node;


public class Case_Boueuse extends Case {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Case_Boueuse(Panel_Grid g, Node node) {
		super(g, node);
		this.node.setWalkable(true);
		setBackground(new Color(139, 69, 19));
		setLayout(new FlowLayout());
		setOpaque(true);
		this.type = "Boueuse";
	}
	
	public void setDestination() {
		
	}

}
