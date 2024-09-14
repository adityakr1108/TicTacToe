
import java.util.InputMismatchException;
import java.util.Scanner;
public class tac{
    private static final char EMPTY = ' ';
    private static final char X = 'X';
    private static final char O = 'O';
    private static char[][] board = new char[3][3];
    private static char currentPlayer = X;

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Print the board
    private static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    // Check if the current player has won
    private static boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    // Check if the board is full (draw)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Switch player turns
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == X) ? O : X;
    }

    // Handle a player's move
    private static void makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
            System.out.println("This move is not valid, please try again.");
        } else {
            board[row][col] = currentPlayer;
            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                System.exit(0);
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw!");
                System.exit(0);
            } else {
                switchPlayer();
            }
        }
    }

    // Get and validate user input
    private static int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int col = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    validInput = true;
                } else {
                    System.out.println("Row and column must be between 0 and 2. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers only.");
                scanner.next(); // Clear the invalid input
            }
        }

        return new int[]{row, col};
    }

    public static void main(String[] args) {
        initializeBoard();
        System.out.println("Tic-Tac-Toe Game!");
        printBoard();

        while (true) {
            int[] move = getPlayerMove();
            makeMove(move[0], move[1]);
            printBoard();
        }
    }
}