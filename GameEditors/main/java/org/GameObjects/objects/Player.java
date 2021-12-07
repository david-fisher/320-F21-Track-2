package org.GameObjects.objects;

import javafx.scene.paint.Color;
import org.scenebuilder.scenebuilder.dummy.DummyInventory;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private String playerID;

    private Color color;
    private ArrayList<Gamepiece> gamePieces;
    private DummyInventory inventory;

    private boolean isHuman;

    public Player(String playerID, Color color, ArrayList<Gamepiece> gamePieces, DummyInventory inventory, boolean isHuman) {
        this.playerID = playerID;
        this.color = color;
        this.gamePieces = gamePieces;
        this.inventory = inventory;
        this.isHuman = isHuman;
    }

    public Player(String playerID, ArrayList<Gamepiece> gamePieces, DummyInventory inventory, boolean isHuman) {
        this.playerID = playerID;
        this.gamePieces = gamePieces;
        this.inventory = inventory;
        this.isHuman = isHuman;

        this.color = getRandomColor();
    }

    // setters
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    public void setColor(Color color) { this.color = color; }
    public void setGameTokens(ArrayList<Gamepiece> gamePieces) { this.gamePieces = gamePieces; }
    public void setInventory(DummyInventory inventory) { this.inventory = inventory; }
    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    // getters
    public String getPlayerID() {
        return this.playerID;
    }
    public Color getColor() { return this.color; }
    public ArrayList<Gamepiece> getGamePieces() { return this.gamePieces; }
    public DummyInventory getInventory() { return this.inventory; }
    public boolean getIsHuman() { return this.isHuman; }

    // modifiers
    public void addPiece(Gamepiece piece) { this.gamePieces.add(piece); }
    public void removePiece(Gamepiece piece) { this.gamePieces.remove(piece); }
    public void removePiece(int pieceIndex) { this.gamePieces.remove(pieceIndex); }
    public void setPiece(int pieceIndex, Gamepiece piece) { this.gamePieces.set(pieceIndex, piece); }

    public Color getRandomColor(){
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        return Color.rgb(red, green, blue, .99);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID='" + playerID + '\'' +
                ", color=" + color +
                ", gamePieces=" + gamePieces +
                ", inventory=" + inventory +
                ", isHuman=" + isHuman +
                '}';
    }
}
