package org.RuleEngine.nodes.engine.oracles;

import org.GameObjects.objects.Button;
import org.RuleEngine.nodes.engine.GameState;

import java.util.ArrayList;

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
