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
    	this.setIcon("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAMAAAAOusbgAAAAaVBMVEX///8AAABiYmIcHBwiIiJmZmZVVVUREREFBQX29vbm5ua/v79ubm4LCwteXl65ubkpKSnU1NQwMDCEhISvr69TU1M1NTWNjY3Jycng4OAXFxfs7OzS0tKgoKBNTU1AQEBISEiZmZk+Pj51hvrDAAAD7ElEQVRoge2ba5uyIBCGIU94wFNqpmW2//9HvpW7CjjwYm2xH3i+7NbVzK0cBxgQmhVWSd10wZvUNXVShWitY0oxLcfUe5PSsbwB0qOA7T1Ckwx6oF9UmCWUJD371cklXvRe6qTII26xfMxo134Ce1fc0fjn/yIoP/K6k6Iy+H7n3nU+yL01486d6tkjHyvnSS1J7n9OOPksF6GE3Avbp/1/f/nLiqh/61x0/cLhORnK/JLuC8BKW8U+veTlkJzXw4NHQ1ThWPg29in+UXl4clAJD+XshPoiIsMVSijv+jhgTk71DLdyeC8D32/uxTyU/LMEWJS3neutnAQZ94PmgpqRe1KyMsG43ljc4Qg4IVzJ1TlyU+ZzBnEx9reBfdAJYd85dZCzWz5G63KedNjCPUicBEw973hwLTHBVJxGFTpSmRem4HhwJrO4VbM+eJB7WQqbBytMsPYrHxVOBhjcwy1rknYty2r4LjIPzhz4rDDBjS64UXk5g+BEZUJ0wapiW+ZBDixt0w9pxgqR0sncRjmwspCwZrDQKp3MFcaBc6WNOMFIFCud5CAYGmEXafYnVW/CeATBO6WN5kQRKp0sLBa8V5k4elyEHJWXPQguVCbaAaGyU86hFD9klgoT7QhY1ayXmIMHK8ra1eUi5Mq9LOMuDw7lNhmEgFXJn35poMJ8LJ0XN8yKihGQGQoEMEphi2BDHHDrypI4ho2xRDBMdjbG9QXYpVjuGgwFat3m9cSpW3vhA8Y1GFWikffE0qoXA+tOWBYAYBT57JSab2jPrDJ2yiGpOKlC4NvzXr8mtpO8sHJuk6mqydd1XWYw+K6ojYuXN4HCIm7hAEIOfrMs2IIt2IIt2IIt2IIt2IIt2IIt2IIt2IIt2AjYyHaToQ02U1uKe/Hcedi0WT1JTK3AdM//4M9sG4P5DDjYWNMtuEU/sm31rxwNSA9Qxi3gi8wLs1nOg3ugar51BgASyQ/eHdnxj7EDL9UBsubxsfoAeT4+5sEnhYl+ptE6v4jRCQQbO8Y1dnAt7QYPaXZl5SE0HkGwseQEY+kYxhJQjKXcGEsyCqWpZ/iX0qqWTEx+yIST/B4i2vnIkaKsl2Dgj6TOyeIAPsHwv68sS3VkIwEBLJ2Qr/pchK4SJx0T5d7AxhJC+RTYGGrZKdooqMooN+bWubmkXzHNuRcGsPypVUwrzDj8tYxHmnO2yuP5SGJ3Zi6V3VjyPirIE63nNe3II575+AWNeLqggXq3e2Il+rzmKymoCPIPko95MLfZjHbaK4VXFQfsGGbqotV0tWwH3nX7RYXVTrxahr4v0zW1/zbVDXSZbnqg5Mt9oy7c9cF/5XE8hSZf+w8AAAAASUVORK5CYII=") ;
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
