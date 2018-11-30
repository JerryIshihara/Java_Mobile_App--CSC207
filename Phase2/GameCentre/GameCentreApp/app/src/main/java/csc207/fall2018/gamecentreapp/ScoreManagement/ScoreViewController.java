package csc207.fall2018.gamecentreapp.ScoreManagement;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.DataBaseAdapter.ScoreDataBaseAdapter;

public class ScoreViewController extends ScoreDataBaseAdapter {

    /**
     * Context context.
     */
    private Context context;

    /**
     * The constructor of the ScoreViewController.
     *
     * @param context the context.
     */
    public ScoreViewController(Context context) {
        this.context = context;
    }

    /**
     * Show the user's score by the given game
     *
     * @param listView the list view.
     * @param gameName the name of the game.
     */
    public void showMyScoreByGame(ListView listView, String gameName) {
        ArrayList<String> scores = loadUserScore(context, gameName);
        setUpScore(listView, scores);
    }

    /**
     * Show the Global score by game.
     *
     * @param listView the list view.
     * @param gameName the name of the game.
     */
    public void showGlobalScoreByGame(ListView listView, String gameName) {
        ArrayList<String> scores = loadGameScore(context, gameName);
        setUpScore(listView, scores);
    }

    /**
     * Set up the score.
     *
     * @param listView the listView.
     * @param scores the scores.
     */
    private void setUpScore(ListView listView, ArrayList<String> scores) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_activated_1, scores);
        listView.setAdapter(arrayAdapter);
    }
}
