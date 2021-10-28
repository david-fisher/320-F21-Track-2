import java.util.ArrayList;

public class BinaryStatement extends Statement {
    private String op;

    public BinaryStatement(String op, ArrayList<ArrayList<OpTree>> operands) {
        super.operands = operands;
        this.op = op;
    }

    public String getOperator() { return op; }
    public void setOperator(String op) { this.op = op; }

    public OpTree execute(GameState currState) {
        Object operand1 = ((Expression)super.getOperand(0).execute(currState)).getValue();
        Object operand2 = ((Expression)super.getOperand(1).execute(currState)).getValue();

        if (operand1 == null || operand2 == null) {
            System.out.println("Error: Something went wrong processing binary operation " + op);
            return null;
        }

        if (!(operand1 instanceof Integer && operand2 instanceof Integer)) {
            System.out.println("Error: Bad operand type for binary operation!");
            return null;
        }

        Integer op1 = (Integer)operand1;
        Integer op2 = (Integer)operand2;

        switch (op) {
            case "+":
                return new Expression<Integer>(op1 + op2);
            case "-":
                return new Expression<Integer>(op1 - op2);
            case "*":
                return new Expression<Integer>(op1 * op2);
            case "/":
                return new Expression<Integer>(op1 / op2);
            case "%":
                return new Expression<Integer>(op1 % op2);
            case ">":
                return (op1 > op2) ? new Expression<Integer>(1) : new Expression<Integer>(0);
            case "<":
                return (op1 < op2) ? new Expression<Integer>(1) : new Expression<Integer>(0);
            case "==":
                return (op1 == op2) ? new Expression<Integer>(1) : new Expression<Integer>(0);
            case "<=":
                return (op1 <= op2) ? new Expression<Integer>(1) : new Expression<Integer>(0);
            case ">=":
                return (op1 >= op2) ? new Expression<Integer>(1) : new Expression<Integer>(0);
            case "&&":
                return (op1 == 1 && op2 == 1) ? new Expression<Integer>(1) : new Expression<Integer>(0);
            case "||":
                return (op1 == 1 || op2 == 1) ? new Expression<Integer>(1) : new Expression<Integer>(0);
        }
        return null;
    }
}