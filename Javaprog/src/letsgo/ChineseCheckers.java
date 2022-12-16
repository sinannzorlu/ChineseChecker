package letsgo;
import java.util.Scanner;

public class ChineseCheckers {
		
	public int boardSize;
	public CellState[][] boardState;
	
	
	 public static void main(String[] args) {
		 	
		    int[][] board = new int[8][8];
		    int player = 1;
		    Scanner scan = new Scanner(System.in);
		    
		    // initialize the board with player 1 and 2 pieces
		    for (int i = 0; i < 3; i++) {
		      for (int j = 0; j < 3; j++) {
		        board[i][j] = 1;
		      }
		    }
		    for (int i = 5; i < 8; i++) {
		      for (int j = 5; j < 8; j++) {
		        board[i][j] = 2;
		      }
		    }

		    // Game loop
		    while (true) {
		      // Print the board
		      for (int i = 0; i < 8; i++) {
		        for (int j = 0; j < 8; j++) {
		          System.out.print(board[i][j] + " ");
		        }
		        System.out.println();
		      }
		      System.out.println();

		      // Get the player's piece 
		      
		      System.out.print("Player " + player + ", enter your piece that you want to move (row column): ");
		      int row = scan.nextInt();
		      int col = scan.nextInt();

		      // Validate the move		     
		      if (row < 0 || row >= 8 || col < 0 || col >= 8) {
		        System.out.println("Invalid piece(Out of Board's size). Try again.");
		        continue;
		      }
		      if (board[row][col] != player) {
			    System.out.println("Invalid piece(This piece is not yours). Try again.");
			    continue;
			  }

		      // Make the move
		      board[row][col] = 0;
		      System.out.print("Player " + player + ", enter your move where you want to go (row column): ");
		      // new row and column for destination
		      int newRow = scan.nextInt();
		      int newCol = scan.nextInt();
		      while(newRow>7 || newRow<0 || newCol>7 || newCol<0) {
		    	   System.out.println("Invalid Move ! Try again: ");
		    	   newRow = scan.nextInt();
			       newCol = scan.nextInt();		    	  
		      }     
		           // this side of logic checks whether moves horizontal or not---this side of logic checks whether moves vertical or not  
		      while(!((((newRow == row+1)||(newRow == row-1)) && (newCol == col))||((newRow == row) && ((newCol == col+1)||(newCol == col-1))))) {
		    	  System.out.println("Your piece must move horizontal or vertical. Try again: ");
		    	  newRow = scan.nextInt();
			      newCol = scan.nextInt();	
		      }
		           // if there is a piece where you want go you can't move
		      while(board[newRow][newCol] != 0) {
		    	  System.out.println("There is a piece over there. Try again: ");
		    	  newRow = scan.nextInt();
			      newCol = scan.nextInt();
		      }
		    	  
		      
		      board[newRow][newCol] = player;

		      // Switch players
		      player = (player == 1 ? 2 : 1);
		    }
		  }

	
}
