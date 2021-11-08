//package org.objects;
//
//import java.awt.Color;
//
//public class Die extends GameObject {
//
//  private static int count = 0;
//
//  public Die() {
//	super() ;
//	this.setLabel("die" + String.format("%02d", ++count));
//	this.setIcon("default_gamepiece_icon.jpg") ;
//	this.setColor(Color.WHITE) ;
//	this.setNumSides(6);
//    this.setDotColor(Color.BLACK);
//  }
//
//  /* Trait Types:
//   * 	label 	: 	String
//   * 	icon 	: 	String
//   * 	color 	:	Color
//   * 	dotColor:	Color
//   * 	numSides:	Integer
//   */
//
////set trait to value. Overrides checking for default traits only
// public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
//
//	  // run game object's set trait first
//	  if (super.setTrait(trait, value, suppressTraitChecker)) {
//		  return true ;
//	  }
//
//	  // checks for other valid inputs
//	  else if (suppressTraitChecker ||	// if true don't check trait type
//			  (trait.equals("dotColor") && value instanceof Color) || // check dotColor is Color
//			  (trait.equals("numSides") && value instanceof Integer)) {
//		  traits.put(trait, value) ;
//		  return true ;
//	  }
//
//	  // returns false if input is invalid
//	  return false ;
// }
//
//  public int roll() {
//	  return (int)(Math.random() * this.getNumSides()) + 1;
//  }
//
//  public boolean setNumSides(int num) {
//	  return this.setTrait("numSides", num);
//  }
//
//  public int getNumSides() {
//	  return (Integer)this.getTrait("numSides");
//  }
//
//  public boolean setDotColor(Color color) {
//	  return this.setTrait("dotColor", color);
//  }
//
//  public Color getDotColor() {
//	  return (Color)this.getTrait("dotColor");
//  }
//}
