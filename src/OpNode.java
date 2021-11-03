import java.util.ArrayList;

// The base class for any rule statements.
// operator: Dictates the kind of rule this is. Used primarily for identification in the editor.
// operands: The operands of this rule statement. Can be either an Expression or another statement.
public abstract class OpNode implements Node {
    public ArrayList<ArrayList<Node>> operands;

    public OpNode(ArrayList<ArrayList<Node>> operands) {
        this.operands = operands;
    }

    public OpNode() {
        operands = new ArrayList<ArrayList<Node>>();
        operands.add(new ArrayList<Node>());
    }

// TODO: Implement methods to add rule group/operands

    // Returns a "block" or rules. Mainly used by control statements (e.g. if)
    public ArrayList<Node> getRuleGroup(int i) {
        if (operands.size() <= i) {
            System.out.println("Error: Attempting to access an out-of-bound rule group.");
            return null;
        }
        return operands.get(i);
    }

    // Return the operand at position i. Operands are always stored in list0.
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