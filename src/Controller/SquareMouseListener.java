package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.PathFinding;
import View.Case;
import View.Panel_Grid;

public class SquareMouseListener extends MouseAdapter {
	
	private Panel_Grid g;
	private PathFinding pathFinding;

	public SquareMouseListener(Panel_Grid g, PathFinding pathFinding) {
		this.g = g;
		this.pathFinding = pathFinding;
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		if (g.getUniteClick() != null) {
		    Case clicked_case = (Case) ev.getSource();
		    for (int i = 0; i < this.g.getNum_square(); i++) {
		    	for(int j = 0; j < this.g.getNum_square(); j++) {
		    		if(this.g.getSquare()[i][j] == clicked_case) {
		    			if(clicked_case.getType() == "Or") {
		    				clicked_case.getNode().setWalkable(true);
		    				if(this.g.getSquare()[i][j].getNode().isWalkable()) {
		    					g.recolteurAction(i,j);		    				
		    				}
		    			} else if (clicked_case.getType() == "Teleporteur") {
		    				clicked_case.getNode().setWalkable(true);
		    				pathFinding.useAstar(i,j);
		  
		    			}
		    			
		    			else if (this.g.getSquare()[i][j].getNode().isWalkable()){
		    				pathFinding.useAstar(i,j);
		    				} else {
			    				System.out.println("Can't walk to that node!");
		    				}
		    			}
					}		
		      	}
		    
		} else {
					System.out.println("Choissiez un unite pour commencer!");
		} 
	}
}
