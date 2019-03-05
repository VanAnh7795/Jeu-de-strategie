package Model;

import Model.Coordinate;
import View.Case;


public class Node {
	private Coordinate coor;
	private boolean walkable;//If the node is not a obstacle and can be walked through
	private Node parent;//Used for when getting the resulting path. The node prior to this node
	private int g; //The cost of getting from the first node to this node
	private int h; //A heuristic that estimates the cost of the cheapest path from this node to the goal
	private Case square;
	
	public Node(Coordinate coor){
		this.coor = coor;
	}	
	
	public void setCase(Case square) {
		this.square = square;
	}
	
	public Case getCase() {
		return this.square;
	}
	
	public Coordinate getCoordinate() {
		return coor;
	}

	public void setCoor(Coordinate coor) {
		this.coor = coor;
	}
	
	public int getG(){
		return g;
	}
	
	//Sets the G score based on the parent's G score and the movement cost
	public void setG(Node parent){
		g = (parent.getG() + 1);
	}
	
	public int calculateG(Node parent){
		return (parent.getG() + 1);
	}
	
	// goal: The final node on the path
	public void setH(Node goal){
		h = (Math.abs(this.coor.getX() - goal.coor.getX()) + Math.abs(this.coor.getY() - goal.coor.getY()));
	}
	
	public int getH(){
		return h;
	}
	
	//G score + H score
	public int getF(){
		return g + h;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	//True if the node is not a obstacle and can be walked through, false otherwise
	public boolean isWalkable() {
		return walkable;
	}
	
	//Sets if the node is not a obstacle and can be walked through
	public void setWalkable(boolean walkable){
		this.walkable = walkable;
	}
	
}
	
	