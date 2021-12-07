//2nd way to implement Tile class. For now it's the safer way
//because it's compatible with Odyssey/Game Object team's design flow
//Currently modifying some attributing to be work with drag-and-drop

package application;

import java.awt.Color;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * description of class Tile here.
 *
 * @author William Ton
 * @version 11/17/21 - 1pm
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Tile extends GameObject 
{
    // instance variables - replace the example below with your own
	private ArrayList<Tile> connections;
    private static int count = 0;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();
    public Shape tileShape;
    
    public int x;
    public int y;
    public int startXCoordinate;
    public int startYCoordinate;
    
    /**
     * Constructor for objects of class Tile
     */
    public Tile()
    {
    	super() ;  

        connections = new ArrayList<Tile>() ;
    	
    	this.setLabel("tile" + String.format("%02d", ++count));
        this.setIcon("default_tile_icon.jpg") ;
        this.setColor(Color.BLACK) ;
        this.setTrait("connections", connections, true) ;
    }

    public long getTileId() {
        return this.id;
   }
    
    public List<Tile> getConnect()
    {
        return connections;
    }
    
    public boolean addConnect(Tile tile) {
    	Line line = new Line();
    	Bounds localCoor = this.tileShape.localToScene(this.tileShape.getBoundsInLocal());
    	Bounds outCoor = tile.tileShape.localToScene(tile.tileShape.getBoundsInLocal());
    	line.setStartX((localCoor.getMaxX()+localCoor.getMinX())/2);
    	line.setStartY((localCoor.getMaxY()+localCoor.getMinY())/2);
    	line.setEndX((outCoor.getMaxX()+outCoor.getMinX())/2);
    	line.setEndY((outCoor.getMaxY()+outCoor.getMinY())/2);
        return connections.add(tile);
    }
    
    public boolean deleteConnect(Tile tile) {
    	return connections.remove(tile);
    }
    
    public List<Gamepiece> getGamePieces() {
    	// TODO
    	return new ArrayList<Gamepiece>() ;
    }
    
    public String toString() {
    	return this.getLabel();
    }
    
    public int getTileXLocation() {
    	return x;
    }
    
    public int getTileYLocation() {
    	return y;
    }
    
    public int getTileXInitial() {
    	return startXCoordinate;
    }
    
    public int getTileYInitial() {
    	return startYCoordinate;
    }
    
    public void setTileXInitial(int x) {
    	startXCoordinate = x;
    }
    
    public void setTileYInitial(int y) {
    	startYCoordinate = y;
    }
    
    public void setTileXLocation(int x) {
    	this.x = x;
    }
    
    public void setTileYLocation(int y) {
    	this.y = y;
    }
    
}