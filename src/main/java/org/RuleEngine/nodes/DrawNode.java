package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - Deck to draw from
public class DrawNode extends OpNode {
    
    private String placement;
    
    public DrawNode() {
        super();
        placement = "random";
        addOperand(null);
    }
    
    public String getPlacement() { return placement; }
    public void setPlacement(String placement) { this.placement = placement; }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        if (e1 == null || !(e1.getValue() instanceof String)) {
            System.out.println("Error: Draw operation takes a string as input!");
        }
        
        String deckName = (String)e1.getValue();
        GameObject deck = (deckName.charAt(0) == '_') ? currState.findObject(deckName.substring(1)) : currState.getRegistry(deckName);
        
        if (deck == null) {
            System.out.println("Error: Cannot find deck of label " + deckName);
        }
        
        switch(placement) {
            case "top":
                return new LiteralNode<Card>(((Deck)deck).drawTop());
            case "bottom":
                return new LiteralNode<Card>(((Deck)deck).drawBottom());
            case "random":
                return new LiteralNode<Card>(((Deck)deck).drawRandom());
            default:
                System.out.println("Error: Unknown placement: " + placement);
        }
        
        return null;
    }
    
    
}