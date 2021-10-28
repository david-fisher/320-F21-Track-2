import java.util.ArrayList;

public abstract class Statement implements OpTree {
    public ArrayList<ArrayList<OpTree>> operands;

    public ArrayList<OpTree> getRuleGroup(int i) {
        if (operands.size() <= i) {
            System.out.println("Error: Attempting to access an out-of-bound rule group.");
            return null;
        }
        return operands.get(i);
    }

    public OpTree getOperand(int i) {
        if (operands.size() < 1) {
            System.out.println("Error: Attempting to access an out-of-bound rule group.");
            return null;
        }
        if (operands.get(0).size() <= i) {
            System.out.println("Error: Attempting to access an out-of-bound rule operand.");
            return null;
        }
        return operands.get(0).get(i);
    }
}