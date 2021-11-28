package org.objects;

import javafx.scene.paint.Color;
import org.scenebuilder.scenebuilder.dummy.DummyGameToken;
import org.scenebuilder.scenebuilder.dummy.DummyInventory;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private String playerID;

    private Color color;
    private ArrayList<Token> gameTokens;
    private DummyInventory inventory;

    private boolean isHuman;

    public Player(String playerID, Color color, ArrayList<Token> gameTokens, DummyInventory inventory, boolean isHuman) {
        this.playerID = playerID;
        this.color = color;
        this.gameTokens = gameTokens;
        this.inventory = inventory;
        this.isHuman = isHuman;
    }

    public Player(String playerID, ArrayList<Token> gameTokens, DummyInventory inventory, boolean isHuman) {
        this.playerID = playerID;
        this.gameTokens = gameTokens;
        this.inventory = inventory;
        this.isHuman = isHuman;

        this.color = getRandomColor();
    }

    // setters
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    public void setColor(Color color) { this.color = color; }
    public void setGameTokens(ArrayList<Token> gameTokens) { this.gameTokens = gameTokens; }
    public void setInventory(DummyInventory inventory) { this.inventory = inventory; }
    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    // getters
    public String getPlayerID() {
        return this.playerID;
    }
    public Color getColor() { return this.color; }
    public ArrayList<Token> getGameTokens() { return this.gameTokens; }
    public DummyInventory getInventory() { return this.inventory; }
    public boolean getIsHuman() { return this.isHuman; }

    // modifiers
    public void addToken(Token token) { this.gameTokens.add(token); }
    public void removeToken(Token token) { this.gameTokens.remove(token); }
    public void removeToken(int tokenIndex) { this.gameTokens.remove(tokenIndex); }
    public void setToken(int tokenIndex, Token token) { this.gameTokens.set(tokenIndex, token); }

    public Color getRandomColor(){
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        return Color.rgb(red, green, blue, .99);
    }

    @Override
    public String toString() {
        return "DummyPlayer{" +
                "playerID='" + playerID + '\'' +
                ", color=" + color +
                ", gameTokens=" + gameTokens +
                ", inventory=" + inventory +
                ", isHuman=" + isHuman +
                '}';
    }
}
