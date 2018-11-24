package csc207.fall2018.gamecentreapp.Sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import csc207.fall2018.gamecentreapp.R;


public class SudokuGameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        Button click = (Button) v;
        click.setAnimation(anim);
        Intent i = new Intent(this, GameInterface.class);
        Button pressed = (Button) v;
        System.out.print(pressed.getText());
        String gameLevel = pressed.getText().toString();
        int level;
        switch (gameLevel) {
            case "LEVEL_1":
                level = 2;
                break;
            case "LEVEL_2":
                level = 3;
                break;
            case "LEVEL_3":
                level = 4;
                break;
            default:
                level = 0;
        }
        i.putExtra("Level_Message", level);
        startActivity(i);
        overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
    }


}
