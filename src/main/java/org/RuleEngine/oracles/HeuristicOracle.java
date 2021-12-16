package org.RuleEngine.oracles;
import java.util.ArrayList;
import java.util.Map;

import org.RuleEngine.engine.*;
import org.GameObjects.objects.Button;
import org.RuleEngine.nodes.Node;

public class HeuristicOracle implements Oracle {
    public HeuristicOracle() {
    }

    public void processMove(String event, GameState currState) {
        currState.getAllEvents().get(event).get(0).execute(currState);
    }

    public String decideMove(GameState currState, ArrayList<String> prev) {
        return null;
    }
}
