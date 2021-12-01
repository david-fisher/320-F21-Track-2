package org.RuleEngine.nodes.engine.oracles;

import org.GameObjects.objects.Button;
import org.RuleEngine.nodes.engine.GameState;

import java.util.ArrayList;

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
