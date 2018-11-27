package csc207.fall2018.gamecentreapp.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import csc207.fall2018.gamecentreapp.GameCentreActivity.UserSpecificActivity;
import csc207.fall2018.gamecentreapp.R;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
    /**
     * The board manager.
     */
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        boardManager = new BoardManager();
//        saveToFile(TEMP_SAVE_FILENAME);

        setContentView(R.layout.slidingtile_activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent tmp = new Intent(this, SlidingtileSelectSizeActivity.class);
                switchToSelectSize();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastLoadedText();
                switchToGame();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile(TEMP_SAVE_FILENAME);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        saveToFile(StartingActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    private void switchToSelectSize() {
        Intent tmp = new Intent(this, SlidingtileSelectSizeActivity.class);
        startActivity(tmp);
    }

    public void onclickGoBack(View view) {
        Intent tmp = new Intent(this, UserSpecificActivity.class);
        startActivity(tmp);
    }


    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {
        try {
            File inputFile = new File(getFilesDir(), fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFile));
            boardManager = (BoardManager) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            File outputFile = new File(getFilesDir(), fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
            objectOutputStream.writeObject(boardManager);
            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
