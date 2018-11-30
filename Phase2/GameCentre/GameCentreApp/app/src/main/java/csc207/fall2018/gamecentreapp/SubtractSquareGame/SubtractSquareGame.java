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
     * current state of the game.
     */
    private static final String GAME_NAME = "Subtract Square";

    private SubtractSquareState currentState;

    private int undoBatch;

    private int time;


//    private String p1Name;
//
//    private String p2Name;

    private ArrayList<SubtractSquareState> pastStates;


//    /**
//     * initialize a game
//     */
//    private SubtractSquareGame(String p1Name, String p2Name, int count) {
//        SubtractSquareGame.p1Name = p1Name;
//        SubtractSquareGame.p2Name = p2Name;
//        this.currentState = new SubtractSquareState(true, count);
//    }

    public SubtractSquareGame(String p1Name, String p2Name) {
        this.undoBatch = 3;
        this.currentState = new SubtractSquareState(p1Name, p2Name);
        this.currentState.randomize();
        this.pastStates = new ArrayList<>();
    }

//    public String getP1Name() {
//        return p1Name;
//    }
//
//    public String getP2Name() {
//        return p2Name;
//    }

    public static String getGameName() {
        return GAME_NAME;
    }

    public String getCurrentPlayerName() {
        return currentState.isP1_turn() ? currentState.getP1Name() : currentState.getP2Name();
    }

    public SubtractSquareState getCurrentState() {
        return currentState;
    }

    public int getUndoBatch() {
        return undoBatch;
    }

    public void applyMove(String move) {
        this.pastStates.add(0, this.currentState);
        this.currentState = currentState.makeMove(move);
        setChanged();
        notifyObservers();

    }

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


    public boolean is_over() {
        return currentState.getCurrentTotal() == 0;
    }


    public String getWinner() {
        if (is_over()) {
            return (currentState.isP1_turn()) ? currentState.getP2Name() : currentState.getP1Name();
        }
        return "Not finished";
    }


    public boolean isValidMove(String move) {
        if (isInteger(move)) {
            int moveInt = Integer.parseInt(move);
            return (moveInt <= this.currentState.getCurrentTotal()) && (moveInt > 0) && (checkSquare(moveInt));
        }
        return false;
    }

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

