package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import org.junit.Test;

import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;

import static org.junit.Assert.*;

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



    }

    private boolean dealWithSquare(int n) {
        return (new SubtractSquareGame("", "").checkSquare(n));

    }

    private boolean dealWithSquarePlusTwo(int n) {
        int k = 1;
        while (k < n) {
            if (k * k + 2 == n) {
                return true;
            }
            k++;
        }
        return false;
    }

    private boolean dealWithSquarePlusFive(int n) {
        int k = 1;
        while (k < n) {
            if (k * k + 5 == n) {
                return true;
            }
            k++;
        }
        return false;
    }


}