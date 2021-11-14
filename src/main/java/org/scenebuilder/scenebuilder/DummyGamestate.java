package org.scenebuilder.scenebuilder;

import java.util.ArrayList;

public class DummyGamestate {

    private ArrayList<DummyPlayer> players;
    private ArrayList<DummyTile> tiles;
    private ArrayList<DummyDeck> decks;
    private ArrayList<DummyRNG> rng;

    public DummyGamestate(ArrayList<DummyPlayer> players, ArrayList<DummyTile> tiles, ArrayList<DummyDeck> decks, ArrayList<DummyRNG> rng) {

        this.players = players;
        this.tiles = tiles;
        this.decks = decks;
        this.rng = rng;
    }

    // setters
    public void setPlayers(ArrayList<DummyPlayer> players) { this.players = players; }
    public void setTiles(ArrayList<DummyTile> tiles) { this.tiles = tiles; }
    public void setDecks(ArrayList<DummyDeck> decks) { this.decks = decks; }
    public void setRNG(ArrayList<DummyRNG> rng) { this.rng = rng; }

    // getters
    public ArrayList<DummyPlayer> getPlayers() { return this.players; }
    public ArrayList<DummyTile> getTiles() { return this.tiles; }
    public ArrayList<DummyDeck> getDecks() { return this.decks; }
    public ArrayList<DummyRNG> getRNG() { return this.rng; }

}
