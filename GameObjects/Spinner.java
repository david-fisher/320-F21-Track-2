import java.util.*;
/**
 * Write a description of class Spinner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spinner
{
    // instance variables - replace the example below with your own
    private String label;
    private Object icon;
    private String color;
    private int numCategories;
    private HashMap<Integer,Category> categories;

    /**
     * Constructor for objects of class Spinner
     */
    public Spinner()
    {
        // initialise instance variables
        label = "";
        icon = null;
        color = "";
        numCategories = 0;
        categories = new HashMap<Integer,Category>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setNumCategories(int num)
    {
        // put your code here
        categories = new HashMap<Integer,Category>();
        numCategories = num;
        int i = 0;
        while (i < num + 1) {
            categories.put(i, new Category("category" + String.valueOf(i), 0.0,"#00000F"));
            i++;
        }
    }
    
    public int getNumCategories() {
        return numCategories;
    }
    
    public int spin() {
        //calculates spin based off category weights
        //if weights don't add up to 1, return -1 (Error State);
        double total = 0;
        double[] arr = new double[numCategories+1];
        for (int i = 0; i < numCategories; i++) {
            double curW = categories.get(i).getCategoryWeight();
            total += ((1 / numCategories) * curW);
            arr[i] = total;
        }
        
        if (total > 1) { //invalid weights
            return -1;
        }
        double output = (double)(Math.round(Math.random() * numCategories));
        
        int idx = 0;
        for (int i = 0; i < numCategories; i++) {
            if (arr[i] > output) { //if value found
                break;
            }
            idx++;
        }
        
        return idx + 1;
    }
    
    public double getCategoryTraitValueInt(int catNum, String trait) {
        Category cat = categories.get(catNum);
        if (trait != "weight") {
            return -1.0;
        }
        else {
            return cat.getCategoryWeight();
        }
    }
    
    public String getCategoryTraitValueString(int catNum, String trait) {
        Category cat = categories.get(catNum);
        if (trait != "label" || trait != "color") {
            return null;
        }
        else {
            if (trait == "label") {
                return cat.getCategoryLabel();
            }
            else {
                return cat.getCategoryColor();
            }
        }
    }
    
    public void setCategoryTraitValue(int catNum, String trait, double val) {
        Category cat = categories.get(catNum);
        if (trait != "weight") {
            return;
        }
        else {
            cat.setCategoryWeight(val);
        }
    }
    
    public void setCategoryTraitValue(int catNum, String trait, String val) {
        Category cat = categories.get(catNum);
        if (trait != "label" || trait != "color") {
            return;
        }
        else {
            if (trait == "label") {
                cat.setCategoryLabel(val);
            }
            else {
                cat.setCategoryColor(val);
            }
        }
    }
    
}
