package csc207.fall2018.gamecentreapp.DataBaseAdapter;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.DataBase.DataBase;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;

/**
 * An interface containing default methods to get different score array list.
 */
public abstract class ScoreDataBaseAdapter {

    /**
     * return an array list containing all scores based on given Cursor c.
     *
     * @param c a result set returned by a database query.
     * @return ArrayList<String></>
     */
    private ArrayList<String> getScoreList(Cursor c) {
        ArrayList<String> scores = new ArrayList<>();
        if (c.getCount() == 0) {
            scores.add("No score yet, play right now!");
        } else {
            int i = 1;
            int nameIndex = c.getColumnIndex("NAME");
            int gameIndex = c.getColumnIndex("GAME");
            int scoreIndex = c.getColumnIndex("SCORE");

            while (c.moveToNext()) {
                String temp = "No." + Integer.toString(i) + " |    "
                        + c.getString(gameIndex) + "  |  "
                        + c.getString(nameIndex) + "  |  "
                        + "Score: " + c.getString(scoreIndex);
                scores.add(temp);
                i++;
            }
        }
        return scores;
    }

    /**
     * return an array list containing top 10 scores of current user in the game called gameName.
     *
     * @param context  global information about an application environment
     * @param gameName name of the game
     * @return ArrayList<String></>
     */
    protected ArrayList<String> loadUserScore(Context context, String gameName) {
        DataBase dataBase = new DataBase(context);
        Session session = Session.getInstance(context);
        Cursor c = dataBase.getScoreByGameAndName(session.getCurrentUserName(), gameName);
        return getScoreList(c);
    }

    /**
     * return an array list containing top 10 scores of different users in the game called gameName.
     *
     * @param context  global information about an application environment
     * @param gameName name of the game.
     * @return ArrayList<String></>
     */
    protected ArrayList<String> loadGameScore(Context context, String gameName) {
        DataBase dataBase = new DataBase(context);

        Cursor c = dataBase.getScoreByGame(gameName);
        return getScoreList(c);
    }
}
