package objects ;

import java.awt.Color;
import java.util.*;
/**
 * Write a description of class Spinner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spinner extends GameObject
{

	private static int count = 0;
	  
    // instance variables - replace the example below with your own
//    private String label;
//    private Object icon;
//    private String color;
//    private int numCategories;
    private ArrayList<Category> categories;
    private int numCategories;

    /**
     * Constructor for objects of class Spinner
     */
    public Spinner()
    {
    	super() ;  
    	
    	categories = new ArrayList<Category>() ;
    	numCategories = 0;
    	
    	this.setLabel("deck" + String.format("%02d", ++count));
    	this.setIcon("default_gamepiece_icon.jpg") ;
    	this.setColor(Color.BLACK) ;
//    	this.setCategories(categories) ;
        this.setTrait("categories", categories, true) ;
        // initialise instance variables
//        label = "";
//        icon = null;
//        color = "";
//        numCategories = 0;
//        categories = new HashMap<Integer,Category>();
    }
    
    /* Trait Types:
     * 	label 	: 	String
     * 	icon 	: 	String
     * 	color 	:	Color
     * 	categories: ArrayList
     */
    
  //set trait to value. Overrides checking for default traits only
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
   	  
   	  // run game object's set trait first
   	  if (super.setTrait(trait, value, suppressTraitChecker)) {
   		  return true ;
   	  }
   	  
   	  // checks for other valid inputs
//   	  else if (suppressTraitChecker ||	// if true don't check trait type
//   			  (trait.equals("categories") && value instanceof ArrayList)) {	// check categories are ArrayList
//   		  traits.put(trait, value) ;
//   		  return true ;
//   	  }
   	  
   	  // returns false if input is invalid
   	  return false ;
    }
    
    public void addCategory(Category c) {
    	if (!this.getCategories().contains(c)) {
        	this.getCategories().add(c) ;
        	numCategories++;
    	}
      }

      public void deleteCategory(Category c, int quantity) {
    	if (this.getCategories().contains(c)) {
    		this.getCategories().remove(c) ;
        	numCategories--;
    	}
      }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean setNumCategories(int num)
    {
    	// number of categories must be greater than or equal to 1
    	if (num < 1) {
    		return false ;
    	}
        
    	// if old spinner has less categories than new spinner, add categories
    	while (numCategories < num) {
        	categories.add(new Category());
        	numCategories++ ; 
        } 
    	
    	// if old spinner has more categories than new spinner, remove cats
    	while (numCategories > num) {
        	categories.remove(categories.size() - 1);
        	numCategories-- ; 
        } 
        
    	// regularize all categories
        for (Category c : categories) {
        	if (!c.setWeight(1.0 / num)) return false ;
        }
        
        return true ;
    }
    
    public int getNumCategories() {
        return this.getCategories().size();
    }
    
    public Category spin() {
        //calculates spin based off category weights
        //if weights don't add up to 1, return null (Error State);
        double total = 0;
        double[] arr = new double[getNumCategories()];
        for (int i = 0; i < getNumCategories(); i++) {
            double curW = getCategories().get(i).getCategoryWeight();
            total += curW;
            arr[i] = total;
        }
        
        if (total != 1) { //invalid weights
            return null;
        }
        double output = Math.random();
        
        int idx = 0;
        for (int i = 0; i < getNumCategories(); i++) {
        	if (arr[i] > output) { //if value found
            	return this.getCategories().get(idx);
            }
            idx++;
        }
        
        return null ;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public double getCategoryTraitValueInt(int catNum, String trait) {
//        Category cat = categories.get(catNum);
//        if (trait != "weight") {
//            return -1.0;
//        }
//        else {
//            return cat.getCategoryWeight();
//        }
//    }
//    
//    public String getCategoryTraitValueString(int catNum, String trait) {
//        Category cat = categories.get(catNum);
//        if (trait != "label" || trait != "color") {
//            return null;
//        }
//        else {
//            if (trait == "label") {
//                return cat.getLabel();
//            }
//            else {
//                return cat.getColor().toString();
//            }
//        }
//    }
//    
//    public void setCategoryTraitValue(int catNum, String trait, double val) {
//        Category cat = categories.get(catNum);
//        if (trait != "weight") {
//            return;
//        }
//        else {
//            cat.setCategoryWeight(val);
//        }
//    }
//    
//    public void setCategoryTraitValue(int catNum, String trait, String val) {
//        Category cat = categories.get(catNum);
//        if (trait != "label" || trait != "color") {
//            return;
//        }
//        else {
//            if (trait == "label") {
//                cat.setLabel(val);
//            }
//            else {
////                cat.setColor(val);
//            }
//        }
//    }
    

//    public boolean setCategories(ArrayList<Category> categories) {
//  	  return this.setTrait("categories", categories);
//    }
    
    public ArrayList<Category> getCategories() {
    	return (ArrayList<Category>)this.getTrait("categories");
    }
    
}
