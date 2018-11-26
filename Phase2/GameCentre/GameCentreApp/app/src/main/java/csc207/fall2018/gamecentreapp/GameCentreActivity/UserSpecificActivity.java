package csc207.fall2018.gamecentreapp.GameCentreActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGameCentreActivity;
import csc207.fall2018.gamecentreapp.Sudoku.SudokuGameActivity;
import csc207.fall2018.gamecentreapp.UserManager;
import csc207.fall2018.gamecentreapp.slidingtiles.StartingActivity;

/**
 * A class dealing with user's playing the game
 */
public class UserSpecificActivity extends AppCompatActivity {

    private UserManager userManager;

    private final static String FILE_NAME = "userManager.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_specific);

        loadFromFile(FILE_NAME);

        // show username on top
        TextView userName = findViewById(R.id.centreUserName);
        String userNameInstance = userManager.getCurrentUserName();
        userName.setText(userNameInstance);
    }


    /**
     * Deal with clicking Logout
     *
     * @param view a view of the model.
     */
    public void onClickLogOut(View view) {
        userManager.logOut();
        saveToFile(FILE_NAME);
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnIntent);
    }



    public void onClickSlidingTileGame(View view) {
        Intent slidingTileGameIntent = new Intent(getApplicationContext(), StartingActivity.class);
        startActivity(slidingTileGameIntent);
    }

    public void onclickSubtractSquareGame(View view) {
        Intent startSubtractSquare = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(startSubtractSquare);
    }

    public void onclickSudokugame(View view) {
        Intent startSudoku = new Intent(getApplicationContext(), SudokuGameActivity.class);
        startActivity(startSudoku);
    }

    private void loadFromFile(String fileName) {
        try {
            File inputFile = new File(getFilesDir(), fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFile));
            userManager = (UserManager) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    public void saveToFile(String fileName) {
        try {
            File outputFile = new File(getFilesDir(), fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
            objectOutputStream.writeObject(userManager);
            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

