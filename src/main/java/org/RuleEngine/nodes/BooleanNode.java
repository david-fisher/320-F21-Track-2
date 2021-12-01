package org.RuleEngine.nodes;
import org.RuleEngine.engine.GameState;

// Probably will be merged with ALNode. 
public class BooleanNode extends OpNode {
    private String operator;
    
    public BooleanNode(String op) {
        super();
        operator = op;
        this.addOperand(null).addOperand(null);
    }
    
    public String getOperator() { return operator; }
    public void setOperator(String op) { operator = op; }
    
    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing binary operation " + operator);
            return null;
        }

        switch (operator) {
            case "||":  return BooleanOperation.or(e1.getValue(), e2.getValue());
            
            case "&&":  return BooleanOperation.and(e1.getValue(), e2.getValue());

            case "!": return BooleanOperation.not(e1.getValue(), e2.getValue());

            case "^":  return BooleanOperation.xor(e1.getValue(), e2.getValue());
        }
        return null;
    }
}
