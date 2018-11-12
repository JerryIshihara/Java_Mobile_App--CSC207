package csc207.fall2018.gamecentreapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import csc207.fall2018.gamecentreapp.SlidingTileGame.SlidingTileScore;

/**
 * A class which manages the scores of a user for all games.
 */
public class UserScoreBoard {

    private List<SlidingTileScore> userSLTScores = new ArrayList<>();

    private static String fileName = UserManager.getCurrentUserName();

    private static UserScoreBoard userScoreBoard = null;


    private UserScoreBoard() {}

    public static UserScoreBoard getBoardInstance(){
        if (userScoreBoard == null){
            userScoreBoard = new UserScoreBoard();
        }
        return userScoreBoard;
    }

    /**
     * Read the file which contains all SlidingTileScores of the user
     */
    private void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Write a file which contains all SlidingTileScores of the user.
     */
    private void writeFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userSLTScores);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Add the score to UserScoreBoard
     * @param newScore score of the user.
     */
    public void addScore(Score newScore) {
        readFile();
        if (newScore.getClass() != SlidingTileScore.class) throw new ClassFormatError();
        userSLTScores.add(((SlidingTileScore) newScore));
        writeFile();
    }

    /**
     * Get the best 10 grades of the user for a game.
     * @return an ArrayList containing 10 best grades of the user.
     */
    public ArrayList<String> getTop10() {
        readFile();
        sort();
        if (userSLTScores.size() >= 10) {
            ArrayList<SlidingTileScore> temp = (ArrayList<SlidingTileScore>) userSLTScores.subList(0, 10);
            ArrayList<String> result = new ArrayList<>();
            for (SlidingTileScore item : temp) {
                result.add(item.toString());
            }
            return result;
        }
        ArrayList<String> result_ = new ArrayList<>();
        for (SlidingTileScore ch : userSLTScores) {
            result_.add(ch.toString());
        }
        while (result_.size() != 10) {
            result_.add("");
        }
        return result_;
    }

    /**
     * Sort all scores stored in UserScoreBoard.
     */
    private void sort() {
        userSLTScores.sort(new Comparator<SlidingTileScore>() {
            @Override
            public int compare(SlidingTileScore o1, SlidingTileScore o2) {
                if (o1.getScore() > o2.getScore()) return 1;
                if (o1.getScore() == o2.getScore()) return 0;
                else return -1;
            }
        });
        writeFile();
    }

}
