package org.engine;
import java.util.ArrayList;
import org.nodes.*;

public class Interpreter {
    public void interpretRule(Node rule, GameState currState) {
        LiteralNode result = rule.execute(currState);
        if (result != null) 
		    System.out.println(result.getValue());
    }

    public void interpretEvent(ArrayList<Node> event, GameState currState) {
        for(int i = 0; i < event.size(); i++) {
            interpretRule(event.get(0), currState);
        }
    }
}