package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.DataBase.ScoreBoard;

public class SubtractSquareScoreActivity extends AppCompatActivity {

    ScoreBoard scoreBoard;

    private static final String GAME_NAME = "Subtract Square";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_score);

        scoreBoard = new ScoreBoard(this);

        ListView listView = findViewById(R.id.scoreList);
        ArrayList<String> scores = new ArrayList<>();

        Cursor c = scoreBoard.getScoreByGame(GAME_NAME);
        if (c.getCount() == 0) {
            scores.add("No score yet, play right now!");
        } else {
            c.moveToFirst();
            int i = 1;
            int nameIndex = c.getColumnIndex("NAME");
            int gameIndex = c.getColumnIndex("GAME");
            int scoreIndex = c.getColumnIndex("SCORE");

            while (c.moveToNext()) {
                String temp = "No." + Integer.toString(i) + " |    "
                        + c.getString(gameIndex) + "  |  "
                        + c.getString(nameIndex) + "  |  "
                        + "Score: "+ c.getString(scoreIndex);
                scores.add(temp);
                i++;
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, scores);
        listView.setAdapter(arrayAdapter);
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(goBackIntent);
    }
}
