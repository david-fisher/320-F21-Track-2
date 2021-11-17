package nodes;
import java.util.ArrayList;
import java.lang.reflect.Field;
import engine.GameState;

// Arithmetic-Logic Node. Performs basic arithmetic and logic operations on two child nodes.
public class ALNode extends OpNode {

    private String operator;
    
    public ALNode(String op) {
        super();
        operator = op;
    }

    public ALNode(String op, ArrayList<ArrayList<Node>> operands) {
        super(operands);
        operator = op;
    }

    public String getOperator() { return operator; }
    public void setOperator(String op) { operator = op; }

    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing binary operation " + operator);
            return null;
        }

        double compare; // placeholder for boolean comparison

        switch (operator) {
            case "+":
                return ALOperation.add(e1.getValue(), e2.getValue());

            case "-":
                return ALOperation.subtract(e1.getValue(), e2.getValue());

            case "*":
                return ALOperation.multiply(e1.getValue(), e2.getValue());

            case "/":
                return ALOperation.divide(e1.getValue(), e2.getValue());
            
            case "%":
                return ALOperation.modulo(e1.getValue(), e2.getValue());

            // factorial is a unary operator, but here's the code to compute it. might want to change the 
            //    operator though to something unique that isn't the logical-not operator
            // case "!":
            //     return ALOperation.factorial(e1.getValue());
            
            case ">":
                compare = ALOperation.arithmetic_compare(e1.getValue(), e2.getValue());
                return compare > 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);

            case "<":
                compare = ALOperation.arithmetic_compare(e1.getValue(), e2.getValue());
                return compare > 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);

            case "==":
                compare = ALOperation.arithmetic_compare(e1.getValue(), e2.getValue());
                return compare == 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);
            
            case "<=":
                compare = ALOperation.arithmetic_compare(e1.getValue(), e2.getValue());
                return compare <= 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);

            case ">=":
                compare = ALOperation.arithmetic_compare(e1.getValue(), e2.getValue());
                return compare >= 0 ? new LiteralNode<Boolean>(true) : new LiteralNode<Boolean>(false);
/*
            case "&&":
                if (op1 > 0 && op2 > 0) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
            case "||":
                if (op1 > 0 || op2 > 0) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
*/
        }
        return null;
    }
}
