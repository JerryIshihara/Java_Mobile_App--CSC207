package csc207.fall2018.gamecentreapp.ScoreManagement;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuScoreTest {
    @Test
    public void testSetAndGet(){
        SudokuScore sudokuScore = new SudokuScore();
        sudokuScore.takeInSizeTimeName(3, 100, "Heng");
        assertEquals("Heng", sudokuScore.returnPlayerName());
        assertEquals("Sudoku", sudokuScore.returnGameName());
    }

    @Test
    public void testCalculate(){
        SudokuScore sudokuScore = new SudokuScore();
        sudokuScore.takeInSizeTimeName(3, 70, "Heng");
        assertEquals("50", sudokuScore.calculateScore());
        sudokuScore.takeInSizeTimeName(4, 100, "Heng");
        assertEquals("60", sudokuScore.calculateScore());
        sudokuScore.takeInSizeTimeName(5, 600, "Heng");
        assertEquals("25", sudokuScore.calculateScore());
    }

}