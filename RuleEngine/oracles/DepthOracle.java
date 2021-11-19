package oracles;
import java.util.ArrayList;
import java.util.Deque;
import engine.*; 
import nodes.*;
import objects.*;

public class DepthOracle implements Oracle {
    public ArrayList<Button> buttons;
    public DepthOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public DepthOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return null;
    }
}
