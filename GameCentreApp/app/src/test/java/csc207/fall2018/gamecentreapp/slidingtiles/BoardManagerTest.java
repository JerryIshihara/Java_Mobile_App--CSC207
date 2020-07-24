package csc207.fall2018.gamecentreapp.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Board manager Test
 */
public class BoardManagerTest {


    /**
     * make tile by size
     * @param size tile colomn size
     * @return List of Tiles
     */
    private List<Tile> makeTiles(int size) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = size * size;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     *make a correct List of tiles
     * @param size colomn size
     * @return a solved board
     */
    private Board setUpCorrect(int size) {
        List<Tile> tiles = makeTiles(size);
        return new Board(tiles, size);
    }


    /**
     * Test get board function
     */
    @Test
    public void getBoard() {
        Board correct = setUpCorrect(4);
        BoardManager manager1 = new BoardManager(correct);
        assertSame(correct, manager1.getBoard());


    }

    /**
     * GAME_NAME getter
     */
    @Test
    public void getGameName() {
        assertEquals("Sliding Tiles", BoardManager.getGameName());

    }


    /**
     * return if the board is solved
     */
    @Test
    public void puzzleSolved() {
        Board correct = setUpCorrect(5);
        BoardManager manager1 = new BoardManager(correct);
        assertTrue(manager1.puzzleSolved());
        manager1.getBoard().swapTiles(0, 0, 1, 1);
        assertFalse(manager1.puzzleSolved());
    }


    /**
     * Test isvalidtap is valid
     */
    @Test
    public void isValidTap() {
        Board correct = setUpCorrect(5);
        BoardManager manager1 = new BoardManager(correct);
        assertTrue(manager1.isValidTap(23));
        assertFalse(manager1.isValidTap(1));
    }

    /**
     * Test touch move function
     */
    @Test
    public void touchMove() {
        Board correct = setUpCorrect(5);
        BoardManager manager1 = new BoardManager(correct);
        manager1.touchMove(19);
        assertEquals(2, manager1.getNumPastMove());
        assertEquals(25, manager1.getBoard().getTile(3, 4).getId());
        assertEquals(20, manager1.getBoard().getTile(4, 4).getId());
    }

    /**
     * Test game undo function
     */
    @Test
    public void undoMove() {
        Board correct = setUpCorrect(5);
        BoardManager manager1 = new BoardManager(correct);
        manager1.touchMove(19);
        boolean undoable = manager1.UndoMove();
        assertTrue(undoable);
        assertEquals(20, manager1.getBoard().getTile(3, 4).getId());
        assertEquals(25, manager1.getBoard().getTile(4, 4).getId());

    }

    /**
     * Test BoardManager constructor
     */
    @Test
    public void testBoardManager() {
        new BoardManager(4);
    }

    /**
     * Test time storage
     */
    @Test
    public void testSetAndGetTime() {
        Board correct = setUpCorrect(5);
        BoardManager manager1 = new BoardManager(correct);
        manager1.setTime(10);
        assertEquals(10, manager1.getIntTime());
    }

    /**
     * Test iterator
     */
    @Test
    public void testIterator() {
        Board board = setUpCorrect(4);
        BoardManager boardManager = new BoardManager(board);
        boardManager.touchMove(14);
        boardManager.touchMove(15);
        Iterator<Integer> iterator = boardManager.iterator();
        assertEquals(true, iterator.hasNext());
        assertFalse(boardManager.isValidTap(iterator.next()));

    }
}