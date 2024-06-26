import java.util.Scanner;

public class Main {
    private static char[][] board;
    private static char currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            board = new char[3][3];
            currentPlayer = 'X';
            initializeBoard();
            playGame(scanner);
            playAgain = askPlayAgain(scanner);
            if (playAgain) {
                System.out.println("Thank you for playing! Starting a new game...\n");
            }
        } while (playAgain);

        System.out.println("Thank you for playing! Goodbye!");
        scanner.close();
    }

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if the board is full
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for a win
    private static boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Check the rows for a win
    private static boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    // Check the columns for a win
    private static boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    // Check the diagonals for a win
    private static boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    // Check if all three values are the same (and not empty) indicating a win
    private static boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Change the player from X to O or O to X
    private static void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Make a move at the specified location
    private static boolean placeMove(int row, int col) {
        if (row >= 0 && col >= 0 && row < 3 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // The main game loop
    private static void playGame(Scanner scanner) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Players take turns entering their move (row and column) as two numbers separated by a space.");
        System.out.println("The board positions are indexed from 0 to 2.");

        while (true) {
            printBoard();
            int row, col;
            do {
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter two numbers between 0 and 2.");
                    scanner.next();
                }
                row = scanner.nextInt();
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter two numbers between 0 and 2.");
                    scanner.next();
                }
                col = scanner.nextInt();
            } while (!placeMove(row, col));
            if (checkForWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a tie!");
                break;
            }
            changePlayer();
        }
    }

    // Ask if the players want to play again
    private static boolean askPlayAgain(Scanner scanner) {
        System.out.println("Do you want to play again? (yes/no)");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y");
    }
}
