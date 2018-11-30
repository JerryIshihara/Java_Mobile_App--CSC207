package csc207.fall2018.gamecentreapp.ScoreManagement;


/**
 * Interface scores of games.
 */
    public interface Score {

    /**
     * return player name of the score
     *
     * @return String
     */
    String returnPlayerName();

    /**
     * return game name of the score
     *
     * @return String
     */
    String returnGameName();

    /**
     * calculate the score
     *
     * @return String
     */
    String calculateScore();
}
