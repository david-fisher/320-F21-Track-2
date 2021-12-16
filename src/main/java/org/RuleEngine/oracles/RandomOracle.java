package org.RuleEngine.oracles;

import org.RuleEngine.engine.GameState;

import java.util.ArrayList;
import java.util.Random;

public class RandomOracle implements Oracle {
    public Cache cache;
    public RandomOracle() {
        this.cache = new Cache(1000000);
    }

    public void processMove(String event, GameState currState) {
        currState.getAllEvents().get(event).get(0).execute(currState);
    }

    public String decideMove(GameState currState, ArrayList<String> prev) {
        Random rand = new Random();
        ArrayList<String> keyArray = new ArrayList<String>(currState.getAllEvents().keySet());
        String s = keyArray.get(rand.nextInt(keyArray.size()));
        while (prev.indexOf("A"+s) != -1 || prev.indexOf("H"+s) != -1 || !s.endsWith("Event")) {
            s = keyArray.get(rand.nextInt(keyArray.size()));
        }
        System.out.println("AI Chooses " + s);
        return s;
    }
}
