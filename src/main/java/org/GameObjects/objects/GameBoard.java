
package org.GameObjects.objects;

import java.util.ArrayList;

public class GameBoard extends GameObject {

    private String boardID;

    private String shape;
    private double width;
    private double height;

    private int xPos;
    private int yPos;

    private ArrayList<Tile> tiles;

    public GameBoard(String boardID, String shape, double width, double height, int xPos, int yPos, ArrayList<Tile> tiles) {
        this.boardID = boardID;
        this.shape = shape;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.tiles = tiles;
    }

    // setters
    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setXPos(int xPos) { this.xPos = xPos; }
    public void setYPos(int yPos) { this.yPos = yPos; }
    public void setTiles(ArrayList<Tile> tiles) { this.tiles = new ArrayList<>(tiles); }

    // getters
    public String getBoardID() { return this.boardID; }
    public String getShape() { return this.shape; }
    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public int getXPos() { return this.xPos; }
    public int getYPos() { return this.yPos; }
    public ArrayList<Tile> getTiles() { return this.tiles; }

}
