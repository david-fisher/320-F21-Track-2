import java.util.ArrayList;
import java.util.Deque;

public class HeuristicOracle implements Oracle {
    public HeuristicOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public HeuristicOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return this.buttons.get(this.buttons.length*(Math.random()));
    }
}
