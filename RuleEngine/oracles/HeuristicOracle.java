package oracles;
import java.util.ArrayList;
import java.util.Deque;
import engine.*; 
import nodes.*;

public class HeuristicOracle implements Oracle {
    public ArrayList<GameObject> buttons;
    public HeuristicOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public HeuristicOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return null;
    }
}
