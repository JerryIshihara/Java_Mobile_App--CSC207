package csc207.fall2018.gamecentreapp.Sudoku.SudokuActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import csc207.fall2018.gamecentreapp.DataBase.DataBase;
import csc207.fall2018.gamecentreapp.DataBase.GameStateDataBase;
import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.ScoreManagement.ScoreFactory;
import csc207.fall2018.gamecentreapp.ScoreManagement.SudokuScore;
import csc207.fall2018.gamecentreapp.Sudoku.SudokuManager;
import csc207.fall2018.gamecentreapp.TimeDiplay.Timer;

import java.io.*;
import java.util.ArrayList;

public class SudokuGameActivity extends AppCompatActivity {
    /**
     * AlertDialog for user to enter value.
     */
    private AlertDialog.Builder enterNumber;
    /**
     * The ArrayAdapter for converting ArrayList to GridView.
     */
    private ArrayAdapter<Object> gridViewArrayAdapter;
    /**
     * The ArrayList containing each value at all positions. The value of an empty tile is " ".
     */
    private ArrayList<Object> allValue;
    /**
     * The EditText for user input.
     */
    private EditText input;
    /**
     * The Timer for recording time.
     */
    private Timer timer;
    /**
     * The GridView representing a Sudoku.
     */
    private GridView gridOfSudoku;
    /**
     * The SudokuManager for the game.
     */
    private SudokuManager sudokuManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game_interface);
        loadFromFile(SudokuGameStartingActivity.FILE_NAME);
        gridOfSudoku = findViewById(R.id.sudokuGrid);

        timer = new Timer(findViewById(R.id.timer));
        timer.setUpTimer(sudokuManager.getIntTime());
        timer.start();
        input = new EditText(this);

        Button undoButton = findViewById(R.id.undo);
        if (sudokuManager.getSudoku().getMoves().size() == 0) {
            undoButton.setEnabled(false);
        }

        enterNumber = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Type in new number")
                .setView(input)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = getIntent().getExtras().getInt("position");
                        if (checkInputValidity(input.getText().toString())) {
                            int newValue = Integer.parseInt(input.getText().toString());
                            allValue.set(position, newValue);
                            sudokuManager.getSudoku().changeValueAt(position, newValue);
                            Button undoButton = findViewById(R.id.undo);
                            undoButton.setEnabled(true);
                            gridViewArrayAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(SudokuGameActivity.this, "Please enter a valid number",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null);
        generateGrid();
    }

    /**
     * Display a sudoku board in GridView through ArrayAdapter.
     */
    public void generateGrid() {
        ArrayList<ArrayList<Integer>> sudoku = sudokuManager.getSudoku().getSudokuBoard();
        allValue = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            int x = i % 9;
            int y = i / 9;
            if (sudoku.get(x).get(y) == 0) {
                allValue.add("");
            } else {
                allValue.add(sudoku.get(x).get(y));
            }

        }
        gridViewArrayAdapter = new ArrayAdapter<Object>
                (this, android.R.layout.simple_list_item_1, allValue) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                int color = Color.parseColor("#DB7093");
                if (sudokuManager.getSudoku().getModifiablePositions().contains(position)) {
                    view.setBackgroundColor(color);
                }
                return view;
            }
        };
        gridOfSudoku.setAdapter(gridViewArrayAdapter);
        gridOfSudoku.setOnItemClickListener(
                (parent, view, position, id) -> {
                    if (sudokuManager.getSudoku().getModifiablePositions().contains(position)) {
                        //Restate EditText input because the view is added to dialog layout before
                        //so it has a parent layout right now, must remove it or restate a new view.
                        getIntent().putExtra("position", position);
                        input = new EditText(SudokuGameActivity.this);
                        enterNumber.setView(input);
                        sudokuManager.getSudoku().trackMoves(position);
                        enterNumber.show();
                    }
                }
        );

    }

    /**
     * Clear the value at the position where user made latest move.
     */
    public void undo(View v) {
        Button undoButton = (Button) v;
        Animation bounce = AnimationUtils.loadAnimation(SudokuGameActivity.this, R.anim.bounce);
        undoButton.setAnimation(bounce);
        int numMoves = sudokuManager.getSudoku().getMoves().size();
        if (numMoves != 0) {
            int lastMove = sudokuManager.getSudoku().getMoves().remove(numMoves - 1);
            if (sudokuManager.getSudoku().getMoves().size() == 0) {
                undoButton.setEnabled(false);
            }
            sudokuManager.getSudoku().changeValueAt(lastMove, 0);
            allValue.set(lastMove, "");
            gridViewArrayAdapter.notifyDataSetChanged();
            saveToDataBase();
        } else {
            undoButton.setEnabled(false);
        }
    }

    /**
     * Start a new sudoku game of the same difficulty.
     */
    public void startAgain(View v) {
        Button restart = (Button) v;
        Animation bounce = AnimationUtils.loadAnimation(SudokuGameActivity.this, R.anim.bounce);
        restart.setAnimation(bounce);
        int difficulty = sudokuManager.getDifficulty();
        sudokuManager = new SudokuManager(difficulty);
        generateGrid();
    }

    /**
     * Check whether is sudoku is solved.
     */
    public void endGameCheck(View v) {
        boolean puzzleSolved = sudokuManager.checkPuzzleSolved();
        if (puzzleSolved) {
            timer.stop();
            updateScore();
            Toast.makeText(SudokuGameActivity.this, "Congratulation", Toast.LENGTH_LONG).show();
            //------------Have timer stop and score logged at here---------------------
            //-------------------------------------------------------------------------
        } else {
            Toast.makeText(SudokuGameActivity.this, "Failed",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Check whether the user input is valid. Only one to nine can be accepted.
     */
    public boolean checkInputValidity(String input) {
        return input.length() == 1 && input.matches("-?([1-9]\\d*)");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_to_right, R.anim.slide_to_left);
    }


    private void loadFromFile(String fileName) {
        try {
            File inputFile = new File(getFilesDir(), fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFile));
            sudokuManager = (SudokuManager) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Go back to the SudokuGameStartingActivity.
     */
    public void onclickGoBack(View view) {
        saveToDataBase();
        Intent intent = new Intent(this, SudokuGameStartingActivity.class);
        startActivity(intent);
    }

    /**
     * Save to the database.
     */
    private void saveToDataBase() {
        sudokuManager.setTime(timer.returnIntTime());
        GameStateDataBase dataBase = new GameStateDataBase(this);
        byte[] stream = null;

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(sudokuManager);
            stream = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = Session.getInstance(this);
        dataBase.deleteState(session.getCurrentUserName(), SudokuManager.getGameName());
        dataBase.saveState(session.getCurrentUserName(), SudokuManager.getGameName(), stream);
    }

    /**
     * Update the score.
     */
    private void updateScore() {
        DataBase dataBase = new DataBase(this);
        ScoreFactory factory = new ScoreFactory();
        SudokuScore score = (SudokuScore) factory.generateScore(SudokuManager.getGameName());
        Session session = Session.getInstance(this);

        sudokuManager.setTime(timer.returnIntTime());
        score.takeInSizeTimeName(sudokuManager.getDifficulty(), sudokuManager.getIntTime(), session.getCurrentUserName());

        dataBase.addScore(score);
    }
}


