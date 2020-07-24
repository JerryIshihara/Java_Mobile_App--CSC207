package csc207.fall2018.gamecentreapp.ScoreManagement;

import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;

public class SlidingTileScore implements Score {

    /**
     * String GAME_NAME  BoardManager.getGameName()
     */
    private static final String GAME_NAME = BoardManager.getGameName();

    /**
     * int time.
     */
    private int time; //Seconds

    /**
     * String userName.
     */
    private String userName;

    /**
     * int size
     */
    private int size;

    /**
     * int numMove.
     */
    private int numMove;

    /**
     * Require these information.
     *
     * @param size the size.
     * @param time the time.
     * @param userName the user name.
     * @param numMove the number move.
     */
    public void takeInSizeTimeName(int size, int time, String userName, int numMove) {
        this.time = time;
        this.size = size;
        this.userName = userName;
        this.numMove = numMove;
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
        float floatMove = numMove - 1;
        float floatScore = time;
        int intScore;
        if (size == 3) {
            intScore = (int) (100 * (1 - ((floatScore + floatMove) / (floatScore + floatMove + 50))));
        } else if (size == 4) {
            intScore = (int) (100 * (1 - ((floatScore + floatMove) / (floatScore + floatMove + 100))));
        } else {
            intScore = (int) (100 * (1 - ((floatScore + floatMove) / (floatScore + floatMove + 200))));
        }
        return String.valueOf(intScore);
    }
}
