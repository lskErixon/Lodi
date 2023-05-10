import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class ShipBattleGame {
    private static final int BOARD_SIZE = 5;
    public static final char EMPTY_CELL = '-';
    public static final char SHIP_CELL = 'S';
    public static final char HIT_CELL = 'X';
    public static final char MISS_CELL = 'O';

    private char[][] board;
    private int numShips;

    private GamePanel gamePanel = new GamePanel();
    private  Window window = new Window(gamePanel);

    /**
     * initialize Board and numShips
     */
    public ShipBattleGame(int numShips) throws IOException {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.numShips = numShips;
        initializeBoard();
        placeShips();
    }

    /**
     * my grid
     */
    public void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }

    }

    /**
     * the location of my SHIP
     */
    public void placeShips() {
        int count = 0;
        while (count < numShips) {
            int row = (int) (Math.random() * BOARD_SIZE);
            int col = (int) (Math.random() * BOARD_SIZE);
            int direction = (int) (Math.random() * 2);  // 0 for horizontal, 1 for vertical

            if (direction == 0 && col < BOARD_SIZE - 1 && board[row][col] != SHIP_CELL && board[row][col + 1] != SHIP_CELL) {
                board[row][col] = SHIP_CELL;
                board[row][col + 1] = SHIP_CELL;            //  <----  cannot be one ship above another
                count++;
            } else if (direction == 1 && row < BOARD_SIZE - 1 && board[row][col] != SHIP_CELL && board[row + 1][col] != SHIP_CELL) {
                board[row][col] = SHIP_CELL;
                board[row + 1][col] = SHIP_CELL;            // -||-
                count++;
            }
        }
    }

    /**
     * my board printout
     * @param showShips
     */
    public void printBoard(boolean showShips) {
        System.out.println("  1 2 3 4 5");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!showShips && board[row][col] == SHIP_CELL) {
                    System.out.print(EMPTY_CELL + " ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * game over
     * @return
     */
    public boolean isGameOver() {
        int count = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == SHIP_CELL) {
                    count++;
                }
            }
        }
        return count == 0;
    }

    public boolean isHit(int row, int col) {
        return board[row][col] == SHIP_CELL;
    }

    /**
     * shooting method
     * @param row
     * @param col
     */
    public void fireMissile(int row, int col) {
        boolean isShipDestroyed = false;
        if (board[row][col] == SHIP_CELL) {
            board[row][col] = HIT_CELL;
            System.out.println("You hit a ship!");
            // Check if the ship is destroyed
            if ((row - 1 >= 0 && board[row - 1][col] == HIT_CELL && board[row][col] == HIT_CELL) ||
                    (row + 1 < BOARD_SIZE && board[row + 1][col] == HIT_CELL && board[row][col] == HIT_CELL) ||
                    (col - 1 >= 0 && board[row][col - 1] == HIT_CELL && board[row][col] == HIT_CELL) ||
                    (col + 1 < BOARD_SIZE && board[row][col + 1] == HIT_CELL && board[row][col] == HIT_CELL)) {
                isShipDestroyed = true;
            }
        } else {
            board[row][col] = MISS_CELL;
            System.out.println("You missed!");
        }
        if (isShipDestroyed) {
            System.out.println("You destroyed a ship!");
        }
    }

    /**
     * game designer
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int numMissiles = 0;

        while (!isGameOver()) {
            gamePanel.redraw(board);
            printBoard(false);
            try {
                System.out.print("Enter row (A-E): ");
                int row = scanner.next().toUpperCase().charAt(0) - 'A';
                if (row < 0 || row >= BOARD_SIZE) {
                    throw new IllegalArgumentException("Invalid row");
                }
                System.out.print("Enter col (1-5): ");
                int col = scanner.nextInt() - 1;
                if (col < 0 || col >= BOARD_SIZE) {
                    throw new IllegalArgumentException("Invalid column");
                }
                numMissiles++;
                fireMissile(row, col);
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // consume the invalid input
            }
        }

        printBoard(true);

        System.out.println("Congratulations! You sunk all ships in " + numMissiles + " missiles.");
    }




    //ShipBattleGame
    //initializeBoard
    //placeShips
    //printBoard                <--- plan my job
    //isGameOver
    //isHit
    //fireMissile
    //play
}