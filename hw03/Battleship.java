import java.util.Scanner;

public class Battleship {
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	private static final int NUM_SHIPS = 5;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char[][][] map = new char[2][NUM_ROWS][NUM_COLS], fireMap = new char[2][NUM_ROWS][NUM_COLS];
		for (int i = 0; i < 2; i++) for (int j = 0; j < NUM_ROWS; j++) for (int k = 0; k < NUM_COLS; k++)
			map[i][j][k] = fireMap[i][j][k] = '-';

		System.out.println("Welcome to Battleship!");
		int p = 0;
		do {
			System.out.printf("\nPLAYER %d, ENTER YOUR SHIPS’ COORDINATES.\n", p % 2 + 1);
			printBattleShip(getInitPos(input, p % 2, 1, map));
			p++;
		} while (p < 2);

		int[] shipsLeft = {NUM_SHIPS, NUM_SHIPS};
		while (shipsLeft[0] > 0 && shipsLeft[1] > 0) {
			getFirePos(input, p % 2, map, fireMap, shipsLeft);
			printBattleShip(fireMap[p % 2]);
			p++;
		}

		System.out.printf("PLAYER %d WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!\n\n",
				shipsLeft[0] > shipsLeft[1] ? 1 : 2);
		System.out.println("Final Boards:");
		for (int i = 0; i < 2; i++) {
			System.out.println();
			printBattleShip(map[i]);
		}
	}

	private static char[][] getInitPos(Scanner input, int player, int location, char[][][] map) {
		while (location < NUM_SHIPS + 1) {
			try {
				System.out.printf("Enter ship %d location:\n", location);
				String[] tmp = input.nextLine().split(" ");
				int r = Integer.parseInt(tmp[0]), c = Integer.parseInt(tmp[1]);
				if (map[player % 2][r][c] == '-') {
					map[player % 2][r][c] = '@';
					location++;
				} else if (map[player % 2][r][c] == '@') {
					System.out.println("You already have a ship there. Choose different coordinates.");
				}
			} catch (NumberFormatException | IndexOutOfBoundsException e) {
				System.out.println("Invalid coordinates. Choose different coordinates.");
			}
		}
		return map[player % 2];
	}

	private static void getFirePos(Scanner i, int p, char[][][] m, char[][][] fireMap, int[] shipsLeft) {
		boolean validInput = false;
		while (!validInput) {
			try {
				System.out.printf("Player %d, enter hit row/column:\n", p%2 + 1);
				String[] tmp = i.nextLine().split(" ");
				int r = Integer.parseInt(tmp[0]), c = Integer.parseInt(tmp[1]);
				if (fireMap[p % 2][r][c] == 'O' || fireMap[p % 2][r][c] == 'X') {
					System.out.println("You already fired on this spot. Choose different coordinates.");
				} else if (m[(p + 1) % 2][r][c] == '@') {
					System.out.printf("PLAYER %d HIT PLAYER %d’s SHIP!\n", p%2 + 1, (p%2 + 1) % 2 + 1);
					m[(p + 1) % 2][r][c] = fireMap[p % 2][r][c] = 'X';
					shipsLeft[(p + 1) % 2]--;
					validInput = true;
				} else {
					System.out.printf("PLAYER %d MISSED!\n", p%2 + 1);
					m[(p + 1) % 2][r][c] = fireMap[p % 2][r][c] = 'O';
					validInput = true;
				}
			} catch (NumberFormatException | IndexOutOfBoundsException e) {
				System.out.println("Invalid coordinates. Choose different coordinates.");
			}
		}
	}

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < NUM_ROWS; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < NUM_COLS; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}
