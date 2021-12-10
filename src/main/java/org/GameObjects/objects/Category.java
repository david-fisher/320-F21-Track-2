package org.GameObjects.objects;


/**
 * Write a description of class Category here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Category extends GameObject
{
    
    private static int count = 0;

    /**
     * Constructor for objects of class Category
     */
    public Category()
    {
    	super() ;  
    	this.setLabel("category" + String.format("%02d", ++count));
    	this.setIcon("default_category_icon.jpg") ;
    	this.setColorString("#000000") ;
    	this.setWeight(0) ;
    }
    
//    public Category(Color color, Double weight) {
//    	super() ;  
//    	this.setLabel("category" + String.format("%02d", ++count));
//    	this.setIcon("default_category_icon.jpg") ;
//    	this.setColor(color) ;
//    	this.setWeight(weight) ;
//    }
    
    /* Trait Types:
     * 	label 	: 	String
     * 	icon 	: 	String
     * 	color 	:	String (Can be obtained as JAVAFX Color object)
     * 	weight	:	Double
     */
    
  //set trait to value. Overrides checking for default traits only
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
   	  
   	  // run game object's set trait first
   	  if (super.setTrait(trait, value, suppressTraitChecker)) {
   		  return true ;
   	  }
   	  
   	  // checks for other valid inputs
   	  else if (suppressTraitChecker ||	// if true don't check trait type
   			  (trait.equals("weight") && value instanceof Double)) {	// check weight is Double
   	      prevTraits.put(trait, traits.get(trait)) ;
   		  traits.put(trait, value) ;
   		  return true ;
   	  }
   	  
   	  // returns false if input is invalid
   	  return false ;
    }
    
    public boolean setWeight(double weight) {
    	return this.setTrait("weight", weight);
    }
    
    public double getWeight() {
    	return (Double)this.getTrait("weight");
    }
    
    public void setCategoryWeight(double w)
    {
        // put your code here
        this.setWeight(w) ;
    }
    
    public double getCategoryWeight() {
        return this.getWeight();
    }
    
    public String toString() {
    	return this.getLabel() ;
    }

    public String repr(boolean hasLabel) {
        return "Category\n" + super.repr(hasLabel);
    }
}
