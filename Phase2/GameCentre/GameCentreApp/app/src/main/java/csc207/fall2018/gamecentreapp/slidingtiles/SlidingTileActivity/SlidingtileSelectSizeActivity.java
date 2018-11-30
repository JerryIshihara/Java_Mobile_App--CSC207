package csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;
import csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity.GameActivity;
import csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity.StartingActivity;

public class SlidingtileSelectSizeActivity extends AppCompatActivity {

//    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
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

    public void onclickSizeThree(View view) {
        slidingTileActivityController.seleteNewGameSize(view, 3);
    }

    public void onclickSizeFour(View view) {
        slidingTileActivityController.seleteNewGameSize(view, 4);
    }

    public void onclickSizeFive(View view) {
        slidingTileActivityController.seleteNewGameSize(view, 5);
    }

    public void onclickGoBack(View view) {
        Intent goBackIntent = new Intent(this, StartingActivity.class);
        startActivity(goBackIntent);
    }


//    /**
//     * Load the board manager from fileName.
//     *
//     * @param fileName the name of the file
//     */
//    private void loadFromFile(String fileName) {
//        try {
//            File inputFile = new File(getFilesDir(), fileName);
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFile));
//            boardManager = (BoardManager) objectInputStream.readObject();
//            objectInputStream.close();
//        } catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
//
//    /**
//     * Save the board manager to fileName.
//     *
//     * @param fileName the name of the file
//     */
//    public void saveToFile(String fileName) {
//        try {
//            File outputFile = new File(getFilesDir(), fileName);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
//            objectOutputStream.writeObject(boardManager);
//            objectOutputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    private void switchToGame() {
//        Intent tmp = new Intent(this, GameActivity.class);
//        saveToFile(StartingActivity.TEMP_SAVE_FILENAME);
//        startActivity(tmp);
//    }
}
