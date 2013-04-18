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
	Game game;
	Board b;

	@Before
	public void setup() {
		game = new Game();
		b = new Board();
	}
	
	@Test
	public void testAdjacencies() {
		b.calcAdj();
		Map<Integer, LinkedList<Integer>> testMap = b.getAdjMatrix();
		
		//testing for the upper left hand corner
		LinkedList<Integer> testList = testMap.get(0);
		assertTrue(testList.contains(1));
		assertTrue(testList.contains(20));
		assertTrue(testList.size() == 2);
		
		//test on the right hand side
		testList = testMap.get(39);
		assertTrue(testList.contains(19));
		assertTrue(testList.contains(38));
		assertTrue(testList.contains(59));
		assertTrue(testList.size() == 3);
		
		//test with an adjacency on all sides
		testList = testMap.get(365);
		assertTrue(testList.contains(345));
		assertTrue(testList.contains(385));
		assertTrue(testList.contains(366));
		assertTrue(testList.contains(364));
		assertTrue(testList.size() == 4);
	}
	
	@Test
	public void testCalcIndex() {
		int testRow, testCol, testIndex;
		
		//test somewhere that is not on an edge
		testRow = 3;
		testCol = 7;
		testIndex = 67;
		assertEquals(testIndex, b.calcIndex(testRow, testCol));
		
		//test on an edge
		testRow = 0;
		testCol = 10;
		testIndex = 10;
		assertEquals(testIndex, b.calcIndex(testRow, testCol));
	}
	
	@Test
	public void testAdjWithPillar() {
		b.calcAdj();
		Map<Integer, LinkedList<Integer>> testMap = b.getAdjMatrix();
		ArrayList<BoardCell> testCellList = b.getBoardCells();
		
		//test somewhere in the middle of the board with one blocked cell
		LinkedList<Integer> testList = testMap.get(44);
		BoardCell blockedCell = testCellList.get(45);
		blockedCell.setBlocked(true);
		
		assertTrue(testList.contains(43));
		assertTrue(testList.contains(24));
		assertTrue(testList.contains(64));
		assertEquals(testList.size(), 3);
		
		//test somewhere near an edge with the edge cell blocked off
		testList = testMap.get(81);
		blockedCell = testCellList.get(80);
		blockedCell.setBlocked(true);
		
		assertTrue(testList.contains(61));
		assertTrue(testList.contains(101));
		assertTrue(testList.contains(82));
		assertEquals(testList.size(), 3);
		
		//test somewhere that has every possible adjacency blocked off
		testList = testMap.get(135);
		blockedCell = testCellList.get(134);
		blockedCell.setBlocked(true);
		blockedCell = testCellList.get(136);
		blockedCell.setBlocked(true);
		blockedCell = testCellList.get(114);
		blockedCell.setBlocked(true);
		blockedCell = testCellList.get(154);
		blockedCell.setBlocked(true);
		
		assertEquals(testList.size(), 0);
	}
	
}
