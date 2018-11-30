package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComputerChoiceTest {

    @Test
    public void testIterativeMiniMax() {
        SubtractSquareGame game = new SubtractSquareGame("Henry", "Mike");
        int currentTotal = game.getCurrentState().getCurrentTotal();
        assertTrue(currentTotal >= new ComputerChoice().iterativeMiniMax(game));
        assertTrue(game.checkSquare(((new ComputerChoice().iterativeMiniMax(game)))));
        game.getCurrentState().setCurrentTotal(9);
        assertEquals(9, new ComputerChoice().iterativeMiniMax(game));
        game.getCurrentState().setCurrentTotal(11);
        assertEquals(9, new ComputerChoice().iterativeMiniMax(game));
        game.getCurrentState().setCurrentTotal(14);
        assertEquals(9, new ComputerChoice().iterativeMiniMax(game));
        game.getCurrentState().setCurrentTotal(10);
        assertEquals(1, new ComputerChoice().iterativeMiniMax(game));
        game.getCurrentState().setCurrentTotal(0);
        assertEquals(0, new ComputerChoice().iterativeMiniMax(game));
        game.getCurrentState().setCurrentTotal(15);
        assertEquals(1, new ComputerChoice().iterativeMiniMax(game));
    }

    @Test
    public void testPCWin() {
        SubtractSquareGame game = new SubtractSquareGame("Henry", "Mike");
        game.getCurrentState().setCurrentTotal(2);
        assertEquals(1, new ComputerChoice().iterativeMiniMax(game));
    }
}