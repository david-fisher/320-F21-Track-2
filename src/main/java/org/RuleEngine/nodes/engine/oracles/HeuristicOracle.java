package org.RuleEngine.nodes.engine.oracles;

import org.GameObjects.objects.Button;
import org.RuleEngine.nodes.engine.GameState;

import java.util.ArrayList;

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
