package csc207.fall2018.gamecentreapp;

import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import csc207.fall2018.gamecentreapp.SlidingTileGame.SlidingTileState;

/**
 * A class managing all states of the game sliding tile.
 */
// Singleton
public class SlidingTileStateManager {
    /**
     * A stateManagerInstance checking if there is more than one instance of SlidingTileStateManager
     */
    private static SlidingTileStateManager stateManagerInstance = null;
    /**
     * A list containing SlidingTileStates.
     */
    private static List<SlidingTileState> stateList = new ArrayList<>();
    /**
     * A string representing the name of a file.
     */
    private final static String fileName = "slidingTileStateManager" +
            UserManager.getCurrentUserName() + ".ser";

    /**
     * The constructor of the class SlidingTileStateManager.
     */
    private SlidingTileStateManager() {
    }

    //  Initiator

    /**
     * Check if an instance of SlidingTileStateManager is null and return non-null one.
     *
     * @return a SlidingTileStateManager
     */
    public static SlidingTileStateManager getSlidingTileStateManager() {
        if (stateManagerInstance == null) {
            readFile();
            stateManagerInstance = new SlidingTileStateManager();
        }
        return stateManagerInstance;
    }

    /**
     * Read the file which contains  all slidingTileStates.
     */
    private static void readFile() {

        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            stateList = (List<SlidingTileState>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            Log.e("game activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("game activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("game activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Write the file which contains  all slidingTileStates.
     */
    private static void writeFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(stateList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed:" + e.toString());
        }
    }

    /**
     * Add a SlidingTileState to the interface.
     *
     * @param state a SlidingTileState being added.
     */
    public void addGameState(SlidingTileState state) {
        stateList.add(state);
    }

    /**
     * Remove the last state of the game.
     */
    public void undoGameState() throws Exception {
        readFile();
        if (stateList.size() != 0) {
            stateList.remove(-1);
        }
        writeFile();
    }

    /**
     * Start a new sliding tiles game
     */
    public void initiateGameState() {
        stateList = new ArrayList<>();
        writeFile();
    }

    /**
     * Get the  current state of the sliding tiles game.
     *
     * @return a SlidingTileState
     */
    public SlidingTileState giveCurrentState() {
        readFile();
        return stateList.get(stateList.size() - 1);
    }

    /**
     * Check if the SlidingTileStateManager stores any states.
     *
     * @return boolean value representing whether at least one state is stored.
     */
    public boolean hasState() {
        readFile();
        return (stateList.size() != 0);

    }

    /**
     * Get the number of steps to the current state from beginning.
     *
     * @return an integer showing total number of steps.
     */

    public int getSteps() {
        readFile();
        return stateList.size() - 1;
    }

    /**
     * Go back to the first state.
     */
    public void backToFirstState() {
        readFile();
        SlidingTileState tempState = stateList.get(0);
        stateList = new ArrayList<>();
        stateList.add(tempState);
        writeFile();
    }

    public long getStartTime() {
        readFile();
        return stateList.get(0).getTime();
    }


    /**
     * Close the manager currently.
     */
    public void closeCurrentManager() {
        writeFile();
        stateManagerInstance = null;
        stateList = new ArrayList<>();
    }
}