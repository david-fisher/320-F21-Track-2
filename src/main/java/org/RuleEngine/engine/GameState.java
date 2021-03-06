package org.RuleEngine.engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.GameObjects.objects.*;
import org.RuleEngine.impossible.Game;
import org.RuleEngine.nodes.*;

public class GameState extends Savable {

    public String projectName;
    
    public org.GameEditor.application.GameBoard board;
    public ArrayList<Button> buttons;
    public ArrayList<Card> cards;
    public ArrayList<Deck> decks;
    public ArrayList<Die> dice; 
    public ArrayList<Gamepiece> gamepieces;
    public ArrayList<GameTimer> timers;
    public ArrayList<Spinner> spinners;
    public ArrayList<Tile> tiles;
    public ArrayList<Token> tokens;
    public ArrayList<Player> players;

    // Temporary GameObject list.
    public ArrayList<GameObject> gameObjects;

    public boolean tutorialEnabled;
    public int minPlayer, maxPlayer;
    
    public HashMap<String, GameObject> registers;
    public HashMap<String, ArrayList<Node>> events = new HashMap<String, ArrayList<Node>>();
    
    public GameState() {
        projectName = "New Project";
        minPlayer = 1;
        maxPlayer = 4;
        
        gameObjects = new ArrayList<GameObject>();
        buttons = new ArrayList<Button>();
        cards = new ArrayList<Card>();
        decks = new ArrayList<Deck>();
        dice = new ArrayList<Die>();
        gamepieces = new ArrayList<Gamepiece>();
        timers = new ArrayList<GameTimer>();
        spinners = new ArrayList<Spinner>();
        tiles = new ArrayList<Tile>();
        tokens = new ArrayList<Token>();
        players = new ArrayList<Player>();

        tutorialEnabled = false;
        registers = new HashMap<String, GameObject>();
        registers.put("winner", null);
        registers.put("currPlayer", null);
        events.put("heuristic", null);
	    events.put("initialization", null);
    }

    public GameState clone() {
        GameState state = new GameState();
        state.projectName = this.projectName;
        state.minPlayer = this.minPlayer;
        state.maxPlayer = this.maxPlayer;
        state.tutorialEnabled = this.tutorialEnabled;
        state.registers = new HashMap<String, GameObject>();
        for (Map.Entry<String, GameObject> e: this.registers.entrySet()) {
            state.registers.put(e.getKey(), e.getValue().clone());
        }
        state.events = this.events;

        // Individual stuff
        state.gameObjects = new ArrayList<GameObject>();
        for (GameObject x: this.gameObjects) {
            state.gameObjects.add(x.clone());
        }
        state.buttons = new ArrayList<Button>();
        for (Button x: this.buttons) {
            state.buttons.add(x.clone());
        }
        state.dice = new ArrayList<Die>();
        for (Die x: this.dice) {
            state.dice.add(x.clone());
        }
        state.timers = new ArrayList<GameTimer>();
        for (GameTimer x: this.timers) {
            state.timers.add(x.clone());
        }
        state.spinners = new ArrayList<Spinner>();
        for (Spinner x: this.spinners) {
            state.spinners.add(x.clone());
        }
        state.tokens = new ArrayList<Token>();
        for (Token x: this.tokens) {
            state.tokens.add(x.clone());
        }

        // Gamepieces and Tiles are intertwined
        // TODO: Reimplement references between both
        state.gamepieces = new ArrayList<Gamepiece>();
        for (Gamepiece x: this.gamepieces) {
            state.gamepieces.add(x.clone());
        }
        state.tiles = new ArrayList<Tile>();
        for (Tile x: this.tiles) {
            state.tiles.add(x.clone());
        }

        // Decks and Cards are intertwined
        // TODO: Reimplement references between both
        state.decks = new ArrayList<Deck>();
        for (Deck x: this.decks) {
            state.decks.add(x.clone());
        }
        state.cards = new ArrayList<Card>();
        for (Card x: this.cards) {
            state.cards.add(x.clone());
        }

        state.players = new ArrayList<Player>();
        for (Player x: this.players) {
            state.players.add(x.clone());
        }

        // TODO: Reimplement board reference to tiles
        //state.board = this.board.clone();

        return state;
    }

