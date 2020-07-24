package csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.DataBase.DataBase;
import csc207.fall2018.gamecentreapp.DataBaseAdapter.GameDataBaseAdapter;
import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreFactory;
import csc207.fall2018.gamecentreapp.ScoreManagement.SlidingTileScore;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.TimeDiplay.Timer;
import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;

class SlidingTileActivityController extends GameDataBaseAdapter {

    /**
     * Time timer.
     */
    private Timer timer;

    /**
     * Context context.
     */
    private Context context;


    /**
     * the constructor of the SlingTileActivityController class.
     * @param context the context.
     */
    SlidingTileActivityController(Context context) {
        super(context);
        this.context = context;
    }


    /**
     * Start the game.
     */
    public void startGame() {
        BoardManager boardManager = (BoardManager) loadFromDataBase(context, BoardManager.getGameName());
        timer = new Timer(((Activity) context).findViewById(R.id.sliding_chronometer));
        timer.setUpTimer(boardManager.getIntTime());
        timer.start();
    }

    /**
     * Attempt to load the saved game.
     * @param view the view.
     */
    public void loadSavedGameAttempt(View view) {
        Object loadable = loadFromDataBase(view.getContext(), BoardManager.getGameName());
        if (loadable != null) {
            Toast.makeText(view.getContext(), "Loaded Game", Toast.LENGTH_SHORT).show();
            Intent tmp = new Intent(view.getContext(), GameActivity.class);
            view.getContext().startActivity(tmp);
        }
    }

    /**
     * Attempt to save the game.
     *
     * @param view the view.
     * @param boardManager the boardManager.
     */
    public void saveGameAttempt(View view, BoardManager boardManager) {
        boardManager.setTime(timer.returnIntTime());
        saveToDataBase(view.getContext(), boardManager, BoardManager.getGameName());
        Toast.makeText(view.getContext(), "Saved Game", Toast.LENGTH_SHORT).show();
    }


    /**
     * Selete the new game size.
     *
     * @param view the view.
     * @param size the size.
     */
    public void seleteNewGameSize(View view, int size) {
        BoardManager boardManager = new BoardManager(size);
        saveToDataBase(view.getContext(), boardManager, BoardManager.getGameName());
        Intent tmp = new Intent(view.getContext(), GameActivity.class);
        view.getContext().startActivity(tmp);
    }

    /**
     * Update the game.
     *
     * @param context the context.
     * @param boardManager the boardManager.
     */
    public void update(Context context, BoardManager boardManager){
        boolean isSolved = boardManager.puzzleSolved();
        if (isSolved) {
            boardManager.setTime(timer.returnIntTime());
            timer.stop();
            updateScore(context, boardManager);
        }
    }

    /**
     * Update the score.
     *
     * @param context the context.
     * @param boardManager the boardManager.
     */
    private void updateScore(Context context, BoardManager boardManager) {
        DataBase dataBase = new DataBase(context);
        ScoreFactory factory = new ScoreFactory();
        SlidingTileScore score = (SlidingTileScore) factory.generateScore(BoardManager.getGameName());
        Session session = Session.getInstance(context);
        int numMove = boardManager.getNumPastMove();
        score.takeInSizeTimeName(boardManager.getBoard().NUM_COLS, boardManager.getIntTime(), session.getCurrentUserName(), numMove);
        dataBase.addScore(score);
    }

    /**
     * Attempt to exit the game.
     *
     * @param view the view.
     * @param boardManager the boardManager.
     */
    public void exitAttempt(View view, BoardManager boardManager){
        boardManager.setTime(timer.returnIntTime());
        saveToDataBase(view.getContext(), boardManager, BoardManager.getGameName());
    }
}
