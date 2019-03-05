package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
public class Panel_Info extends JComponent {
	private static final long serialVersionUID = 1L;
	//Works using this
	    private void drawString(Graphics g, String text, int x, int y) {
	        for (String line : text.split("\n"))
	            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	    }
	    
	    public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
	    drawString(g,"Player 1-----Number of unit: "  + "     Gold:     \nPress C to buy a unit(cost 50 gold/unit)\nClick a gold to collect --Recolteur--\nPress S to perform a guard action(cost 20 gold/action) --Sentinelle--\nPress A to attack(cost 30 gold/action) --Attanquant--",0,0);
	  }
	}
