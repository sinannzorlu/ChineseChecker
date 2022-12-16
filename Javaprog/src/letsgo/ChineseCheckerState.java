package letsgo;

import java.util.List;

public class ChineseCheckerState extends BoardState{
	final int boardSize;
	private final CellState boardState[][];
	
	ChineseCheckerState(int boardSize){
		this.boardSize = Math.max(boardSize, 5);		
		this.boardState = new CellState[boardSize][boardSize];		
	}
	
	ChineseCheckerState(ChineseCheckers state) {
		this.boardSize = state.boardSize;		
		this.boardState = new CellState[boardSize][boardSize];
		
		for (int y=0; y<boardSize; y++) {			
			for (int x=0; x<boardSize; x++) {
				this.boardState[y][x] = state.boardState[y][x];
			}
		}	
	}

	@Override
	public boolean wins(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean set(int x, int y, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getUtility(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int estimatedDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardState> getChildList(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardState clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardState> possibleMoves(int x, int y, Player player, boolean isJumped) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIllegal() {
		// TODO Auto-generated method stub
		return false;
	}

}
