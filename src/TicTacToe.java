import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3; // Size of the Tic Tac Toe board (3x3)
    private static final char BLANK = '-'; // Character representing an empty space on the board
    private static final char PLAYER_X = 'X'; // Character representing Player X
    private static final char PLAYER_O = 'O'; // Character representing Player O

    private char[][] board; // 2D array representing the Tic Tac Toe board

    // Constructor initializes the game board
    public TicTacToe() {
        board = new char[SIZE][SIZE]; // Create the board of given size
        initializeBoard(); // Call method to fill the board with blanks
    }

    // Initializes the board to be empty
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) { // Iterate through rows
            for (int j = 0; j < SIZE; j++) { // Iterate through columns
                board[i][j] = BLANK; // Set each cell to BLANK
            }
        }
    }

    // Prints the current state of the board to the console
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) { // Iterate through rows
            for (int j = 0; j < SIZE; j++) { // Iterate through columns
                System.out.print(board[i][j]); // Print the cell value

                if (j < SIZE - 1) {
                    System.out.print(" | "); // Print column separator
                }
            }
            System.out.println(); // Move to the next line after each row

            if (i < SIZE - 1) {
                System.out.println("-----------"); // Print row separator
            }
        }
        System.out.println(); // Extra line for spacing
    }

    // Prints an example board using numbers instead of X and O
    public void printExampleBoard() {
        for (int count = 0; count < SIZE * SIZE; count++) { // Iterate through all positions
            System.out.print(count); // Print the position number

            if ((count + 1) % SIZE != 0) { // Check if it's not the end of a row
                System.out.print(" | "); // Print column separator
            }

            if ((count + 1) % SIZE == 0) { // Check if it's the end of a row
                System.out.println(); // Move to the next line
                if (count < SIZE * SIZE - 1) {
                    System.out.println("-----------"); // Print row separator
                }
            }
        }
        System.out.println(); // Extra line for spacing
    }

    // Makes a move for the player at the specified position
    public boolean makeMove(int position, char player) {
        // Check if the position is valid and empty
        if (position < 0 || position >= SIZE * SIZE || board[position / SIZE][position % SIZE] != BLANK) {
            return false; // Invalid move
        }

        // Place the player's symbol on the board
        board[position / SIZE][position % SIZE] = player;
        return true; // Successful move
    }

    // Checks if the specified player has won
    private boolean checkWin(char player) {
        // Check rows and columns for a win
        for (int i = 0; i < SIZE; i++) {
            boolean rowWin = true;
            boolean colWin = true;

            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != player) {
                    rowWin = false; // Current row doesn't have a win
                }
                if (board[j][i] != player) {
                    colWin = false; // Current column doesn't have a win
                }
            }

            if (rowWin || colWin) {
                return true; // Win found
            }
        }

        // Check both main diagonal and anti-diagonal for a win
        boolean mainDiagonalWin = true;
        boolean antiDiagonalWin = true;

        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) {
                mainDiagonalWin = false; // Main diagonal doesn't have a win
            }
            if (board[i][SIZE - i - 1] != player) {
                antiDiagonalWin = false; // Anti-diagonal doesn't have a win
            }
        }
        return mainDiagonalWin || antiDiagonalWin; // Return true if either diagonal has a win
    }

    // Checks if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == BLANK) {
                    return false; // At least one space is empty
                }
            }
        }
        return true; // Board is full
    }

    // Makes a move for the AI using the minimax algorithm
    public void makeAIMove() {
        int bestScore = Integer.MIN_VALUE; // Initialize best score
        int bestMovePosition = -1; // Initialize best move position
        char currentPlayer = PLAYER_O; // Set AI player

        for (int position = 0; position < SIZE * SIZE; position++) {
            int row = position / SIZE; // Calculate row index
            int col = position % SIZE; // Calculate column index

            if (board[row][col] == BLANK) { // Check if the position is empty
                board[row][col] = currentPlayer; // Simulate the move
                int score = minimax(board, 0, false); // Evaluate move using minimax
                board[row][col] = BLANK; // Undo the move

                if (score > bestScore) { // Check for a better score
                    bestScore = score; // Update best score
                    bestMovePosition = position; // Update best move position
                }
            }
        }

        // Place the AI's best move on the board
        if (bestMovePosition != -1) {
            int bestRowMove = bestMovePosition / SIZE; // Calculate row index
            int bestColumnMove = bestMovePosition % SIZE; // Calculate column index
            board[bestRowMove][bestColumnMove] = currentPlayer; // Place AI move on board
        }
    }

    // Minimax algorithm to evaluate the best move for the current player
    public int minimax(char[][] currentBoard, int depth, boolean maximizingPlayer) {
        char currentPlayer = maximizingPlayer ? PLAYER_O : PLAYER_X; // Determine current player

        // Check if the game has ended with a win or a draw
        if (checkWin(PLAYER_X)) return -1; // If X wins, return negative score
        if (checkWin(PLAYER_O)) return 1;  // If O wins, return positive score
        if (isBoardFull()) return 0;       // If it's a draw, return 0

        // Maximizing player's turn
        if (maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE; // Initialize max evaluation score

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (currentBoard[i][j] == BLANK) { // Check for empty spaces
                        currentBoard[i][j] = currentPlayer; // Simulate move
                        int eval = minimax(currentBoard, depth + 1, false); // Recursively evaluate
                        currentBoard[i][j] = BLANK; // Undo the move
                        maxEvaluation = Math.max(maxEvaluation, eval); // Update max evaluation
                    }
                }
            }
            return maxEvaluation; // Return the best evaluation score
        } else { // Minimizing player's turn
            int minEvaluation = Integer.MAX_VALUE; // Initialize min evaluation score

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (currentBoard[i][j] == BLANK) { // Check for empty spaces
                        currentBoard[i][j] = currentPlayer; // Simulate move
                        int eval = minimax(currentBoard, depth + 1, true); // Recursively evaluate
                        currentBoard[i][j] = BLANK; // Undo the move
                        minEvaluation = Math.min(minEvaluation, eval); // Update min evaluation
                    }
                }
            }
            return minEvaluation; // Return the best evaluation score
        }
    }

    // Checks if the game is over
    public boolean isGameOver() {
        // Check if there is a win or if the board is full
        return checkWin(PLAYER_X) || checkWin(PLAYER_O) || isBoardFull();
    }

    // Main method to run the game
    public static void main(String[] args) {
        System.out.println("Perfect TicTacToe - Dominic Rucker\n");
        TicTacToe game = new TicTacToe(); // Create a new TicTacToe game
        try (Scanner scanner = new Scanner(System.in)) { // Try-with-resources

            game.printExampleBoard(); // Display example board

            // Main game loop
            while (!game.isGameOver()) {
                int position = -1;
                boolean validMove = false;

                System.out.println("Your move (X):");

                do {
                    try {
                        System.out.print("What position (0-8)? ");
                        position = scanner.nextInt(); // Get user input

                        if (position < 0 || position >= 9) {
                            System.out.println("Out of range, please enter a number between 0 and 8.");
                            continue; // Skip to next iteration if out of range
                        }

                        validMove = game.makeMove(position, PLAYER_X); // Attempt move
                        if (!validMove) {
                            System.out.println("That position is already taken. Try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input, please enter a number between 0 and 8.");
                        scanner.nextLine(); // Clear invalid input
                    }
                } while (!validMove); // Repeat until a valid move is made

                System.out.println();

                if (game.isGameOver()) break; // Exit if game is over

                game.makeAIMove(); // Make AI move
                game.printBoard(); // Display updated board
            }

            // Display game result
            if (game.checkWin(PLAYER_X)) {
                System.out.println("Player X wins!");
            } else if (game.checkWin(PLAYER_O)) {
                System.out.println("Player O wins!");
            } else {
                System.out.println("It's a draw!");
            }
        }
    }
}