package org.oracles;
import java.util.ArrayList;

import org.engine.*;
import org.objects.Button;

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
