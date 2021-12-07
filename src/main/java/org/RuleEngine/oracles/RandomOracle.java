package org.RuleEngine.oracles;
import java.util.ArrayList;

import org.RuleEngine.engine.*;
import org.GameObjects.objects.Button;

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
