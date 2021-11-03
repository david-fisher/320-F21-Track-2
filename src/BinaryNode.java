import java.util.ArrayList;
import java.lang.reflect.Field;

public class BinaryNode extends OpNode {

    public BinaryNode(String op) {
        super(op);
    }

    public BinaryNode(String op, ArrayList<ArrayList<Node>> operands) {
        super(op, operands);
    }

    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing binary operation " + operator);
            return null;
        }

        switch (operator) {
            case "+":
                return BinaryOperation.add(e1.getValue(), e2.getValue());
/*
            case "-":
                result.setValue(op1 - op2);
                break;
            case "*":
                result.setValue(op1 * op2);
                break;
            case "/":
                result.setValue(op1 / op2);
                break;
            case "%":
                result.setValue(Double.valueOf(op1.intValue() % op2.intValue()));
                break;
            case ">":
                if (op1 > op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
            case "<":
                if (op1 < op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
            case "==":
                if (op1 - op2 < 0.001) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
            case "<=":
                if (op1 <= op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
            case ">=":
                if (op1 >= op2) { result.setValue(1.0); } else { result.setValue(-1.0); }
                break;
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