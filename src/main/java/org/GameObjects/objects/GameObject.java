package org.GameObjects.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javafx.scene.paint.Color;
import javafx.scene.Node;
import org.GamePlay.Display;

public abstract class GameObject extends Savable {
  
  protected HashMap<String, Object> traits;
  protected HashMap<String, Object> prevTraits;
  
  private static List<String> labels = new ArrayList<String>(256);

  // default constructor
  public GameObject() {
	traits = new HashMap<String, Object>(10) ;
	prevTraits = new HashMap<String, Object>(10) ;
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
   * 	color 	:	String (Can be obtained as JAVAFX Color object)
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
		  prevTraits.put(trait, traits.get(trait)) ;
		  traits.put(trait, value) ;
		  return true ;
	  }
	  
	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("icon") && value instanceof String) ||	// check icon is String
			  (trait.equals("color") && value instanceof String) ||  // check color is Color
			  (trait.equals("shape") && value instanceof String) || // check shape is String
  			  (trait.equals("xPos") && value instanceof Integer) || // check xPos is Integer
  			  (trait.equals("yPos") && value instanceof Integer) || // check yPos is Integer
			  (trait.equals("width") && value instanceof Double) || // check width is Double
  			  (trait.equals("height") && value instanceof Double) || // check height is Double
	          (trait.equals("parent") && value instanceof Node) || // check parent is Node 
	          (traits.get(trait) != null && traits.get(trait).getClass().getName().equals(value.getClass().getName()))) { 
		  prevTraits.put(trait, traits.get(trait)) ;
          traits.put(trait, value) ;
          if (trait.equals("color") && this.getParent() != null) {
              Display.getDisplay().updateColor(this);
          }
		  return true ;
	  }
	  
	  // returns false if input is invalid
	  return false ;
  }
  
  // gets trait of
  public Object getTrait(String key) {
	  return this.traits.get(key) ;
  }
  
  public Object getPrevTrait(String key) {
    return this.prevTraits.get(key) ;
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
	 return setColorString(toHexCode(color));
 }
 
 protected String formatColor(double val) {
	 String in = Integer.toHexString((int) Math.round(val * 255));
	    return in.length() == 1 ? "0" + in : in;
 }
 
 protected String toHexCode(Color color) {
	 return "#" + (formatColor(color.getRed()) + formatColor(color.getGreen()) + formatColor(color.getBlue()) + formatColor(color.getOpacity()))
	            .toUpperCase();
 }
 
 public boolean setColorString(String color) {
     return this.setTrait("color", color);
 }

 public String getColorString() {
	   return (String)this.getTrait("color");
 }
 
 public Color getColor() {
	 return Color.web(getColorString());
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
 
 public boolean setParent(Node parent) {
   return setTrait("parent", parent);
 }
 
 public Node getParent() {
   return (Node)getTrait("parent");
 }
 
 public String toString() {
	 return getLabel() ;
 }
 
 // GKNEW Integration Function
 public String repr(boolean hasLabel) {
	String s = "";
	TreeSet<String> sortedKeys = new TreeSet<String>(this.traits.keySet());
	for (String key: sortedKeys) {
		if (hasLabel || !key.equals("label")) {
			s = s + key + '=' + this.traits.get(key).toString() + "\n";
		}
	} 
	return s;
 }
}
