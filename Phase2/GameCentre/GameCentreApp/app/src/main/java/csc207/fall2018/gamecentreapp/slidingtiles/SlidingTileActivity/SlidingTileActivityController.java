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

public class SlidingTileActivityController extends GameDataBaseAdapter {

    private Timer timer;

    private Context context;


    SlidingTileActivityController(Context context) {
        super(context);
        this.context = context;
    }


    public void startGame() {
        BoardManager boardManager = (BoardManager) loadFromDataBase(context, BoardManager.getGameName());
        timer = new Timer(((Activity) context).findViewById(R.id.sliding_chronometer));
        timer.setUpTimer(boardManager.getIntTime());
        timer.start();
    }

    public void loadSavedGameAttempt(View view) {
        Object loadable = loadFromDataBase(view.getContext(), BoardManager.getGameName());
        if (loadable != null) {
            Toast.makeText(view.getContext(), "Loaded Game", Toast.LENGTH_SHORT).show();
            Intent tmp = new Intent(view.getContext(), GameActivity.class);
            view.getContext().startActivity(tmp);
        }
    }

    public void saveGameAttempt(View view, BoardManager boardManager) {
        boardManager.setTime(timer.returnIntTime());
        saveToDataBase(view.getContext(), boardManager, BoardManager.getGameName());
        Toast.makeText(view.getContext(), "Saved Game", Toast.LENGTH_SHORT).show();
    }


    public void seleteNewGameSize(View view, int size) {
        BoardManager boardManager = new BoardManager(size);
        saveToDataBase(view.getContext(), boardManager, BoardManager.getGameName());
        Intent tmp = new Intent(view.getContext(), GameActivity.class);
        view.getContext().startActivity(tmp);
    }

    public void update(Context context, BoardManager boardManager){
        boolean isSolved = boardManager.puzzleSolved();
        if (isSolved) {
            boardManager.setTime(timer.returnIntTime());
            timer.stop();
            updateScore(context, boardManager);
        }
    }

    private void updateScore(Context context, BoardManager boardManager) {
        DataBase dataBase = new DataBase(context);
        ScoreFactory factory = new ScoreFactory();
        SlidingTileScore score = (SlidingTileScore) factory.generateScore(BoardManager.getGameName());
        Session session = Session.getInstance(context);
        int numMove = boardManager.getNumPastMove();
        score.takeInSizeTimeName(boardManager.getBoard().NUM_COLS, boardManager.getIntTime(), session.getCurrentUserName(), numMove);
        dataBase.addScore(score);
    }

    public void exitAttempt(View view, BoardManager boardManager){
        boardManager.setTime(timer.returnIntTime());
        saveToDataBase(view.getContext(), boardManager, BoardManager.getGameName());
    }
}
