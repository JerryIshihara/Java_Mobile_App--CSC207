package csc207.fall2018.gamecentreapp;

import java.io.Serializable;

/**
 * An interface dealing with the scores of games.
 */
public interface Score extends Comparable, Serializable {

    @Override
    int compareTo(Object o);

    /**
     * Return the string corresponding to Score.
     */
    String toString();
    /**
     * Return the score.
     */
    int getScore();
}
