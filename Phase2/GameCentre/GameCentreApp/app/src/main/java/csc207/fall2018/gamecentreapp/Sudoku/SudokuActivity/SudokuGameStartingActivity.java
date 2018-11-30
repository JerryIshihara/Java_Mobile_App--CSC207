package csc207.fall2018.gamecentreapp.Sudoku.SudokuActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import csc207.fall2018.gamecentreapp.DataBase.GameStateDataBase;
import csc207.fall2018.gamecentreapp.GameCentreActivity.UserSpecificActivity;
import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.Sudoku.SudokuManager;


public class SudokuGameStartingActivity extends AppCompatActivity {

    private SudokuManager sudokuManager;

    static final String FILE_NAME = "tmp.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game);
    }

    public void onClickLevelOne(View view) {
        Button button = findViewById(R.id.levelOne);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        button.setAnimation(anim);
        switchToGame(1);
//        overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
    }

    public void onClickLevelTwo(View view) {
        Button button = findViewById(R.id.levelTwo);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        button.setAnimation(anim);
        switchToGame(2);
//        overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
    }

    public void onClickLevelThree(View view) {
        Button button = findViewById(R.id.levelThree);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        button.setAnimation(anim);
        switchToGame(3);
//        Intent intent = new Intent(this, SudokuGameActivity.class);
//        intent.putExtra("Level_Message", 4);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
    }

    private void switchToGame(int difficulty) {
        sudokuManager = new SudokuManager(difficulty);
        saveToFile(FILE_NAME);
        Intent intent = new Intent(this, SudokuGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
    }

    public void onclickMyScore(View view) {
        Intent intent = new Intent(this, SudokuMyScoreActivity.class);
        startActivity(intent);
    }

    public void onclickScoreBoard(View view) {
        Intent intent = new Intent(this, SudokuScoreBoardActivity.class);
        startActivity(intent);
    }

    public void onclickGoBack(View view) {
        Intent intent = new Intent(this, UserSpecificActivity.class);
        startActivity(intent);
    }

    public void onclickLoadGame(View view) {
        boolean loadable = loadFromDataBase();
        if (loadable) {
            saveToFile(FILE_NAME);
            Intent intent = new Intent(this, SudokuGameActivity.class);
            startActivity(intent);
        }
    }

    private boolean loadFromDataBase(/*byte[] byteState*/) {
        GameStateDataBase dataBase = new GameStateDataBase(this);
        Session session = Session.getInstance(this);
        Cursor cursor = dataBase.getStateByGame(session.getCurrentUserName(), SudokuManager.getGameName());

        boolean result = cursor.getCount() != 0;

        if (!result) {
            Toast.makeText(this, "No previous played game, start new one!", Toast.LENGTH_SHORT).show();
        } else {
            int stateIndex = cursor.getColumnIndex(GameStateDataBase.COL3);
            cursor.moveToFirst();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cursor.getBlob(stateIndex));
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                sudokuManager = (SudokuManager) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Error in de-serialization
                e.printStackTrace();
            }
        }
        return result;
    }

    private void saveToFile(String fileName) {
        try {
            File outputFile = new File(getFilesDir(), fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
            objectOutputStream.writeObject(sudokuManager);
            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
