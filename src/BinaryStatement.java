import java.util.ArrayList;

public class BinaryStatement extends Statement {

    public BinaryStatement(String op) {
        super(op, new ArrayList<ArrayList<OpTree>>());
        operands.add(new ArrayList<OpTree>());
    }

    public BinaryStatement(String op, ArrayList<ArrayList<OpTree>> operands) {
        super(op, operands);
    }

    public Expression execute(GameState currState) {
// FIXME: Devise a way to cast the return type properly.
/*
        var operand1 = getOperand(0).execute(currState).getValue();
        var operand2 = getOperand(1).execute(currState).getValue();

        if (operand1 == null || operand2 == null) {
            System.out.println("Error: Something went wrong processing binary operation " + operator);
            return null;
        }

        if (!(operand1 instanceof Double && operand2 instanceof Double)) {
            System.out.println("Error: Bad operand type for binary operation!");
            return null;
        }

        Double op1 = (Double)operand1;
        Double op2 = (Double)operand2;
        Expression<Double> result = new Expression<Double>(null);
*/

        var operand1 = Integer.valueOf(1);
        var operand2 = Double.valueOf(5.0);
        switch (operator) {
            case "+":
                return BinaryOperation.add(operand1, operand2);
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