package csc207.fall2018.gamecentreapp.SlidingTileGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.R;

public class WorldScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_scoreboard);
        ListView worldScoreBoardLV = (ListView) findViewById(R.id.worldScoreboardLV);
        SlidingTileScoreBoard scoreBoard = SlidingTileScoreBoard.getBoardInstance();
        ArrayList<String> top10 = scoreBoard.getTop10();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.activity_world_scoreboard, top10);
        worldScoreBoardLV.setAdapter(adapter);

    }
}
