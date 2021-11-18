package org.objects;

import java.awt.Color;

public class Die extends GameObject {

  private static int count = 0;

  public Die() {
	super() ;
	this.setLabel("die" + String.format("%02d", ++count));
	this.setIcon("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAMAAAAOusbgAAAAaVBMVEX///8AAABiYmIcHBwiIiJmZmZVVVUREREFBQX29vbm5ua/v79ubm4LCwteXl65ubkpKSnU1NQwMDCEhISvr69TU1M1NTWNjY3Jycng4OAXFxfs7OzS0tKgoKBNTU1AQEBISEiZmZk+Pj51hvrDAAAD7ElEQVRoge2ba5uyIBCGIU94wFNqpmW2//9HvpW7CjjwYm2xH3i+7NbVzK0cBxgQmhVWSd10wZvUNXVShWitY0oxLcfUe5PSsbwB0qOA7T1Ckwx6oF9UmCWUJD371cklXvRe6qTII26xfMxo134Ce1fc0fjn/yIoP/K6k6Iy+H7n3nU+yL01486d6tkjHyvnSS1J7n9OOPksF6GE3Avbp/1/f/nLiqh/61x0/cLhORnK/JLuC8BKW8U+veTlkJzXw4NHQ1ThWPg29in+UXl4clAJD+XshPoiIsMVSijv+jhgTk71DLdyeC8D32/uxTyU/LMEWJS3neutnAQZ94PmgpqRe1KyMsG43ljc4Qg4IVzJ1TlyU+ZzBnEx9reBfdAJYd85dZCzWz5G63KedNjCPUicBEw973hwLTHBVJxGFTpSmRem4HhwJrO4VbM+eJB7WQqbBytMsPYrHxVOBhjcwy1rknYty2r4LjIPzhz4rDDBjS64UXk5g+BEZUJ0wapiW+ZBDixt0w9pxgqR0sncRjmwspCwZrDQKp3MFcaBc6WNOMFIFCud5CAYGmEXafYnVW/CeATBO6WN5kQRKp0sLBa8V5k4elyEHJWXPQguVCbaAaGyU86hFD9klgoT7QhY1ayXmIMHK8ra1eUi5Mq9LOMuDw7lNhmEgFXJn35poMJ8LJ0XN8yKihGQGQoEMEphi2BDHHDrypI4ho2xRDBMdjbG9QXYpVjuGgwFat3m9cSpW3vhA8Y1GFWikffE0qoXA+tOWBYAYBT57JSab2jPrDJ2yiGpOKlC4NvzXr8mtpO8sHJuk6mqydd1XWYw+K6ojYuXN4HCIm7hAEIOfrMs2IIt2IIt2IIt2IIt2IIt2IIt2IIt2IIt2AjYyHaToQ02U1uKe/Hcedi0WT1JTK3AdM//4M9sG4P5DDjYWNMtuEU/sm31rxwNSA9Qxi3gi8wLs1nOg3ugar51BgASyQ/eHdnxj7EDL9UBsubxsfoAeT4+5sEnhYl+ptE6v4jRCQQbO8Y1dnAt7QYPaXZl5SE0HkGwseQEY+kYxhJQjKXcGEsyCqWpZ/iX0qqWTEx+yIST/B4i2vnIkaKsl2Dgj6TOyeIAPsHwv68sS3VkIwEBLJ2Qr/pchK4SJx0T5d7AxhJC+RTYGGrZKdooqMooN+bWubmkXzHNuRcGsPypVUwrzDj8tYxHmnO2yuP5SGJ3Zi6V3VjyPirIE63nNe3II575+AWNeLqggXq3e2Il+rzmKymoCPIPko95MLfZjHbaK4VXFQfsGGbqotV0tWwH3nX7RYXVTrxahr4v0zW1/zbVDXSZbnqg5Mt9oy7c9cF/5XE8hSZf+w8AAAAASUVORK5CYII=");
	this.setColor(Color.WHITE) ;
	this.setNumSides(6);
    this.setDotColor(Color.BLACK);
  }

  /* Trait Types:
   * 	label 	: 	String
   * 	icon 	: 	String
   * 	color 	:	Color
   * 	dotColor:	Color
   * 	numSides:	Integer
   */

//set trait to value. Overrides checking for default traits only
 public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

	  // run game object's set trait first
	  if (super.setTrait(trait, value, suppressTraitChecker)) {
		  return true ;
	  }

	  // checks for other valid inputs
	  else if (suppressTraitChecker ||	// if true don't check trait type
			  (trait.equals("dotColor") && value instanceof Color) || // check dotColor is Color
			  (trait.equals("numSides") && value instanceof Integer)) {
		  traits.put(trait, value) ;
		  return true ;
	  }

	  // returns false if input is invalid
	  return false ;
 }

  public int roll() {
	  return (int)(Math.random() * this.getNumSides()) + 1;
  }

  public boolean setNumSides(int num) {
	  return this.setTrait("numSides", num);
  }

  public int getNumSides() {
	  return (Integer)this.getTrait("numSides");
  }

  public boolean setDotColor(Color color) {
	  return this.setTrait("dotColor", color);
  }

  public Color getDotColor() {
	  return (Color)this.getTrait("dotColor");
  }
}
