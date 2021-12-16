package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - The gamepiece
//        Operand 1 - The tile
public class MoveToNode extends OpNode {
    public MoveToNode() {
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
    
        GameObject piece = NodeUtil.processNodeToObj(op0, currState);
        GameObject tile = NodeUtil.processNodeToObj(op1, currState);
        
        if (piece == null || !(piece instanceof Gamepiece)) {
            NodeUtil.InputTypeError(this, 0, "Valid Gamepiece");
            return null;
        }
        if (tile == null || !(tile instanceof Tile)) {
            NodeUtil.InputTypeError(this, 1, "Valid Tile");
            return null;
        }
        
        if (!((Gamepiece)piece).setLocation((Tile)tile, currState)) {
            NodeUtil.OtherError("Cannot move gamepiece " + op0.getValue() + " to " + op1.getValue());
        }
        
        return null;
    }
    
    
}