package csc207.fall2018.gamecentreapp.slidingtiles;

import csc207.fall2018.gamecentreapp.Score;

public class SlidingTileScore implements Score {

    private static final String GAME_NAME = BoardManager.getGameName();

    private String time;

    private String userName;

    private int size;


    public void takeInSizeTimeName(int size, String time, String userName) {
        this.time = time;
        this.size = size;
        this.userName = userName;
    }

    @Override
    public String returnPlayerName() {
        return userName;
    }

    @Override
    public String returnGameName() {
        return GAME_NAME;
    }

    @Override
    public String calculateScore() {
        time = time.replace(":", "");
        float floatScore = Integer.valueOf(time);
        int intScore;
        if (size == 3) {
            intScore = (int) (100 * (1 - (floatScore / (floatScore + 50))));
        } else if (size == 4) {
            intScore = (int) (100 * (1 - (floatScore / (floatScore + 100))));
        } else {
            intScore = (int) (100 * (1 - (floatScore / (floatScore + 200))));
        }
        return String.valueOf(intScore);
    }
}
