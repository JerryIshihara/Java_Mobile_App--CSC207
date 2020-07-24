package csc207.fall2018.gamecentreapp.ScoreManagement;

import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareState;

public class SubtractSquareScore implements Score {
    /**
     * String GAME_NAME  SubtractSquareGame.getGameName()
     */
    private static final String GAME_NAME = SubtractSquareGame.getGameName();

    /**
     * int time;
     */
    private int time;

    /**
     * SubtractSquareState subtractSquareState
     */
    private SubtractSquareState subtractSquareState;


    /**
     * Take instate and time.
     *
     * @param state the state.
     * @param time  the time.
     */
    public void takeInStateAndTime(SubtractSquareState state, int time) {
        this.time = time;
        this.subtractSquareState = state;
    }

    @Override
    public String returnPlayerName() {
        return subtractSquareState.getP1Name();
    }

    @Override
    public String returnGameName() {
        return GAME_NAME;
    }

    @Override
    public String calculateScore() {
        if (subtractSquareState.isP1_turn()) {
            return "0";
        } else {
            float floatScore = time;
            int intScore = (int) (100 * (1 - (floatScore / (floatScore + 200))));
            return String.valueOf(intScore);
        }
    }
}
