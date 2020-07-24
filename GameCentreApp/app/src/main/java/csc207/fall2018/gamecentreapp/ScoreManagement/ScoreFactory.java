package csc207.fall2018.gamecentreapp.ScoreManagement;

import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;
import csc207.fall2018.gamecentreapp.Sudoku.SudokuManager;
import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;

/**
 * Generate different score class based on game name.
 */
public class ScoreFactory {

    /**
     * name of subtract square game
     */
    private static final String SUBTRACT_SQUARE = SubtractSquareGame.getGameName();

    /**
     * name of sliding tile game
     */
    private static final String SLIDING_TILE = BoardManager.getGameName();

    /**
     * name of sudoku game.
     */
    private static final String SUDOKU = SudokuManager.getGameName();

    /**
     * return different score class based on game name.
     *
     * @param gameName the name of the game
     * @return Score
     */
    public Score generateScore(String gameName) {
        if (gameName == null) {
            return null;
        } else if (gameName.equals(SUBTRACT_SQUARE)) {
            return new SubtractSquareScore();
        } else if (gameName.equals(SLIDING_TILE)) {
            return new SlidingTileScore();
        } else if (gameName.equals(SUDOKU)) {
            return new SudokuScore();
        }
        return null;
    }

}
