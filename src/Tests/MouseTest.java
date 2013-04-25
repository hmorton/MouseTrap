package Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import MouseTrap.*;

//note: testAdjacencies and testAdjWithPillar have errors because
//some needed lists and/or maps have not been initialized yet

public class MouseTest {
	Game testGame;
	// Board b;  //should be automatically created when game constructor called

	@Before
	public void setup() {
		testGame = new Game();
		
	//	b = new Board(); //should be automatically created when game constructor called
	}
	
	@Test
	public void testBoardSize()
	{
		assertEquals(testGame.board.getXsize(),20);
		assertEquals(testGame.board.getYsize(),20);
		assertEquals(testGame.board.getBoardCells().size(),400);
	}
	
	@Test
	public void testMouseInitialization()
	{
		assertEquals(testGame.mouse.getxCoord(),10); //don't know board size might need to be changed (!!!!!!)
		assertEquals(testGame.mouse.getyCoord(),10);  
	}
	
	@Test
	public void testAdjacencies() {
		//b.calcAdj();  		Handled when game constructor calls board constructor (after every human turn also?)
		//Map<Integer, LinkedList<Integer>> testMap = b.getAdjMatrix();
		
		//testing for the upper left hand corner
		LinkedList<Integer> testList = testGame.board.getAdjMatrix().get(0);
		assertTrue(testList.contains(1));
		assertTrue(testList.contains(20));
		assertTrue(testList.size() == 2);
		
		//test on the right hand side
		testList = testGame.board.getAdjMatrix().get(39);
		assertTrue(testList.contains(19));
		assertTrue(testList.contains(38));
		assertTrue(testList.contains(59));
		assertTrue(testList.size() == 3);
		
		//test with an adjacency on all sides
		testList = testGame.board.getAdjMatrix().get(365);
		assertTrue(testList.contains(345));
		assertTrue(testList.contains(385));
		assertTrue(testList.contains(366));
		assertTrue(testList.contains(364));
		assertTrue(testList.size() == 4);
	}
	
	@Test
	public void testAdjWithPillar() {
		//b.calcAdj();		Handled when game constructor calls board constructor
		Map<Integer, LinkedList<Integer>> testMap = testGame.board.getAdjMatrix();
		ArrayList<BoardCell> testCellList = testGame.board.getBoardCells();
		
		//test somewhere in the middle of the board with one blocked cell
		
		BoardCell blockedCell = testCellList.get(45);
		blockedCell.setBlocked(true);
		testGame.board.calcAdj();
		LinkedList<Integer> testList = testGame.board.getAdjMatrix().get(44);
		System.out.println(testGame.board.getBoardCells().get(45).getBlocked());
		assertTrue(testList.contains(43));
		assertTrue(testList.contains(24));
		assertTrue(testList.contains(64));
		assertEquals(testList.size(), 3);
		
		//test somewhere near an edge with the edge cell blocked off
		
		blockedCell = testCellList.get(80);
		blockedCell.setBlocked(true);
		testGame.board.calcAdj();
		testList = testGame.board.getAdjMatrix().get(81);
		
		assertTrue(testList.contains(61));
		assertTrue(testList.contains(101));
		assertTrue(testList.contains(82));
		assertEquals(testList.size(), 3);
		
		//test somewhere that has every possible adjacency blocked off
		
		blockedCell = testCellList.get(134);
		blockedCell.setBlocked(true);
		blockedCell = testCellList.get(136);
		blockedCell.setBlocked(true);
		blockedCell = testCellList.get(115);
		blockedCell.setBlocked(true);
		blockedCell = testCellList.get(155);
		blockedCell.setBlocked(true);
		
		testGame.board.calcAdj();
		testList = testGame.board.getAdjMatrix().get(135);
		//System.out.println(testList.size());
		for(Integer i : testList) {
			System.out.print(i + " ");
		}
		assertEquals(testList.size(), 0);
	}
	
	@Test
	public void testCalcIndex() {
		int testRow, testCol, testIndex;
		
		//test somewhere that is not on an edge
		testRow = 3;
		testCol = 7;
		testIndex = 67;
		assertEquals(testIndex, testGame.board.calcIndex(testCol, testRow));
		
		//test on an edge
		testRow = 0;
		testCol = 10;
		testIndex = 10;
		assertEquals(testIndex, testGame.board.calcIndex(testCol, testRow));
	}
	

	
	@Test
	public void testMouseMovement()
	{
		testGame.mouse.setxCoord(50);
		testGame.mouse.setyCoord(50);
		testGame.mouseTurn();
		assertTrue(
				(testGame.mouse.getxCoord()==49 && testGame.mouse.getyCoord()==50)
				||
				(testGame.mouse.getxCoord()==51 && testGame.mouse.getyCoord()==50)
				||
				(testGame.mouse.getxCoord()==50 && testGame.mouse.getyCoord()==49)
				||
				(testGame.mouse.getxCoord()==50 && testGame.mouse.getyCoord()==51)
				);
	}
	

	
	@Test
	public void testSetBlock()
	{
		testGame.setTileBlocked(9);
		assertTrue(testGame.board.getBoardCells().get(9).getBlocked());
		
		testGame.setTileBlocked(18);
		assertTrue(testGame.board.getBoardCells().get(18).getBlocked());
		
		testGame.setTileBlocked(18);
		assertTrue(testGame.board.getBoardCells().get(18).getBlocked());
	}
}
