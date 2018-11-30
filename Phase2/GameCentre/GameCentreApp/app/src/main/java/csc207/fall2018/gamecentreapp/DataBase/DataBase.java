package csc207.fall2018.gamecentreapp.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import csc207.fall2018.gamecentreapp.ScoreManagement.Score;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.GameCentreActivity.User;

public class DataBase {

    private SQLiteOpenHelper gameStateDataBase;

    private SQLiteOpenHelper scoreBoard;

    private SQLiteOpenHelper userDataBase;


    public DataBase(Context context) {
        this.gameStateDataBase = new GameStateDataBase(context);
        this.scoreBoard = new ScoreBoard(context);
        this.userDataBase = new UserDataBase(context);
    }

    public int loginUser(User user) {
        UserDataBase base = (UserDataBase) userDataBase;
        if (!base.hasUser(user)) return 0;
        else if (!base.checkUserValidation(user)) return -1;
        return 1;
    }

    public int registerUser(User user) {
        UserDataBase base = (UserDataBase) userDataBase;
        if (!base.hasUser(user)) {
            base.addUser(user);
            return 1;
        }
        return -1;
    }

    public void deleteUser(String user) {
        ((UserDataBase) userDataBase).deleteUser(user);
        ((ScoreBoard) scoreBoard).deleteUser(user);
        ((GameStateDataBase) gameStateDataBase).deleteUser(user);
    }

    public void adminDeleteGameInfo() {
        ((ScoreBoard) scoreBoard).deleteAllScores();
        ((GameStateDataBase) gameStateDataBase).deleteAll();
    }

    public void addScore(Score score) {
        ((ScoreBoard) scoreBoard).addScore(score);
    }

    public Cursor getScoreByGameAndName(String user, String game) {
        return ((ScoreBoard) scoreBoard).getScoreByGameAndName(user, game);
    }

    public Cursor getScoreByGame(String game) {
        return ((ScoreBoard) scoreBoard).getScoreByGame(game);
    }

    public void deleteState(Context context, String gameName) {
        Session session = Session.getInstance(context);
        ((GameStateDataBase) gameStateDataBase).deleteState(session.getCurrentUserName(), gameName);
    }

    public void saveState(Context context, String gameName, byte[] stream) {
        Session session = Session.getInstance(context);
        ((GameStateDataBase) gameStateDataBase).saveState(session.getCurrentUserName(), gameName, stream);
    }

    public Cursor getStateByGame(Context context, String gameName) {
        Session session = Session.getInstance(context);
        return ((GameStateDataBase) gameStateDataBase).getStateByGame(session.getCurrentUserName(), gameName);
    }


//    dataBase.deleteState(session.getCurrentUserName(), SubtractSquareGame.getGameName());
//
//        dataBase.saveState(session.getCurrentUserName(), SubtractSquareGame.getGameName(), stream);


}
