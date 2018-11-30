package csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.DisplayScore;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreViewController;
import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;

public class SlidingTileScoreBoardActivity extends AppCompatActivity implements DisplayScore {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tile_score_board);

        display();
    }

    /**
     * Click to go back.
     *
     * @param view the view.
     */
    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), StartingActivity.class);
        startActivity(goBackIntent);
    }

    @Override
    public void display() {
        ScoreViewController scoreViewController = new ScoreViewController(this);
        ListView listView = this.findViewById(R.id.scoreList);
        scoreViewController.showGlobalScoreByGame(listView, BoardManager.getGameName());
    }
}
