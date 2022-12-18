package letsgo;

import java.util.List;

enum Player {One, Two};

public abstract class BoardState implements Cloneable{
	
	abstract public boolean wins();
	
	abstract boolean set(int x, int y, Player player);
	
	abstract public double getUtility(Player player);
	
	abstract public int estimatedDepth();
	
	abstract public void possibleMoves(int x, int y, Player player, BoardState boardstate);
	
	abstract public boolean isMoveLegal(int start_x, int start_y, int end_x, int end_y);
	
	abstract public List<BoardState> getChildList(Player player);
		
	abstract public BoardState clone() throws CloneNotSupportedException;

}
