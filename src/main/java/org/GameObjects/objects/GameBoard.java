
package org.GameObjects.objects;

import java.util.ArrayList;

public class GameBoard {
    private String boardID;

    private String shape;
    private double width;
    private double height;

    private double xPos;
    private double yPos;

    private ArrayList<Tile> tiles;

    public GameBoard(String boardID, String shape, double width, double height, double xPos, double yPos, ArrayList<Tile> tiles) {
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
    public void setShape(String shape) {
        this.shape = shape;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setXPos(double xPos) { this.xPos = xPos; }
    public void setYPos(double yPos) { this.yPos = yPos; }
    public void setTiles(ArrayList<Tile> tiles) { this.tiles = new ArrayList<>(tiles); }

    // getters
    public String getBoardID() { return this.boardID; }
    public String getShape() { return this.shape; }
    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public double getXPos() { return this.xPos; }
    public double getYPos() { return this.yPos; }
    public ArrayList<Tile> getTiles() { return this.tiles; }
}

