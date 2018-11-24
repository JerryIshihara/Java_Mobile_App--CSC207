package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import java.util.Random;

import csc207.fall2018.gamecentreapp.State;

/**
 * The state of the SubtractSquare game.
 */

public class SubtractSquareState {

    //    private static final int WIN = 1;
//
//    private static final int LOSE = -1;
//
//    private static final int DRAW = 0;


    /**
     * whether it is p1's turn.
     */
    private boolean p1Turn;

    /**
     * The current number after subtraction in the game.
     */
    private int currentTotal;


    // New Game State
    public SubtractSquareState() {
        this.p1Turn = true;
        // can be adjust by difficulty
        this.currentTotal = getRandomInt(70, 200);
    }


    private SubtractSquareState(boolean p1_turn, int current_total) {
        this.p1Turn = p1_turn;
        this.currentTotal = current_total;
    }


    /**
     * Return whether it is p1's turn.
     *
     * @return boolean showing that it is p1's turn or not.
     */
    public boolean isP1_turn() {
        return this.p1Turn;
    }


//    /**
//////     * Get all possible moves for a player.
//////     *
//////     * @return a list containing of all moves.
//////     */
//////    public List getPossibleMoves() {
//////        List<Integer> moves = new ArrayList<>();
//////        int i = 1;
//////        while (i < this.current_total + 1) {
//////            if (i * i <= this.current_total) {
//////                moves.add(i * i);
//////            }
//////            i++;
//////        }
//////        return moves;
//////    }

    /**
     * Get current_total
     *
     * @return an int representing current_total
     */
    public int getCurrentTotal() {
        return currentTotal;
    }

    /**
     * Return the new state after the player makes a move.
     *
     * @param move String representing the move.
     * @return a new SubtractSquareState.
     */
    public SubtractSquareState makeMove(String move) {
        int moveInt = Integer.parseInt(move);
        return new SubtractSquareState(!p1Turn, this.currentTotal - moveInt);
    }

//    /**
//     * Return the rough outcome.
//     *
//     * @return an int which shows win, draw or lose.
//     */
//    public int roughOutcome() {
//        if (checkSquare(this.current_total)) {
//            return WIN;
//        }
//        int i = 1;
//        while (i < Math.sqrt(current_total)) {
//            if (!checkSquare(current_total - i * i)) {
//                return DRAW;
//            }
//            i++;
//        }
//        return LOSE;
//    }



    private int getRandomInt(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random randInt = new Random();
        return randInt.nextInt((max - min) + 1) + min;
    }

}
