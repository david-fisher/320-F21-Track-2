package org.RuleEngine.oracles;
import java.util.ArrayList;

import org.RuleEngine.engine.*;
import org.GameObjects.objects.Button;

public class PruningOracle implements Oracle {
    public ArrayList<Button> buttons;
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
