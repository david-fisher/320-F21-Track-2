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
        if (e1 == null) {
            NodeUtil.OperandError(this, 0);
            return null;
        }
        
        GameObject go = NodeUtil.processNodeToObj(e1, currState);
        
        if (go == null || !(go instanceof Deck)) {
            NodeUtil.InputTypeError(this, 0, "Deck");
            return null;
        }
        
        Deck deck = (Deck)go;
        
        switch(placement) {
            case "top":
                return new LiteralNode<Card>(deck.drawTop());
            case "bottom":
                return new LiteralNode<Card>(deck.drawBottom());
            case "random":
                return new LiteralNode<Card>(deck.drawRandom());
            default:
                System.out.println("Error: Unknown placement: " + placement);
        }
        
        return null;
    }
    
    
}