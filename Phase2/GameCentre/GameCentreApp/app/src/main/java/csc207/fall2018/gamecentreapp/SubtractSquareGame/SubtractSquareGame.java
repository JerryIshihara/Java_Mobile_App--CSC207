package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import csc207.fall2018.gamecentreapp.TimeDiplay.TimeStorable;

/**
 * subtract square game manager
 */
public class SubtractSquareGame extends Observable implements Serializable, Iterable<SubtractSquareState>, TimeStorable {
    /**
     * The game name for the game
     */
    private static final String GAME_NAME = "Subtract Square";
    /**
     * current state of the game
     */
    private SubtractSquareState currentState;

    /**
     * The number of undo moves allowed
     */
    private int undoBatch;
    /**
     * Time for the game
     */
    private int time;
    /**
     * An ArrayList of all past states for the game
     */
    private ArrayList<SubtractSquareState> pastStates;

    /**
     * Initialize a SubtractSquareGame.
     *
     * @param p1Name name of p1.
     * @param p2Name name of p2.
     */
    public SubtractSquareGame(String p1Name, String p2Name) {
        this.undoBatch = 3;
        this.currentState = new SubtractSquareState(p1Name, p2Name);
        this.currentState.randomize();
        this.pastStates = new ArrayList<>();
    }

    /**
     * Get the name of the game, which is "Subtract Square"
     *
     * @return "Subtract Square"
     */
    public static String getGameName() {
        return GAME_NAME;
    }

    /**
     * Get the name of current player.
     *
     * @return the name of the current player
     */
    public String getCurrentPlayerName() {
        return currentState.isP1_turn() ? currentState.getP1Name() : currentState.getP2Name();
    }

    /**
     * Get the current state for the game.
     *
     * @return the current state for the game
     */
    public SubtractSquareState getCurrentState() {
        return currentState;
    }

    /**
     * Get the number of undo moves allowed.
     *
     * @return the number of undo moves allowed
     */
    public int getUndoBatch() {
        return undoBatch;
    }

    /**
     * Apply a move so that the currentState could be changed.
     *
     * @param move a string showing the move of the player.
     */
    public void applyMove(String move) {
        this.pastStates.add(0, this.currentState);
        this.currentState = currentState.makeMove(move);
        setChanged();
        notifyObservers();

    }

    /**
     * Go back to the last state while playing the game.
     *
     * @return a boolean which represents whether the game could go back to last state or not.
     */

    public boolean undoMove() {
        Iterator<SubtractSquareState> stateIterator = pastStates.iterator();
        boolean undoable = stateIterator.hasNext();
        if (undoable) {
            this.currentState = stateIterator.next();
            deleteState();
            undoBatch = (undoBatch == 0) ? 3 : undoBatch - 1;
        }
        setChanged();
        notifyObservers();
        return undoable;
    }

    /**
     * Return whether the game is over.
     *
     * @return a boolean showing whether the game is over or not.
     */
    public boolean is_over() {
        return currentState.getCurrentTotal() == 0;
    }

    /**
     * Get the winner's name.
     *
     * @return a string showing who the winner is.
     */
    public String getWinner() {
        if (is_over()) {
            return (currentState.isP1_turn()) ? currentState.getP2Name() : currentState.getP1Name();
        }
        return "Not finished";
    }

    /**
     * Return whether the move is possible.
     *
     * @param move a string representing a move.
     * @return a boolean representing whether the move is possible or not.
     */
    public boolean isValidMove(String move) {
        if (isInteger(move)) {
            int moveInt = Integer.parseInt(move);
            return (moveInt <= this.currentState.getCurrentTotal()) && (moveInt > 0) && (checkSquare(moveInt));
        }
        return false;
    }

    /**
     * Return whether a String can be converted to numbers.
     *
     * @param str the string we want to test for.
     * @return a boolean showing whether the string is digital.
     */
    private boolean isInteger(String str) {
        return str.matches("-?(0|[1-9]\\d*)");
    }

    /**
     * helper function for roughOutcome which checks whether a number is a square number.
     *
     * @param n int which is checked.
     * @return a boolean whether n is a square number.
     */
    boolean checkSquare(int n) {
        for (int i = 1; i <= n; i++) {
            if (i * i == n) return true;
        }
        return false;
    }

    /**
     * Get the particular state in the pastStates.
     *
     * @param index the specific index of the state we want in the pastStates.
     * @return subtractSquareState
     */
    private SubtractSquareState getState(int index) {
        return this.pastStates.get(index);
    }

    private void deleteState() {
        this.pastStates.remove(0);
    }

    int numState() {
        return this.pastStates.size();
    }

    @NonNull
    @Override
    public Iterator<SubtractSquareState> iterator() {
        return new SubtractSquareStateIterator();
    }

    private class SubtractSquareStateIterator implements Iterator<SubtractSquareState> {

        int index = 0;


        @Override
        public boolean hasNext() {
            return index != numState();
        }

        @Override
        public SubtractSquareState next() {
            SubtractSquareState result = getState(index);
            index++;
            return result;
        }
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int getIntTime() {
        return time;
    }
}

