package MouseTrap;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Board {

	private static final int XSIZE = 20;
	private static final int YSIZE = 20;
	private ArrayList<BoardCell> boardCells;
	private Map<Integer, LinkedList<Integer>> adjMatrix;
	
	
	public Board() {
		// TODO Auto-generated constructor stub
		boardCells = new ArrayList<BoardCell>();
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		setUpBoardCells();
		calcAdj();
	}
	
	public void setUpBoardCells() {
		for(int i = 0; i < XSIZE * YSIZE; i++) {
			boardCells.add(new BoardCell());
		}
	}
	
	
	public void paintComponent(Graphics g) {
		
	}
	
	public int calcIndex(int row, int col) {
		return 0;
	}
	
	public void calcAdj() {
		
	}


	public static int getXsize() {return XSIZE;}
	public static int getYsize() {return YSIZE;}
	public ArrayList<BoardCell> getBoardCells() {return boardCells;}
	public Map<Integer, LinkedList<Integer>> getAdjMatrix() {return adjMatrix;}
}
