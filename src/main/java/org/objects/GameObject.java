package org.objects;

import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;


public abstract class GameObject {

    // removed because of errors
    // extends Savable

  protected HashMap<String, Object> traits;

  private static ArrayList<String> labels = new ArrayList<String>(256);

  // default constructor
  public GameObject() {
	traits = new HashMap<String, Object>(10) ;
  }


  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	Color
   */

  // set trait to value. Do not allow creation of non-default traits
  public boolean setTrait(String trait, Object value) {
	  return this.setTrait(trait, value, false) ;
  }

  // set trait to value. Overrides checking for default traits only
  public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

	  // checks and sets label
	  if (trait.equals("label") && 			// trait is label
			  value instanceof String && 	// label is String
			  ! labels.contains(value)) {	// label is unique

		  // update static list of labels to maintain
		  String oldLabel = this.getLabel() ;
		  int oldLabelIndex = GameObject.labels.indexOf(oldLabel) ;
		  if (oldLabelIndex >= 0) {
			  GameObject.labels.set(oldLabelIndex, (String)value) ;
		  } else {
			  GameObject.labels.add((String)value) ;
		  }

		  // set label in HashMap
		  traits.put(trait, value) ;
		  return true ;
	  }

	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("icon") && value instanceof String) ||	// check icon is String
			  (trait.equals("color") && value instanceof Color)) {	// check color is Color
		  traits.put(trait, value) ;
		  return true ;
	  }

	  // returns false if input is invalid
	  return false ;
  }

  // gets trait of
  public Object getTrait(String key) {
	  return this.traits.get(key) ;
  }

  // returns HashMap of all the traits
  public HashMap<String, Object> getAllTraits() {
	  return this.traits ;
  }

  // this is a dangerous method to use. Careful if you don't know what you're doing...
  public boolean setAllTraits(HashMap<String, Object> traits) {
	  this.traits = traits ;
	  return true ;
  }


  /*	Below are specific getter and setter methods
   * 	for the default traits of all game objects
   * 	(label, icon, and color)
   *
   * 	These can be considered wrapper methods to help
   * 	other teams with implementation, but are not
   * 	necessary for the usage of this API.
   */

 public boolean setLabel(String label) {
   return this.setTrait("label", label);
 }

 public String getLabel() {
   return (String)this.getTrait("label");
 }

 public boolean setIcon(String icon) {
	 return this.setTrait("icon", icon);
 }

 public String getIcon() {
	   return (String)this.getTrait("icon");
 }

 public boolean setColor(Color color) {
	 return this.setTrait("color", color);
 }

 public Color getColor() {
	   return (Color)this.getTrait("color");
 }

}
