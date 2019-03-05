package Model;

import java.util.List;
import View.Case;
import View.Panel_Grid;

public class PathFinding {
	private Panel_Grid g;
	private Astar astar;
	private List<Node> path;
	
	public PathFinding(Panel_Grid g) {
		super();
		this.g = g;
		}

	public void useAstar(int i, int j) {
		Case case_depart = g.getUniteClick().getPanel_unite().get_square();
		Case case_arrive = this.g.getSquare()[i][j];
		astar= new Astar(g);
		path = astar.findPath(case_depart, case_arrive);
		g.getUniteClick().setPath(path);
		g.getUniteClick().timer.start();	
		g.getUniteClick().setComptePath(0);
	}
	
	public List<Node> getPath(){
		return this.path;
	}
	
	
}
