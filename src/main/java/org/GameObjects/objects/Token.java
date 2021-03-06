package org.GameObjects.objects;

public class Token extends GameObject{

  private static int count = 0;

  public Token() {
	  
	super() ;  
	this.setLabel("token" + String.format("%02d", ++count));
    this.setIcon("default_token_icon.jpg") ;
    this.setColorString("#000000") ;
    this.setValue(1) ;
  }
  
  
  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	String (Can be obtained as JAVAFX Color object)
   * 	value	:	Integer
   */
  public Token clone() {
    Token x = new Token();
    for (String s : this.getAllTraits().keySet()) {
      x.setTrait(s, this.getTrait(s), true);
    }
    return x;
  }

  // set trait to value. Overrides checking for default traits only
  public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
	  
	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }
	  
	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("value") && value instanceof Integer)) {	// check value is String
	      prevTraits.put(trait, traits.get(trait)) ;
		  traits.put(trait, value) ;
		  return true ;
	  }
	  
	  // returns false if input is invalid
	  return false ;
  }
  
  public boolean setValue(int value) {
	  return this.setTrait("value", value);
  }

  public int getValue() {
	  return (Integer)this.getTrait("value");
  }

  public String repr(boolean hasLabel) {
    return "Token\n" + super.repr(hasLabel);
  }
}
