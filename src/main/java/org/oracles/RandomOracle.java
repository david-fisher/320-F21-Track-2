package org.oracles;
import java.util.ArrayList;

import org.engine.*;
import org.objects.Button;

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
