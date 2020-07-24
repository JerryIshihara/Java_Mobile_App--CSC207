package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The state of the SubtractSquare game.
 */

public class SubtractSquareState implements Serializable {

    /**
     * Whether it is p1's turn.
     */
    private boolean p1Turn;
    /**
     * The name of p1.
     */
    private String p1Name;
    /**
     * The name of p2.
     */
    private String p2Name;

    /**
     * The current number after subtraction in the game.
     */
    private int currentTotal;


    /**
     * Initialize SubtractSquareState.
     *
     * @param p1Name name of player 1.
     * @param p2Name name of player 2.
     */
    public SubtractSquareState(String p1Name, String p2Name) {
        this.p1Turn = true;
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    /**
     * Set the currentTotal between 200 and 500 which could be a number randomly.
     */

    void randomize() {
        this.currentTotal = getRandomInt();
    }

    /**
     * Set the current number for the game.
     *
     * @param currentTotal the new current total for the game
     */
    void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }

    /**
     * Private constructor for generating new SubtractSquareState after a move.
     *
     * @param p1_turn       whether it is p1's turn.
     * @param current_total the current number for the game.
     * @param p1Name        the name of p1.
     * @param p2Name        the name of p2.
     */
    private SubtractSquareState(boolean p1_turn, int current_total, String p1Name, String p2Name) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.p1Turn = p1_turn;
        this.currentTotal = current_total;
    }

    /**
     * Get the name of p1.
     *
     * @return the name of p1.
     */
    public String getP1Name() {
        return p1Name;
    }

    /**
     * Get the name of p2.
     *
     * @return the name of p2.
     */
    public String getP2Name() {
        return p2Name;
    }

    /**
     * Return whether it is p1's turn.
     *
     * @return boolean showing that it is p1's turn or not.
     */
    public boolean isP1_turn() {
        return this.p1Turn;
    }


    /**
     * Get all possible moves for a player.
     *
     * @return a list containing of all moves.
     */
    ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        int i = 1;
        while (i * i <= this.currentTotal) {
            moves.add(i * i);
            i++;
        }
        return moves;
    }

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
        return new SubtractSquareState(!p1Turn, this.currentTotal - moveInt, this.p1Name, this.p2Name);
    }

    /**
     * Return a random integer between min and max, inclusive.
     *
     * @return a random integer between min and max, inclusive.
     */
    private int getRandomInt() {
        Random randInt = new Random();
        return randInt.nextInt((500 - 200) + 1) + 200;
    }

}
