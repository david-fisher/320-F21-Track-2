package org.RuleEngine.engine;
import java.util.ArrayList;
import org.RuleEngine.nodes.*;
import org.RuleEngine.oracles.*;

public class Interpreter {
    
    public static Interpreter instance;
    public Cache cache;
    public Oracle oracle;

    public Interpreter() {
    }
    
    public static Interpreter getInstance() {
        if (instance == null) { instance = new Interpreter(); }
        return instance;
    }

    public void interpretAI(GameState currState) {
        String choice = this.oracle.decideMove(currState);
        this.oracle.processMove(choice, currState);
    }

    public void interpretRule(Node rule, GameState currState) {
        LiteralNode result = rule.execute(currState);
        if (result != null) 
		    System.out.println(result.getValue());
    }

    public void interpretEvent(ArrayList<Node> event, GameState currState) {
        if (this.oracle == null) {
            if (currState.getAllEvents().get("heuristic") == null) {
                this.oracle = new FullOracle();
            } else {
                this.oracle = new HeuristicOracle();
            }
        }
        for(int i = 0; i < event.size(); i++) {
            interpretRule(event.get(i), currState);
        }
    }
}