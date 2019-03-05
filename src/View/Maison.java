package View;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import Model.Node;



public class Maison extends Case {
		
	private static final long serialVersionUID = 1L;

	public Maison (Panel_Grid g, Node node) {	
		super(g, node);
		this.node.setWalkable(true);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 215, 0));
		setVisible(true);
		setOpaque(true);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Maison.class.getResource("/maison.png")));
		label.setBounds(0, 0, 40, 40);
		add(label);
		
	}

}
