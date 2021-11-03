package objects ;

public class Dice
{
    // instance variables - replace the example below with your own
    private String label;
    private Object icon;
    private String color;
    private String dotColor;
    private int numSides;

    /**
     * Constructor for objects of class Dice
     */
    public Dice()
    {
        // initialise instance variables
        label = "";
        icon = null;
        color = "";
        numSides = 0;
        dotColor = "";
    }
    
    public void setIcon(Object image)
    {
        // put your code here
        icon = image;
    }
    
    public Object getIcon()
    {
        // put your code here
        return icon;
    }
    
    public void setSides(int sides) {
        numSides = sides;
    }
    
    public int getSides()
    {
        // put your code here
        return numSides;
    }
    
    public String geDotColor()
    {
        // put your code here
        return dotColor;
    }
    
    public void setDotColor(String c) {
        dotColor = c;
    }
    
    public int roll() {
        return (int)(Math.round(Math.random() * numSides));
    }
}
