package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.util.Log;

import java.io.FileNotFoundException;

import csc207.fall2018.gamecentreapp.GameInterface;
import csc207.fall2018.gamecentreapp.SlidingTileStateManager;

public class SlidingTileGameInterface implements GameInterface {

    static final String GAME_NAME = "slidingTileGame";

    private static SlidingTileGameInterface gameInstance = null;

    private SlidingTileState state;

    private BoardManager boardManager;

    private SlidingTileGameInterface() {
    }

    public static SlidingTileGameInterface getInstance() {
        if (gameInstance == null) {
            gameInstance = new SlidingTileGameInterface();
        }
        return gameInstance;
    }

    @Override
    public void loadState() {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        state = stateManager.giveCurrentState();
        boardManager = state.getBoard();
    }

    @Override
    public void startNewGame() {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        stateManager.initiateGameState();
    }


    public void startNewBoard(int boardSize, long time) {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        boardManager = new BoardManager(boardSize);
        state = new SlidingTileState(boardManager, time, 0);
        stateManager.addGameState(state);
    }

    public SlidingTileState getState() {
        return state;
    }

    @Override
    public String toString() {
        return GAME_NAME;
    }

    @Override
    public void replay() throws Exception {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        stateManager.backToFirstState();
        loadState();
    }

    @Override
    public void undo() throws Exception {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        stateManager.undoGameState();
        loadState();
    }

    public void closeInterface(){
        state = null;
    }
}
