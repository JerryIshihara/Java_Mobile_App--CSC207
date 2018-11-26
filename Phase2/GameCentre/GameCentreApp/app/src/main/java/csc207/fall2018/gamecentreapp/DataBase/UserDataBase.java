package csc207.fall2018.gamecentreapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import csc207.fall2018.gamecentreapp.User;

public class UserDataBase extends SQLiteOpenHelper {

    private static final String FILE_NAME = "userDataBase.db";

    private static final String TABLE_NAME = "userList";

    private static final String COL1 = "NAME";

    private static final String COL2 = "PASSWORD";

    private static User currentUser;


    public UserDataBase(Context context) {
        super(context, FILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (NAME VARCHAR, PASSWORD VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void signUpUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, user.getUserName());
        contentValues.put(COL2, user.getPassword());

        currentUser = user;

        db.insert(TABLE_NAME, null, contentValues);
    }

    boolean isRegistered(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE NAME = '" + user.getUserName() + "'", null);
        return (c != null && c.getCount() > 0);
//        int nameIndex = c.getColumnIndex(COL1);
//        while(c !=null && c.getCount()>0){
//            if (c.getString(nameIndex).equals(user.getUserName())) return true;
//            c.moveToNext();
//        }
//        return false;
    }

    public void logOut() {
        currentUser = null;
    }

    public String getCurrentUserName() {
        return currentUser.getUserName();
    }

    void loginUser(User user) {
        if (checkUserValidation(user)) {
            currentUser = user;
        }
    }

    boolean checkUserValidation(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            int nameIndex = c.getColumnIndex(COL1);
            while (c.moveToNext()) {
                if (c.getString(nameIndex).equals(user.getUserName())){
                    int passwordIndex = c.getColumnIndex(COL2);
                    return user.getPassword().equals(c.getString(passwordIndex));
                }
            }
        }
        return false;
    }
}
