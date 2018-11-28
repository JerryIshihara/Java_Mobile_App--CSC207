package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import csc207.fall2018.gamecentreapp.Score;

public class SubtractSquareScore implements Score {

    private static final String GAME_NAME = SubtractSquareGame.getGameName();

    private String time;

    private SubtractSquareState subtractSquareState;


    public void takeInStateAndTime(SubtractSquareState state, String time) {
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
            time = time.replace(":", "");
            float floatScore = Integer.valueOf(time);
            int intScore = (int) (100 * (1 - (floatScore/(floatScore + 200))));
            return String.valueOf(intScore);
        }
    }
}
