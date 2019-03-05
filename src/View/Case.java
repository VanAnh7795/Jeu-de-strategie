package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Model.Node;
import Model.Coordinate;

public abstract class Case extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected static final Dimension DIMENSION = new Dimension(80,90);
	protected Coordinate coor;
	protected Panel_Grid grid;
	protected String type;
	protected Node node;
	
	public Case(Panel_Grid grid, Node node) {
		this.grid = grid;
		this.node = node;
		coor = node.getCoordinate();
		node.setCase(this);
		this.setPreferredSize(DIMENSION);
		this.setMaximumSize(DIMENSION);
		this.setMinimumSize(DIMENSION);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setVisible(true);
		setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	public Node getNode(){
		return this.node;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}

