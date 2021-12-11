package org.RuleEngine.nodes;
import org.RuleEngine.engine.GameState;

// Arithmetic-Logic Node. Performs basic arithmetic and logic operations on two child nodes.
// Usage: (Operand 0) op (Operand 1)
public class ALNode extends OpNode {

    private String operator;
    
    public ALNode(String op) {
        super();
        operator = op;
        this.addOperand(null).addOperand(null);
    }

    public String getOperator() { return operator; }
    public void setOperator(String op) { operator = op; }

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

        double compare; // placeholder for boolean comparison

        switch (operator) {
            case "+":
                return ALOperation.add(op0.getValue(), op1.getValue());

            case "-":
                return ALOperation.subtract(op0.getValue(), op1.getValue());

            case "*":
                return ALOperation.multiply(op0.getValue(), op1.getValue());

            case "/":
                return ALOperation.divide(op0.getValue(), op1.getValue());
            
            case "%":
                return ALOperation.modulo(op0.getValue(), op1.getValue());
            
            case ">":
                compare = ALOperation.arithmetic_compare(op0.getValue(), op1.getValue());
                return compare > 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);

            case "<":
                compare = ALOperation.arithmetic_compare(op0.getValue(), op1.getValue());
                return compare > 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);

            case "==":
                compare = ALOperation.arithmetic_compare(op0.getValue(), op1.getValue());
                return compare == 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);
            
            case "<=":
                compare = ALOperation.arithmetic_compare(op0.getValue(), op1.getValue());
                return compare <= 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);

            case ">=":
                compare = ALOperation.arithmetic_compare(op0.getValue(), op1.getValue());
                return compare >= 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);
                
            case "&&":
                return ALOperation.and(op0.getValue(), op1.getValue());
                
            case "||":
                return ALOperation.and(op0.getValue(), op1.getValue());
        }
        return null;
    }
}
