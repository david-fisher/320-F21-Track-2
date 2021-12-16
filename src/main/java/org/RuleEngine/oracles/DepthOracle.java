package org.RuleEngine.oracles;
import java.util.ArrayList;
import org.RuleEngine.engine.*;

public class DepthOracle implements Oracle {
    public DepthOracle() {
    }

    public void processMove(String event, GameState currState) {
        currState.getAllEvents().get(event).get(0).execute(currState);
    }

    public String decideMove(GameState currState, ArrayList<String> prev) {
        return null;
    }
}
