package org.objects;

import java.awt.Color;
import java.util.*;
/**
 * Write a description of class Spinner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spinner extends GameObject {

	private static int count = 0;

    // instance variables
    private List<Category> categories;
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
        this.setTrait("categories", categories, true) ;
        this.setNumCategories(1) ;
    }

    /* Trait Types:
     * 	label 	: 	String
     * 	icon 	: 	String
     * 	color 	:	Color
     * 	categories: ArrayList
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
        return numCategories;
    }

    public boolean setCategoryWeights(List<Double> weights) {
    	if (weights.size() != numCategories) return false ;

    	double sum = 0 ;
    	for (double w : weights) {
    		sum += w ;
    	}

    	if (sum < 0.99 || sum > 1.01) return false ;

    	for (int i = 0; i < numCategories; ++i) {
    		if (!this.getCategory(i).setWeight(weights.get(i))) return false ;
    	}
    	return true ;
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

    public Category getCategory(int i) {
    	return categories.get(i) ;
    }

    public Category getCategory(String label) {
    	for (Category cat : categories) {
    		if (cat.getLabel().equals(label)) return cat ;
    	}
    	return null ;
    }


    public List<Category> getCategories() {
    	return categories ;
    }

}
