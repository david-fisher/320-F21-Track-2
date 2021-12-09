package org.RuleEngine.nodes;

import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.*;

// Usage: Operand 0 - The name of the die/spinner to be used.
public class UseNode extends OpNode {
    public UseNode() { 
        super();
        this.addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        if (e1 == null || !(e1.getValue() instanceof String)) {
            System.out.println("Error: Use rule only takes in the name of a Dice or Spinner.");
        }
        
        String name = (String)e1.getValue();
        GameObject go = (name.charAt(0) == '_') ? go = currState.findObject(name) : currState.getRegistry(name);
        
        if (go instanceof Die) {
            Integer result = ((Die)go).roll();
            // Display(result);
            return new LiteralNode<Integer>(result);
        } else if (go instanceof Spinner) {
            Category c = ((Spinner)go).spin();
            String result = c.getTrait("label").toString();
            // Display(result);
            return new LiteralNode<String>(result); 
        } else {
            System.out.println("Error: Only a Die or Spinner can be used.");
        }
        return null;
    }
}