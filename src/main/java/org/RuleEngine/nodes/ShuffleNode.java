package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - Deck to shuffle
public class ShuffleNode extends OpNode {
    public ShuffleNode() {
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
        }
        
        GameObject go = NodeUtil.processNodeToObj(op0, currState);
        
        if (go == null || !(go instanceof Deck)) {
            NodeUtil.InputTypeError(this, 0, "Valid Deck");
            return null;
        }
        
        ((Deck)go).shuffle();
        return null;
    }
    
}
