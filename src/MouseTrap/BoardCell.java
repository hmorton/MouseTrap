package MouseTrap;
import java.awt.Color;
import java.awt.Graphics;


public class BoardCell {
//boolean to show type whether wall or open
	static final int CELLSIZE=30;
	private int xCoord, yCoord, index;
	private Color color=Color.YELLOW;
	private boolean containsMouse, blocked, isEdge;
	
	
	public BoardCell() {
	}
	
	public BoardCell(int index) {
		this.index = index;
		xCoord = index/20;
		yCoord = index%20;
	}
	
	void draw(Graphics g) {
		g.setColor(color);
	    g.fillRect (CELLSIZE*xCoord,CELLSIZE*yCoord,CELLSIZE,CELLSIZE);
	    
	    g.setColor(Color.BLACK);
	    g.drawRect (CELLSIZE*xCoord,CELLSIZE*yCoord,CELLSIZE,CELLSIZE);
	}
	
	public boolean getBlocked() {
		return blocked;
	}
	
	public boolean containsClick(int xCoord, int yCoord) {
		return false;
	}
	
	public boolean containsMouse() {
		return false;
	}

	public void setBlocked(boolean b) {
		blocked = b;
	}
	
	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}
	
	public int calcIndex() {
		return xCoord + 20*yCoord;
	}

	
}
