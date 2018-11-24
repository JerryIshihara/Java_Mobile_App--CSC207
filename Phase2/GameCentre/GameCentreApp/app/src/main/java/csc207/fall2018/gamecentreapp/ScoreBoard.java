package csc207.fall2018.gamecentreapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class ScoreBoard extends SQLiteOpenHelper {

    private static final String FILE_NAME = "scoreBoard.db";

    private static final String TABLE_NAME = "scoreList";

    private static final String COL1 = "NAME";

    private static final String COL2 = "GAME";

    private static final String COL3 = "SCORE";

    public ScoreBoard(Context context) {
        super(context, FILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (NAME VARCHAR, GAME VARCHAR, SCORE INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addScore(String name, String gameName, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, gameName);
        contentValues.put(COL3, score);
        db.insert(TABLE_NAME, name, contentValues);
    }

    public Cursor getScoreByGame(String game) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE GAME = '" + game + "' ORDER BY SCORE DESC LIMIT 10", null);
    }

    public Cursor getScoreByGameAndName(String name, String game) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE GAME = '" + game + "' AND NAME = '" + name + "' ORDER BY SCORE DESC LIMIT 10", null);
    }

}


