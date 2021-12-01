package org.RuleEngine.oracles;
import java.util.ArrayList;

import org.RuleEngine.engine.*;
import org.GameObjects.objects.Button;

public class HeuristicOracle implements Oracle {
    public ArrayList<Button> buttons;
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
