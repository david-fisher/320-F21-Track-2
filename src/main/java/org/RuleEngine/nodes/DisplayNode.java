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
        LiteralNode op0 = getOperand(0).execute(currState);
        
        if (op0 == null) {
            NodeUtil.OperandError(this, 0);
            return null;
        }
        if (!(op0.getValue() instanceof String)) {
            NodeUtil.InputTypeError(this, 0, "String");
            return null;
        }
        
        Display.getDisplay().print(op0.getValue().toString());
        return null;
    }
    
    
}