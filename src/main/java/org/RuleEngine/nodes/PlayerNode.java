package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

public class PlayerNode extends OpNode {
    public PlayerNode() {
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
        
        Integer index = (Integer)op0.getValue();
        if (index < 0 || index >= currState.players.size()) {
            NodeUtil.OtherError("Index of player out of bound for PlayerNode!");
            return null;
        }
        
        return new LiteralNode<Player>(currState.players.get(index));
    }
}