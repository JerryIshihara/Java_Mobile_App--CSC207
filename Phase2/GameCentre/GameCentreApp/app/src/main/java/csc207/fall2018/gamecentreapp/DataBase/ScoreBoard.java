package csc207.fall2018.gamecentreapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import csc207.fall2018.gamecentreapp.ScoreManagement.Score;

class ScoreBoard extends SQLiteOpenHelper {

    /**
     * String FILE_NAME  "scoreBoard.db"
     */
    private static final String FILE_NAME = "scoreBoard.db";

    /**
     * String TABLE_NAME  "scoreList"
     */
    private static final String TABLE_NAME = "scoreList";

    /**
     * String String COL1  "NAME"
     */
    private static final String COL1 = "NAME";

    /**
     * String COL2  "GAME"
     */
    private static final String COL2 = "GAME";

    /**
     * String COL3  "SCORE"
     */
    private static final String COL3 = "SCORE";

    /**
     * the constructor of the ScoreBoar class.
     *
     * @param context the context
     */
    ScoreBoard(Context context) {
        super(context, FILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (NAME VARCHAR, GAME VARCHAR, SCORE VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * add the score.
     *
     * @param score the score.
     */
    void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, score.returnPlayerName());
        contentValues.put(COL2, score.returnGameName());
        contentValues.put(COL3, score.calculateScore());
        db.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * Return the score by game.
     *
     * @param game the game.
     * @return the score according to name.
     */
    Cursor getScoreByGame(String game) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE GAME = '" + game + "' ORDER BY SCORE DESC LIMIT 10", null);
    }

    /**
     * Return the score according to the name and game.
     *
     * @param name the name.
     * @param game the game.
     * @return the score by the given name and game.
     */
    Cursor getScoreByGameAndName(String name, String game) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE GAME = '" + game + "' AND NAME = '" + name + "' ORDER BY SCORE DESC LIMIT 10", null);
    }

    /**
     * delete the user.
     *
     * @param userName the user name.
     */
    void deleteUser(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE NAME = '" + userName + "'");
    }

    /**
     * delete all scores.
     */
    void deleteAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}


