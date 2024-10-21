import java.util.Arrays;

public class GenTree {

    // Inner class to represent Tic-Tac-Toe positions with board state and valuation
    public static class TicTacToePositions {
        private String board; // Stores the board configuration as a string
        private int valuation; // Stores the valuation of the position

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
        // Get the board configuration of the parent position
        String parentBoard = gameTree[parentIndex].getBoard();
        char[] parentArray = parentBoard.toCharArray(); // Convert board string to char array

        // Loop through each cell in the parent board to find empty spaces
        for (int i = 0; i < parentArray.length; i++) {
            if (parentArray[i] == ' ') { // Check if the cell is empty
                // Create a copy of the parent board and make the current player's move
                char[] childArray = Arrays.copyOf(parentArray, parentArray.length);
                childArray[i] = player;

                // Convert the updated char array back to a string for the child board
                String childBoard = new String(childArray);

                // Calculate the index for the child position in the gameTree array
                int childIndex = parentIndex * 9 + i + 1;

                // Create a new Tic-Tac-Toe position for the child board and store it in the array
                gameTree[childIndex] = new TicTacToePositions(childBoard);

                // Recursively generate children for the next player ('O' if current player is 'X')
                if (player == 'X') {
                    expandGameTree(childIndex, 'O');
                }
            }
        }
    }


    // Main method to run the program
    public static void main(String[] args) {
        GenTree genTree = new GenTree(); // Create a new instance of the GenTree

        // Generate children starting from the root node, with 'X' making the first move
        genTree.expandGameTree(0, 'X');

        // Print the first 100 positions in the gameTree array
        for (int i = 0; i < 100; i++) {
            TicTacToePositions t = genTree.get(i);
            if (t != null)
                System.out.printf("%2d) %s\n", i, t.getBoard()); // Print the index and board configuration
            else
                System.out.printf("%2d) Empty\n", i); // Print 'Empty' for null positions
        }
    }
}