package csc207.fall2018.gamecentreapp.slidingtiles;

import android.content.Context;
import android.widget.Toast;


public class MovementController {

    /**
     * BoardManager boardManager  null
     */
    private BoardManager boardManager = null;

    /**
     * The constructor of the MovementController.
     */
    MovementController() {
    }

    /**
     * Set the board manager.
     *
     * @param boardManager the board manager.
     */
    void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Return the information according to the movement of the player.
     *
     * @param context  the context.
     * @param position the position.
     * @param display  the display
     */
    void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
