package org.scenebuilder.dummy;

public class DummyCard {

    private String cardID;

    public DummyCard(String cardID) {
        this.cardID = cardID;
    }

    // setters
    public void setCardID(String cardID) { this.cardID = cardID; }

    // getters
    public String getCardID() { return this.cardID; }
}
