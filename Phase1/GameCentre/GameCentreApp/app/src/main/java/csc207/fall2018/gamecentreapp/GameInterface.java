package csc207.fall2018.gamecentreapp;

import android.content.Context;
import android.content.Intent;

/**
 * An interface dealing with playing the games.
 */
public interface GameInterface {
    /**
     * The name of the game
     */
    String gameName = null;

    /**
     * Start a new game
     */
    void startNewGame();

    /**
     * Replay the game.
     */
    void replay() throws Exception;

    /**
     * Go back to 3 steps before.
     */
    void undo() throws Exception;

    /**
     * Load a new game.
     */
    void loadState() throws Exception;

}