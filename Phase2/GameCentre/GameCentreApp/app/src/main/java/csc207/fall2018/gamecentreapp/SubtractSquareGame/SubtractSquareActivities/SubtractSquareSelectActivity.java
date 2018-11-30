package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;

public class SubtractSquareSelectActivity extends AppCompatActivity {

    private SubtractSquareController subtractSquareController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_select);

        subtractSquareController = new SubtractSquareController(this);
    }

    public void onclickSelectTwoPlayers(View view) {
        Intent selectTwoPlayers = new Intent(getApplicationContext(), SubtractSquareStartActivity.class);
        startActivity(selectTwoPlayers);
    }

    public void onclickSelectComputer(View view) {
        subtractSquareController.PCNewGame(view);
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(goBackIntent);
    }

}
