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
        LiteralNode e1 = getOperand(0).execute(currState);
        if (e1 == null || !(e1.getValue() instanceof String)) {
            System.out.println("Error: Shuffle operation takes a string as input!");
        }
        
        String deckName = (String)e1.getValue();
        GameObject deck = (deckName.charAt(0) == '_') ? currState.findObject(deckName.substring(1)) : currState.getRegistry(deckName);
        
        if (deck == null) {
            System.out.println("Error: Cannot find deck of label " + deckName);
        }
        
        ((Deck)deck).shuffle();
        return null;
    }
    
}