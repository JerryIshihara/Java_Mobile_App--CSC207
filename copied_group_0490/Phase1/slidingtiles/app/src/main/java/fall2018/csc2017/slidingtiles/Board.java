package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * The sliding tiles board.
 */

public class Board extends Observable implements Iterable<Tile>, Serializable{
    /**
     * The number of rows.
     */
    final static int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    final static int NUM_COLS = 4;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * The interator for sliding tiles board.
     */
    private class BoardIterator implements Iterator<Tile> {
        /**
         * The next row index of 2D array tiles
         */
        int nextRowIndex = 0;
        /**
         * The next column index of 2D array tiles
         */
        int nextColumnIndex = 0;

        @Override
        public boolean hasNext() {
            return nextRowIndex != NUM_ROWS && nextColumnIndex != NUM_COLS;
        }

        @Override
        public Tile next() {
            Tile result = tiles[nextRowIndex][nextColumnIndex];
            if (! hasNext()) {throw new NoSuchElementException();}
            if (nextColumnIndex == NUM_COLS - 1) {
                nextColumnIndex = 0;
                nextRowIndex ++;
            }
            else {
                nextColumnIndex++;
            }
            return result;
        }
    }

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_COLS*NUM_ROWS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}
