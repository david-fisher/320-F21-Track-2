package org.engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import org.objects.*;
import org.nodes.*;

public class GameState {

    public GameBoard board;
    public ArrayList<Button> buttons;
    public ArrayList<Card> cards;
    public ArrayList<Deck> decks;
    public ArrayList<Die> dice; 
    public ArrayList<Gamepiece> gamepieces;
    public ArrayList<GameTimer> timers;
    public ArrayList<Spinner> spinners;
    public ArrayList<Tile> tiles;
    public ArrayList<Token> tokens;

    // Temporary GameObject list.
    public ArrayList<GameObject> gameObjects;

    public HashMap<String, GameObject> registers;
    public static HashMap<String, ArrayList<Node>> events;

    public GameState() {
        gameObjects = new ArrayList<GameObject>();
        registers = new HashMap<String, GameObject>();
        registers.put("currPlayer", null);
    }

    public GameObject getRegistry(String key) {
        return registers.get(key);
    }
    
    public void addRegistry(String key, GameObject obj) {
    		registers.put(key, obj);
    }
    
    public boolean removeRegistry(String key) {
    	return registers.remove(key) != null;
    }
    
    public ArrayList<Node> getEvent(String key) {
      return events.get(key);
	  }
	  
	  public void addEvent(String key, ArrayList<Node> obj) {
	  		events.put(key, obj);
	  }
	  
	  public boolean removeEvent(String key) {
	  	return events.remove(key) != null;
	  }

    public GameObject findObject(String label) {
        for(GameObject go : gameObjects) {
            if (go.label.equals(label)) {
                return go;
            }
        }
        return null;
    }

    public String toString() {
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
    public GameBoard getGameBoard() { return board; }
    public void setGameBoard(GameBoard board) { this.board = board; }

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

    public static HashMap<String, ArrayList<Node>> getAllEvents() { return events; }
    public static void setAllEvents(HashMap<String, ArrayList<Node>> allEvents) { events = allEvents; }
}