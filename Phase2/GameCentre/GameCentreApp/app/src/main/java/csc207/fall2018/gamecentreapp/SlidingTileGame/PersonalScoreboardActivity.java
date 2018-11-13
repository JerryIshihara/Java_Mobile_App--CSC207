package csc207.fall2018.gamecentreapp.SlidingTileGame;

        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.UserScoreBoard;

public class PersonalScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_scoreboard);
        ListView worldScoreBoardLV = findViewById(R.id.worldScoreboardLV);
        UserScoreBoard userScoreBoard = UserScoreBoard.getBoardInstance();
        ArrayList<String> top10 = userScoreBoard.getTop10();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.activity_world_scoreboard, top10);
        worldScoreBoardLV.setAdapter(adapter);
    }
}
