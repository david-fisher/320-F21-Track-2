package org.RuleEngine.engine;
import java.util.ArrayList;
import org.RuleEngine.nodes.*;

public class Interpreter {
    
    public static Interpreter instance;
    
    public static Interpreter getInstance() {
        if (instance == null) { instance = new Interpreter(); }
        return instance;
    }
    
    public void interpretRule(Node rule, GameState currState) {
        LiteralNode result = rule.execute(currState);
        if (result != null) 
		    System.out.println(result.getValue());
    }

    public void interpretEvent(ArrayList<Node> event, GameState currState) {
        for(int i = 0; i < event.size(); i++) {
            interpretRule(event.get(i), currState);
        }
    }
}