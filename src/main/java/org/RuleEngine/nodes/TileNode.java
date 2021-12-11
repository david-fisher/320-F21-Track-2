package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - index of the Tile.
// TODO: Is this even necessary? 
public class TileNode extends OpNode {
    public TileNode() {
        super();
        addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        
        if (e1 == null || !(e1.getValue() instanceof Integer)) {
            System.out.println("Error: Tile operation takes an integer as input!");
        }
        
        Integer tIndex = (Integer)e1.getValue();
        
        if (tIndex < 0 || tIndex >= currState.tiles.size()) {
            System.out.println("Error: No tile at index " + tIndex);
        }
        
        return new LiteralNode<Tile>(currState.tiles.get(tIndex)); 
    }
}