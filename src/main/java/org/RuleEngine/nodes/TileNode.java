package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - index of the Tile.
public class TileNode extends OpNode {
    public TileNode() {
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
        if (!(op0.getValue() instanceof Integer)) {
            NodeUtil.InputTypeError(this, 0, "Integer");
            return null;
        }
        
        Integer tIndex = (Integer)op0.getValue();
        
        if (tIndex < 0 || tIndex >= currState.tiles.size()) {
            NodeUtil.OtherError("No tile at index " + tIndex);
        }
        
        return new LiteralNode<Tile>(currState.tiles.get(tIndex)); 
    }
}
