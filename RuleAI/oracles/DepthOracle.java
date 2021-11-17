package oracles;
import java.util.ArrayList;
import java.util.Deque;

public class DepthOracle implements Oracle {
    public DepthOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public DepthOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return this.buttons.get(this.buttons.length*(Math.random()));
    }
}
