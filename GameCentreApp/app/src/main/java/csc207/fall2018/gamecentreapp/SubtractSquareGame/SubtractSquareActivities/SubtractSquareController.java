package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import csc207.fall2018.gamecentreapp.DataBase.DataBase;
import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreFactory;
import csc207.fall2018.gamecentreapp.ScoreManagement.SubtractSquareScore;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.ComputerChoice;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;
import csc207.fall2018.gamecentreapp.TimeDiplay.Timer;
import csc207.fall2018.gamecentreapp.DataBaseAdapter.GameDataBaseAdapter;

class SubtractSquareController extends GameDataBaseAdapter implements Observer {

    private Context context;

    private SubtractSquareGame subtractSquareGame;

  /**
   * Whether playing against a computer
   */
  private boolean pcMode;

    private Timer timer;

  /**
   * Number of undo available
   */
  private int undoBatch;

    SubtractSquareController(Context context) {
        super(context);
        this.context = context;
    }


    /**
     * @param input EditText
     */
    void startGame(EditText input) {
        this.subtractSquareGame = (SubtractSquareGame) loadFromDataBase(context, SubtractSquareGame.getGameName());
        this.subtractSquareGame.addObserver(this);
        this.pcMode = subtractSquareGame.getCurrentState().getP2Name().equals("PC");
        if (pcMode && !subtractSquareGame.getCurrentState().isP1_turn()) {
            input.setText(String.valueOf(pcMadeChoice()));
        }
        timer = new Timer(((Activity) context).findViewById(R.id.chronometer2));
        timer.setUpTimer(subtractSquareGame.getIntTime());
        timer.start();
        updateAll();
    }

  /**
   * Go back to the last state if possible.
   * @return whether the undo is successful.
   */
    boolean undoMove(View view) {
        boolean undoable = undoBatch != 0;
        boolean undo = subtractSquareGame.undoMove();
        if (undoable) {
            if (!undo) {
                Toast.makeText(view.getContext(), "It's the first state now", Toast.LENGTH_SHORT).show();
            }
        }
        return undoable;
    }

    /**
     * Enter and record a number.
     * @param input the input in the EditText.
     */
    void enterNumber(View view, EditText input) {
        if (!subtractSquareGame.getCurrentState().getP2Name().equals("PC")) {
            humanMadeChoice(view, input);
        } else {
            if (subtractSquareGame.getCurrentState().isP1_turn()) {
                String move = input.getText().toString();
                if (subtractSquareGame.isValidMove(move)) {
                    subtractSquareGame.applyMove(move);
                    // PC CHOICE
                    input.setText(String.valueOf(pcMadeChoice()));
                } else {
                    makeNotValidToast(view);
                }
            } else {
                String move = input.getText().toString();
                subtractSquareGame.applyMove(move);
                input.setText("");
            }
        }
        saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
    }

