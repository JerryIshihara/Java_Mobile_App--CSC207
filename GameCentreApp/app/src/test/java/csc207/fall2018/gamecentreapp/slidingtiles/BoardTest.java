package csc207.fall2018.gamecentreapp.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


import static org.junit.Assert.*;

public class BoardTest {

    private Board getBoard() {
        List<Tile> tiles = new ArrayList<>();
        int i = 1;
        while (i < 17) {
            tiles.add(new Tile(i, i - 1));
            i++;
        }
        return new Board(tiles, 4);
    }

    @Test
    public void numTiles() {
        Board board = getBoard();
        assertEquals(16, board.numTiles());
    }

    @Test
    public void getTile() {
        Board board = getBoard();
        assertEquals(1, board.getTile(0, 0).getId());
        assertEquals(6, board.getTile(1, 1).getId());
    }

    @Test
    public void swapTiles() {
        Board board = getBoard();
        board.swapTiles(0, 0, 1, 1);
        assertEquals(6, board.getTile(0, 0).getId());
        assertEquals(1, board.getTile(1, 1).getId());

    }


}








