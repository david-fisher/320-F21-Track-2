import java.util.ArrayList;

// The base class for any rule statements.
// operator: Dictates the kind of rule this is. Used primarily for identification in the editor.
// operands: The operands of this rule statement. Can be either an Expression or another statement.
public abstract class Statement implements OpTree {
    protected String operator;
    public ArrayList<ArrayList<OpTree>> operands;

    public Statement(String operator, ArrayList<ArrayList<OpTree>> operands) {
        this.operator = operator;
        this.operands = operands;
    }

    public String getOperator() { return operator; }
    public void setOperator(String op) { operator = op; }

    public ArrayList<OpTree> getRuleGroup(int i) {
        if (operands.size() <= i) {
            System.out.println("Error: Attempting to access an out-of-bound rule group.");
            return null;
        }
        return operands.get(i);
    }

    public OpTree getOperand(int i) {
        if (operands.size() < 1) {
            System.out.println("Error: No operands in rule statement.");
            return null;
        }
        if (operands.get(0).size() <= i) {
            System.out.println("Error: Attempting to access an out-of-bound rule operand.");
            return null;
        }
        return operands.get(0).get(i);
    }
}