    /**
     * Make a Toast message when user input is not valid.
     *
     * @param view View
     */
    private void makeNotValidToast(View view) {
        Toast.makeText(view.getContext().getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
    }

    /**
     * Process a human made choice. Only a square number is accepted.
     *
     * @param view  View
     * @param input the user input in the EditText
     */
    private void humanMadeChoice(View view, EditText input) {
        String move = input.getText().toString();
        if (subtractSquareGame.isValidMove(move)) {
            subtractSquareGame.applyMove(move);
            input.setText("");
        } else {
            makeNotValidToast(view);
        }
    }

    /**
     * Get a choice made by computer.
     *
     * @return a choice made by computer
     */
    private int pcMadeChoice() {
        ComputerChoice pcChoice = new ComputerChoice();
        return pcChoice.iterativeMiniMax(subtractSquareGame);
    }

    /**
     * Quit the current game and save.
     *
     * @param context Context
     */
    public void exit(Context context) {
        subtractSquareGame.setTime(timer.returnIntTime());
        timer.stop();
        saveToDataBase(context, subtractSquareGame, SubtractSquareGame.getGameName());
    }

    /**
     * Create a game between two human players.
     *
     * @param view View
     * @param p1   name of the first player
     * @param p2   name of the second player
     */
    void twoPlayerNewGame(View view, String p1, String p2) {
        if (!(p1.equals("") || p2.equals(""))) {
            if (p2.equals("PC")) {
                Toast.makeText(view.getContext().getApplicationContext(), "PC is a reserved name for PC MODE", Toast.LENGTH_SHORT).show();
            } else {
                subtractSquareGame = new SubtractSquareGame(p1, p2);
                saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
                Intent tmp = new Intent(view.getContext(), SubtractSquareActivity.class);
                tmp.putExtra("PC_MODE", false);
                view.getContext().startActivity(tmp);
            }
        } else {
            Toast.makeText(view.getContext().getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Create a game against the computer.
     *
     * @param view View
     */
    void PCNewGame(View view) {
        Session session = Session.getInstance(view.getContext());
        subtractSquareGame = new SubtractSquareGame(session.getCurrentUserName(), "PC");
        saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
        Intent selectComputer = new Intent(view.getContext().getApplicationContext(), SubtractSquareActivity.class);
        view.getContext().startActivity(selectComputer);
    }

    /**
     * Try to load an existing game.
     *
     * @param view View
     */
    void loadGameAttempt(View view) {

        Object loadable = loadFromDataBase(view.getContext(), SubtractSquareGame.getGameName());
        if (loadable == null) {
            Toast.makeText(view.getContext(), "No previous played game, start new one!", Toast.LENGTH_SHORT).show();
        } else {

            Intent tmp = new Intent(view.getContext(), SubtractSquareActivity.class);
            String p2Name = (subtractSquareGame == null) ? "" : subtractSquareGame.getCurrentState().getP2Name();
            tmp.putExtra("PC_MODE", p2Name.equals("PC"));
            view.getContext().startActivity(tmp);
        }
    }

    /**
     * Update and display whose turn it is and whether the game is over.
     */
    private void updateProgressContext() {
        TextView progress = ((Activity) context).findViewById(R.id.progress);
        String progressContext;
        if (subtractSquareGame.is_over()) {
            progressContext = "Game over, " + subtractSquareGame.getWinner() + " win !";
            timer.stop(); //chronometer.stop();
        } else {
            boolean pcMode = subtractSquareGame.getCurrentState().getP2Name().equals("PC");
            if (pcMode && !(subtractSquareGame.getCurrentState().isP1_turn())) {
                progressContext = "PC has made choice, press ENTER";
            } else {
                progressContext = subtractSquareGame.getCurrentPlayerName() + "'s turn";
            }
        }
        progress.setText(progressContext);
    }

    /**
     * Update and display the current total of the game
     */
    private void updateCurrentTotal() {
        TextView gameNumber = ((Activity) context).findViewById(R.id.gameNumber);
        int currentTotal = subtractSquareGame.getCurrentState().getCurrentTotal();
        gameNumber.setText(String.valueOf(currentTotal));
    }

    /**
     * Update and display the number of undo moves allowed for thr user.
     */
    private void updateUndoTimes() {
        undoBatch = subtractSquareGame.getUndoBatch();
        TextView undoTimes = ((Activity) context).findViewById(R.id.undoTimes);
        String undoText = "Undo: " + String.valueOf(subtractSquareGame.getUndoBatch()) + " times left";
        undoTimes.setText(undoText);
    }

    /**
     * Update score and record it to the database when a user has finished a game against computer.
     */
    private void updateScore() {
        if (pcMode && subtractSquareGame.is_over()) {
            DataBase dataBase = new DataBase(context);
            ScoreFactory factory = new ScoreFactory();
            SubtractSquareScore score = (SubtractSquareScore) factory.generateScore(SubtractSquareGame.getGameName());
            subtractSquareGame.setTime(timer.returnIntTime());
            score.takeInStateAndTime(subtractSquareGame.getCurrentState(), subtractSquareGame.getIntTime());
            dataBase.addScore(score);
        }
    }

    /**
     * Update all needed states to display the game.
     */
    private void updateAll() {
        updateCurrentTotal();
        updateProgressContext();
        updateScore();
        updateUndoTimes();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateAll();
    }
}
