package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubtractSquareGameTest {

    @Test
    public void getGameName() {
        assertEquals("Subtract Square", SubtractSquareGame.getGameName());
    }

    @Test
    public void getCurrentPlayerName() {
        SubtractSquareGame game = new SubtractSquareGame("H", "k");
        assertEquals("H", game.getCurrentPlayerName());
    }

    @Test
    public void getCurrentState() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        assertEquals("H", game.getCurrentState().getP1Name());
        assertEquals("K", game.getCurrentState().getP2Name());
        assertTrue(game.getCurrentState().isP1_turn());
    }

    @Test
    public void getUndoBatch() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        assertEquals(3, game.getUndoBatch());
    }

    @Test
    public void applyMove() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        int current = game.getCurrentState().getCurrentTotal();
        game.applyMove("9");
        assertEquals(current - 9, game.getCurrentState().getCurrentTotal());
        assertEquals(1, game.numState());


    }

    @Test
    public void undoMove() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        int current = game.getCurrentState().getCurrentTotal();
        game.applyMove("9");
        boolean undoable = game.undoMove();
        assertEquals(current, game.getCurrentState().getCurrentTotal());
        assertEquals(2, game.getUndoBatch());
        assertTrue(undoable);

    }

    @Test
    public void is_over() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        game.applyMove("9");
        assertFalse(game.is_over());
    }

    @Test
    public void getWinner() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        game.applyMove("9");
        assertEquals("Not finished", game.getWinner());
        game.getCurrentState().setCurrentTotal(9);
        game.applyMove("9");
        assertEquals(game.getWinner(), game.getCurrentState().getP2Name());
    }

    @Test
    public void isValidMove() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        assertTrue(game.isValidMove("9"));
        assertFalse(game.isValidMove("625"));
        assertFalse(game.isValidMove("0"));
        assertFalse(game.isValidMove("45"));
        assertFalse(game.isValidMove("rt"));
    }

    @Test
    public void testCheckSquare(){
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        assertTrue(game.checkSquare(9));
        assertFalse(game.checkSquare(6));
    }

    @Test
    public void testSetTimeAndGetTime() {
        SubtractSquareGame game = new SubtractSquareGame("H", "K");
        game.setTime(5);
        assertEquals(5, game.getIntTime());
    }
}

