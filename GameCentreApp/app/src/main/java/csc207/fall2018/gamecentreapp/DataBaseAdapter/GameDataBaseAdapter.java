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

    /**
     * The constructor of the GameDataBaseAdapter class.
     *
     * @param context the context
     */
    public GameDataBaseAdapter(Context context) {
        this.dataBase = new DataBase(context);
    }

    /**
     * Save it to database.
     *
     * @param context  the context.
     * @param object   the object.
     * @param gameName the name of the game.
     */
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

    /**
     * Check if load from database.
     *
     * @param context  the context.
     * @param gameName the name of the game.
     * @return whether load from database.
     */
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
                e.printStackTrace();
            }
        }
        return null;
    }
}
