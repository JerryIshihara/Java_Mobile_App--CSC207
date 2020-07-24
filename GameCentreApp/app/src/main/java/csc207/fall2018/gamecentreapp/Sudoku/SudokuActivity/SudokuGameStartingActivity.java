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
    /**
     * SudokuManager for the game.
     */
    private SudokuManager sudokuManager;
    /**
     * File name for saving and loading..
     */
    static final String FILE_NAME = "tmp.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game);
    }

    /**
     * OnClickListener for Button levelOne.
     */
    public void onClickLevelOne(View view) {
        Button button = findViewById(R.id.levelOne);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        button.setAnimation(anim);
        switchToGame(1);
    }

    /**
     * OnClickListener for Button levelTwo.
     */
    public void onClickLevelTwo(View view) {
        Button button = findViewById(R.id.levelTwo);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        button.setAnimation(anim);
        switchToGame(2);
    }

    /**
     * OnClickListener for Button levelThree.
     */
    public void onClickLevelThree(View view) {
        Button button = findViewById(R.id.levelThree);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.its_me);
        button.setAnimation(anim);
        switchToGame(3);
    }

    /**
     * Navigate to SudokuGameActivity with a SudokuManager of the given difficulty .
     */
    private void switchToGame(int difficulty) {
        sudokuManager = new SudokuManager(difficulty);
        saveToFile(FILE_NAME);
        Intent intent = new Intent(this, SudokuGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
    }

    /**
     * OnClickListener for Button myscore. When clicked, navigate to the SudokuMyScoreActivity.
     */
    public void onclickMyScore(View view) {
        Intent intent = new Intent(this, SudokuMyScoreActivity.class);
        startActivity(intent);
    }

    /**
     * OnClickListener for Button scoreboard. When clicked, navigate to the SudokuScoreBoardActivity.
     */
    public void onclickScoreBoard(View view) {
        Intent intent = new Intent(this, SudokuScoreBoardActivity.class);
        startActivity(intent);
    }

    /**
     * OnClickListener for Button goBack. When clicked, navigate to the UserSpecificActivity.
     */
    public void onclickGoBack(View view) {
        Intent intent = new Intent(this, UserSpecificActivity.class);
        startActivity(intent);
    }

    /**
     * OnClickListener for Button loadSudoku. When clicked, if there is a saved game load that game
     * and navigate to the SudokuGameActivity..
     */
    public void onclickLoadGame(View view) {
        boolean loadable = loadFromDataBase();
        if (loadable) {
            saveToFile(FILE_NAME);
            Intent intent = new Intent(this, SudokuGameActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Load from database.
     */
    private boolean loadFromDataBase() {
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

    /**
     * Save to file.
     */
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
