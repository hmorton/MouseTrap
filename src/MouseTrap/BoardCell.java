package MouseTrap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class BoardCell {
	//boolean to show type whether wall or open
	static final int CELLSIZE=30;
	private int xCoord, yCoord, index;
	private Color color= Color.CYAN;
	private boolean containsMouse=false, blocked = false, isEdge = false;



	public BoardCell(int index) {
		this.index = index;
		xCoord = index%20;
		yCoord = index/20;
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

	public boolean containsClick(int mouseX, int mouseY) {
		Rectangle rect = new Rectangle(CELLSIZE*xCoord,CELLSIZE*yCoord,CELLSIZE,CELLSIZE);
		if (rect.contains(new Point(mouseX, mouseY))) {
			return true;
		}else return false;

	}

	public boolean containsMouse() {
		return false;
	}

	public void setBlocked(boolean b) {
		blocked = b;
		if(b){
			color = Color.GRAY;
		}
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
