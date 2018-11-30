package csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import csc207.fall2018.gamecentreapp.GameCentreActivity.UserSpecificActivity;
import csc207.fall2018.gamecentreapp.R;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {


    private SlidingTileActivityController slidingTileActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingtile_activity_starting_);
        slidingTileActivityController = new SlidingTileActivityController(this);

        addStartButtonListener();
        addLoadButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(v -> switchToSelectSize());
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(v -> slidingTileActivityController.loadSavedGameAttempt(v));
    }


    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToSelectSize() {
        Intent tmp = new Intent(this, SlidingtileSelectSizeActivity.class);
        startActivity(tmp);
    }

    public void onclickGoBack(View view) {
        Intent tmp = new Intent(this, UserSpecificActivity.class);
        startActivity(tmp);
    }

    /**
     * Click my score.
     *
     * @param view the view.
     */
    public void onclickMyScore(View view) {
        Intent intent = new Intent(this, MyScoreActivity.class);
        startActivity(intent);
    }

    /**
     * Click the scoreBoard.
     *
     * @param view the view.
     */
    public void onclickScoreBoard(View view) {
        Intent intent = new Intent(this, SlidingTileScoreBoardActivity.class);
        startActivity(intent);
    }
}
