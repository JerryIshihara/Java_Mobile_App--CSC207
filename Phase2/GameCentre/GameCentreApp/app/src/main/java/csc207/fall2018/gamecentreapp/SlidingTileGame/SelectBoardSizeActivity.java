package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.UserSpecificActivity;

public class SelectBoardSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_board_size);
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        startActivity(tmp);
    }

    public void onClickSizeThree(View view) {
        long start = System.currentTimeMillis();
        SlidingTileGameInterface game = SlidingTileGameInterface.getInstance();
        game.startNewGame();
        game.startNewBoard(3, start);
        switchToGame();
    }

    public void onClickSizeFour(View view) {
        long start = System.currentTimeMillis();
        SlidingTileGameInterface game = SlidingTileGameInterface.getInstance();
        game.startNewGame();
        game.startNewBoard(4, start);
        switchToGame();
    }

    public void onClickSizeFive(View view) {
        long start = System.currentTimeMillis();
        SlidingTileGameInterface game = SlidingTileGameInterface.getInstance();
        game.startNewGame();
        game.startNewBoard(5, start);
        switchToGame();
    }

    public void onClickSelectBoardReturn(View view) {
        Intent returnIntent = new Intent(getApplicationContext(), SlidingTileStartingActivity.class);
        startActivity(returnIntent);
    }
}
