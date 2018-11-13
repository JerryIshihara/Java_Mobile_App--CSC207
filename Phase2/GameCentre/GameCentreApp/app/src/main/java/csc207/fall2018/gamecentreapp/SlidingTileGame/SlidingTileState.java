package csc207.fall2018.gamecentreapp.SlidingTileGame;

import java.io.Serializable;

public class SlidingTileState implements Serializable {

    private BoardManager boardManager;

    private int steps;

    private long time;

    private int boardSize;

    public SlidingTileState(BoardManager boardManager, long time, int steps){
        this.boardManager = boardManager;
        this.steps = steps;
        this.time = time;
        this.boardSize= Board.NUM_COLS;
    }

    public BoardManager getBoard(){
        return boardManager;
    }

    public int getSteps() {
        return steps;
    }

    public long getTime() {
        return time;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
