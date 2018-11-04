package csc207.fall2018.gamecentreapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private List<Score> scoreArray = new ArrayList<>();

    private final static String gameScores = "scoreBoard.ser";

    public void addScore(Score newScore) {

        scoreArray.add(newScore);

        try {
            FileOutputStream fileOut = new FileOutputStream("scoreBoard.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(scoreArray);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in scoreBoard.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
