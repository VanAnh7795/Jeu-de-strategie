package View;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import Model.Node;
import Model.Coordinate;
import Controller.GrilleKeyListener;
import Model.PathFinding;
import Model.State;
import Controller.SquareMouseListener;
import Model.Unite;

@SuppressWarnings("serial")
public class Panel_Grid extends JComponent implements State{
	public final static int WIDTH = 704;
	public final static int HEIGHT = 716;
	public static int GOLD = 100;
	private static final int num_square = 21;/*Numero case chaque ligne et colonne*/
	private static final int num_arbre  = 10;
	private static final int num_or  = 10;
	private static final int num_boueuse  = 10;
	private static final int num_tele  = 10;
	private PathFinding pathFinding = new PathFinding(this);
	private ArrayList<Unite> list_unit =  new ArrayList<Unite>();
	private ArrayList<ArrayList<Integer>> list_arbre = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> list_or = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> list_tele = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> list_boueuse = new ArrayList<ArrayList<Integer>>();
	private SquareMouseListener squareMouseListener = new SquareMouseListener(this, pathFinding);
	private GrilleKeyListener key = new GrilleKeyListener(this);
	
	public ArrayList<Unite> getList_unit(){
		return this.list_unit;
	}
	
	public int getNum_square() {
		return num_square;
	}
	
	private Case[][] square = new Case[num_square][num_square];
	private Node[][] nodes= new Node[num_square][num_square];
	private Unite UniteClick;
	
	public Case[][] getSquare() {
		return this.square;
	}
	public Node getNode(int x, int y) {
		return this.nodes[x][y];
	}

	public Unite getUniteClick() {
		return this.UniteClick;
	}
	 
	public void setUniteClick(Unite unite ) {
		this.UniteClick = unite;
	}
	
	public void setVisible(int x, int y) {
		square[x][y].setOpaque(true);
	}

	//afficher unite dans case quand ordonnee d'unite = coordonne de case
	private void afficheUnite(ArrayList<Unite> list_unit) {
				for(int k = 0; k < list_unit.size(); k++) {
					int i = list_unit.get(k).getCoor().getX();
					int j = list_unit.get(k).getCoor().getY();
						Panel_Unite unite_panel= new Panel_Unite(this, list_unit.get(k));
						square[i][j].add(unite_panel);
						setVisible(i,j);
						unite_panel.set_square(square[i][j]);			
				}
	}
	
	public void creationUnite() {
		if(GOLD < 50) {
			System.out.println("Il n'a pas assez l'or");
		} else {		
			int x = (int)(Math.random() * 4 + 0);
			int y = (int)(Math.random() * 4 + 0);
			while((x == 1 && y == 1) || (x == 2 && y == 2) || (x == 1 && y == 2) || (x == 2 && y == 1))  {
			 x = (int)(Math.random() * 4 + 0);
			 y = (int)(Math.random() * 4 + 0);
			}
				
			Unite u = new Unite(this, new Coordinate(x,y));
			list_unit.add(u);
			square[x][y].setOpaque(true);
			Panel_Unite unite_panel= new Panel_Unite(this, u);
			square[x][y].add(unite_panel);
			unite_panel.set_square(square[x][y]);
			System.out.println("Number of unit : " + list_unit.size());
			GOLD -= 50;
			System.out.println("Total gold: " + GOLD);
		}
	}
	
	@Override
	public void recolteurAction(int arriveX, int arriveY) {	
			pathFinding.useAstar(arriveX,arriveY);
	}
	
	public int NumberUnite() {
		return list_unit.size();
	}
	
