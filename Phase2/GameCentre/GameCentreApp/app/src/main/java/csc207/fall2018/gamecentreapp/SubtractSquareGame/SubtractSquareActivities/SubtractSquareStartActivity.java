package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import csc207.fall2018.gamecentreapp.R;

public class SubtractSquareStartActivity extends AppCompatActivity {

    private SubtractSquareController subtractSquareController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_start);

        subtractSquareController = new SubtractSquareController(this);
    }


    public void onclickStart(View view) {
        final EditText p1Name = findViewById(R.id.p1Name);
        final EditText p2Name = findViewById(R.id.p2Name);
        String p1 = p1Name.getText().toString();
        String p2 = p2Name.getText().toString();

        subtractSquareController.twoPlayerNewGame(view, p1, p2);
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareSelectActivity.class);
        startActivity(goBackIntent);
    }
}