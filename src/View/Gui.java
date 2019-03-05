
package View;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame("Ma grille");
	private Panel_Grid game = new Panel_Grid();
	private Panel_Info infor = new Panel_Info();
	private JScrollPane sp;

	public Gui() {
		sp = new JScrollPane(game);
		sp.setBounds(0, 100, 808, 808);
//		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//      sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(sp);
        frame.add(infor);
		frame.setSize(808,846);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*"X"*/
		frame.setVisible(true);
	}
}