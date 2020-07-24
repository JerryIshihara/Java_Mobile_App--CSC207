package csc207.fall2018.gamecentreapp.ScoreManagement;

import org.junit.Test;

import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;
import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * Test for ScoreFactory
 */
public class ScoreFactoryTest {

    /**
     * Test GenerateScore for different sub-class
     */
    @Test
    public void testGenerateScore() {
        assertTrue(new ScoreFactory().generateScore(SubtractSquareGame.getGameName()) instanceof SubtractSquareScore);
        assertTrue(new ScoreFactory().generateScore(BoardManager.getGameName()) instanceof SlidingTileScore);
        assertTrue(new ScoreFactory().generateScore("Sudoku") instanceof SudokuScore);
        assertNull(new ScoreFactory().generateScore(null));
        assertNull(new ScoreFactory().generateScore("qqq"));
    }
}
