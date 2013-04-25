package MouseTrap;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;


public class Board extends JPanel {

	private static final int XSIZE = 20;
	private static final int YSIZE = 20;
	private ArrayList<BoardCell> boardCells;
	private Map<Integer, LinkedList<Integer>> adjMatrix;
	
	
	public Board() {
		boardCells = new ArrayList<BoardCell>();
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		setUpBoardCells();
		calcAdj();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (BoardCell cell : boardCells)
		{
			cell.draw(g);
		}
		
//		for(Player value: players.values()){
//			value.draw(g);
//		}			
	}
	
	public void setUpBoardCells() {
		for(int i = 0; i < XSIZE * YSIZE; i++) {
			boardCells.add(new BoardCell(i));
		}
	}
	
	public int calcIndex(int xCoord, int yCoord) {
		return yCoord*20 + xCoord;
	}
	
	public void calcAdj() {
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		for(BoardCell b : boardCells){
			LinkedList<Integer> tempList = new LinkedList<Integer>();
			int index = b.calcIndex();
			if (b.getxCoord()-1 >= 0) {
				int xMinus1 = calcIndex(b.getxCoord()-1, b.getyCoord());
				if(!boardCells.get(xMinus1).getBlocked()){
					tempList.add(xMinus1);
				}
			}
			if (b.getyCoord()-1 >= 0) {
				int yMinus1 = calcIndex(b.getxCoord(), b.getyCoord()-1);
				if(!boardCells.get(yMinus1).getBlocked()){
					tempList.add(yMinus1);
				}
			}
			if (b.getxCoord()+1 < XSIZE) {
				int xPlus1 = calcIndex(b.getxCoord()+1, b.getyCoord());
			//	System.out.println("xPlus1 is: " + xPlus1);
				if(!boardCells.get(xPlus1).getBlocked()){
					tempList.add(xPlus1);
				}
			}
			if (b.getyCoord()+1 < YSIZE) {
				int yPlus1 = calcIndex(b.getxCoord(), b.getyCoord()+1);
				if(!boardCells.get(yPlus1).getBlocked()){
					tempList.add(yPlus1);
				}
			}
			adjMatrix.put(index, tempList);
		}
	}


	public static int getXsize() {return XSIZE;}
	public static int getYsize() {return YSIZE;}
	public ArrayList<BoardCell> getBoardCells() {return boardCells;}
	public Map<Integer, LinkedList<Integer>> getAdjMatrix() {return adjMatrix;}
}
