package csc207.fall2018.gamecentreapp.ScoreManagement;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.DataBaseAdapter.ScoreDataBaseAdapter;

public class ScoreViewController extends ScoreDataBaseAdapter {

    private Context context;

    public ScoreViewController(Context context) {
        this.context = context;
    }


    public void showMyScoreByGame(ListView listView, String gameName) {
        ArrayList<String> scores = loadUserScore(context, gameName);
        setUpScore(listView, scores);
    }

    public void showGlobalScoreByGame(ListView listView, String gameName) {
        ArrayList<String> scores = loadGameScore(context, gameName);
        setUpScore(listView, scores);
    }

    private void setUpScore(ListView listView, ArrayList<String> scores) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_activated_1, scores);
        listView.setAdapter(arrayAdapter);
    }
}


//    ListView listView = findViewById(R.id.scoreList);
//    ArrayList<String> scores = loadGameScore(this, SubtractSquareGame.getGameName());
//
//    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, scores);
//        listView.setAdapter(arrayAdapter);