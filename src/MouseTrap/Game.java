
package MouseTrap;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends JFrame{
	public static Board board;
	
	private int score;

	public Game(){
		board  = new Board();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mouse Trap!!");
		setSize(620,640);
		//mouse = new Mouse(board.getXsize()/2, board.getYsize()/2);
		
		add(board,BorderLayout.CENTER);
//		validate();
//		repaint();
//		setMinimumSize(new Dimension(620, 640));
//		pack();
		
	}
	
	public void endGame() {
		
	}
	
	public void humanTurn() {
		//get input
		//call setTileBlocked
	}
	
	public void mouseTurn() {
		//mouse.move();
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
