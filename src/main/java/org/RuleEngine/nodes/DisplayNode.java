package org.RuleEngine.nodes;

import org.RuleEngine.engine.GameState;
import org.GamePlay.Display;

// Usage: Operand 0 - String to display
public class DisplayNode extends OpNode {
    public DisplayNode() {
        super();
        addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        
        if (e1 == null || !(e1.getValue() instanceof String)) {
            System.out.println("Error: Display operation takes a string as input!");
        }
        
        String message = (String)e1.getValue();
        Display.getDisplay().print(message);
        return null;
    }
    
    
}