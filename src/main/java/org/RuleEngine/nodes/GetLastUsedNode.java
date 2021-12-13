package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - The source player (GameObject label | Registry key | actual GameObject)
public class GetLastUsedNode extends OpNode {
    public GetLastUsedNode() {
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
        
        GameObject go = NodeUtil.processNodeToObj(op0, currState);
        Player player = null;
        if (go instanceof Player) {
            player = (Player)go;
        } else {
            NodeUtil.InputTypeError(this, 0, "Valid GameObject");
            return null;
        }
        
        return new LiteralNode<GameObject>(player.getLastUsedObj());
    }
    
}