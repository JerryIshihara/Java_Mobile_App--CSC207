package csc207.fall2018.gamecentreapp;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A class storing all users' information and managing users to play the games.
 */
public class UserManager implements Serializable {

    private static UserManager userManagerInstance;

    private static User currentUser;

    private static ArrayList<User> users = new ArrayList<>();


    private UserManager() {
    }

    public static UserManager getInstance() {
        if (userManagerInstance == null) {
            userManagerInstance = new UserManager();
        }
        return userManagerInstance;
    }

    public boolean isRegistered(User user) {
        String userName = user.getUserName();
        for (User user1 : users) {
            if (user1.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public void signUp(User user) {
        users.add(user);
        currentUser = user;
    }

    public boolean checkUserValidation(User user) {
        if (!isRegistered(user)) return false;
        for (User user1 : users) {
            if (user1.getUserName().equals(user.getUserName())) {
                return user.getPassword().equals(user1.getPassword());
            }
        }
        return false;
    }

    public void loginUser(User user) {
        if (!isLoggedIn() && checkUserValidation(user)) {
            currentUser = user;
        }
    }

    private boolean isLoggedIn() {
        return currentUser != null;
    }

    public void logOut() {
        currentUser = null;
    }

    public String getCurrentUserName() {
        if (currentUser != null) {
            return currentUser.getUserName();
        } else {
            return "No Username";
        }
    }
}


//    public boolean isRegistered(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + fileName + " WHERE NAME = " + user.getUserName(), null);
//        return c != null;
//    }
//
//
//    public void signUp(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String name = user.getUserName();
//        String password = user.getPassword();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("NAME", name);
//        contentValues.put("PASSWORD", password);
//        db.insert(fileName, null, contentValues);
//    }
//
//
//    public boolean checkUserValidation(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + fileName + " WHERE NAME = " + user.getUserName(), null);
//        int passwordIndex = c.getColumnIndex("PASSWORD");
//        return user.getPassword().equals(c.getString(passwordIndex));
//    }
//
//
//    /**
//     * Check whether currentUser is null.
//     *
//     * @return boolean value whether there is someone logged in.
//     */
//    public boolean isLoggedIn() {
//        return currentUser != null;
//    }
//
//    /**
//     * Login the gameApp
//     *
//     * @param user an User playing then game.
//     */
//    public void loginUser(User user) {
//        if (!isLoggedIn() && checkUserValidation(user)) {
//            currentUser = user;
//        }
//    }
//
//    /**
//     * Logout the gameApp
//     */
//    public void logOut() {
//        currentUser = null;
//    }
//
//    /**
//     * Return the name of the current user.
//     */
//    public static String getCurrentUserName() {
//        if (currentUser != null) {
//            return currentUser.getUserName();
//        } else {
//            return "No User Right Now";
//        }
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + fileName + " (NAME VARCHAR, PASSWORD VARCHAR)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + fileName);
//        onCreate(db);
//    }
//    }
