package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.GameCentreActivity.UserSpecificActivity;

public class SubtractSquareGameCentreActivity extends AppCompatActivity {

//    private SubtractSquareGame subtractSquareGame;
//
//    private final static String TEMP_FILE_NAME = "temp_file.ser";

//    private Session session;

    private SubtractSquareController subtractSquareController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square_game_centre);
//        session = Session.getInstance(this);
        subtractSquareController = new SubtractSquareController(this);
    }

    public void onclickGameScoreBoard(View view) {
        Intent gameScoreBoard = new Intent(getApplicationContext(), SubtractSquareScoreActivity.class);
        startActivity(gameScoreBoard);
    }

    public void onclickNewGame(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), SubtractSquareSelectActivity.class);
        startActivity(newGameIntent);
    }

    public void onclickLoadGame(View view) {
//        Object loadable = loadFromDataBase(this,SubtractSquareGame.getGameName());
//
//        if (loadable == null) {
//            Toast.makeText(this, "No previous played game, start new one!", Toast.LENGTH_SHORT).show();
//        } else {
//            switchToGame();
//        }
        subtractSquareController.loadGameAttempt(view);
    }

    public void onclickMyScore(View view) {
        Intent myScoreBoardIntent = new Intent(this, SubtractSquareMyScoreActivity.class);
        startActivity(myScoreBoardIntent);
    }

    public void onclickGoBack(View view) {
        Intent newGameIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
        startActivity(newGameIntent);
    }

//    private void loadFromFile(String fileName) {
//        try {
//            File inputFile = new File(getFilesDir(), fileName);
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFile));
//            subtractSquareGame = (SubtractSquareGame) objectInputStream.readObject();
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
//     * Save the subtract square to fileName.
//     *
//     * @param fileName the name of the file
//     */
//    public void saveToFile(String fileName) {
//        try {
//            File outputFile = new File(getFilesDir(), fileName);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
//            objectOutputStream.writeObject(subtractSquareGame);
//            objectOutputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    private boolean loadFromDataBase(/*byte[] byteState*/) {
//        GameStateDataBase dataBase = new GameStateDataBase(this);
//        Cursor cursor = dataBase.getStateByGame(session.getCurrentUserName(), SubtractSquareGame.getGameName());
//
//        boolean result = cursor.getCount() != 0;
//
//        if (!result) {
//            Toast.makeText(this, "No previous played game, start new one!", Toast.LENGTH_SHORT).show();
//        } else {
//            int stateIndex = cursor.getColumnIndex(GameStateDataBase.COL3);
//            cursor.moveToFirst();
//            try {
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cursor.getBlob(stateIndex));
//                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//                subtractSquareGame = (SubtractSquareGame) objectInputStream.readObject();
//            } catch (IOException | ClassNotFoundException e) {
//                // Error in de-serialization
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }

//    private void switchToGame() {
////        saveToFile(TEMP_FILE_NAME);
//        Intent tmp = new Intent(this, SubtractSquareActivity.class);
//        String p2Name = (subtractSquareGame == null) ? "" : subtractSquareGame.getCurrentState().getP2Name();
//        tmp.putExtra("PC_MODE", p2Name.equals("PC"));
//        startActivity(tmp);
//    }
}
