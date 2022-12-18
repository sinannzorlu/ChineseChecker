package letsgo;

public class Main {

	public static void main(String[] args) {
		int boardSize = 8;
		ChineseCheckerState boardState = new ChineseCheckerState(boardSize);
		
		// create empty board
		System.out.println("Initial board state");
		System.out.println("-------------------");
		System.out.println(boardState);
		System.out.println();

	}

}
