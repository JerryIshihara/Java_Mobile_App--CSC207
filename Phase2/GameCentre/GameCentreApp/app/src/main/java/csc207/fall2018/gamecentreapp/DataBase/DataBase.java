package csc207.fall2018.gamecentreapp.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import csc207.fall2018.gamecentreapp.ScoreManagement.Score;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.GameCentreActivity.User;

public class DataBase {
    /**
     * SQLiteOpenHelper  gameStateDataBase.
     */
    private SQLiteOpenHelper gameStateDataBase;

    /**
     * SQLiteOpenHelper scoreBoard.
     */
    private SQLiteOpenHelper scoreBoard;

    /**
     * SQLiteOpenHelper userDataBase
     */
    private SQLiteOpenHelper userDataBase;

    /**
     * Constructor of the DataBase class.
     *
     * @param context the context.
     */
    public DataBase(Context context) {
        this.gameStateDataBase = new GameStateDataBase(context);
        this.scoreBoard = new ScoreBoard(context);
        this.userDataBase = new UserDataBase(context);
    }

    /**
     * Return an integer according to different 3 situations.
     * Return 0 if this user does not exist.
     * Return -1 if this user exist but the input information is invalid.
     * Return 1 is the user exist and all information are correct.
     *
     * @param user the user.
     * @return an integer from three different integers according to three different situations.
     */
    public int loginUser(User user) {
        UserDataBase base = (UserDataBase) userDataBase;
        if (!base.hasUser(user)) return 0;
        else if (!base.checkUserValidation(user)) return -1;
        return 1;
    }

    /**
     * Return an integer according to different 2 situations.
     * Return 1 if this user has not been registered.
     * Return -1 if this user has already been registered
     *
     * @param user the user.
     * @return an integer from -1 and 1 about whether the user has been registered.
     */
    public int registerUser(User user) {
        UserDataBase base = (UserDataBase) userDataBase;
        if (!base.hasUser(user)) {
            base.addUser(user);
            return 1;
        }
        return -1;
    }

    /**
     * delete the User.
     *
     * @param user the user.
     */
    public void deleteUser(String user) {
        ((UserDataBase) userDataBase).deleteUser(user);
        ((ScoreBoard) scoreBoard).deleteUser(user);
        ((GameStateDataBase) gameStateDataBase).deleteUser(user);
    }

    /**
     * delete all the scores for all users.
     */
    public void adminDeleteGameInfo() {
        ((ScoreBoard) scoreBoard).deleteAllScores();
        ((GameStateDataBase) gameStateDataBase).deleteAll();
    }

    /**
     * add the score of one user for one game.
     *
     * @param score the score.
     */
    public void addScore(Score score) {
        ((ScoreBoard) scoreBoard).addScore(score);
    }

    /**
     * Return the score of the user for the game.
     *
     * @param user the user.
     * @param game the game.
     * @return an integer which is the score of the user for one game.
     */
    public Cursor getScoreByGameAndName(String user, String game) {
        return ((ScoreBoard) scoreBoard).getScoreByGameAndName(user, game);
    }

    /**
     * Return all the scores of the game.
     *
     * @param game the game.
     * @return integers which are all the scores of the game.
     */
    public Cursor getScoreByGame(String game) {
        return ((ScoreBoard) scoreBoard).getScoreByGame(game);
    }

    /**
     * delete the state of the given game.
     *
     * @param context  the context.
     * @param gameName the name.
     */
    public void deleteState(Context context, String gameName) {
        Session session = Session.getInstance(context);
        ((GameStateDataBase) gameStateDataBase).deleteState(session.getCurrentUserName(), gameName);
    }

    /**
     * save the state of the given game.
     *
     * @param context  the context.
     * @param gameName the name.
     */
    public void saveState(Context context, String gameName, byte[] stream) {
        Session session = Session.getInstance(context);
        ((GameStateDataBase) gameStateDataBase).saveState(session.getCurrentUserName(), gameName, stream);
    }

    /**
     * Return the state of the game.
     *
     * @param context the context.
     * @return the state of the given context and name.
     */
    public Cursor getStateByGame(Context context, String gameName) {
        Session session = Session.getInstance(context);
        return ((GameStateDataBase) gameStateDataBase).getStateByGame(session.getCurrentUserName(), gameName);
    }

}
