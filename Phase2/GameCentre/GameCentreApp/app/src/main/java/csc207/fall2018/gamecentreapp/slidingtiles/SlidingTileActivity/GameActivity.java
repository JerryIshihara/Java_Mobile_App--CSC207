package csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.slidingtiles.Board;
import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;
import csc207.fall2018.gamecentreapp.slidingtiles.CustomAdapter;
import csc207.fall2018.gamecentreapp.slidingtiles.GestureDetectGridView;


/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * The controller of the SlingTimeActivity.
     */
    private SlidingTileActivityController slidingTileActivityController;

    // Grid View and calculated column height and width based on device size
    /**
     * GestureDetectGridView gridView.
     */
    private GestureDetectGridView gridView;

    /**
     * int columnWidth; int columnHeight.
     */
    private static int columnWidth, columnHeight;


    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        slidingTileActivityController.update(this, boardManager);
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingtile_activity_main);

        slidingTileActivityController = new SlidingTileActivityController(this);
        boardManager = (BoardManager) slidingTileActivityController.loadFromDataBase(this, BoardManager.getGameName());
        slidingTileActivityController.startGame();

        final Board board = boardManager.getBoard();
        createTileButtons(this);
        // Add a chronometer for timer.


        // Add View to activity
        gridView = this.findViewById(R.id.grid);
        gridView.setNumColumns(board.NUM_COLS);
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / board.NUM_COLS;
                        columnHeight = displayHeight / board.NUM_ROWS;

                        display();
                    }
                });
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.NUM_ROWS; row++) {
            for (int col = 0; col != board.NUM_COLS; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.NUM_ROWS;
            int col = nextPos % board.NUM_COLS;
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    /**
     * Click to go back.
     *
     * @param view the view
     */
    public void onclickGoBack(View view) {
        slidingTileActivityController.exitAttempt(view, boardManager);
        Intent goBackIntent = new Intent(this, StartingActivity.class);
        startActivity(goBackIntent);
    }

    /**
     * Click to undo.
     *
     * @param view the view.
     */
    public void onclickUndo(View view) {
        boolean undoable = boardManager.UndoMove();
        if (!undoable) {
            Toast.makeText(this, "It's the first state", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Click to save the game.
     *
     * @param view the view.
     */
    public void onclickSaveGame(View view) {
        slidingTileActivityController.saveGameAttempt(view, boardManager);
    }
}