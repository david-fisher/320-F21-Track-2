package objects;

import java.awt.Color;
import javax.swing.Timer ;
import java.awt.event.ActionListener;


public class GameTimer extends GameObject {
	
  private Timer timer ; 


  public GameTimer() {
	  super() ; 
	  this.setLabel("token");
	  this.setIcon("default_token_icon.jpg") ;
	  this.setColor(Color.BLACK) ;
	  this.setInitialTime(60.) ;
	  
	  timer = new Timer(100, null) ;
  }  
  
  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	Color
   * 	initialTime:Double
   */
  
  //set trait to value. Overrides checking for default traits only
  public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
	  
	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }
	  
	  // check and set initial time
	  else if (trait.equals("initialTime") && value instanceof Double) {
		  timer.setDelay((Integer)value);
	  }
	  
	  // checks for other valid inputs
	  else if (suppressTraitChecker) {	// if true don't check trait type
		  traits.put(trait, value) ;
		  return true ;
	  }
	  
	  // returns false if input is invalid
	  return false ;
 }

  public void addActionListener(ActionListener listener) {
	  timer.addActionListener(listener);
  }
 
  public boolean start() {
    // TODO
    return true;
  }

  public boolean reset() {
    // TODO
    return true;
  }

  public float pause() {
    // TODO
    return 1;
  }

  public float stop() {
    // TODO
    return 1;
  }

  public float getTime() {
    // TODO
    return 1;
  }

  public boolean setInitialTime(Double initialTime) {
	  return this.setTrait("initialTime", initialTime);
  }

  public Double getInitialTime() {
	  return (Double)this.getTrait("initialTime");
  }
}
