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

public class SubtractSquareMyScoreActivity extends AppCompatActivity implements DisplayScore {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_my_score);

        display();
    }

    /**
     * OnClickListener for the Button goback. When clicked navigate back to the
     * SubtractSquareGameCentreActivity.
     */
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
