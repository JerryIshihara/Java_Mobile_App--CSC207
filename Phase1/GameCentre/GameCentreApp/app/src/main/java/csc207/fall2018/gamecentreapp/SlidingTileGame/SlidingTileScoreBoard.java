package csc207.fall2018.gamecentreapp.SlidingTileGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import csc207.fall2018.gamecentreapp.Score;

public class SlidingTileScoreBoard {

    private static List<SlidingTileScore> scores = new ArrayList<>();

    private static SlidingTileScoreBoard boardInstance = null;

    private final static String fileName = SlidingTileGameInterface.GAME_NAME + "ScoreBoard.ser";

    private SlidingTileScoreBoard() {}

    public static SlidingTileScoreBoard getBoardInstance(){
        if (boardInstance == null){
            boardInstance = new SlidingTileScoreBoard();
        }
        return boardInstance;
    }

    public void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            writeFile();
        }
    }

    public void writeFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(scores);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void addScore(SlidingTileScore newScore) {
        if (newScore.getClass() != SlidingTileScore.class) throw new ClassFormatError();
        readFile();
        scores.add(newScore);
        writeFile();
    }

    public ArrayList getTop10() {
        readFile();
        sort();
        if (scores.size() >= 10) {
            ArrayList<SlidingTileScore> temp = (ArrayList<SlidingTileScore>) scores.subList(0,10);
            ArrayList<String> result = new ArrayList<>();
            for (SlidingTileScore item : temp) {
                result.add(item.toString());
            }
            return result;
        }
        ArrayList<String> result_ = new ArrayList<>();
        for (SlidingTileScore ch : scores) {
            result_.add(ch.toString());
        }
        while (result_.size() != 10) {
            result_.add("");
        }
        return result_;
    }

     private void sort() {
        readFile();
        scores.sort(new Comparator<SlidingTileScore>() {
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
