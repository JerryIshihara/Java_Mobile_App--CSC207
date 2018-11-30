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

public class SubtractSquareController extends GameDataBaseAdapter implements Observer {

    private Context context;

    private SubtractSquareGame subtractSquareGame;

    private boolean pcMode;

    private Timer timer;

    private int undoBatch;

    public SubtractSquareController(Context context) {
        super(context);
        this.context = context;
    }

    public void startGame(boolean pcMode) {
        this.pcMode = pcMode;
        this.subtractSquareGame = (SubtractSquareGame) loadFromDataBase(context, SubtractSquareGame.getGameName());
        this.subtractSquareGame.addObserver(this);
        timer = new Timer(((Activity) context).findViewById(R.id.chronometer2));
        timer.setUpTimer(subtractSquareGame.getIntTime());
        timer.start();
        updateAll();
    }

    public boolean undoMove(View view) {
        boolean undoable = undoBatch != 0;
        boolean undo = subtractSquareGame.undoMove();
        if (undoable) {
            if (!undo) {
                Toast.makeText(view.getContext(), "It's the first state now", Toast.LENGTH_SHORT).show();
            }
        }
        return undoable;
    }

    // TODO: HARD CODED
    public void enterNumber(View view, EditText input) {
//        EditText input = view.findViewById(R.id.input);
        if (!pcMode) {
            String move = input.getText().toString();
            if (subtractSquareGame.isValidMove(move)) {
                subtractSquareGame.applyMove(move);
                saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
                input.setText("");
            } else {
                Toast.makeText(view.getContext().getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
            }
        } else {
            ComputerChoice pcChoice = new ComputerChoice();
            if (subtractSquareGame.getCurrentState().isP1_turn()) {
                String move = input.getText().toString();
                if (subtractSquareGame.isValidMove(move)) {
                    subtractSquareGame.applyMove(move);
                    saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
                    // PC CHOICE
                    int choice = pcChoice.iterativeMiniMax(subtractSquareGame);
                    input.setText(String.valueOf(choice));
                } else {
                    Toast.makeText(view.getContext().getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
                }
            } else {
                String move = input.getText().toString();
                if (subtractSquareGame.isValidMove(move)) {
                    subtractSquareGame.applyMove(move);
                    saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
                    input.setText("");
                } else {
                    Toast.makeText(view.getContext().getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
                    int choice = pcChoice.iterativeMiniMax(subtractSquareGame);
                    input.setText(String.valueOf(choice));
                }
            }
        }
        saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());
    }

    public void exit(Context context) {
        subtractSquareGame.setTime(timer.returnIntTime());
        timer.stop();
        saveToDataBase(context, subtractSquareGame, SubtractSquareGame.getGameName());
    }

    public void twoPlayerNewGame(View view, String p1, String p2) {
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

    public void PCNewGame(View view) {
        Session session = Session.getInstance(view.getContext());
        subtractSquareGame = new SubtractSquareGame(session.getCurrentUserName(), "PC");
        saveToDataBase(view.getContext(), subtractSquareGame, SubtractSquareGame.getGameName());

        Intent selectComputer = new Intent(view.getContext().getApplicationContext(), SubtractSquareActivity.class);
        selectComputer.putExtra("PC_MODE", true);
        view.getContext().startActivity(selectComputer);
    }

    public void loadGameAttempt(View view) {
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


//    private void updateTimer() {
//        timer = new Timer(view.findViewById(R.id.chronometer2));
//        timer.setUpTimer(subtractSquareGame.getIntTime());
//        timer.start();
//        if (subtractSquareGame.is_over()) {
//            timer.stop();
//        }
//    }

    private void updateProgressContext() {
        TextView progress = (TextView) ((Activity) context).findViewById(R.id.progress);
        String progressContext;
        if (subtractSquareGame.is_over()) {
            progressContext = "Game over, " + subtractSquareGame.getWinner() + " win !";
            timer.stop(); //chronometer.stop();
        } else {
            if (pcMode && !(subtractSquareGame.getCurrentState().isP1_turn())) {
                progressContext = subtractSquareGame.getCurrentPlayerName() + " has made choice";
            } else {
                progressContext = subtractSquareGame.getCurrentPlayerName() + "'s turn";
            }
        }
        progress.setText(progressContext);
    }

    private void updateCurrentTotal() {
        TextView gameNumber = (TextView) ((Activity) context).findViewById(R.id.gameNumber);
        int currentTotal = subtractSquareGame.getCurrentState().getCurrentTotal();
        gameNumber.setText(String.valueOf(currentTotal));
    }

    private void updateUndoTimes() {
        undoBatch = subtractSquareGame.getUndoBatch();
        TextView undoTimes = ((Activity) context).findViewById(R.id.undoTimes);
        String undoText = "Undo: " + String.valueOf(subtractSquareGame.getUndoBatch()) + " times left";
        undoTimes.setText(undoText);
    }

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

    private void updateAll() {
        updateCurrentTotal();
        updateProgressContext();
//        updateTimer();
        updateScore();
        updateUndoTimes();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateAll();
    }
}
