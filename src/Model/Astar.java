package Model;

import java.util.LinkedList;
import java.util.List;
import View.Case;
import View.Panel_Grid;

public class Astar {
	private Panel_Grid grille;
	
	public Astar(Panel_Grid grille) {
		this.grille = grille;	
	}
	
	public Node getNode(int x, int y){
		if (x >= 0 && x < grille.getNum_square() && y >= 0 && y < grille.getNum_square()){
			return grille.getNode(x,y);
			
		} else {
			return null;
		}
	} 
	
	//a list containing all of the visited Nodes, from the goal to the start
	private List<Node> calcPath(Node start, Node goal) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node c = goal;
		boolean done = false;
		while (!done){
			path.addFirst(c);
			c = c.getParent();
			if (c.equals(start)){
				done = true;
			}
		}
		return path;
	}
	
	//The Node with the lowest F score in the list
	private Node lowestFInList(List<Node> list){
		Node cheapest = list.get(0);
		for (int i = 0; i < list.size(); i++){
			if (list.get(i).getF() < cheapest.getF()){
				cheapest = list.get(i);
			}
		}
		return cheapest;
	}
	
	//A LinkedList with Nodes adjacent to the given Node if those exist, are walkable and are not already in the closed list
	private List<Node> getAdjacent(Node c, List<Node> closedList){
		List<Node> adjacentNodes = new LinkedList<Node>();
		int x = c.getCoordinate().getX();
		int y = c.getCoordinate().getY();

		Node adjacent;

		// Check left Node
		if (x > 0){
			adjacent = getNode(x-1, y);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)){
				adjacentNodes.add(adjacent);
			}
		}

		// Check right Node
		if (x < grille.getNum_square()){
			adjacent = getNode(x + 1, y);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)){
				adjacentNodes.add(adjacent);
			}
		}

		// Check top Node
		if (y > 0){
			adjacent = this.getNode(x, y - 1);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)){
				adjacentNodes.add(adjacent);
			}
		}

		// Check bottom Node
		if (y < grille.getNum_square()){
			adjacent = this.getNode(x, y + 1);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent)){
				adjacentNodes.add(adjacent);
			}
		}
		return adjacentNodes;
	}

	
	public final List<Node> findPath(Case case_start, Case case_goal){
		int x_start = case_start.getNode().getCoordinate().getX();
		int y_start = case_start.getNode().getCoordinate().getY();
		int x_goal = case_goal.getNode().getCoordinate().getX();
		int y_goal = case_goal.getNode().getCoordinate().getY();
		if (x_start == x_goal && y_start == y_goal){
			return new LinkedList<Node>();// Return an empty path, because we don't need to move at all.
		}
		
		List<Node> openList = new LinkedList<Node>(); // The set of Nodes already visited.			
		List<Node> closedList = new LinkedList<Node>();// The set of currently discovered Nodes still to be visited.
		
		openList.add(case_start.getNode()); // Add starting Node to open list.
		
		//This loop will be broken as soon as the current Node position is equal to the goal position
		while (true){		
			Node current = lowestFInList(openList);// Gets Node with the lowest F score from open list.		
			openList.remove(current);// Remove current Node from open list.
			closedList.add(current);// Add current Node to closed list.

			// If the current Node position is equal to the goal position ...
			if ((current.getCoordinate().getX() == x_goal) && (current.getCoordinate().getY() == y_goal)){	
				return calcPath(case_start.getNode(), current);// Return a LinkedList containing all of the visited Nodes.
			}

			List<Node> adjacentNodes = getAdjacent(current, closedList);
			for (Node adjacent : adjacentNodes){
				if (!openList.contains(adjacent)){// If Node is not in the open list ...
					adjacent.setParent(current);// Set current Node as parent for this Node.
					adjacent.setH(case_goal.getNode());// Set H costs of this Node (estimated costs to goal).
					adjacent.setG(current);// Set G costs of this Node (costs from start to this Node).
					openList.add(adjacent);// Add Node to openList.
				}
				// Else if the Node is in the open list and the G score from
				// current Node is cheaper than previous costs ...
				else if (adjacent.getG() > adjacent.calculateG(current)){
					adjacent.setParent(current);// Set current Node as parent for this Node.				
					adjacent.setG(current);// Set G costs of this Node (costs from start to this Node).
				}
			}

			// If no path exists ...
			if (openList.isEmpty()){
				// Return an empty list.
				return new LinkedList<Node>();
			}
			// But if it does, continue the loop.
		
			
	}
}

}

