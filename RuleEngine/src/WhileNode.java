import java.util.ArrayList;

// Rule Groups based on IfNode class
// Requires similar testing

public class WhileNode extends OpNode {
    public WhileNode() { super(); }
    public WhileNode(ArrayList<ArrayList<Node>> operands) {
        super(operands);
    }

    public LiteralNode execute(GameState currState) {
        LiteralNode condition = getOperand(0).execute(currState);
        if (condition == null) { return null; }

        Boolean result;
        if (condition.getValue() instanceof Boolean) {
            result = (Boolean)condition.getValue();
        } else {
            System.out.println("Error: While statement can only take boolean expressions as conditions.");
            return null;
        }

        while (result) {
            if (hasValidRuleGroup(1)) {
                for (Node node : getRuleGroup(1)) {
                    node.execute(currState);
                }
            } else if (hasValidRuleGroup(2)){
                for (Node node : getRuleGroup(2)) {
                    node.execute(currState);
                }
            } else {
                return null;
            }
        }
        return null; 
    }
}