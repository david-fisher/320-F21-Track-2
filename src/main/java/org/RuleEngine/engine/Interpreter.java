package org.RuleEngine.engine;
import java.util.ArrayList;

import org.GameObjects.objects.Player;
import org.RuleEngine.nodes.*;
import org.RuleEngine.oracles.*;

public class Interpreter {
    
    public static Interpreter instance;
    public Cache cache;
    public Oracle oracle;
    public ArrayList<String> prev;

    public Interpreter() {
        this.prev = new ArrayList<String>();
    }
    
    public static Interpreter getInstance() {
        if (instance == null) { instance = new Interpreter(); }
        return instance;
    }

    public String interpretAI(GameState currState, ArrayList<String> prev) {
        String choice = this.oracle.decideMove(currState, prev);
        return choice;
    }

    public void interpretRule(Node rule, GameState currState) {
        LiteralNode result = rule.execute(currState);
        if (result != null) 
		    System.out.println(result.getValue());
    }

    public void interpretEvent(ArrayList<Node> event, GameState currState) {
        System.out.println(((Player) currState.getRegistry("currPlayer")).getIsHuman());
        if (this.oracle == null) {
            if (currState.getAllEvents().get("heuristic") == null) {
                this.oracle = new RandomOracle();
            } else {
                this.oracle = new HeuristicOracle();
            }
        }
        for (int i = 0; i < event.size(); i++) {
            interpretRule(event.get(i), currState);
        }
        if (currState.getRegistry("winner") == null & ((Player) currState.getRegistry("currPlayer")).getIsHuman() == false) {
            interpretEvent(currState.getEvent(interpretAI(currState, this.prev)), currState);
        }
    }
    public void interpretEventNamed(String s, GameState currState) {
        ArrayList<Node> event = currState.getEvent(s);
        this.prev.add(s);
        System.out.println(((Player) currState.getRegistry("currPlayer")).getIsHuman());
        if (this.oracle == null) {
            if (currState.getAllEvents().get("heuristic") == null) {
                this.oracle = new RandomOracle();
            } else {
                this.oracle = new HeuristicOracle();
            }
        }
        for (int i = 0; i < event.size(); i++) {
            interpretRule(event.get(i), currState);
        }
        if (currState.getRegistry("winner") == null & ((Player) currState.getRegistry("currPlayer")).getIsHuman() == false) {
            interpretEventNamed(interpretAI(currState, this.prev), currState);
        }
    }
}