package csc207.fall2018.gamecentreapp.ScoreManagement;

import org.junit.Test;

import csc207.fall2018.gamecentreapp.slidingtiles.BoardManager;

import static org.junit.Assert.*;

public class SlidingTileScoreTest {
    @Test
    public void setAndGet(){
        SlidingTileScore slidingTileScore = new SlidingTileScore();
        slidingTileScore.takeInSizeTimeName(4, 100, "Heng", 60);
        assertEquals("Heng", slidingTileScore.returnPlayerName());
        assertEquals(BoardManager.getGameName(), slidingTileScore.returnGameName());

    }

    @Test
    public void calculateScore(){
        SlidingTileScore slidingTileScore = new SlidingTileScore();
        slidingTileScore.takeInSizeTimeName(4, 100, "Heng", 51);
        assertEquals("40", slidingTileScore.calculateScore());
        slidingTileScore.takeInSizeTimeName(3, 100, "Heng", 51);
        assertEquals("25", slidingTileScore.calculateScore());
        slidingTileScore.takeInSizeTimeName(5, 100, "Heng", 101);
        assertEquals("50", slidingTileScore.calculateScore());

    }

}