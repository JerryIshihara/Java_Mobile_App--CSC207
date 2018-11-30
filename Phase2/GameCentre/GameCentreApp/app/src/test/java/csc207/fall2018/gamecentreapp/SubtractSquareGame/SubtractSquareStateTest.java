package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SubtractSquareStateTest {

    @Test
    public void randomize() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        state.randomize();
        assertTrue(200 <= state.getCurrentTotal() && state.getCurrentTotal() <= 500);
    }

    @Test
    public void setCurrentTotal() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        state.setCurrentTotal(9);
        assertEquals(9, state.getCurrentTotal());
    }

    @Test
    public void getP1Name() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        assertEquals("H", state.getP1Name());
    }

    @Test
    public void getP2Name() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        assertEquals("K", state.getP2Name());
    }


    @Test
    public void isP1_turn() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        assertTrue(state.isP1_turn());
    }

    @Test
    public void getPossibleMoves() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        state.setCurrentTotal(9);
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 4, 9));
        assertEquals(numbers, state.getPossibleMoves() );
    }

    @Test
    public void getCurrentTotal() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        state.setCurrentTotal(9);
        assertEquals(9, state.getCurrentTotal());
    }

    @Test
    public void makeMove() {
        SubtractSquareState state = new SubtractSquareState("H", "K");
        state.setCurrentTotal(9);
        SubtractSquareState newState = state.makeMove("4");
        assertEquals(5, newState.getCurrentTotal());
        assertFalse(newState.isP1_turn());
        assertEquals(newState.getP1Name(), state.getP1Name());
        assertEquals(newState.getP2Name(), state.getP2Name());
    }



}