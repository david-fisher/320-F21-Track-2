package org.RuleEngine.oracles;

import org.RuleEngine.engine.GameState;

import java.util.ArrayList;

public class FullOracle implements Oracle {
    public Cache cache;
    public FullOracle() {
        this.cache = new Cache(1000000);
    }

    public void processMove(String event, GameState currState) {
        currState.getAllEvents().get(event).get(0).execute(currState);
    }

    public String decideMove(GameState currState, ArrayList<String> prev) {
        return null;
    }
}
