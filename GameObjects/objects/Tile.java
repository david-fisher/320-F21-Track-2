package objects ;

import java.util.*;
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile
{
    // instance variables - replace the example below with your own
    private ArrayList<Tile> connections;

    /**
     * Constructor for objects of class Tile
     */
    public Tile()
    {
        // initialise instance variables
        connections = new ArrayList<Tile>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<Tile> getConnect()
    {
        // put your code here
        return connections;
    }
    
    public void addConnect(Tile tile) {
        connections.add(tile);
    }
    
    public void deleteConnect(Tile tile) {
        connections.remove(tile);
    }
}
