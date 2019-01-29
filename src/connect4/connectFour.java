package connect4;

// project imports
import java.util.*;

public class connectFour {
	
	// Initialize board and player variables to be used in the game
	static Scanner input = new Scanner(System.in);
	static char playBoard[][] = new char[6][7];
	static char playerToken = 'O';
	
	public static void main(String[] args) {
		
		// Game-play settings and logic loop
		playBoard = popBoard(playBoard, '~');
		
		while(true) {
			dispBoard(playBoard);
			System.out.println("-----------------------------");
			System.out.println("! 1 ! 2 ! 3 ! 4 ! 5 ! 6 ! 7 !");
			System.out.println("It's your turn, Player " + playerToken + ".  Enter the number of the column you wish to drop your token: ");
			int y = input.nextInt();
			
			if(tryMove(playBoard, y - 1, playerToken)) {
				if(winCheck(playBoard)) {
					System.out.println("Congratulations to Player " + playerToken + "! You're the winner!");
					dispBoard(playBoard);
					return;
				}
				playerToken = changePlayer(playerToken);
			}
			
			
		}

	}
	
	// Populate board with open board positions since there can't be any truly "empty" spaces
	public static char[][] popBoard(char[][] playBoard, char boardChar) {
		for (int x = 0; x < playBoard.length; x++) {
			java.util.Arrays.fill(playBoard[x], 0, playBoard[x].length, boardChar);
		}
		return playBoard;
	}
	
	// Show the board to the players
	public static void dispBoard(char[][] playBoard) {
		System.out.println();
		for (int x = 0; x < playBoard.length; x++) {
			System.out.print("!");
			for (int y = 0; y < playBoard[x].length; y++) {
				System.out.print(" " + playBoard[x][y] + " !");
			}
			System.out.println();
		}
	}
	
	// Attempt the player's proposed move
	public static boolean tryMove(char[][] playBoard, int y, char playerToken) {
		boolean findings = false;
		// first check to see that the entered column number (y) is a valid move choice
		if(y < 0 || y > 7) {
			System.out.println("Incorrect column choice! You must select a column number between 1 and 7.");
			return false;
		} else if(playBoard[0][y] != '~') {
			// next check to see if the vertical line containing the desired move is full
			System.out.println("Full column! Select another to drop your piece.");
			return false;
		}
		
		//player "drops" their piece into the lowest available spot
		for (int x = playBoard.length - 1; x >= 0; x--) {
			if(playBoard[x][y] == '~') {
				playBoard[x][y] = playerToken;
				return true;
			}
		}
		return findings;
	}
	
	// check for win conditions in all possible cases (horiz, vert, diag1, diag2)
	// iterate through both char loops and check to see if 4 tokens in any row match each other
	public static boolean winCheck(char[][] playBoard) {
		boolean check = false;
		
		// win check for horizontal rows (x-axis)
		for (int x = 0; x < playBoard.length; x++) {
			for (int y = 0; y < playBoard[x].length - 3; y++) {
				if (playBoard[x][y] != '~' && playBoard[x][y] == playBoard[x][y + 1] && playBoard[x][y] == playBoard[x][y + 2] && playBoard[x][y] == playBoard[x][y + 3]) {
				return true;
				}
			}
		}
		
		// win check for vertical columns (y-axis)
		for (int y = 0; y < playBoard[0].length; y++) {
			for (int x = 0; x < playBoard.length - 3; x++) {
				if (playBoard[x][y] != '~' && playBoard[x][y] == playBoard[x + 1][y] && playBoard[x][y] == playBoard[x + 2][y] && playBoard[x][y] == playBoard[x + 3][y]) {
				return true;
				}
			}
		}
		
		// win check for diagonal starting in top-right
		for (int x = 0; x < playBoard.length - 3; x++) {
			for (int y = 3; y < playBoard[x].length; y++) {
				if(playBoard[x][y] != '~' && playBoard[x][y] == playBoard[x + 1][y - 1] && playBoard[x][y] == playBoard[x + 2][y - 2] && playBoard[x][y] == playBoard[x + 3][y - 3]) {
				return true;
				}
			}
		}
		
		// win check for the opposite diagonal, starting in top-left
		for (int x = 0; x < playBoard.length - 3; x++) {
			for (int y = 0; y < playBoard[x].length; y++) {
				if(playBoard[x][y] != '~' && playBoard[x][y] == playBoard[x - 1][y + 1] && playBoard[x][y] == playBoard[x - 2][y + 2] && playBoard[x][y] == playBoard[x - 3][y + 3]) {
				return true;
				}
			}
		}
		return check;
	}
	
	// change the player based on current token
	public static char changePlayer(char playerNow) {
		if (playerNow == 'O') {
			return 'X';
		} else {
			return 'O';
		}
	}
}
