package csc207.fall2018.gamecentreapp.Sudoku;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import csc207.fall2018.gamecentreapp.TimeDiplay.TimeStorable;

public class SudokuManager implements Serializable, TimeStorable {

    /**
     * String GAME_NAME  "Sudoku".
     */
    private static final String GAME_NAME = "Sudoku";

    /**
     * Random rand new Random().
     */
    private Random rand = new Random();

    /**
     * int time.
     */
    private int time;

    /**
     * Sudoku sudoku.
     */
    private Sudoku sudoku;


    /**
     * int difficulty.
     */
    private int difficulty;

    /**
     * Initialize a SudokuManager.
     *
     * @param difficulty the difficulty level of the sudoku game.
     */
    public SudokuManager(int difficulty) {
        this.sudoku = new Sudoku();
        this.difficulty = difficulty;
        createFullBoard(sudoku);
        generateHoles(difficulty);
    }

    /**
     * Create a complete and correct sudoku board according to the sudoku rule.
     *
     * @param sudoku the Sudoku to create full sudoku board in.
     */
    private void createFullBoard(Sudoku sudoku) {
        ArrayList<ArrayList<Integer>> available = createAvailable();
        int pos = 0;
        while (pos < 81) {
            int row = pos / 9;
            int col = pos % 9;
            if (available.get(pos).size() != 0) {
                int i = rand.nextInt(available.get(pos).size());
                int number = available.get(pos).get(i);
                if (checkAll(number, row, col)) {
                    sudoku.getSudokuBoard().get(row).set(col, number);
                    available.get(pos).remove(i);
                    pos++;
                } else {
                    available.get(pos).remove(i);
                }
            } else {
                sudoku.getSudokuBoard().get(row).set(col, 0);
                available.set(pos, oneToNine());
                pos--;
            }
        }
    }


    /**
     * Erase an existing value in some tiles in the sudoku board so that it can take in user input.
     * The number of empty tiles is decided by the difficulty of the sudoku game.
     *
     * @param level the difficulty level of the sudoku game.
     */
    private void generateHoles(int level) {
        int numHoles = level * 9 + 9;
        ArrayList<Integer> posList = new ArrayList<>();
        for (int i = 1; i < 81; i++) {
            posList.add(i);
        }
        Collections.shuffle(posList);
        for (int j = 0; j < numHoles; j++) {
            this.sudoku.changeValueAt(posList.get(j), 0);
            this.sudoku.addModifiablePosition(posList.get(j));
        }
    }

    /**
     * Get the name of the game, which is "Sudoku".
     */
    public static String getGameName() {
        return GAME_NAME;
    }

    /**
     * Get the difficulty of the game, 1,2,3 represent easy, normal, hard respectively.
     *
     * @return the difficulty of the game.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Get the Sudoku the SudokuManager manage currently.
     *
     * @return the Sudoku the SudokuManager manage currently.
     */
    public Sudoku getSudoku() {
        return sudoku;
    }

    /**
     * Add integer one to nine to an ArrayList.
     */
    private ArrayList<Integer> oneToNine() {
        return new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
    }

    /**
     * Add one to nine to each position of the sudoku board.
     *
     * @return a two dimensional ArrayList of length 81, Each item is an ArrayList consisting of
     * integer oe to nine.
     */
    private ArrayList<ArrayList<Integer>> createAvailable() {
        ArrayList<ArrayList<Integer>> available = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            available.add(i, oneToNine());
        }
        return available;
    }

    /**
     * Check whether the value already exist in the given row , excluding the given column.
     *
     * @param value the value at given row and column.
     * @param row   the row the value at.
     * @param col   the column the value at.
     * @return whether the value already exist in the row.
     */
    private boolean checkHorizontal(int value, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (value == this.sudoku.getValueAt(row, i) && i != col) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the value already exist in the given column, excluding the given row.
     *
     * @param value the value at the given row and column.
     * @param row   the row the value at.
     * @param col   the column the value at.
     * @return whether the value already exist in the column.
     */
    private boolean checkVertical(int value, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (value == this.sudoku.getValueAt(i, col) && i != row) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the input value already exist in the 3 x 3 square the given row and column at,
     * excluding the position of the given row and column.
     *
     * @param value the input value.
     * @param row   the row the value at.
     * @param col   the column the value at.
     * @return whether the value already exist in the 3 x 3 square.
     */
    private boolean checkSquare(int value, int row, int col) {
        int xGroup = (col / 3);
        int yGroup = (row / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (value == this.sudoku.getValueAt(yGroup * 3 + i, xGroup * 3 + j) && (row != (yGroup * 3 + i) || col != (xGroup * 3 + j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check whether the value at the given row and column cause a conflict according to the sudoku
     * rule.
     *
     * @param value the value at the given row and column.
     * @param row   the row the value at.
     * @param col   the column the value at.
     * @return whether the value at the given row and column cause a conflict.
     */
    boolean checkAll(int value, int row, int col) {
        return (checkHorizontal(value, row, col)
                && checkVertical(value, row, col)
                && checkSquare(value, row, col));
    }

    /**
     * Check whether the sudoku board of the current Sudoku is complete and correct.
     *
     * @return whether the sudoku board of the current Sudoku is full and correct.
     */
    public boolean checkPuzzleSolved() {
        for (int position = 0; position < 81; position++) {
            int row = position % 9;
            int col = position / 9;
            boolean result = checkAll(this.sudoku.getValueAt(row, col), row, col);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getIntTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }
}




