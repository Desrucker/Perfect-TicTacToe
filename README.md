
# **Perfect TicTacToe**

![Java](https://img.shields.io/badge/Java-11%2B-blue.svg)  
A command-line **Tic-Tac-Toe** game in Java where the player faces off against an AI using the **minimax algorithm** for perfect play.

---

## **Features**
- 🎮 **Player vs AI Gameplay**: Player X competes against AI O.
- 🧠 **AI uses Minimax Algorithm**: AI makes optimal moves.
- 🛑 **Invalid Move Prevention**: Players cannot overwrite existing moves.
- 🎯 **Automatic Win, Loss, or Draw Detection**.
- 🖥️ **Clear board display** after every move.

---

## **Gameplay**
You play as **Player X**. Use the **reference board** below to select your move by entering a number between **0-8**:

```
 0 | 1 | 2
-----------
 3 | 4 | 5
-----------
 6 | 7 | 8
```

**Game Example:**

```
 X | O | X
-----------
 O | X | -
-----------
 - | O | X
```

- The game ends when:
  - A player wins (three in a row, column, or diagonal).
  - The board fills up, resulting in a **draw**.

---

## **How to Run**

1. **Clone the Repository:**
   ```bash
   git clone <repository_url>
   cd Perfect-TicTacToe
   ```

2. **Compile the Game:**
   ```bash
   javac src/TicTacToe.java
   ```

3. **Run the Game:**
   ```bash
   java -cp src TicTacToe
   ```

4. **Play the Game:**
   - Enter a position number from **0 to 8** when prompted.
   - The game will show the updated board after each move.
   - The AI will respond with its move.

---

## **How the AI Works**

The AI uses the **minimax algorithm** to select the best move. It evaluates all possible moves and assigns scores:
- **+1** if the AI wins.
- **-1** if the player wins.
- **0** for a draw.

The AI always chooses the move with the highest score to maximize its chance of winning or forcing a draw.

---

## **Game Logic**

- **Winning Conditions**:  
  The game checks if a player wins by completing:
  - A **row**.
  - A **column**.
  - The **main diagonal** (top-left to bottom-right: 0, 4, 8).
  - The **anti-diagonal** (top-right to bottom-left: 2, 4, 6).

- **Draw Condition**:  
  The game declares a draw if the board is full and no player wins.

---

## **Requirements**

- **Java Development Kit (JDK)** 8 or later.
- Terminal or **IDE** (like IntelliJ IDEA, Eclipse, or VS Code).

---

## **Potential Improvements**

Here are a few ideas for customization:
1. **Change Board Size:**  
   Modify the `SIZE` constant to change the grid size:
   ```java
   private static final int SIZE = 4;
   ```
2. **Customize Symbols:**  
   Change the symbols for players:
   ```java
   private static final char PLAYER_X = 'A';
   private static final char PLAYER_O = 'B';
   ```
3. **Add Multiplayer Support:**  
   Allow two human players to play alternately.
4. **Implement Undo Functionality:**  
   Keep track of moves to allow undo operations.

---

## **Acknowledgments**

- The **minimax algorithm** logic is inspired by various open educational resources.
- Created by **Dominic Rucker**.
