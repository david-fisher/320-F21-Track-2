package objects;

import java.awt.Color;

public class Card extends GameObject {
	
  private static int count = 0;

  public Card() {
	  super() ;  
	  this.setLabel("card" + String.format("%02d", ++count));
	  this.setIcon("default_gamepiece_icon.jpg") ;
	  this.setColor(Color.BLACK) ;
	  this.setText("default text");
  }
  
  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	Color
   * 	text	:	String
   */
  
//set trait to value. Overrides checking for default traits only
 public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
	  
	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }
	  
	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("text") && value instanceof String)) {	// check value is String
		  traits.put(trait, value) ;
		  return true ;
	  }
	  
	  // returns false if input is invalid
	  return false ;
 }

  public boolean setText(String text) {
	  return this.setTrait("text", text);
  }

  public String getText() {
	  return (String)this.getTrait("text");
  }
  
  public String toString() {
	  return this.getLabel() ;
  }
}
