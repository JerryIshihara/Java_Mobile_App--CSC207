package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.MainActivity;
import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.UserScoreBoard;
import csc207.fall2018.gamecentreapp.UserSpecificActivity;

public class SlidingTileStartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tile_starting);
    }

    public void onClickStartNewGame(View view) {
        Intent newGame = new Intent(getApplicationContext(), SelectBoardSizeActivity.class);
        startActivity(newGame);
    }

    public void onClickLoadGame(View view) {
        SlidingTileGameInterface game = SlidingTileGameInterface.getInstance();
        try {
            game.loadState();
            Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Sorry, no previous game", Toast.LENGTH_SHORT).show();
        }
        Intent newGame = new Intent(getApplicationContext(), SlidingTilesGameActivity.class);
        startActivity(newGame);
    }


    // return & display scores

    public void onClickWorldRecord(View view) {
        Intent displayWorldRecord = new Intent(getApplicationContext(), WorldScoreboardActivity.class);
        startActivity(displayWorldRecord);

    }

    public void onClickMyRecord(View view) {
        Intent displayMyRecord = new Intent(getApplicationContext(), PersonalScoreboardActivity.class);
        startActivity(displayMyRecord);
    }

    public void onclickSLTStartingReturn(View view) {
        Intent returnIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
        startActivity(returnIntent);
    }
}
