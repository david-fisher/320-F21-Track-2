import java.util.ArrayList;

// The base class for any rule statements.
// operands: The operands of this rule statement. Can be either an Expression or another statement.
public abstract class OpNode implements Node {

    // TODO: Is it better to, instead of having a 2d list for all operation node,
    // let each node have their own way of storing the children? May be doable with factory and visitors.
    // A future topic to discuss perhaps.
    public ArrayList<ArrayList<Node>> operands;

    public OpNode(ArrayList<ArrayList<Node>> operands) {
        this.operands = operands;
    }

    public OpNode() {
        operands = new ArrayList<ArrayList<Node>>();
        operands.add(new ArrayList<Node>());
    }

    // TODO: Implement methods to add rule group/operands
    public boolean addOperand(Node operand) {
        if (operands.get(0) != null) {
            operands.get(0).add(operand);
            return true;
        }
        return false;
    }

    // Returns a "block" or rules. Mainly used by control statements (e.g. if).
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