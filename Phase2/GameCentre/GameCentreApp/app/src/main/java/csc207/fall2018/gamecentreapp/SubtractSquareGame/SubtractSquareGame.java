package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * subtract square game manager
 */
public class SubtractSquareGame implements Serializable {

    /**
     * current state of the game.
     */
    private SubtractSquareState currentState;

    private String p1Name;

    private String p2Name;

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
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.currentState = new SubtractSquareState();
        this.pastStates = new ArrayList<>();
    }

//    public String getP1Name() {
//        return p1Name;
//    }
//
//    public String getP2Name() {
//        return p2Name;
//    }

    public String getCurrentPlayerName(){
        return currentState.isP1_turn() ? p1Name : p2Name;
    }

    public SubtractSquareState getCurrentState() {
        return currentState;
    }

    //    /**
//     * Set P1 Name for SubtractSquareGame.
//     *
//     * @param name String representing the name for player 1.
//     */
//    public void setP1Name(String name) {
//        this.p1Name = name;
//    }
//
//    /**
//     * Set P1 Name for SubtractSquareGame.
//     *
//     * @param name String representing the name for player 2.
//     */
//    public void setP2Name(String name) {
//        this.p2Name = name;
//    }

//    /**
//     * return the name of the game.
//     *
//     * @return the name of the game.
//     */
//    public String toString() {
//        return "SubtractSquareGame";
//    }


    public void applyMove(String move) {
        try {
            this.pastStates.add(this.currentState);
            this.currentState = currentState.makeMove(move);
        } catch (NumberFormatException e) {
            Log.e("Exception", "Parse failed: " + e.toString());
        }
    }

    public boolean undoMove() {
        int lastState = this.pastStates.size() - 1;
        if (lastState != -1) {
            this.currentState = this.pastStates.get(this.pastStates.size() - 1);
            this.pastStates.remove(lastState);
            return true;
        } else {
            return false;
        }
    }


    public boolean is_over() {
        return currentState.getCurrentTotal() == 0;
    }


    public String getWinner() {
        if (is_over()) {
            return (currentState.isP1_turn()) ? p2Name : p1Name;
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

    public boolean isInteger(String str) {
        return str.matches("-?(0|[1-9]\\d*)");
    }

    /**
     * helper function for roughOutcome which checks whether a number is a square number.
     *
     * @param n int which is checked.
     * @return a boolean whether n is a square number.
     */
    public boolean checkSquare(int n) {
        for (int i = 1; i <= n; i++) {
            if (i * i == n) return true;
        }
        return false;
    }


}

