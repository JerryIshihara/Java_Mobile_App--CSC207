package csc207.fall2018.gamecentreapp.Sudoku;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SudokuManagerTest {
    private ArrayList<ArrayList<Integer>> makeSampleSudoku() {
        ArrayList<Integer> row0 = new ArrayList<Integer>() {{
            add(5);
            add(6);
            add(1);
            add(8);
            add(2);
            add(7);
            add(4);
            add(9);
            add(3);
        }};
        ArrayList<Integer> row1 = new ArrayList<Integer>() {{
            add(7);
            add(9);
            add(2);
            add(1);
            add(3);
            add(4);
            add(6);
            add(5);
            add(8);
        }};
        ArrayList<Integer> row2 = new ArrayList<Integer>() {{
            add(8);
            add(3);
            add(4);
            add(5);
            add(9);
            add(6);
            add(1);
            add(7);
            add(2);
        }};
        ArrayList<Integer> row3 = new ArrayList<Integer>() {{
            add(6);
            add(2);
            add(8);
            add(9);
            add(5);
            add(3);
            add(7);
            add(4);
            add(1);
        }};
        ArrayList<Integer> row4 = new ArrayList<Integer>() {{
            add(3);
            add(7);
            add(5);
            add(4);
            add(1);
            add(8);
            add(2);
            add(6);
            add(9);
        }};
        ArrayList<Integer> row5 = new ArrayList<Integer>() {{
            add(4);
            add(1);
            add(9);
            add(7);
            add(6);
            add(2);
            add(3);
            add(8);
            add(5);
        }};
        ArrayList<Integer> row6 = new ArrayList<Integer>() {{
            add(1);
            add(8);
            add(6);
            add(3);
            add(7);
            add(5);
            add(9);
            add(2);
            add(4);
        }};
        ArrayList<Integer> row7 = new ArrayList<Integer>() {{
            add(9);
            add(4);
            add(7);
            add(2);
            add(8);
            add(1);
            add(5);
            add(3);
            add(6);
        }};
        ArrayList<Integer> row8 = new ArrayList<Integer>() {{
            add(2);
            add(5);
            add(3);
            add(6);
            add(4);
            add(9);
            add(8);
            add(1);
            add(7);
        }};
        return new ArrayList<ArrayList<Integer>>() {{
            add(row0);
            add(row1);
            add(row2);
            add(row3);
            add(row4);
            add(row5);
            add(row6);
            add(row7);
            add(row8);
        }};
    }

    @Test
    public void testGetGameName() {
        assertEquals("Sudoku", SudokuManager.getGameName());
    }

    @Test
    public void testGetModifiablePosition() {
        SudokuManager sudokuManagerLevelOne = new SudokuManager(1);
        SudokuManager sudokuManagerLevelTwo = new SudokuManager(2);
        SudokuManager sudokuManagerLevelThree = new SudokuManager(3);
        ArrayList<Integer> modifiablePositionLevelOne = sudokuManagerLevelOne.getSudoku().getModifiablePositions();
        ArrayList<Integer> modifiablePositionLevelTwo = sudokuManagerLevelTwo.getSudoku().getModifiablePositions();
        ArrayList<Integer> modifiablePositionLevelThree = sudokuManagerLevelThree.getSudoku().getModifiablePositions();
        assertEquals(18, modifiablePositionLevelOne.size());
        assertEquals(27, modifiablePositionLevelTwo.size());
        assertEquals(36, modifiablePositionLevelThree.size());
        for (int i = 0; i < 81; i++) {
            int row = i % 9;
            int col = i / 9;
            if (modifiablePositionLevelOne.contains(i)){
                assertEquals(0, (int) sudokuManagerLevelOne.getSudoku().getSudokuBoard().get(row).get(col));
            }else {
                assertNotEquals(0,(int) sudokuManagerLevelOne.getSudoku().getSudokuBoard().get(row).get(col) );
            }
            if (modifiablePositionLevelTwo.contains(i)){
                assertEquals(0, (int) sudokuManagerLevelTwo.getSudoku().getSudokuBoard().get(row).get(col));
            }else {
                assertNotEquals(0,(int) sudokuManagerLevelTwo.getSudoku().getSudokuBoard().get(row).get(col) );
            }
            if (modifiablePositionLevelThree.contains(i)){
                assertEquals(0, (int) sudokuManagerLevelThree.getSudoku().getSudokuBoard().get(row).get(col));
            }else {
                assertNotEquals(0,(int) sudokuManagerLevelThree.getSudoku().getSudokuBoard().get(row).get(col) );
            }
        }
    }

    @Test
    public void testChangeValue() {
        SudokuManager sudokuManagerLevelOne = new SudokuManager(1);
        SudokuManager sudokuManagerLevelTwo = new SudokuManager(2);
        SudokuManager sudokuManagerLevelThree = new SudokuManager(3);
        sudokuManagerLevelOne.getSudoku().changeValueAt(0, 1);
        sudokuManagerLevelTwo.getSudoku().changeValueAt(0, 2);
        sudokuManagerLevelThree.getSudoku().changeValueAt(0, 3);
        assertEquals(1, sudokuManagerLevelOne.getSudoku().getValueAt(0, 0));
        assertEquals(2, sudokuManagerLevelTwo.getSudoku().getValueAt(0,0));
        assertEquals(3, sudokuManagerLevelThree.getSudoku().getValueAt(0, 0));
    }

    @Test
    public void testCheckAll() {
        ArrayList<ArrayList<Integer>> solvedSudoku = makeSampleSudoku();
        SudokuManager sudokuManager = new SudokuManager(1);
        sudokuManager.getSudoku().setSudokuBoard(solvedSudoku);
        assertTrue(sudokuManager.checkAll(5, 0, 0));
        assertFalse(sudokuManager.checkAll(1, 0, 0));
    }

    @Test
    public void testTrackMoves() {
        ArrayList<ArrayList<Integer>> solvedSudoku = makeSampleSudoku();
        SudokuManager sudokuManager = new SudokuManager(1);
        sudokuManager.getSudoku().setSudokuBoard(solvedSudoku);
        sudokuManager.getSudoku().trackMoves(77);
        assertEquals(1, sudokuManager.getSudoku().getMoves().size());
        sudokuManager.getSudoku().trackMoves(77);
        assertEquals(1, sudokuManager.getSudoku().getMoves().size());
        sudokuManager.getSudoku().trackMoves(2);
        assertEquals(2, sudokuManager.getSudoku().getMoves().size());
        sudokuManager.getSudoku().trackMoves(77);
        assertEquals(2, sudokuManager.getSudoku().getMoves().size());
    }

    @Test
    public void testCheckPuzzleSolved(){
        ArrayList<ArrayList<Integer>> solvedSudoku = makeSampleSudoku();
        SudokuManager sudokuManager = new SudokuManager(1);
        sudokuManager.getSudoku().setSudokuBoard(solvedSudoku);
        assertTrue(sudokuManager.checkPuzzleSolved());
        sudokuManager.getSudoku().changeValueAt(66, 3);
        assertFalse(sudokuManager.checkPuzzleSolved());
    }

    @Test
    public void testGetDifficulty(){
        SudokuManager sudokuManager = new SudokuManager(1);
        assertEquals(1, sudokuManager.getDifficulty());
    }

    @Test
    public void testTime(){
        SudokuManager sudokuManager = new SudokuManager(1);
        sudokuManager.setTime(1000);
        assertEquals(1000, sudokuManager.getIntTime());
    }

}