
package MouseTrap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MouseTrap.Board.BoardListener;

public class Game extends JFrame{
	public static Board board;
	public static boolean ongoing;
	private int score;

	public Game(){
		board  = new Board(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mouse Trap!!");
		setSize(620,640);
		ongoing = true;
		
		add(board,BorderLayout.CENTER);
	}
	
	public void endGame(boolean win) {
		if(win) {
			JOptionPane p = new JOptionPane();
			p.showMessageDialog(board, "Congratulations, you have won! ", "Good job!", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		} else {
			JOptionPane p = new JOptionPane();
			p.showMessageDialog(board, "Better luck next time!", "Condolences...", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		}
	}
	
	public void humanTurn() {
		//get input
		//call setTileBlocked
	}
	
	public void mouseTurn() {
		//mouse.move();
	}
	
	public void pathFinder() {
		if(board.mouse.getxCoord() == 0||board.mouse.getyCoord() == 0||board.mouse.getxCoord() == Board.getXsize()-1||board.mouse.getyCoord() == Board.getYsize()-1)
		{
			endGame(false);
		}
		else
		{
			if(Game.board.getAdjMatrix().get(board.calcIndex(board.mouse.getxCoord(),board.mouse.getyCoord())).size() == 0) {
				endGame(true);
			} else
				board.mouse.selectLocation();
		}
	}
	
	public void decrementScore() {
	}
	
	public void setTileBlocked(int index) {
		board.getBoardCells().get(index).setBlocked(true);
	}
	
	public static void main(String[] args) {
		Game newGame = new Game();
		newGame.setVisible(true);
	}

}
