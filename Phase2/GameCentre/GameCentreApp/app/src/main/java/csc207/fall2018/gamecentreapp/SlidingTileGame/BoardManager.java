package csc207.fall2018.gamecentreapp.SlidingTileGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * The number of shuffle times during the construction of the board.
     */
    private static int shuffleNumber;


    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    public BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Return the shuffleNumber.
     */
    public int getShuffleNumber() {
        return shuffleNumber;
    }

    /**
     * Manage a new shuffled board.
     */
    BoardManager(int n) {
        int i = 0;
        List<Tile> tileList = complete(n);
        List<Tile> shuffled = shuffleList(tileList, n);
        int a;
        if (n == 3) {
            a = 20;
        } else if (n == 4) {
            a = 40;
        } else {
            a = 60;
        }
        while (i != a) {
            tileList = shuffleList(shuffled, n);
            while (shuffled == tileList) {
                tileList = shuffleList(shuffled, n);
            }
            shuffled = tileList;
            i++;
        }
        this.board = new Board(n);
        shuffleNumber = a;
    }

    /**
     * Return a list of tiles which is exactly in the right order according to the size.
     *
     * @param n the integer which is the size of the board.
     * @return a final list containing tiles in the right order.
     */

    private List<Tile> complete(int n) {
        List<Tile> tileList = new ArrayList<>();
        int i = 0;
        while (i != n * n - 1) {
            Tile a = new Tile(i);
            tileList.add(a);
            i++;
        }
        tileList.add(new Tile(24));
        return tileList;
    }


    /**
     * Return a list of tiles which is shuffled correctly.
     *
     * @param n        the integer which is the size of the board.
     * @param tileList the list which contains tiles.
     * @return a list of tiles
     */

    private List<Tile> shuffleList(List<Tile> tileList, int n) {
        int i = 0;
        while (i < n * n && tileList.get(i).getId() != 25) {
            i++;
        }
        Tile blank = tileList.get(i);
        int row = i / n;
        int col = i % n;
        List<Tile> possible = new ArrayList<>();
        if (row != 0) {
            possible.add(tileList.get(i - n));
        }
        if (row != n - 1) {
            possible.add(tileList.get(i + n));
        }
        if (col != 0) {
            possible.add(tileList.get(i - 1));
        }
        if (col != n - 1) {
            possible.add(tileList.get(i + 1));
        }
        Tile random = possible.get(new Random().nextInt(possible.size()));
        int position = tileList.indexOf(random);
        tileList.set(position, blank);
        tileList.set(i, random);
        return tileList;
    }


    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */

    boolean puzzleSolved() {
        Iterator<Tile> a = board.iterator();
        Integer b = null;
        while (a.hasNext()) {
            int c = a.next().getId();
            if (b != null && b > c) {
                return false;
            }
            b = c;
        }
        return true;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        Iterator<Tile> a = board.iterator();
        int b = 0;
        if (isValidTap(position)) {
            while (a.hasNext() && a.next().getId() != blankId) {
                b++;
            }
            board.swapTiles(b / Board.NUM_ROWS, b % Board.NUM_COLS, row, col);
        }
    }
}

