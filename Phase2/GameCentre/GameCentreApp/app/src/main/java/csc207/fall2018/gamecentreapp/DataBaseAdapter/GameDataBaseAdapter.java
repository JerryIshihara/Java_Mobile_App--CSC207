package csc207.fall2018.gamecentreapp.DataBaseAdapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import csc207.fall2018.gamecentreapp.DataBase.DataBase;

public abstract class GameDataBaseAdapter {

    private DataBase dataBase;

    private static final String COL_INDEX = "GAME_STATE";

    public GameDataBaseAdapter(Context context) {
        this.dataBase = new DataBase(context);
    }

    //    String TEMP_FILE = "temp.ser";

//    default void saveToFile(Context context, Object object) {
//        try {
//            File outputFile = new File(context.getFilesDir(), TEMP_FILE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(outputFile));
//            objectOutputStream.writeObject(object);
//            objectOutputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    default Object loadFromFile(Context context) {
//        try {
//            File inputFile = new File(context.getFilesDir(), TEMP_FILE);
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFile));
//            Object game = objectInputStream.readObject();
//            objectInputStream.close();
//            return game;
//        } catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
//        }
//        return null;
//    }

    protected void saveToDataBase(Context context, Object object, String gameName) {
        byte[] stream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            stream = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBase.deleteState(context, gameName);
        dataBase.saveState(context, gameName, stream);
    }

    public Object loadFromDataBase(Context context, String gameName) {
        Cursor cursor = dataBase.getStateByGame(context, gameName);

        boolean result = cursor.getCount() != 0;

        if (!result) {
            Toast.makeText(context, "No previous played game, start new one!", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            int stateIndex = cursor.getColumnIndex(COL_INDEX);
            cursor.moveToFirst();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cursor.getBlob(stateIndex));
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Error in de-serialization
                e.printStackTrace();
            }
        }
        return null;
    }
}
