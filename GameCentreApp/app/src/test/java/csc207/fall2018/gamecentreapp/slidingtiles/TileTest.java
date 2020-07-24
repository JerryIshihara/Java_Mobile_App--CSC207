package csc207.fall2018.gamecentreapp.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TileTest {

    @Test
    public void getBackground() {
        Tile tile = new Tile(1,0);
        assertEquals(0, tile.getBackground());
    }

    @Test
    public void getId() {
        Tile tile = new Tile(1,0);
        assertEquals(1, tile.getId());
    }

    @Test
    public void compareTo() {
        Tile tile1 = new Tile(1, 0);
        Tile tile2 = new Tile(2, 1);
        assertEquals(1,tile1.compareTo(tile2));
    }




}