    public GameState cloneEvent(String event) {
        return null;
    }

    public GameObject getRegistry(String key) { return registers.get(key); }
    
    public void addRegistry(String key, GameObject obj) { registers.put(key, obj); }
    
    public boolean removeRegistry(String key) { return registers.remove(key) != null; }
    
    public ArrayList<Node> getEvent(String key) { return events.get(key); }
	  
	public void addEvent(String key, ArrayList<Node> obj) { events.put(key, obj); }
	
	public boolean removeEvent(String key) { return events.remove(key) != null; }

    public GameObject findObject(String label) {
    	/*
        for(GameObject go : gameObjects) {
            if (go.label.equals(label)) {
                return go;
            }
        }
    	*/
        for(Button go : buttons) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Card go : cards) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Deck go : decks) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Die go : dice) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Gamepiece go : gamepieces) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(GameTimer go : timers) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Spinner go : spinners) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Tile go : tiles) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Token go : tokens) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        for(Player go : players) {
            if (go.getTrait("label").equals(label)) { return go; }
        }
        return null;
    }

    public String repr() {
        String repr = "";
        TreeSet<String> sortedKeys = new TreeSet<String>(this.registers.keySet());
        for (String key: sortedKeys){
            repr = repr + key + '=' + this.registers.get(key).repr(false) + '\n';
        }
        return repr;
    }

    public boolean equals(GameState g0) {
        return this.toString().hashCode() == g0.toString().hashCode();
    }
    
    // For object persistence
    public String getProjectName() { return projectName; }
    public void setProjectName(String name) { projectName = name; }
    
    public int getMaxPlayer() { return maxPlayer; }
    public void setMaxPlayer(int num) { maxPlayer = num >= minPlayer ? num : minPlayer; }
    
    public int getMinPlayer() { return minPlayer; }
    public void setMinPlayer(int num) { minPlayer = num > 0 ? num : 1; }
    
    public org.GameEditor.application.GameBoard getGameBoard() { return board; }
    public void setGameBoard(org.GameEditor.application.GameBoard board) { this.board = board; }

    public ArrayList<Button> getAllButtons() { return buttons; }
    public void setAllButtons(ArrayList<Button> buttons) { this.buttons = buttons; }

    public ArrayList<Card> getAllCards() { return cards; }
    public void setAllCards(ArrayList<Card> cards) { this.cards = cards; }

    public ArrayList<Deck> getAllDecks() { return decks; }
    public void setAllDecks(ArrayList<Deck> decks) { this.decks = decks; }

    public ArrayList<Die> getAllDice() { return dice; } 
    public void setAllDice(ArrayList<Die> dice) { this.dice = dice; }

    public ArrayList<Gamepiece> getAllGamePieces() { return gamepieces; }
    public void setAllGamepieces(ArrayList<Gamepiece> gamepieces) { this.gamepieces = gamepieces; }

    public ArrayList<Player> getAllPlayers() { return players; }
    public void setAllPlayers(ArrayList<Player> players) { this.players = players; }

    public ArrayList<GameTimer> getAllTimers() { return timers; }
    public void setAllTimers(ArrayList<GameTimer> timers) { this.timers = timers; }

    public ArrayList<Spinner> getAllSpinners() { return spinners; }
    public void setAllSpinners(ArrayList<Spinner> spinners) { this.spinners = spinners; }

    public ArrayList<Tile> getAllTiles() { return tiles; }
    public void setAllTiles(ArrayList<Tile> tiles) { this.tiles = tiles; }

    public ArrayList<Token> getAllTokens() { return tokens; }
    public void setAllTokens(ArrayList<Token> tokens) { this.tokens = tokens; }
    
    public HashMap<String, GameObject> getAllRegisters() { return registers; }
    public void setAllRegisters(HashMap<String, GameObject> registers) { this.registers = registers; }

    public HashMap<String, ArrayList<Node>> getAllEvents() { return events; }
    public void setAllEvents(HashMap<String, ArrayList<Node>> allEvents) { events = allEvents; }

    public boolean getTutorialEnabled() { return tutorialEnabled; }
    public void setTutorialEnabled(boolean tutorialEnabled) { this.tutorialEnabled = tutorialEnabled; }
}