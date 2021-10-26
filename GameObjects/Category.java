
/**
 * Write a description of class Category here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Category
{
    // instance variables - replace the example below with your own
    private String label;
    private double weight;
    private String color;

    /**
     * Constructor for objects of class Category
     */
    public Category(String s, double w, String c)
    {
        // initialise instance variables
        label = s;
        weight = w;
        color = c;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setCategoryLabel(String l)
    {
        // put your code here
        label = l;
    }
    
    public String getCategoryLabel() {
        return label;
    }
    
    public void setCategoryWeight(double w)
    {
        // put your code here
        weight = w;
    }
    
    public double getCategoryWeight() {
        return weight;
    }
    
    public void setCategoryColor(String c)
    {
        // put your code here
        color = c;
    }
    
    public String getCategoryColor() {
        return color;
    }
    

}
