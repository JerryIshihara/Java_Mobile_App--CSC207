package csc207.fall2018.gamecentreapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameStateDataBase extends SQLiteOpenHelper {

    /**
     * String FILE_NAME "gameDataBase.db"
     */
    private static final String FILE_NAME = "gameDataBase.db";

    /**
     * String TABLE_NAME  "gameStates"
     */
    private static final String TABLE_NAME = "gameStates";

    /**
     * String COL1  "NAME"
     */
    private static final String COL1 = "NAME";

    /**
     * String COL2  "GAME"
     */
    private static final String COL2 = "GAME";

    /**
     * String COL3 "GAME_STATE"
     */
    public static final String COL3 = "GAME_STATE";


    /**
     * The constructor of the GameStateDataBase class.
     *
     * @param context the context
     */
    public GameStateDataBase(Context context) {
        super(context, FILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (NAME VARCHAR, GAME VARCHAR, GAME_STATE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * save the state.
     *
     * @param userName name of the user.
     * @param gameName name of the game.
     * @param state    the state.
     */
    public void saveState(String userName, String gameName, byte[] state) {
        SQLiteDatabase db = this.getWritableDatabase();
        deleteState(userName, gameName);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, userName);
        contentValues.put(COL2, gameName);
        contentValues.put(COL3, state);
        db.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * delete the state.
     *
     * @param userName name of the user.
     * @param gameName name of the game.
     */
    public void deleteState(String userName, String gameName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE NAME = '" + userName + "' AND GAME = '" + gameName + "'");
    }

    /**
     * Return the state according to the name of the user and game.
     *
     * @param userName the name of the user
     * @param gameName the name of the game
     * @return the state according to the user and game.
     */
    public Cursor getStateByGame(String userName, String gameName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE NAME = '" + userName + "' AND GAME = '" + gameName + "'", null);
    }

    /**
     * delete the user.
     *
     * @param userName the name of the user.
     */
    void deleteUser(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE NAME = '" + userName + "'");
    }

    /**
     * delete all information.
     */
    void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
