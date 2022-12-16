package letsgo;

import java.util.List;

enum Player {One, Two};

public abstract class BoardState implements Cloneable{
	
	abstract public boolean wins(Player player);
	
	abstract boolean set(int x, int y, Player player);
	
	abstract public double getUtility(Player player);
	
	abstract public int estimatedDepth();
	
	abstract public List<BoardState> possibleMoves(int x, int y, Player player, boolean isJumped);
	
	abstract public boolean isIllegal();
	
	abstract public List<BoardState> getChildList(Player player);
		
	abstract public BoardState clone() throws CloneNotSupportedException;

}
