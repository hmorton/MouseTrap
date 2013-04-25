package MouseTrap;

public class Game {// JFrame running the game
	public Board board;
	public Mouse mouse;
	private int score;

	public Game() {
		// TODO Auto-generated constructor stub
		board  = new Board();
		mouse = new Mouse();
	}
	
	public void endGame() {
		
	}
	
	public void humanTurn() {
		//get input
		//call setTileBlocked
	}
	
	public void mouseTurn() {
		mouse.move();
	}
	
	public void decrementScore() {
	}
	
	public void setTileBlocked(int index) {
		board.getBoardCells().get(index).setBlocked(true);
	}

}
