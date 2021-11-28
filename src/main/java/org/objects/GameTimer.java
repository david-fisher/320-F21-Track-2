package org.objects;

import javafx.scene.paint.Color;
import javax.swing.Timer ;
import java.awt.event.ActionListener;


public class GameTimer extends GameObject {

  private Timer timer ;
  private static int count = 0 ;


  public GameTimer() {
	  super() ;
	  this.setLabel("gametimer" + String.format("%02d", ++count));
	  this.setIcon("default_token_icon.jpg") ;
	  this.setColor(Color.BLACK) ;
	  this.setInitialTime(60.0) ;

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
	  else if (trait.equals("initialTime") && value instanceof Integer) {
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

  public void start() {
    timer.start();
  }

  public void reset() {
    timer.restart();
    timer.stop();
  }

  public int stop() {
    timer.stop();
    return this.getTime();
  }

  public int getTime() {
    return timer.getDelay();
  }

  public boolean setInitialTime(double initialTime) {
	  return this.setTrait("initialTime", initialTime);
  }

  public double getInitialTime() {
	  return (double)this.getTrait("initialTime");
  }
}
