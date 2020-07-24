package csc207.fall2018.gamecentreapp.Sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class Sudoku extends Observable implements Serializable {

    /**
     * ArrayList<ArrayList<Integer>> sudokuBoard.
     */
    private ArrayList<ArrayList<Integer>> sudokuBoard;

    /**
     * ArrayList<Integer> moves
     */
    private ArrayList<Integer> moves;

    /**
     * ArrayList<Integer> modifiablePositions.
     */
    private ArrayList<Integer> modifiablePositions;

    /**
     * Initialize an empty Sudoku.
     */
    public Sudoku() {
        this.sudokuBoard = createBlankSudoku();
        this.moves = new ArrayList<>();
        modifiablePositions = new ArrayList<>();
    }

    /**
     * Create an empty Sudoku board, an empty sudoku tile is represented by 0.
     *
     * @return a 9 x 9 two dimensional ArrayList full of 0, where 0 represent an empty sudoku tile.
     */
    private ArrayList<ArrayList<Integer>> createBlankSudoku() {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            grid.add(i, new ArrayList<Integer>());
            for (int j = 0; j <= 8; j++) {
                //add check horizontal, vertical, and square method
                //if all conditions satisfies, then add this value.
                grid.get(i).add(0);
            }
        }
        return grid;
    }

    /**
     * Get the sudoku board of the Sudoku.
     *
     * @return a 9 x 9 two dimensional ArrayList consisting of integer 0 to 9, 0 representing empty.
     */
    public ArrayList<ArrayList<Integer>> getSudokuBoard() {
        return sudokuBoard;
    }

    /**
     * Set the sudoku board of the Sudoku.
     */
    void setSudokuBoard(ArrayList<ArrayList<Integer>> sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    /**
     * Get a ArrayList of the finalized empty positions when a sudoku puzzle is generated. This position
     * can be modified by users.
     *
     * @return an ArrayList of  modifiable positions.
     */
    public ArrayList<Integer> getModifiablePositions() {
        return modifiablePositions;
    }

    /**
     * Add position to modifiable positions.
     *
     * @param position the position to be added to the modifiable positions.
     */
    public void addModifiablePosition(int position) {
        modifiablePositions.add(position);
    }


    /**
     * Get the value of sudoku board at the given row and col.
     *
     * @param row the row the value is at.
     * @param col the column the value is ar.
     * @return the value at the given row and col.
     */
    public int getValueAt(int row, int col) {
        return this.sudokuBoard.get(row).get(col);
    }

    /**
     * Change the value at the given position.
     *
     * @param position the position to change the value
     * @param newValue the new value at the position
     */
    public void changeValueAt(int position, int newValue) {
        int row = position % 9;
        int col = position / 9;
        this.sudokuBoard.get(row).set(col, newValue);
    }

    /**
     * Add the position of the moves the user have made to track moves. If the position of
     * the latest move the user make is already in the moves, delete all previous occurrence.
     *
     * @param pos the position the user add input.
     */
    public void trackMoves(int pos) {
        if (!moves.contains(pos)) {
            moves.add(pos);
        } else {
            int numMoves = moves.size();
            for (int i = 0; i < numMoves; i++) {
                if (moves.get(i) == pos) {
                    moves.remove(i);
                    i--;
                    numMoves = moves.size();
                }
            }
            moves.add(pos);
        }
    }

    /**
     * Get the moves the user have made.
     *
     * @return the moves the user have made.
     */
    public ArrayList<Integer> getMoves() {
        return moves;
    }
}
