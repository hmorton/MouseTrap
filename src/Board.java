import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


public class Board {

	private static final int XSIZE = 10;
	private static final int YSIZE = 10;
	private ArrayList<BoardCell> boardCells;
	private Map<Integer, LinkedList<Integer>> adjMatrix;
	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void paintComponent(Graphics g) {
		
	}
	
	public int calcIndex(int xCoord, int yCoord) {
		return 0;
	}
	
	public void calcAdj() {
		
	}

}
