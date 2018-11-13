package csc207.fall2018.gamecentreapp.SlidingTileGame;


import java.io.Serializable;

import csc207.fall2018.gamecentreapp.Score;
import csc207.fall2018.gamecentreapp.UserManager;


public class SlidingTileScore implements Serializable, Score {

    private int score;
    private String userName;
    private final static int NUM_FOR_3BOARD = 20;
    private final static int NUM_FOR_4BOARD = 40;
    private final static int NUM_FOR_5BOARD = 60;

    SlidingTileScore(SlidingTileState state) {
        UserManager userManager = UserManager.getInstance();
        this.score = calculateScore(state.getBoardSize(), state.getSteps(), state.getTime());
        this.userName = userManager.getCurrentUserName();
    }

    /**
     * Calculate the score for different Board
     * The maximum to attain a perferct time score is 30 seconds for 3*3 Board
     * and 60 seconds for 4*4 Board and 90 seconds for 5*5 Board.
     * For every five seconds the player lose one mark for timeScore
     * timeScore can not go down below 0.
     * The maximum to attain a perfect move score is 20 moves for 3*3 Board and
     * 40 moves for 4*4 Board and 60 moves for 5*5 Board.
     * For every one move the player lose one mark for moveScore
     * moveScore can not go down below 0.
     */

    private int calculateScore(int size, int numMove, long timeElapsed) {
        int timeScore = 50;
        int moveScore = 50;
        if (size == 3) {
            if (timeElapsed > 30000) {
                timeScore -= (timeElapsed - 30000) / 5000;
                if (timeScore < 0) {
                    timeScore = 0;
                }
            }
            if (numMove > NUM_FOR_3BOARD) {
                moveScore -= (numMove - NUM_FOR_3BOARD);
                if (moveScore < 0) {
                    moveScore = 0;
                }
            }


        }
        if (size == 4) {
            if (timeElapsed > 60000) {
                timeScore -= (timeElapsed - 60000) / 5000;
                if (timeScore < 0) {
                    timeScore = 0;
                }
            }
            if (numMove > NUM_FOR_4BOARD) {
                moveScore -= (numMove - NUM_FOR_4BOARD);
                if (moveScore < 0) {
                    moveScore = 0;
                }
            }

        }
        if (size == 5) {
            if (timeElapsed > 90000) {
                timeScore -= (timeElapsed - 90000) / 5000;
                if (timeScore < 0) {
                    timeScore = 0;
                }
            }
            if (numMove > NUM_FOR_5BOARD) {
                moveScore -= (numMove - NUM_FOR_5BOARD);
                if (moveScore < 0) {
                    moveScore = 0;
                }
            }

        }
        return timeScore + moveScore;
    }

    @Override
    public String toString() {
        return String.valueOf(this.score) + this.userName;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass()) throw new ClassFormatError();
        if (this.score > ((SlidingTileScore) o).score) return 1;
        if (this.score == ((SlidingTileScore) o).score) return 0;
        else return -1;
    }
    //}
}