	public void setElement(){
		ArrayList<ArrayList<Integer>> total = new ArrayList<ArrayList<Integer>>();
		for (int n=0; n<(num_arbre+num_or+num_tele+num_boueuse); n++) {
			boolean check = false;
		    while (!check) {
			 Random r1 = new Random();
			 Random r2 = new Random();
			 int x = 4 + r1.nextInt(num_square - 5 - 4); //choisir aleatoirement coordonnee 4 =< x & y <= num_square - 5
			 int y = 4 + r2.nextInt(num_square - 5 - 4);
			 int count = 0;
			for (int i = 0; i < total.size(); i++) {
				if (x == total.get(i).get(0) & y == total.get(i).get(1)) {
					break;
				} else {
					count += 1;
				}
			}
				if (count == total.size()) {
					check = true;
					ArrayList<Integer> coor = new ArrayList<Integer>();
					coor.add(x);
					coor.add(y);
					total.add(coor);
				}							
			}
		}
		
		for (int n = 0; n < num_arbre; n++) {
			list_arbre.add(total.get(n));
		}
		
		for (int n = num_arbre; n<num_or+num_arbre; n++) {
			list_or.add(total.get(n));
		}
		
		for (int n = num_or+num_arbre; n<num_or+num_arbre+num_tele; n++) {
			list_tele.add(total.get(n));
		}
		
		for (int n = num_or+num_arbre+num_tele; n < num_or+num_arbre+num_tele+num_boueuse; n++) {
			list_boueuse.add(total.get(n));
		}
	}
	
//    private void drawString(Graphics g, String text, int x, int y) {
//        for (String line : text.split("\n"))
//            g.drawString(line, x, y += g.getFontMetrics().getHeight());
//    }
//    
//    public void paintComponent(Graphics g) {
//    	super.paintComponent(g);
//    	g.setColor(Color.RED);
//		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
//		drawString(g,"Player 1-----Number of unit: " + list_unit.size() + "     Gold:     \nPress C to buy a unit(cost 50 gold/unit)\nPress R to collect a gold --Recolteur--\nPress S to perform a guard action(cost 20 gold/action) --Sentinelle--\nPress A to attack(cost 30 gold/action) --Attanquant--",0,0);
//  }
	
	//constructeur
	public Panel_Grid() {
		super();
		this.setLayout(new GridLayout(num_square,num_square));
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		addKeyListener(key);
		setFocusable(true);		
		setElement();
		for (int i = 0; i < num_square; i++) {
			for (int j = 0; j < num_square; j++) {	
				nodes[i][j]= new Node(new Coordinate(i,j));
				if(((i>0 &i<3) & (j>0&j<3)) || ((i<num_square-2 & i>num_square-5) & (num_square-2>j & j>num_square-5))){
					if ((i ==1 & j ==1) || (i==num_square-3 & j==num_square-3)) {
						square[i][j]=new Maison(this, nodes[i][j]);
					}else {
						square[i][j]=new Autour_Maison(this, nodes[i][j]);
					}
				}
				else {
					square[i][j] = new Case_Normale(this, nodes[i][j]);
					square[i][j].addMouseListener(squareMouseListener);
					square[i][j].setOpaque(false);//au debut background n'est pas visible
					for (int n=0; n<num_arbre; n++) {
						if(i == list_arbre.get(n).get(0) & j == list_arbre.get(n).get(1)) {
							square[i][j] = new Case_Arbre(this,  nodes[i][j]);
						}
					}
					for (int n=0; n<num_or; n++) {
						if(i == list_or.get(n).get(0) & j == list_or.get(n).get(1)) {
							square[i][j] = new Case_Or(this, nodes[i][j]);
							square[i][j].addMouseListener(squareMouseListener);
						}
					}
					for (int n=0; n<num_tele; n++) {
						if(i == list_tele.get(n).get(0) & j == list_tele.get(n).get(1)) {
							square[i][j] = new Case_Teleporteur(this, nodes[i][j]);
							square[i][j].addMouseListener(squareMouseListener);
						}
					}
					for (int n=0; n<num_boueuse; n++) {
						if(i == list_boueuse.get(n).get(0) & j == list_boueuse.get(n).get(1)) {
							square [i][j] = new Case_Boueuse(this, nodes[i][j]);
							square[i][j].addMouseListener(squareMouseListener);
						}
					}
			}
				this.add(square[i][j]); //add chaque case dans grille
			}
		}
		list_unit.add(new Unite (this, new Coordinate(3,3)));
		list_unit.add(new Unite (this, new Coordinate(3,3)));
		list_unit.add(new Unite (this, new Coordinate(3,3)));
		afficheUnite(list_unit);
	}
}
	
