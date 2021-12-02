package org.scenebuilder.dummy;

import org.GameObjects.objects.Deck;
import org.GameObjects.objects.Die;
import org.GameObjects.objects.Spinner;

import java.util.ArrayList;

public class DummyGamestate {

    private ArrayList<DummyPlayer> players;
    private ArrayList<DummyTile> tiles;
    private ArrayList<Deck> decks;
    private ArrayList<Die> dice;
    private ArrayList<Spinner> spinners;

    // pieces that are not associated with players
    private ArrayList<DummyGameToken> gameTokens;

    public DummyGamestate(ArrayList<DummyPlayer> players, ArrayList<DummyTile> tiles, ArrayList<Deck> decks, ArrayList<Die> dice, ArrayList<Spinner> spinners, ArrayList<DummyGameToken> gameTokens) {

        this.players = players;
        this.tiles = tiles;
        this.decks = decks;
        this.dice = dice;
        this.spinners = spinners;
        this.gameTokens = gameTokens;
    }

    // setters
    public void setPlayers(ArrayList<DummyPlayer> players) { this.players = players; }
    public void setTiles(ArrayList<DummyTile> tiles) { this.tiles = tiles; }
    public void setDecks(ArrayList<Deck> decks) { this.decks = decks; }
    public void setDice(ArrayList<Die> dice) { this.dice = dice; }
    public void setSpinners(ArrayList<Spinner> spinners) { this.spinners = spinners; }
    public void setGameTokens(ArrayList<DummyGameToken> gameTokens) { this.gameTokens = gameTokens; }

    // getters
    public ArrayList<DummyPlayer> getPlayers() { return this.players; }
    public ArrayList<DummyTile> getTiles() { return this.tiles; }
    public ArrayList<Deck> getDecks() { return this.decks; }
    public ArrayList<Die> getDice() { return this.dice; }
    public ArrayList<Spinner> getSpinners() { return this.spinners; }
    public ArrayList<DummyGameToken> getGameTokens() { return this.gameTokens; }
}
