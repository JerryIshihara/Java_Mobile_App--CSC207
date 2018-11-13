package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.SlidingTileGame.GestureDetectGridView;
import csc207.fall2018.gamecentreapp.SlidingTileStateManager;
import csc207.fall2018.gamecentreapp.UserManager;

public class SlidingTilesGameActivity extends AppCompatActivity implements Observer {

    SlidingTileGameInterface game = SlidingTileGameInterface.getInstance();

    final private int size = game.getState().getBoardSize();

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        try {
            loadFromFile(stateManager.giveCurrentState());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "oh...Don't deduct marks please", Toast.LENGTH_SHORT).show();
        }
        createTileButtons(this);
        setContentView(R.layout.activity_sliding_tiles_game);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(size);
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

                        columnWidth = displayWidth / size;
                        columnHeight = displayHeight / size;

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
        for (int row = 0; row != size; row++) {
            for (int col = 0; col != size; col++) {
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
            int row = nextPos / size;
            int col = nextPos % size;
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        long end = System.currentTimeMillis();
        long start = stateManager.getStartTime();
        super.onPause();
        try {
            saveToFile(new SlidingTileState(boardManager, end - start, stateManager.giveCurrentState().getSteps()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param state the name of the file
     */
    private void loadFromFile(SlidingTileState state) {
        boardManager = state.getBoard();
    }

    /**
     * Save the board manager to fileName.
     *
     * @param state the name of the file
     */
    public void saveToFile(SlidingTileState state) {
        SlidingTileStateManager stateManager = SlidingTileStateManager.getSlidingTileStateManager();
        stateManager.addGameState(state);
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }
}
