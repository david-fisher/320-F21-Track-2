package org.scenebuilder.scenebuilder;

import org.objects.Deck;

import java.util.ArrayList;

public class DummyGamestate {

    private ArrayList<DummyPlayer> players;
    private ArrayList<DummyTile> tiles;
    private ArrayList<Deck> decks;
    private ArrayList<DummyRNG> rng;

    // pieces that are not associated with players
    private ArrayList<DummyGameToken> gameTokens;

    public DummyGamestate(ArrayList<DummyPlayer> players, ArrayList<DummyTile> tiles, ArrayList<Deck> decks, ArrayList<DummyRNG> rng, ArrayList<DummyGameToken> gameTokens) {

        this.players = players;
        this.tiles = tiles;
        this.decks = decks;
        this.rng = rng;
        this.gameTokens = gameTokens;
    }

    // setters
    public void setPlayers(ArrayList<DummyPlayer> players) { this.players = players; }
    public void setTiles(ArrayList<DummyTile> tiles) { this.tiles = tiles; }
    public void setDecks(ArrayList<Deck> decks) { this.decks = decks; }
    public void setRNG(ArrayList<DummyRNG> rng) { this.rng = rng; }
    public void setGameTokens(ArrayList<DummyGameToken> gameTokens) { this.gameTokens = gameTokens; }

    // getters
    public ArrayList<DummyPlayer> getPlayers() { return this.players; }
    public ArrayList<DummyTile> getTiles() { return this.tiles; }
    public ArrayList<Deck> getDecks() { return this.decks; }
    public ArrayList<DummyRNG> getRNG() { return this.rng; }
    public ArrayList<DummyGameToken> getGameTokens() { return this.gameTokens; }
}
