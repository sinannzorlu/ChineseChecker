package letsgo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int boardSize = 8;
		ChineseCheckerState boardState = new ChineseCheckerState(boardSize);
		
		// create empty board
//		System.out.println("Initial board state");
//		System.out.println("-------------------");
//		System.out.println(boardState);
//		System.out.println();
		
		
		Scanner sc= new Scanner(System.in);
		while(true) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			boardState.moveTile(x, y, a, b);
						
			
			System.out.println(boardState);			
			
		}		
		
		
		
		
		
		
		

	}

}
