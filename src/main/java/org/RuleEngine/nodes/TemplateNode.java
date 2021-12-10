package org.RuleEngine.nodes;

import org.RuleEngine.engine.GameState;

// TODO 6: Be sure to comment the usage of the node.
//         Use the format:
//         Operand 0 - X
//         Operand 1 - Y
public class TemplateNode extends OpNode {

    public TemplateNode() {
        super();
        // TODO 1: Initialize the operands based on the documentation.
        // Using addOperand(null), and addRuleGroup(new ArrayList<Node>) if needed.
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        // TODO 2: Execute all operands and store the result LiteralNode in local variables.
        
        // TODO 3: Check if the results from execution are null. 
        // If not null, check if the value of the LiteralNode is the type needed.
        
        // TODO 4: Extract the values from the LiteralNode and compute the value needed.
        
        // TODO 5: Wrap the computed value in a LiteralNode and return.
        //         Or if no value needs to be returned, return null.
        return null;
    }
    
}