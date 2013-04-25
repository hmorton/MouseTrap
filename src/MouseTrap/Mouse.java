package MouseTrap;
import java.awt.Graphics;
import java.util.ArrayList;


public class Mouse {
	
	private Board board;
	private ArrayList<BoardCell> path;
	private int xCoord, yCoord;
	
	public Mouse() {//Jpanel for mouse picture
		board = new Board();
		path = new ArrayList<BoardCell>();
	}
	
	public Mouse(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	


	public void draw(Graphics g) {
		
	}
	




	public void choosePath() {
		
	}
	
	public void chooseTarget() {
		
	}
	
	public void move() {
		
	}

	public int getxCoord() {return xCoord;}
	public int getyCoord() {return yCoord;}
	public void setxCoord(int xCoord) {this.xCoord = xCoord;}
	public void setyCoord(int yCoord) {this.yCoord = yCoord;}
}
