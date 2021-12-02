//2nd way to implement Tile class. For now it's the safer way
//because it's compatible with Odyssey/Game Object team's design flow
//Currently modifying some attributing to be work with drag-and-drop

package application;

import javafx.scene.paint.Color;

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

public class TileWave extends Tile 
{
    // instance variables - replace the example below with your own
	private ArrayList<TileWave> connections;
    private static int count = 0;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();
    public Shape tileShape;
    
    /**
     * Constructor for objects of class Tile
     */
    public TileWave()
    {
    	super() ;  

        connections = new ArrayList<TileWave>() ;
    	
    	this.setLabel("tile" + String.format("%02d", ++count));
        this.setIcon("default_tile_icon.jpg") ;
//        this.setColor(""); TODO: wait for UMassDining to fix setColor (currently a string parameter)
        this.setTrait("connections", connections, true) ;
    }

    public long getTileId() {
        return this.id;
   }
    
    
    public boolean addConnect(TileWave tile) {
    	Line line = new Line();
    	Bounds localCoor = this.tileShape.localToScene(this.tileShape.getBoundsInLocal());
    	Bounds outCoor = tile.tileShape.localToScene(tile.tileShape.getBoundsInLocal());
    	line.setStartX((localCoor.getMaxX()+localCoor.getMinX())/2);
    	line.setStartY((localCoor.getMaxY()+localCoor.getMinY())/2);
    	line.setEndX((outCoor.getMaxX()+outCoor.getMinX())/2);
    	line.setEndY((outCoor.getMaxY()+outCoor.getMinY())/2);
        return connections.add(tile);
    }
    
    public boolean deleteConnect(TileWave tile) {
    	return connections.remove(tile);
    }
    
    public List<Gamepiece> getGamePieces() {
    	return new ArrayList<Gamepiece>() ;
    }
    
    public String toString() {
    	return this.getLabel();
    }
    
}