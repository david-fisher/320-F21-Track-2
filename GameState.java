public class GameState {
    public List<Tile> tiles;
    // TODO: Divide into sub lists of game pieces with filter functions?
    public List<GamePiece> gamePieces;
    public List<Spinner> spinners;
    public List<Die> dice;
    public List<Deck> decks;
    // TODO: Divide into sub lists of custom token types with label filtering?
    public List<Token> tokens;
    // NOTE: What is a timer?
    public List<Timer> timers;

    public Map<String, GamObject> registers;

    public GameState() {
        registers = new Map<String, GameObject>();
        registers.put("currPlayer", null);
    }
}