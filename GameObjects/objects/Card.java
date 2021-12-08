package objects;

public class Card extends GameObject {
	
  private static int count = 0;

  public Card() {
	  super() ;  
	  this.setLabel("card" + String.format("%02d", ++count));
	  this.setIcon("default_gamepiece_icon.jpg") ;
	  this.setColorString("#000000") ;
	  this.setText("default text");
	  this.setEnabled(true);
	  this.setPressed(false);
	  this.setOnPlay("");
  }
  
  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	String (Can be obtained as JAVAFX Color object)
   * 	text	:	String
   *    onPlay  :   String
   *    enabled :   Boolean
   *    pressed :   Boolean
   */
  
//set trait to value. Overrides checking for default traits only
 public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
	  
	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }
	  
	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("text") && value instanceof String) ||
			  (trait.equals("pressed") && value instanceof Boolean) ||
			  (trait.equals("enabled") && value instanceof Boolean) ||
			  (trait.equals("onPlay") && value instanceof String)) {	// check value is String
	      prevTraits.put(trait, traits.get(trait)) ;
		  traits.put(trait, value) ;
		  return true ;
	  }
	  
	  // returns false if input is invalid
	  return false ;
 }

 public boolean setEnabled(boolean enabled) {
	 return this.setTrait("enabled", enabled);
 }

 public boolean getEnabled() {
 	 return (boolean)this.getTrait("enabled");
 }

 public boolean setPressed(boolean pressed) {
	 return this.setTrait("pressed", pressed);
 }

 public boolean getPressed() {
	 return (boolean)this.getTrait("pressed");
 }

 public boolean toggleEnabled() {
	 setEnabled(!getEnabled());
	 return getEnabled();
 }

 public boolean togglePressed() {
	 if(getEnabled() == true){
		 setPressed(!getPressed());
	 }
	 return getPressed();
 }
 
  public boolean setOnPlay(String onPlay){
	  return this.setTrait("onPlay", onPlay);
  }

  public String getOnPlay(){
	  return (String)this.getTrait("onPlay");
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
  
  public String repr(boolean hasLabel) {
	return "Card\n" + super.repr(hasLabel);
  }
}
