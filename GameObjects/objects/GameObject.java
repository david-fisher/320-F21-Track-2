package objects;

import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject extends Savable {
  
  protected HashMap<String, Object> traits;
  
  private static List<String> labels = new ArrayList<String>(256);

  // default constructor
  public GameObject() {
	traits = new HashMap<String, Object>(10) ;
	this.setShape("default") ;
	this.setXPos(0) ;
	this.setYPos(0) ;
	this.setWidth(0.) ;
	this.setHeight(0.) ;
  }
  
  
  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   *    shape   :   String
   * 	color 	:	Color
   *    xPos    :   Integer
   *    yPos    :   Integer
   *    width   :   Double
   *    height  :   Double
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
			  (trait.equals("color") && value instanceof Color) ||  // check color is Color
			  (trait.equals("shape") && value instanceof String) || // check shape is String
  			  (trait.equals("xPos") && value instanceof Integer) || // check xPos is Integer
  			  (trait.equals("yPos") && value instanceof Integer) || // check yPos is Integer
			  (trait.equals("width") && value instanceof Double) || // check width is Double
  			  (trait.equals("height") && value instanceof Double)) {// check height is Double
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
 public boolean setShape(String shape) {
 	return setTrait("shape", shape) ;
 }

 public String getShape() {
 	return (String)getTrait("shape") ;
 }

 public boolean setXPos(Integer xPos) {
 	return setTrait("xPos", xPos) ;
 }

 public int getXPos() {
 	return (int)getTrait("xPos") ;
 }

 public boolean setYPos(Integer yPos) {
 	return setTrait("yPos", yPos) ;
 }

 public int getYPos() {
 	return (int)getTrait("yPos") ;
 }

 public boolean setWidth(Double width) {
 	return setTrait("width", width) ;
 }

 public double getWidth() {
 	return (double)getTrait("width") ;
 }

 public boolean setHeight(Double height) {
 	return setTrait("height", height) ;
 }

 public double getHeight() {
 	return (double)getTrait("height") ;
 }
 
 public String toString() {
	 return getLabel() ;
 } 
}
