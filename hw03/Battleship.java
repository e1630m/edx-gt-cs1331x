import java.util.Scanner;

public class Battleship {
    private static void drawMap(int[][][] arr, int p, int y, int x, char opt) {
        int board = opt != 'o' ? p : (p + 1) % 2;
        System.out.printf("  ");
        for (int c = 0; c < 5; c++)
            System.out.printf("%d ", c);
        System.out.printf("\n");
        for (int r = 0; r < 5; r++) {
            System.out.printf("%d ", r);
            for (int c = 0; c < 5; c++) {
                char cha = arr[board][r][c] > 0 && opt != 'o' ? '@': '-';
                if (opt == 'o') {
                    if (arr[board][r][c] < 0 || (r == y && c == x))
                        cha = 'O';
                    if (arr[board][r][c] > 0 && r == y && c == x)
                        cha = 'X';
                } else {
                    if (arr[board][r][c] == -1)
                        cha = 'O';
                    if (arr[board][r][c] == -2)
                        cha = 'X';
                }
                System.out.printf("%c ", cha);
            }
            System.out.printf("\n");
        }
    }

    private static boolean countShips(int[][][] arr, char opt) {
        int[] numShips = new int[2];
        for (int p = 0; p < arr.length; p++)
            for (int r = 0; r < arr[p].length; r++)
                for (int c = 0; c < arr[p][r].length; c++)
                    if (arr[p][r][c] > 0)
                        numShips[p]++;
        if (opt == 'w') {
            if (numShips[0] > numShips[1])
                return false;
            return true;
        }
        if (numShips[0] != 0 && numShips[1] != 0)
            return true;
        return false;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][][] players = new int[2][5][5];
        System.out.println("Welcome to Battleship!");
        int i = 1, j = 1;
        do {
            System.out.printf("PLAYER %d, ENTER YOUR SHIPS’ COORDINATES.\n", j);
            do {
                System.out.printf("Enter ship %d location: \n", i);
                try {
                    String in = input.nextLine();
                    int r = Integer.parseInt(in.split(" ")[0]), c = Integer.parseInt(in.split(" ")[1]);
                    if (players[j - 1][r][c] == 0) {
                        players[j - 1][r][c] = 1;
                        i++;
                    } else {
                        System.out.println("You already have a ship there. Choose different coordinates.");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                }
            } while (i < 6);
            Battleship.drawMap(players, j - 1, 0, 0, 'm');
            j++;
            i = 1;
        } while (j < 3);
        int p = 0;
        do {
            System.out.printf("Player %d, enter hit row/column: \n", p % 2 + 1);
            try {
                String in = input.nextLine();
                int r = Integer.parseInt(in.split(" ")[0]), c = Integer.parseInt(in.split(" ")[1]);
                int oppo = players[(p + 1) % 2][r][c];
                if (oppo >= 0) {
                    if (oppo > 0)
                        System.out.printf("PLAYER %d HIT PLAYER %d’s SHIP!\n", p % 2 + 1, (p % 2 + 1) % 2 + 1);
                    else
                        System.out.printf("PLAYER %d MISSED!\n", p % 2 + 1);
                    Battleship.drawMap(players, p % 2, r, c, 'o');
                    if (players[(p + 1) % 2][r][c] > 0)
                        players[(p + 1) % 2][r][c] = -2;
                    else
                        players[(p + 1) % 2][r][c] = -1;
                    p++;
                } else {
                    System.out.println("You already fired on this spot. Choose different coordinates.");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
            }
        } while (countShips(players, 'm'));
        input.close();
        int winner = countShips(players, 'w') ? 1 : 0;
        System.out.printf("PLAYER %d WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!\n\n\n", winner + 1);
        System.out.println("\nFinal boards:\n");
        for (int pl = 0; pl < 2; pl++) {
            Battleship.drawMap(players, pl, 0, 0, 'e');
            System.out.println(" ");
        }
    }
}
