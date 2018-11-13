package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;


/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    static int NUM_ROWS;

    /**
     * The number of columns.
     */
    static int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][]    tiles;

    /**
     * Set the size of the board.
     *
     * @param n the number of tiles in a row in the board.
     */
    public void setSize(int n) {
        if ((3 <= n) & (n <= 5)) {
            Board.NUM_COLS = n;
            Board.NUM_ROWS = n;
        }
    }

    /**
     * A new board of tiles in row-major order.
     *
     * @param n the size of the board
     */

    Board(int n) {
        List<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < n * n; i++) {
            tileList.add(new Tile(i));
        }
        Iterator<Tile> iter = tileList.iterator();
        NUM_COLS = n;
        NUM_ROWS = n;
        this.tiles = new Tile[NUM_COLS][NUM_ROWS];
        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_COLS * NUM_ROWS;
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
        Tile tile1 = getTile(row1, col1);
        Tile tile2 = getTile(row2, col2);
        tiles[row1][col1] = tile2;
        tiles[row2][col2] = tile1;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    /**
     * A new TileIterator that iterates the tiles in the Board.
     */
    private class TileIterator implements Iterator<Tile> {

        /**
         * The index of the next tile in the Board.
         */
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return nextIndex != numTiles();
        }

        @Override
        public Tile next() {
            if (this.hasNext()) {
                Tile result = getTile(nextIndex / NUM_COLS, nextIndex % NUM_COLS);
                nextIndex++;
                return result;
            }
            throw new NoSuchElementException();
        }
    }
}
