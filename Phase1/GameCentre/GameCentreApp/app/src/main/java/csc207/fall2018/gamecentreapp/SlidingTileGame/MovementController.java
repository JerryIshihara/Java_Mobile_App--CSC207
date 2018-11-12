package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.content.Context;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.SlidingTileStateManager;
import csc207.fall2018.gamecentreapp.UserScoreBoard;

public class MovementController {

    SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();

    private long start = stateManager.getStartTime();

    private BoardManager boardManager = null;

    public MovementController() {
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            long time = System.currentTimeMillis();
            stateManager.addGameState(new SlidingTileState(boardManager, time - start, stateManager.getSteps()));
            if (boardManager.puzzleSolved()) {
                SlidingTileScore score = new SlidingTileScore(stateManager.giveCurrentState());
                SlidingTileScoreBoard.getBoardInstance().addScore(score);
                UserScoreBoard.getBoardInstance().addScore(score);
                stateManager.closeCurrentManager();
                SlidingTileGameInterface.getInstance().closeInterface();
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}