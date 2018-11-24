package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import android.widget.EditText;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.UserSpecificActivity;

public class SubtractSquareActivity extends AppCompatActivity {

    private SubtractSquareGame subtractSquareGame;

    private final static String TEMP_FILE_NAME = "temp_file.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile(TEMP_FILE_NAME);
        setContentView(R.layout.activity_subtract_square);

        updateCurrentTotal();
        updateProgressContext();
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareStartActivity.class);
        startActivity(goBackIntent);
    }

    public void onclickEnter(View view) {
        EditText input = findViewById(R.id.input);
        String move = input.getText().toString();

        if (subtractSquareGame.isValidMove(move)) {
            subtractSquareGame.applyMove(move);
            updateCurrentTotal();
            updateProgressContext();
            saveToFile(TEMP_FILE_NAME);
            input.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                subtractSquareGame = (SubtractSquareGame) input.readObject();
                inputStream.close();
            }
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
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(subtractSquareGame);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void updateProgressContext() {
        TextView progress = (TextView) findViewById(R.id.progress);
        String progressContext;
        if (subtractSquareGame.is_over()) {
            progressContext = "Game over, " + subtractSquareGame.getWinner() + " win !";
        } else {
            progressContext = subtractSquareGame.getCurrentPlayerName() + "'s turn";
        }
        progress.setText(progressContext);
    }

    private void updateCurrentTotal() {
        TextView gameNumber = (TextView) findViewById(R.id.gameNumber);
        int currentTotal = subtractSquareGame.getCurrentState().getCurrentTotal();
        gameNumber.setText(String.valueOf(currentTotal));
    }

}
