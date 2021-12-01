package nodes;
import java.util.ArrayList;

// The base class for any rule statements.
// operands: The operands of this rule statement. Can be either an Expression or another statement.
public abstract class OpNode extends Node {

    // TODO: Is it better to, instead of having a 2d list for all operation node,
    // let each node have their own way of storing the children? May be doable with factory and visitors.
    // A future topic to discuss perhaps.
    protected ArrayList<ArrayList<Node>> operands;

    public OpNode(ArrayList<ArrayList<Node>> operands) {
        this.operands = operands;
    }

    public OpNode() {
        operands = new ArrayList<ArrayList<Node>>();
        operands.add(new ArrayList<Node>());
    }

    // Check if a rule group exists at index i;
    public boolean hasValidRuleGroup(int i) {
        return (operands.size() > i) && (operands.get(i).size() > 0);
    }

    // TODO: add remove operand/rule group functionality

    // Add a node to the first rule group (index 0). That is where the operands for non-control rules resides.
    public OpNode addOperand(Node operand) {
        if (operands.get(0) == null) {
            operands.add(new ArrayList<Node>());
        }
        operands.get(0).add(operand);
        return this;
    }

    // Add a node to the group at index i. If no group is at index i yet, check if adding a new group can reach i.
    // If yes, add a new group and add the operand. Otherwise, give error. 
    public OpNode addOperand(Node operand, int i) {
        if (operands.size() < i) {
            System.out.println("Error: Cannot add to target rule group. Index too far!");
            return this;
        }
        if (operands.size() == i) {
            operands.add(new ArrayList<Node>());
        }
        operands.get(i).add(operand);
        System.out.println("Operands size: " + operands.size());
        return this;
    }

    // TODO: test this
    // TODO: Probably need to have an overloaded version for any rule groups
    // Set the operand at index i at group 0.
    public OpNode setOperand(Node operand, int i) {
        if (operands.get(0) == null) {
            System.out.println("Error: No operands yet!");
            return this;
        } else if (operands.get(0).size() <= i) {
            System.out.println("Error: Cannot set operand at position " + i + " because it does not exist!");
            return this;
        } else {
            operands.get(0).set(i, operand);
            return this;
        }
    }

    // Manually add a group of rules to operands.
    public OpNode addRuleGroup(ArrayList<Node> rules) {
        operands.add(rules);
        return this;
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
    
    // Required for object persistence
    public ArrayList<ArrayList<Node>> getAllOperands() { return operands; }
    public void setAllOperands(ArrayList<ArrayList<Node>> operands) { this.operands = operands; }
}