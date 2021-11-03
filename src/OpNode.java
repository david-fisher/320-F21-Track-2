import java.util.ArrayList;

// The base class for any rule statements.
// operator: Dictates the kind of rule this is. Used primarily for identification in the editor.
// operands: The operands of this rule statement. Can be either an Expression or another statement.
public abstract class OpNode implements Node {
    protected String operator;
    public ArrayList<ArrayList<Node>> operands;

    public OpNode(String operator, ArrayList<ArrayList<Node>> operands) {
        this.operator = operator;
        this.operands = operands;
    }

    public OpNode(String operator) {
        this.operator = operator;
        operands = new ArrayList<ArrayList<Node>>();
        operands.add(new ArrayList<Node>());
    }

    public String getOperator() { return operator; }
    public void setOperator(String op) { operator = op; }

    public ArrayList<Node> getRuleGroup(int i) {
        if (operands.size() <= i) {
            System.out.println("Error: Attempting to access an out-of-bound rule group.");
            return null;
        }
        return operands.get(i);
    }

    public Node getOperand(int i) {
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