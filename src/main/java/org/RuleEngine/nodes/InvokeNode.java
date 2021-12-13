package org.RuleEngine.nodes;

import java.util.ArrayList;
import org.RuleEngine.engine.*;

// Usage: Operand 0: Name of the event to be invoked
public class InvokeNode extends OpNode {
    public InvokeNode() { 
        super();
        this.addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode op0 = getOperand(0).execute(currState);
        
        if (op0 == null || !(op0.getValue() instanceof String)) {
            NodeUtil.OtherError("Invoke only accepts a event name as input.");
            return null;
        }
        
        String name = (String)op0.getValue();
        ArrayList<Node> event = currState.getEvent(name);
        if (event == null) {
            NodeUtil.OtherError("No event of name " + name);
            return null;
        }
        Interpreter interpreter = new Interpreter();
        interpreter.interpretEvent(event, currState);
        return null;
    }
}