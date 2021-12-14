package org.GameObjects.objects ;

import javafx.geometry.Bounds;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

    /**
     * Constructor for objects of class Tile
     */


    /* Trait Types:
     * 	label 	: 	String
     * 	icon 	: 	String
     * 	color 	:	String (Can be obtained as JAVAFX Color object)
     *  shape   :   String (one of "square",
     *  xPos    :   Integer
     *  yPos    :   Integer
     *  onLand  :   String
     */



    //2nd way to implement Tile class. For now it's the safer way
//because it's compatible with Odyssey/Game Object team's design flow
//Currently modifying some attributing to be work with drag-and-drop
//
public class Tile extends GameObject {
// instance variables - replace the example below with your own
    private ArrayList<Tile> connections;
    private List<Gamepiece> pieces;
    private static int count = 0;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();
    public boolean hasImage = false;
    private String tileName;
    private String tileImage;

    public int x;
    public int y;
    public int startXCoordinate;
    public int startYCoordinate;

    // set trait to value. Overrides checking for default traits only
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

        // run game object's set trait first
        if (super.setTrait(trait, value, suppressTraitChecker)) {
            return true ;
        } else if (suppressTraitChecker ||
                trait.equals("onLand") && value instanceof String ) {
            prevTraits.put(trait, traits.get(trait)) ;
            traits.put(trait, value);
            return true ;
        }

        // returns false if input is invalid
        return false ;
    }
    /**
     * Constructor for objects of class Tile
     */
    public Tile()
    {
        super() ;

        connections = new ArrayList<Tile>() ;
        pieces = new ArrayList<Gamepiece>() ;


        this.setLabel("tile" + String.format("%02d", ++count));
        this.setIcon("default_tile_icon.jpg") ;
        this.setColorString("#000000") ;
        this.setShape("square") ;
        this.setXPos(0) ;
        this.setYPos(0) ;
        this.setTrait("connections", connections, true) ;
        this.setTrait("pieces", pieces, true) ;
        this.setOnLand("");
    }

    public long getTileId() {
        return this.id;
    }

    public List<Tile> getConnect()
    {
        return connections;
    }

    public long getId() { return id; };

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return this.tileName;
    }

    public void setTileImage(String tileImage) {
        this.tileImage = tileImage;
    }

    public String getTileImage() {
        return this.tileImage;
    }

    public boolean addConnect(Tile tile) {
//        Line line = new Line();
//        Bounds localCoor = tileShape.localToScene(tileShape.getBoundsInLocal());
//        Bounds outCoor = tileShape.localToScene(tileShape.getBoundsInLocal());
//        line.setStartX((localCoor.getMaxX()+localCoor.getMinX())/2);
//        line.setStartY((localCoor.getMaxY()+localCoor.getMinY())/2);
//        line.setEndX((outCoor.getMaxX()+outCoor.getMinX())/2);
//        line.setEndY((outCoor.getMaxY()+outCoor.getMinY())/2);
        return connections.add(tile);
    }

    public boolean deleteConnect(Tile tile) {
        return connections.remove(tile);
    }

    public List<Gamepiece> getGamePieces() {
        // TODO
        return this.pieces ;
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

    public boolean setOnLand(String onLand){
        return this.setTrait("onLand", onLand);
    }

    public String getOnLand(){
        return (String)this.getTrait("onLand");
    }

    public boolean addGamepiece(Gamepiece gp) {

        // if gp is already on this Tile
        if (this.hasGamepiece(gp)) {
            return true ;
        }

        // otherwise try to add to this
        if (pieces.add(gp)) {
            if (gp.getLocation() != this) {
                return gp.setLocation(this) ;
            }
            return true ;
        }

        return false ;
    }

    public boolean removeGamepiece(Gamepiece gp) {
        return pieces.remove(gp) ;
    }

    public boolean hasGamepiece(Gamepiece gp) {
        return pieces.contains(gp) ;
    }

    public List<Gamepiece> getGamepieces() {
        return this.pieces ;
    }

    public String repr(boolean hasLabel) {
        return "Tile\n" + super.repr(hasLabel);
    }

}