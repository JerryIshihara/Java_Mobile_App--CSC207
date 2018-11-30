package csc207.fall2018.gamecentreapp.Sudoku.SudokuActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.DisplayScore;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreViewController;
import csc207.fall2018.gamecentreapp.Sudoku.SudokuManager;

public class SudokuScoreBoardActivity extends AppCompatActivity implements DisplayScore {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_score_board);

        display();
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SudokuGameStartingActivity.class);
        startActivity(goBackIntent);
    }

    @Override
    public void display() {
        ListView listView = this.findViewById(R.id.scoreList);
        ScoreViewController scoreViewController = new ScoreViewController(this);
        scoreViewController.showGlobalScoreByGame(listView, SudokuManager.getGameName());
    }
}
