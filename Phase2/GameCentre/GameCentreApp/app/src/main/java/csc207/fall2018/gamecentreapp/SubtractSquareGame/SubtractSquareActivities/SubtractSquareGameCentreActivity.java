package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.GameCentreActivity.UserSpecificActivity;

public class SubtractSquareGameCentreActivity extends AppCompatActivity {

  /**
   * The controller for processing events
   */
  private SubtractSquareController subtractSquareController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_game_centre);

        subtractSquareController = new SubtractSquareController(this);
    }

  /**
   * OnClickListener for the Button SSScoreboard. When clicked navigate to the
   * SubtractSquareScoreActivity.
   */
  public void onclickGameScoreBoard(View view) {
        Intent gameScoreBoard = new Intent(getApplicationContext(), SubtractSquareScoreActivity.class);
        startActivity(gameScoreBoard);
    }

  /**
   * OnClickListener for the Button NewSSGame. When clicked navigate to the
   * SubtractSquareSelectActivity.
   */
    public void onclickNewGame(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), SubtractSquareSelectActivity.class);
        startActivity(newGameIntent);
    }

  /**
   * OnClickListener for the Button loadGameSS. When clicked make an attempt to load a saved game
   * if any.
   */
    public void onclickLoadGame(View view) {
        subtractSquareController.loadGameAttempt(view);
    }

  /**
   * OnClickListener for the Button myScore. When clicked, navigate to the
   * SubtractSquareMyScoreActivity.
   */
    public void onclickMyScore(View view) {
        Intent myScoreBoardIntent = new Intent(this, SubtractSquareMyScoreActivity.class);
        startActivity(myScoreBoardIntent);
    }

  /**
   * OnClickListener for the Button goBackSS. Navigate back to the UserSpecificActivity.
   */
    public void onclickGoBack(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
        startActivity(newGameIntent);
    }
}
