package org.scenebuilder.scenebuilder;

import java.util.ArrayList;

public class DummyDeck {

    private String deckID;

    private ArrayList<DummyCard> cards;

    // assuming decks can be placed on the board
    private int width;
    private int height;

    private int xPos;
    private int yPos;

    public DummyDeck(String deckID, ArrayList<DummyCard> cards, int width, int height, int xPos, int yPos) {
        this.deckID = deckID;
        this.cards = cards;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // setters
    public void setDeckID(String deckID) { this.deckID = deckID; }
    public void setCards(ArrayList<DummyCard> cards) { this.cards = cards; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setXPos(int xPos) { this.xPos = xPos; }
    public void setYPos(int yPos) { this.yPos = yPos; }

    // getters
    public String getDeckID() { return this.deckID; }
    public ArrayList<DummyCard> getCards() { return this.cards; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getXPos() { return xPos; }
    public int getYPos() { return yPos; }
}
