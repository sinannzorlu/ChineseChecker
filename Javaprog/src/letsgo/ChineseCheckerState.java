package letsgo;

import java.util.ArrayList;
import java.util.List;


public class ChineseCheckerState extends BoardState{
	final int boardSize;
	private final CellState boardState[][];
	private List<BoardState> childList;
	public int turn =0;
	
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
	public boolean wins() {
		
		for(int x = 0; x < this.boardSize; x++){
			for(int y = 0; y < this.boardSize; y++){
				if ((CellState.W == this.boardState[x][y] && turn % 2 == 0) ||
					(CellState.B == this.boardState[x][y] && turn % 2 == 1)){
					if(!isInOpponentRegion(x, y))
						return false;
				}
			}
		}
		return true;
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
		this.childList.clear();

		for(int x = 0; x < this.boardSize; x++){
			for(int y = 0; y < this.boardSize; y++){
				if ((CellState.W == this.boardState[x][y] && player == player.One) ||
					(CellState.B == this.boardState[x][y] && player == player.Two)){
					//possibleMoves(x, y, player, this.boardState);
				}
			}
		}

		return this.childList;
	}		
		


	@Override
	public BoardState clone() throws CloneNotSupportedException {
		return new ChineseCheckerState(this);		
	}

	@Override
	public void possibleMoves(int x, int y, Player player,BoardState boardstate) {
		
		// Possible jumping move calculation
		possibleMovesHelper(x, y, player, 2, boardstate);

				// Possible sliding move calculation
				if (isPieceInTargetArea(x , y , player ))
					possibleMovesHelper(x, y, player, -1, boardstate);
				possibleMovesHelper(x, y, player, 1, boardstate);
		
		
	}
	public boolean isPieceInTargetArea(int x, int y, Player player){
		int min;
		int max;
		if (player == Player.One){
			 min = 0;
			 max = this.boardSize / 2 - 1;
		}
		else{
			 min = this.boardSize - (this.boardSize / 2 - 1);
			 max = this.boardSize;
		}
		return (x >= min && x < max) && (y >= min && y < max);

	}
	


	public void possibleMovesHelper(int x, int y, Player player, int jumpSize, BoardState boardstate) {
		
		int tempJumpSize = (player == Player.One ? jumpSize : -jumpSize);
		
		if(isMoveLegal(x, y, x-tempJumpSize, y)) {
			ChineseCheckerState childState = new ChineseCheckerState(this);
			childState.moveTile(x, y, x-tempJumpSize,y);
			
			this.childList.add(childState);
			if(jumpSize == 2)
				possibleMovesHelper(x-tempJumpSize, y, player, jumpSize, childState );
		}
		if(isMoveLegal(x, y, x, y-tempJumpSize)) {
			ChineseCheckerState childState = new ChineseCheckerState(this);
			childState.moveTile(x, y, x,y-tempJumpSize);
			
			this.childList.add(childState);
			if(jumpSize == 2)
				possibleMovesHelper(x, y-tempJumpSize, player, jumpSize, childState );						
		}
		
	}
	
	
    public void moveTile(int start_x, int start_y, int end_x, int end_y){
        if(start_x == -1 && start_y == -1 && end_x == -1 && end_y == -1 )
        	this.turn += 1;
        	
        if(isMoveLegal(start_x, start_y, end_x, end_y)){
            boardState[end_x][end_y] = boardState[start_x][start_y];
            boardState[start_x][start_y] = null;
            //boardState[end_x][end_y].addDistance(getCoveredDistance(start_x, start_y, end_x, end_y));
            if(wins()) {
            	System.out.println("Player "+((turn % 2) + 1) + " Won");           		           	
            	System.exit(0);
            		           		           		            	
            }
            	
            this.turn += 1;
                      
        }       
    }

    int getCoveredDistance(int start_x, int start_y, int end_x, int end_y){
        int x = Math.abs(start_x -end_x);
        int y = Math.abs(start_y-end_y);
        return x+y;
    }
    public boolean isMoveLegal(int start_x, int start_y, int end_x, int end_y){
        if(end_x>=boardSize || end_y>=boardSize){
            return false;
        }
        if(end_x<=0 || end_y <=0){
            return false;
        }
        if(boardState[end_x][end_y]!=null){
            return false;
        }
        if(isDiagonal(start_x, start_y, end_x, end_y)){
            return false;
        }

        if(getCoveredDistance(start_x, start_y,end_x,end_y)==1){

            if(isInOpponentRegion(start_x, start_y)){
                return true;
            }

            if(turn%2==0){
                return (end_x>start_x) || (end_y>start_y); //check direction for W

            } else if (turn%2==1) {
                return (end_x<start_x) || (end_y<start_y); // check direction for B

            }
        }else if(getCoveredDistance(start_x, start_y,end_x,end_y)==2){
                int x_middle = (start_x+end_x)/2;
                int y_middle = (start_y+end_y)/2;
                if(boardState[x_middle][y_middle] == null){
                    return false;
                }
                if(isInOpponentRegion(start_x,start_y)){
                    return true;
                }

            if(turn%2==0){
                return (end_x>start_x) || (end_y>start_y); //check direction for W

            } else if (turn%2==1) {
                return (end_x<start_x) || (end_y<start_y); // check direction for B

            }



        }
        return false;
    }

    boolean isInOpponentRegion(int x, int y){
    	int min;
		int max;
		if (turn%2==0){
			 min = 0;
			 max = this.boardSize / 2 - 1;
		}
		else{
			 min = this.boardSize - (this.boardSize / 2 - 1);
			 max = this.boardSize;
		}
		return (x > min && x < max) && (y >= min && y < max);
    }


    public boolean isDiagonal(int start_x, int start_y, int end_x, int end_y){
        int delta_x = end_x-start_x;
        int delta_y = end_y-start_y;
        return delta_x !=0 && delta_y!=0;
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

