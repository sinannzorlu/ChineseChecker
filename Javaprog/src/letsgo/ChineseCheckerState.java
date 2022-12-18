package letsgo;

import java.util.ArrayList;
import java.util.List;


public class ChineseCheckerState extends BoardState{
	final int boardSize;
	private final CellState boardState[][];
	
	ChineseCheckerState(int boardSize){
		this.boardSize = Math.max(boardSize, 5);		
		this.boardState = new CellState[boardSize][boardSize];
		
		// board configuration for different boardSize
		int pieceSize ;		 
		// initialize the board with player 1 and 2 pieces		
	    if(boardSize%2==0) {
	    	pieceSize = (boardSize/2) -1 ;
	    	for (int i = 0; i < pieceSize; i++) {
	  	      for (int j = 0; j < pieceSize; j++) {
	  	        boardState[i][j] = CellState.B;
	  	      }
	  	    }
	  	    for (int i = boardSize-pieceSize; i < boardSize; i++) {
	  	      for (int j = boardSize-pieceSize; j < boardSize; j++) {
	  	        boardState[i][j] = CellState.W;
	  	      }
	  	    }
	    }
	    else {
	    	pieceSize = (boardSize/2) ;
	    	for (int i = 0; i < pieceSize; i++) {
		  	      for (int j = 0; j < pieceSize; j++) {
		  	    	  if(i==pieceSize-1&&j==pieceSize-1)
		  	    		  continue;
		  	    	  else
		  	    		boardState[i][j] = CellState.B;
		  	        
		  	      }
		  	    }
		  	    for (int i = boardSize-pieceSize; i < boardSize; i++) {
		  	      for (int j = boardSize-pieceSize; j < boardSize; j++) {
		  	    	if(i==boardSize-pieceSize&&j==boardSize-pieceSize)
		  	    		  continue;
		  	    	  else
		  	    		boardState[i][j] = CellState.W;
		  	      }
		  	    }	    		    	
	    }
	}
	
	ChineseCheckerState(ChineseCheckerState state) {
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
		if (x>=0 && x<boardSize && y>=0 && y<boardSize && boardState[y][x] == null) {
    		boardState[y][x] = (player == Player.One ? CellState.B : CellState.W);
    				
    		return true;
    	}
		else
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
		List<BoardState> children = new ArrayList<BoardState>();
		
		CellState playerState = (player == Player.One ? CellState.B : CellState.W);
		
		for (int y=0; y<boardSize; y++) {
			for (int x=0; x<boardSize; x++) {
				if (boardState[y][x] == null) {
					ChineseCheckerState childState = new ChineseCheckerState(this);
					childState.boardState[y][x] = playerState;
					children.add(childState);
				}
			}
		}		
		
		return children;		
	}

	@Override
	public BoardState clone() throws CloneNotSupportedException {
		return new ChineseCheckerState(this);		
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
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i <boardSize; i++) {
	        for (int j = 0; j <boardSize; j++) {
	        	if (boardState[i][j] == null)
					sb.append(".");
	        	else
					sb.append(boardState[i][j].toString());
	        }
	        sb.append('\n');
		}
		return sb.toString();
		
	}
	
}

