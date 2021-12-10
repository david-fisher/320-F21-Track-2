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
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing Put operation");
            return null;
        }
    
        if (!(e1.getValue() instanceof String) || !(e2.getValue() instanceof String)) {
            System.out.println("Error: Put operation takes two strings as input!");
            return null;
        }
        
        String cardName = (String)e1.getValue();
        String deckName = (String)e2.getValue();
        GameObject card = (cardName.charAt(0) == '_') ? currState.findObject(cardName.substring(1)) : currState.getRegistry(cardName);
        GameObject deck = (deckName.charAt(0) == '_') ? currState.findObject(deckName.substring(1)) : currState.getRegistry(deckName);
        if (card == null || !(card instanceof Card)) {
            System.out.println("Error: Cannot find CARD of label " + cardName);
        }
        if (deck == null || !(deck instanceof Deck)) {
            System.out.println("Error: Cannot find DECK of label " + deckName);
        }
        
        switch(placement) {
            case "top":
                ((Deck)deck).replaceTop((Card)card);
                break;
            case "bottom":
                ((Deck)deck).replaceBottom((Card)card);
                break;
            case "random":
                ((Deck)deck).replaceRandom((Card)card);
                break;
            default:
                System.out.println("Error: Unknown placement.");
                break;
        }
        
        return null;
    }
    
    
}