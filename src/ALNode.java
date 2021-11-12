import java.util.ArrayList;
import java.lang.reflect.Field;

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

            // case ">":
            //     if (op1 > op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
            //     break;
            // case "<":
            //     if (op1 < op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
            //     break;

            // TODO: what are we trying to return here? A LiteralNode or a BooleanNode?
            // case "==":
            //     LiteralNode compare = ALOperation.arithmetic_compare(e1.getValue(), e2.getValue());
            
            // case "<=":
            //     if (op1 <= op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
            //     break;
            // case ">=":
            //     if (op1 >= op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
            //     break;
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