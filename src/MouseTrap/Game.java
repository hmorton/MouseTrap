package MouseTrap;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame{
	public Board board;
	public Mouse mouse;
	private int score;

	public Game(){
		// TODO Auto-generated constructor stub
		board  = new Board();
		mouse = new Mouse(board.getXsize()/2, board.getYsize()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mouse Trap!!");
		setSize(620,640);
		
		add(board,BorderLayout.CENTER);
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
