package oracles;
import java.util.ArrayList;
import java.util.Deque;
import engine.*; 
import nodes.*;

public class PruningOracle implements Oracle {
    public ArrayList<GameObject> buttons;
    public PruningOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public PruningOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return null;
    }
}
