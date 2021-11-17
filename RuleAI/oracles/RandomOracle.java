package oracles;
import java.util.ArrayList;
import java.util.Deque;

public class RandomOracle implements Oracle {
    public RandomOracle(GameState g) {
        this.buttons = g.buttons;
    }

    public RandomOracle(Oracle o) {

    }

    public void processMove(Action a) {
    }

    public Action decideMove() {
        return this.buttons.get(this.buttons.length*(Math.random()));
    }
}
