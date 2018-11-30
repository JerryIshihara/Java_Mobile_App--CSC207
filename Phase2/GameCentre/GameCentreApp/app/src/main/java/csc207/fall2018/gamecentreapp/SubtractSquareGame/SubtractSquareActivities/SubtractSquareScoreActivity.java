package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.DisplayScore;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreViewController;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;

public class SubtractSquareScoreActivity extends AppCompatActivity implements DisplayScore {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_score);

        display();
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(goBackIntent);
    }

    @Override
    public void display() {
        ListView listView = this.findViewById(R.id.scoreList);
        ScoreViewController scoreViewController = new ScoreViewController(this);
        scoreViewController.showGlobalScoreByGame(listView, SubtractSquareGame.getGameName());
    }
}
