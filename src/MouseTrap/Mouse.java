package MouseTrap;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Mouse extends JPanel{

	private ArrayList<BoardCell> path;

	private ImageIcon icon;
	private Image pic;

	private int xCoord, yCoord;
	private int shortestPathLength;
	static final int PATHLENGTHTOLERANCE = 3;	//difference between path length and smallest path length
	private boolean[] visited;
	LinkedList<LinkedList<Integer>> potentialPaths;

	public Mouse() {//Jpanel for mouse picture
		potentialPaths = new LinkedList<LinkedList<Integer>>();
		xCoord = 10-1;
		yCoord = 10-1;
		icon = createImageIcon("/MouseTrap/image/mouse.gif");
		pic = icon.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		revalidate();
		repaint();
		visited = new boolean[Game.board.getXsize()*Game.board.getYsize()];
	}

	public Mouse(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;

		icon = createImageIcon("/MouseTrap/image/mouse.gif");
		pic = icon.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		revalidate();
		repaint();
	}

	public void draw(Graphics g) {
		g.drawImage(pic, xCoord*30-10, yCoord*30-10,  this);
	}

	public void selectLocation(){
//		Game.board.calcAdj();
		LinkedList<Integer> possibleMoves = Game.board.getAdjMatrix().get(calcIndex(xCoord, yCoord));
		int dfTop, dfBot, dfLeft, dfRight;
		dfTop = (int)Math.pow((20 - yCoord),5);
		dfBot = (int)Math.pow((20 - (Game.board.getYsize() - yCoord)),5) + dfTop;
		dfLeft = (int)Math.pow((20 - xCoord),5) + dfBot;
		dfRight = (int)Math.pow((20 -(Game.board.getXsize() - xCoord)),5) +dfLeft;
		Random randNums = new Random();
		int randInt = randNums.nextInt(dfRight);
		boolean done = false;
		if(randInt < dfTop){
			if(!Game.board.getBoardCells().get(calcIndex(xCoord, yCoord-1)).getBlocked()){
				done = true;
				move(xCoord,yCoord-1);
			}
		}
		else if(randInt < dfBot){
			if(!Game.board.getBoardCells().get(calcIndex(xCoord, yCoord+1)).getBlocked()){
				done = true;
				move(xCoord,yCoord+1);

			}

		}
		else if (randInt < dfLeft){
			if(!Game.board.getBoardCells().get(calcIndex(xCoord - 1, yCoord)).getBlocked()){
				done = true;
				move(xCoord - 1,yCoord);


			}

		}
		else if (randInt < dfRight){
			if(!Game.board.getBoardCells().get(calcIndex(xCoord+ 1, yCoord)).getBlocked()){
				done = true;
				move(xCoord + 1,yCoord);

			}

		}
		if(!done){
			selectLocation();
		}


	}


	public void move(int xCoord, int yCoord) {
		if(!Game.board.getBoardCells().get(calcIndex(xCoord, yCoord)).getBlocked()) {
			this.xCoord = xCoord;
			this.yCoord = yCoord;
		}
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imageURL = MousePic.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return new ImageIcon(imageURL);
		}
	}

	public int xFromIndex(int tempIndex){return tempIndex%20;}
	public int yFromIndex(int tempIndex){return tempIndex/20;}
	public int getxCoord() {return xCoord;}
	public int getyCoord() {return yCoord;}
	public void setxCoord(int xCoord) {this.xCoord = xCoord;}
	public void setyCoord(int yCoord) {this.yCoord = yCoord;}
	public int calcIndex(int xCoord, int yCoord) {return yCoord*20 + xCoord;}
}
