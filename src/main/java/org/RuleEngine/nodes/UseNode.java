package org.RuleEngine.nodes;

import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.*;

// Usage: Operand 0 - The name of the die/spinner to be used.
public class UseNode extends OpNode {
    public UseNode() { 
        super();
        this.addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode op0 = getOperand(0).execute(currState);
        if (op0 == null) {
            NodeUtil.OperandError(this, 0);
            return null;
        }
        
        GameObject go = NodeUtil.processNodeToObj(op0, currState);
        
        if (go instanceof Die) {
            Integer result = ((Die)go).roll();
            // Display(result);
            return new LiteralNode<Integer>(result);
        } else if (go instanceof Spinner) {
            Category c = ((Spinner)go).spin();
            String result = c.getTrait("label").toString();
            // Display(result);
            return new LiteralNode<String>(result); 
        } else {
            NodeUtil.InputTypeError(this, 0, "Valid Die | Spinner");
        }
        return null;
    }
}