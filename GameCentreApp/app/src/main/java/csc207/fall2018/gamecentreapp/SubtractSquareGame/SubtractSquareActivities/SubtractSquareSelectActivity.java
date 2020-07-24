package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;

public class SubtractSquareSelectActivity extends AppCompatActivity {

  /**
   * The controller for processing events
   */
    private SubtractSquareController subtractSquareController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_select);

        subtractSquareController = new SubtractSquareController(this);
    }

  /**
   * OnClickListener for the Button selectTwoPlayers. When clicked, navigate to the
   * SubtractSquareStartActivity.
   */
    public void onclickSelectTwoPlayers(View view) {
        Intent selectTwoPlayers = new Intent(getApplicationContext(), SubtractSquareStartActivity.class);
        startActivity(selectTwoPlayers);
    }

  /**
   * OnClickListener for the Button selectComputer. When clicked, create a computer player as opponent.
   */
    public void onclickSelectComputer(View view) {
        subtractSquareController.PCNewGame(view);
    }

  /**
   * OnClickListener for the Button goBack. When clicked, navigate back to the
   * SubtractSquareGameCentreActivity
   */
    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(goBackIntent);
    }

}
