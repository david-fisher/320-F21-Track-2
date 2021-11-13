import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GameState {
/*
    public GameBoard board;
    public List<Tile> tiles;
    // TODO: Divide into sub lists of game pieces with filter functions?
    public List<GamePiece> gamePieces;
    public List<Spinner> spinners;
    public List<Die> dice;
    public List<Card> cards;
    public List<Deck> decks;
    // TODO: Divide into sub lists of custom token types with label filtering?
    public List<Token> tokens;
    // NOTE: What is a timer?
    public List<GameTimer> timers;
*/
    // Temporary GameObject list.
    public ArrayList<GameObject> gameObjects;

    public Map<String, GameObject> registers;

    public GameState() {
        gameObjects = new ArrayList<GameObject>();
        registers = new HashMap<String, GameObject>();
        registers.put("currPlayer", null);
    }

    public GameObject getRegistry(String key) {
        return registers.get(key);
    }

    public GameObject findObject(String label) {
        for(GameObject go : gameObjects) {
            if (go.label.equals(label)) {
                return go;
            }
        }
        return null;
    }
}