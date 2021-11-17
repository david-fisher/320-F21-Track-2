package objects ;

import java.awt.Color;
import java.util.*;
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends GameObject
{
    // instance variables - replace the example below with your own
	private List<Tile> connections;
	private List<Gamepiece> pieces;
    private static int count = 0;

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
        this.setColor(Color.BLACK) ;
        this.setShape("square") ;
        this.setXPos(0) ;
        this.setYPos(0) ;
        this.setTrait("connections", connections, true) ;
        this.setTrait("pieces", pieces, true) ;
    }
    
    /* Trait Types:
     * 	label 	: 	String
     * 	icon 	: 	String
     * 	color 	:	Color
     *  shape   :   String (one of "square", 
     *  xPos    :   Integer
     *  yPos    :   Integer
     */
    
 // set trait to value. Overrides checking for default traits only
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
  	  
  	  // run game object's set trait first
  	  if (super.setTrait(trait, value, suppressTraitChecker)) {
  		  return true ;
  	  }
  	  
  	  // returns false if input is invalid
  	  return false ;
    }
    
    public List<Tile> getConnect()
    {
        return connections;
    }
    
    public boolean addConnect(Tile tile) {
        return connections.add(tile);
    }
    
    public boolean deleteConnect(Tile tile) {
    	return connections.remove(tile);
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
}
