package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.GameCentreActivity.UserSpecificActivity;

public class SubtractSquareGameCentreActivity extends AppCompatActivity {

    private SubtractSquareController subtractSquareController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_game_centre);

        subtractSquareController = new SubtractSquareController(this);
    }

    public void onclickGameScoreBoard(View view) {
        Intent gameScoreBoard = new Intent(getApplicationContext(), SubtractSquareScoreActivity.class);
        startActivity(gameScoreBoard);
    }

    public void onclickNewGame(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), SubtractSquareSelectActivity.class);
        startActivity(newGameIntent);
    }

    public void onclickLoadGame(View view) {
        subtractSquareController.loadGameAttempt(view);
    }

    public void onclickMyScore(View view) {
        Intent myScoreBoardIntent = new Intent(this, SubtractSquareMyScoreActivity.class);
        startActivity(myScoreBoardIntent);
    }

    public void onclickGoBack(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
        startActivity(newGameIntent);
    }
}
