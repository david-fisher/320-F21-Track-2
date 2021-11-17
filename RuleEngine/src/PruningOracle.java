import java.util.ArrayList;
import java.util.Deque;

public class PruningOracle implements Oracle {
    public PruningOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public PruningOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return this.buttons.get(this.buttons.length*(Math.random()));
    }
}
