package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.awt.Color;
import objects.Gamepiece;
import objects.Tile;

class GamepieceAndTileUnitTests {

    @Test
    public void testDefaultGamepiece(){
        Gamepiece gp1 = new Gamepiece();
        Gamepiece gp2 = new Gamepiece();
        assert(gp1.getLabel().equals("gamepiece01"));
        assert(gp2.getLabel().equals("gamepiece02"));
        assert(gp1.getIcon().equals("default_gamepiece_icon.jpg"));
        assert(gp1.getColor().equals(Color.BLACK));
        assert(gp1.getLocation()==null);
    }

    @Test
    public void testGamepieceLocation(){
        Gamepiece gp1 = new Gamepiece();
        Tile up = new Tile();
        Tile down = new Tile();
        gp1.setLocation(up);
        assert(gp1.getLocation().equals(up));
        gp1.setLocation(down);
        assert(gp1.getLocation().equals(down));
    }

    @Test
    public void testAddingGamepieceToNewTileRemovesItFromOldTile(){
        Gamepiece gp1 = new Gamepiece();
        Tile up = new Tile();
        Tile down = new Tile();
        gp1.setLocation(up);
        gp1.setLocation(down);
        assert(up.getGamepieces().size() == 0);
        assert(up.hasGamepiece(gp1) == false);
    }

    @Test
    public void testDefaultTile(){
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        assert(tile1.getLabel().equals("tile01"));
        assert(tile2.getLabel().equals("tile02"));
        assert(tile1.getColor.equals(Color.BLACK));
        assert(tile1.getIcon.equals("default_tile_icon.jpg"));
        assert(tile1.getXPos() == 0);
        assert(tile1.getYPos() == 0);
        assert(tile1.getShape().equals("square"));
        assert(tile1.getTrait("connections").equals(tile1.getConnect()));
        assert(tile1.getTrait("pieces").equals(tile1.getGamepieces()));
    }

    @Test
    public void testAddConnections(){
        Tile tile = new Tile();
        Tile up = new Tile();
        Tile down = new Tile();
        tile.addConnect(up);
        assert(tile.getConnect.size() == 1);
        assert(tile.getConnect.contains(up));
        tile.addConnect(down);
        assert(tile.getConnect.size() == 2);
        assert(tile.getConnect.contains(down));
    }

    @Test
    public void testDeleteConnections(){
        Tile tile = new Tile();
        Tile up = new Tile();
        Tile down = new Tile();
        tile.addConnect(up);
        tile.addConnect(down);
        tile.deleteConnect(down);
        assert(tile.getConnect.size() == 1);
        tile.deleteConnect(up);
        assert(tile.getConnect.size() == 0);
    }

    @Test
    public void testDeleteNonExistentConnection(){
        Tile tile = new Tile();
        Tile up = new Tile();
        tile.deleteConnect(up);
        assert(tile.getConnect.size() == 0);
    }

    @Test
    public void testCannotAddNullConnection(){
        Tile tile = new Tile();
        tile.addConnect(null);
        assert(tile.getConnect.size() == 0);
    }

    @Test
    public void testCannotAddDuplicateConnection(){
        Tile tile = new Tile();
        Tile up = new Tile();
        tile.addConnect(up);
        tile.addConnect(up);
        assert(tile.getConnect.size() == 1);
    }

    @Test
    public void testAddGamepieceWorksAsIntended(){
        Tile tile1 = new Tile();
        Gamepiece gp1 = new Gamepiece();
        Gamepiece gp2 = new Gamepiece();
        tile1.addGamepiece(gp1);
        assert(tile1.getGamepieces().size() == 1);
        tile2.addGamepiece(gp2);
        assert(tile1.getGamepieces().size() == 2);
    }

    @Test
    public void testGamepieceCanOnlyBeOnOneTile(){
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Gamepiece gp1 = new Gamepiece();
        tile1.add(gp1);
        tile2.add(gp1);
        assert(!(tile1.hasGamepiece(gp1)));
    }

    @Test
    public void testDeleteGamepieceWorksAsIntended(){
        Tile tile1 = new Tile();
        Gamepiece gp1 = new Gamepiece();
        tile1.addGamepiece(gp1);
        tile1.removeGamepiece(gp1);
        assert(tile1.getGamepieces().size() == 0);
    }

    @Test
    public void testDeleteNonExistentGamepiece(){
        Tile tile1 = new Tile();
        Gamepiece gp1 = new Gamepiece();
        tile1.removeGamepiece(gp1);
        assert(tile1.getGamepieces.size() == 0);
    }

    @Test
    public void testCannotAddNullGamepiece(){
        Tile tile1 = new Tile();
        tile1.addGamepiece(null);
        assert(tile1.getGamepieces().size() == 0);
    }

    @Test
    public void testCannotAddDuplicateGamepiece(){
        Tile tile1 = new Tile();
        Gamepiece gp1 = new Gamepiece();
        tile1.addGamepiece(gp1);
        tile1.addGamepiece(gp1);
        assert(tile1.getGamepieces().size() == 1);
    }
}