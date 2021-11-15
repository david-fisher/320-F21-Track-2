package org.scenebuilder.scenebuilder;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DummyTile {

    private String tileID;
    private String shape;
    private Color color;

    private double width;
    private double height;

    private double xPos;
    private double yPos;

    private ArrayList<DummyTile> connectionsTo;

    public DummyTile(String tileID, String shape, Color color, double width, double height, double xPos, double yPos, ArrayList<DummyTile> connectionsTo) {
        this.tileID = tileID;
        this.shape = shape;
        this.color = color;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.connectionsTo = connectionsTo;
    }

    // setters
    public void setTileID(String tileID) {
        this.tileID = tileID;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public void setColor(Color color) { this.color = color; }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setXPos(double xPos) { this.xPos = xPos; }
    public void setYPos(double yPos) { this.yPos = yPos; }
    public void setTiles(ArrayList<DummyTile> connectionsTo) { this.connectionsTo = new ArrayList<>(connectionsTo); }

    // getters
    public String getTileID() { return this.tileID; }
    public String getShape() { return this.shape; }
    public Color getColor() { return this.color; }
    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public double getXPos() { return this.xPos; }
    public double getYPos() { return this.yPos; }
    public ArrayList<DummyTile> getConnectionsTo() { return this.connectionsTo; }


}
