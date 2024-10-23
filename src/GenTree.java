import java.util.Arrays;

public class GenTree {

    // Inner class to represent Tic-Tac-Toe positions with board state and valuation
    public static class TicTacToePositions {
        private String board;
        private int valuation;

        // Constructor initializes the board with a string and sets the valuation to 0
        public TicTacToePositions(String s) {
            this.board = s;
            this.valuation = 0;
        }

        // Getter method to retrieve the board configuration
        public String getBoard() {
            return board;
        }

        // Getter method to retrieve the valuation of the position
        public int getValuation() {
            return valuation;
        }

        // Setter method to update the valuation of the position
        public void setValuation(int valuation) {
            this.valuation = valuation;
        }
    }

    private int capacity; // Stores the maximum capacity of the gameTree array
    private TicTacToePositions[] gameTree; // Array to hold all the generated Tic-Tac-Toe positions

    // Constructor initializes the gameTree with a predefined capacity and sets up the root node
    public GenTree() {
        // Set a high capacity to accommodate many possible game positions
        capacity = 500_000_000;
        gameTree = new TicTacToePositions[capacity];

        // Create the root node representing the blank Tic-Tac-Toe board
        gameTree[0] = new TicTacToePositions("         ");
    }

    // Method to retrieve a Tic-Tac-Toe position at a specific index
    public TicTacToePositions get(int index) {
        return gameTree[index];
    }

    // Method to generate child positions for a given parent position and the current player
    public void expandGameTree(int parentIndex, char player) {
        String parentBoard = gameTree[parentIndex].getBoard();

        // Loop through each cell to find empty spaces and generate child positions
        for (int i = 0; i < parentBoard.length(); i++) {
            if (parentBoard.charAt(i) == ' ') {
                String childBoard = generateChildBoard(parentBoard, i, player);
                int childIndex = calculateChildIndex(parentIndex, i);

                gameTree[childIndex] = new TicTacToePositions(childBoard);

                // Recursively generate children for the other player
                expandGameTree(childIndex, switchPlayer(player));
            }
        }
    }

    // Generates a new board with the player's move at the given index.
    private String generateChildBoard(String board, int index, char player) {
        char[] childArray = board.toCharArray();
        childArray[index] = player;
        return new String(childArray);
    }

    // Calculates the index for the child position in the game tree.
    private int calculateChildIndex(int parentIndex, int moveIndex) {
        return parentIndex * 9 + moveIndex + 1;
    }

    // Switches the current player between 'X' and 'O'.
    private char switchPlayer(char player) {
        return (player == 'X') ? 'O' : 'X';
    }

    // Main method to run the program
    public static void main(String[] args) {
        GenTree genTree = new GenTree(); // Create a new instance of the GenTree

        // Generate children starting from the root node, with 'X' making the first move
        genTree.expandGameTree(0, 'X');

        // Print the first 100 positions in the gameTree array
        for (int i = 0; i < 100; i++) {
            TicTacToePositions position = genTree.get(i);
            if (position != null)
                System.out.printf("%2d) %s\n", i, position.getBoard()); // Print the index and board configuration
            else
                System.out.printf("%2d) Empty\n", i); // Print 'Empty' for null positions
        }
    }
}