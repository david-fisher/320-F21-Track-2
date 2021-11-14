package org.objects;

import java.awt.Color;

public class Gamepiece extends GameObject{

  private static int count = 0;

  public Gamepiece() {
	  super() ;
	  this.setLabel("gamepiece" + String.format("%02d", ++count));
	  this.setIcon("default_gamepiece_icon.jpg") ;
	  this.setColor(Color.BLACK) ;
	  this.setLocation(null);
  }

  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	Color
   * 	location:	Tile
   */


  // set trait to value. Overrides checking for default traits only
  public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }

	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("location") && value instanceof Tile)) {	// check value is String
		  traits.put(trait, value) ;
		  return true ;
	  }

	  // returns false if input is invalid
	  return false ;
  }

  public boolean setLocation(Tile tile) {
	  return this.setTrait("location", tile);
  }

  public Tile getLocation() {
	  return (Tile)this.getTrait("location");
  }
}
