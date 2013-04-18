import java.awt.Color;
import java.awt.Graphics;


public class BoardCell {
//boolean to show type whether wall or open
	private int xCoord, yCoord, index;
	private Color color;
	private boolean containsMouse, blocked, isEdge;
	
	
	public BoardCell() {
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g){
		
	}
	
	public boolean getBlocked() {
		return blocked;
	}
	
	public int calcIndex(int xCoord, int yCoord) {
		return 0;
	}
	
	public boolean containsClick(int xCoord, int yCoord) {
		return false;
	}
	
	public boolean containsMouse() {
		return false;
	}

	
}
