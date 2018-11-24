package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;

public class SubtractSquareGameCentreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_game_centre);
    }

    public void onclickGameScoreBoard(View view) {
        Intent gameScoreBoard = new Intent(getApplicationContext(), SubtractSquareScoreActivity.class);
        startActivity(gameScoreBoard);
    }

    public void onclickNewGame(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), SubtractSquareStartActivity.class);
        startActivity(newGameIntent);
    }
}
