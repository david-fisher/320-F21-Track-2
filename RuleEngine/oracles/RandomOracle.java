package oracles;
import java.util.ArrayList;
import java.util.Deque;
import engine.*; 
import nodes.*;
import objects.*;

public class RandomOracle implements Oracle {
    public ArrayList<Button> buttons;
    public RandomOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public RandomOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return null;
    }
}
