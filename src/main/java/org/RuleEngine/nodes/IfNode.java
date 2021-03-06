package org.RuleEngine.nodes;
import java.util.ArrayList;
import org.RuleEngine.engine.GameState;

// TODO: This works, but needs more testing.
//       1. Executing a block when there are multiple rules in that block.
//       2. Evaluate complex conditions.

// Usage: Rule group 0 - condition (operand 0)
//        Rule group 1 - true block
//        Rule gorup 2 - false block
public class IfNode extends OpNode {
    public IfNode() { 
        super(); 
        this.addOperand(null);
        this.addRuleGroup(new ArrayList<Node>()).addRuleGroup(new ArrayList<Node>());
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode condition = getOperand(0).execute(currState);
        if (condition == null) { return null; }

        Boolean result;
        if (condition.getValue() instanceof Boolean) {
            result = (Boolean)condition.getValue();
        } else {
            NodeUtil.OtherError("If statement can only take boolean expressions as conditions.");
            return null;
        }

        if (result && hasValidRuleGroup(1)) {
            for (Node node : getRuleGroup(1)) {
                node.execute(currState);
            }
        } else if (hasValidRuleGroup(2)) {
            for (Node node : getRuleGroup(2)) {
                node.execute(currState);
            }
        }

        return null;
    }
}