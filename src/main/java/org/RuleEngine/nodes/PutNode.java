package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - Card
//        Operand 1 - Deck
public class PutNode extends OpNode {
    
    private String placement;
    
    public PutNode() {
        super();
        placement = "random";
        addOperand(null).addOperand(null);
    }
    
    public String getPlacement() { return placement; }
    public void setPlacement(String placement) { this.placement = placement; }

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

        if (go0 == null || !(go0 instanceof Card)) {
            NodeUtil.InputTypeError(this, 0, "Card");
            return null;
        }
        if (go1 == null || !(go1 instanceof Deck)) {
            NodeUtil.InputTypeError(this, 1, "Deck");
            return null;
        }
        
        Card card = (Card)go0;
        Deck deck = (Deck)go1;
        
        switch(placement) {
            case "top":
                deck.replaceTop(card);
                break;
            case "bottom":
                deck.replaceBottom(card);
                break;
            case "random":
                deck.replaceRandom(card);
                break;
            default:
                NodeUtil.OtherError("Unknown placement.");
                break;
        }
        
        return null;
    }
    
    
}