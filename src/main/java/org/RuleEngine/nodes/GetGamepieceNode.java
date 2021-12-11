package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - The index of the game piece.
//        Operand 1 - The source player. (GameObject label | Registry key | actual GameObject) 
public class GetGamepieceNode extends OpNode {
    public GetGamepieceNode() {
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
        
        if (!(op0.getValue() instanceof Integer)) {
            NodeUtil.InputTypeError(this, 0, "Integer");
            return null;
        }
        
        Integer index = (Integer)op0.getValue();
        Object obj = NodeUtil.processNodeToObj(op1, currState);
        Player player = obj instanceof Player ? (Player)obj : null;
        
        if (player == null) {
            NodeUtil.InputTypeError(this, 1, "Valid Player");
            return null;
        }
        if (index < 0 || index >= player.getGamePieces().size()) {
            NodeUtil.OtherError("There is no Gamepiece at index " + index);
            return null;
        }
        
        return new LiteralNode<Gamepiece>(player.getGamePieces().get(index));
    }
    
}