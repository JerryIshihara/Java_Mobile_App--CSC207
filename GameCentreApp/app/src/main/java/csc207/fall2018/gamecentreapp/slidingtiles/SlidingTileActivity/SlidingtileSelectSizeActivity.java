package csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;

public class SlidingtileSelectSizeActivity extends AppCompatActivity {


    /**
     * The board manager.
     */
    private SlidingTileActivityController slidingTileActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingtile_select_size);
        slidingTileActivityController = new SlidingTileActivityController(this);
    }

    /**
     * Click the size three.
     *
     * @param view the view.
     */
    public void onclickSizeThree(View view) {
        slidingTileActivityController.seleteNewGameSize(view, 3);
    }

    /**
     * Click the size four.
     *
     * @param view the view.
     */
    public void onclickSizeFour(View view) {
        slidingTileActivityController.seleteNewGameSize(view, 4);
    }

    /**
     * Click the size five.
     *
     * @param view the view.
     */
    public void onclickSizeFive(View view) {
        slidingTileActivityController.seleteNewGameSize(view, 5);
    }

    /**
     * Click to go back.
     *
     * @param view the view.
     */
    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(this, StartingActivity.class);
        startActivity(goBackIntent);
    }
}
