package csc207.fall2018.gamecentreapp;


import java.util.ArrayList;

/**
 * An interface storing and updating scores for a game.
 */
public interface ScoreBoard {
    /**
     * Add the score to this interface.
     *
     * @param newScore  the new score being added.
     */
    void addScore(Score newScore);

    /**
     * Store all scores in a file.
     */
    void writeFile();

    /**
     * Read all scores of the file.
     */
    void readFile();

//    void sort();

    /**
     * Return the best 10 scores of a specific game.
     *
     * @return an ArrayList containing best 10 scores.
     */
    ArrayList getTop10();
}


