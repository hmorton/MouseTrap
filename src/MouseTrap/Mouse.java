package MouseTrap;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Mouse extends JPanel{
	
	private Board board;
	private ArrayList<BoardCell> path;

	
	private ImageIcon icon;
	private Image pic;
	
	private int xCoord, yCoord;
	private int shortestPathLength;
	static final int PATHLENGTHTOLERANCE = 2;	//difference between path length and smallest path length
	private boolean[] visited;
	LinkedList<LinkedList<Integer>> potentialPaths;

	public Mouse() {//Jpanel for mouse picture
		//	path = new ArrayList<BoardCell>();
		potentialPaths = new LinkedList<LinkedList<Integer>>();
		 xCoord = 10-1;
		 yCoord = 10-1;
		icon = createImageIcon("/MouseTrap/image/mouse.gif");
		pic = icon.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		revalidate();
		repaint();
	}

	public Mouse(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		
		icon = createImageIcon("/MouseTrap/image/mouse.gif");
		pic = icon.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		revalidate();
		repaint();
	}

	public void draw(Graphics g) {
		g.drawImage(pic, xCoord*30-10, yCoord*30-10,  this);
	}


	public void pathFinder() {
		if(xCoord == 0||yCoord == 0||xCoord == Board.getXsize()-1||yCoord == Board.getYsize()-1)
		{
			//LOSE CONDITION!!
		}
		else
		{
		potentialPaths.clear();									//delete any previous potential paths
		calcPathInitializer(calcIndex(xCoord,yCoord));			//call function to generate potential paths using calc index to convert coords
		LinkedList<Integer> chosenPath=chooseFromPaths();		//call function to pick the best path from potential paths
		move(chosenPath.getFirst()/20,chosenPath.getFirst()%20);//move to first position of chosen path
		}
	}
	
	private void calcPathInitializer(int startLocation) {
		for (int i = 0; i < Board.getXsize()*Board.getYsize(); i++) {
			visited[i] = false;
		}
		LinkedList<Integer> posibleMoves = new LinkedList<Integer>();
		LinkedList<Integer> path = new LinkedList<Integer>();
		visited[startLocation] = true;
		posibleMoves = Game.board.getAdjMatrix().get(startLocation);
		calcPathRecursive(path, posibleMoves);
	}

	private void calcPathRecursive(LinkedList<Integer> path,LinkedList<Integer> posibleMoves) {
		for (int curCell : posibleMoves) {
			if (visited[curCell] == false) 
			{
				visited[curCell] = true;
				path.addLast(curCell);

				if(Game.board.getBoardCells().get(curCell).getxCoord() == 0			//if one of the border cells ie trigger condition
						||Game.board.getBoardCells().get(curCell).getyCoord() == 0
						||Game.board.getBoardCells().get(curCell).getxCoord() == Board.getXsize()-1
						||Game.board.getBoardCells().get(curCell).getyCoord() == Board.getYsize()-1)
				{
					if(path.size()<shortestPathLength)								//check if path is shorter than current shortest path
					{
						potentialPaths.add(path);									//add the path to potential paths
						for(LinkedList<Integer> i : potentialPaths)					//check through the previously stored paths and delete all that are now obsolete
						{
							if(i.size() > shortestPathLength + PATHLENGTHTOLERANCE)	//checking if old paths are larger than smallest + our acceptable difference
							{
								potentialPaths.remove(i);							//deleting paths that are no longer short enough
							}
						}
					}
					else															//else only if path is longer than current shortest
					{
						if(path.size() <shortestPathLength + PATHLENGTHTOLERANCE)   //check if within tolerance to be added to potentials and add
						{
							potentialPaths.add(path);
						}															//if not within tolerance then it is ignored and not added to potential paths
					}
				}
				else																//if not at trigger condition
				{
					calcPathRecursive(path, Game.board.getAdjMatrix().get(curCell));//call recursive function by passing in path and adjacency matrix of current location	
				}
				path.removeLast();			//resets as it cascades out
				visited[curCell] = false;	//resets as it cascades out
			}
		}
	}
	
	private LinkedList<Integer> chooseFromPaths ()		// goes through the potential paths and picks one that is the most "open"
	{
		int largestGap = 0;								//Gap is the smallest opening that is in a path, largestGap is the largest gap that any path in potential paths has
		for (LinkedList<Integer> e: potentialPaths)		//first loop finds the largest gap present in all potential paths
		{
			if(gapFinder(e)>largestGap)					
			{
				largestGap=gapFinder(e);
			}
		}
		
		for (LinkedList<Integer> e: potentialPaths)		//second loop removes all paths that have smaller "gaps" than the largest gap path
		{
			if(gapFinder (e)<largestGap)
			{
				potentialPaths.remove(e);
			}
		}
		
		Random randomGenerator = new Random();			//randomly chooses from remaining paths
		int randomChosenPath = randomGenerator.nextInt(potentialPaths.size());
		return potentialPaths.get(randomChosenPath);
	}
	
	private int gapFinder(LinkedList<Integer> checkPath)
	{
		int smallestGap=50;
		for(int i=1; i<checkPath.size();i++)
		{
			int cellGap = 0;
			int checkLoc = checkPath.get(i);			//get index of current cell
			int prevLoc = checkPath.get(i+1);			//get index of next cell in the path
			LinkedList<Integer> checkAdj = Game.board.getAdjMatrix().get(checkLoc);
			
			if (checkLoc==prevLoc+1||checkLoc == prevLoc-1)	//if moving left or right
			{
				if(checkAdj.contains(checkLoc+20))
				{
					cellGap++;
				}
				if(checkAdj.contains(checkLoc-20))
				{
					cellGap++;
				}
			}
			else if (checkLoc == prevLoc+20||checkLoc == prevLoc-20)				//if moving up or down
			{
				if(checkAdj.contains(checkLoc+1))
				{
					cellGap++;
				}
				if(checkAdj.contains(checkLoc-1))
				{
					cellGap++;
				}
			}
			
			if (cellGap<smallestGap)
				{smallestGap = cellGap;}
			//find direction of next movement
			//check adj list to see how many spots are open not in the direction of movement or backwards (left/right from n cell)
			//if smaller than smallest gap then update smallest gap value
			
		}
		return smallestGap;
	}
	public void move(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imageURL = MousePic.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
	}

	public int getxCoord() {return xCoord;}
	public int getyCoord() {return yCoord;}
	public void setxCoord(int xCoord) {this.xCoord = xCoord;}
	public void setyCoord(int yCoord) {this.yCoord = yCoord;}
	public int calcIndex(int xCoord, int yCoord) {return yCoord*20 + xCoord;}
}
