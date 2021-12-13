package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - The GameObject to be added.
//        Operand 1 - The target player 
public class PutInInventoryNode extends OpNode {
    public PutInInventoryNode() {
        super();
        addOperand(null).addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode op0 = getOperand(0).execute(currState);
        LiteralNode op1 = getOperand(1).execute(currState);
        
        if (op0 == null) {
            NodeUtil.OperandError(this, 0);
            return null;
        }
        if (op1 == null) {
            NodeUtil.OperandError(this, 1);
            return null;
        }
        
        GameObject go0 = NodeUtil.processNodeToObj(op0, currState);
        GameObject go1 = NodeUtil.processNodeToObj(op1, currState);
        
        if (go0 == null) {
            NodeUtil.InputTypeError(this, 0, "Valid GameObject");
            return null;
        }
        if (go1 == null || !(go1 instanceof Player)) {
            NodeUtil.InputTypeError(this, 0, "Valid Player");
            return null;
        }
        
        ((Player)go1).addInventory(go0);
        return null;
    }
}