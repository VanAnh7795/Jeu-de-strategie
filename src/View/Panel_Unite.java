package View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.Unite;


public class Panel_Unite extends JPanel {
	private static final long serialVersionUID = 1L;
	protected static final Dimension DIMENSION = new Dimension(40,40);
	private Case square;
	
	public void set_square(Case square) {
		this.square = square;
	}

	public Case get_square() {
		return this.square;
	}
	
	public Panel_Unite(Panel_Grid g, Unite unite) {
		this.setPreferredSize(new Dimension(25, 41));
		this.setMaximumSize(DIMENSION);
		this.setMinimumSize(DIMENSION);
		unite.setPanel_unite(this);
		setLayout(null);
		setVisible(true);
		setOpaque(false);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Panel_Unite.class.getResource("/character.png")));
		label.setBounds(0, 0, 40, 40);
		add(label);
		addMouseListener (new MouseAdapter(){
				@Override
		        public void mousePressed(MouseEvent e) {
		            getBackground();
		            setOpaque(true);
		            setBackground(Color.RED);
		            repaint();
		        }

		        @Override		        
		        public void mouseClicked(MouseEvent e) {
		        	g.setUniteClick(unite);
				}				
		});	
	}

}
