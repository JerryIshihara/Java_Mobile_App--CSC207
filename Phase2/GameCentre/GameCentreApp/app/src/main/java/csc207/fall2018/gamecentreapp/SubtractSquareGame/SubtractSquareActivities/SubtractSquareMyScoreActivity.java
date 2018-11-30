package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.DisplayScore;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreViewController;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;

public class SubtractSquareMyScoreActivity extends AppCompatActivity implements DisplayScore {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_my_score);

        display();
    }

//    private ArrayList<String> loadScoreForDisplay(Cursor c) {
//        ArrayList<String> scores = new ArrayList<>();
//        if (c.getCount() == 0) {
//            scores.add("No score yet, play right now!");
//        } else {
////            c.moveToFirst();
//            int i = 1;
//            int nameIndex = c.getColumnIndex("NAME");
//            int gameIndex = c.getColumnIndex("GAME");
//            int scoreIndex = c.getColumnIndex("SCORE");
//
//            while (c.moveToNext()) {
//                String temp = "No." + Integer.toString(i) + " |    "
//                        + c.getString(gameIndex) + "  |  "
//                        + c.getString(nameIndex) + "  |  "
//                        + "Score: " + c.getString(scoreIndex);
//                scores.add(temp);
//                i++;
//            }
//        }
//        return scores;
//    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(this, SubtractSquareGameCentreActivity.class);
        startActivity(goBackIntent);
    }

    @Override
    public void display() {
        ListView listView = this.findViewById(R.id.scoreList);
        ScoreViewController scoreViewController = new ScoreViewController(this);
        scoreViewController.showMyScoreByGame(listView, SubtractSquareGame.getGameName());
    }
}
