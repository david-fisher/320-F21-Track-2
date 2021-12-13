package org.RuleEngine.nodes;
import java.util.ArrayList;

// The base class for any rule statements.
// operands: The operands of this rule statement. Can be either an Expression or another statement.
// NOTE: the parent of a node is only set if it is added via setOperand or setOperandInGroup.
public abstract class OpNode extends Node {
    
    protected ArrayList<ArrayList<Node>> operands;
    
    public OpNode() {
        operands = new ArrayList<ArrayList<Node>>();
        operands.add(new ArrayList<Node>());
    }

    // Check if a rule group exists at index i;
    public boolean hasValidRuleGroup(int i) {
        return (operands.size() > i) && (operands.get(i).size() > 0);
    }

    // Add a node to the first rule group (index 0). That is where the operands for non-control rules resides.
    public OpNode addOperand(Node operand) {
        return addOperandToGroup(operand, 0);
    }

    // Add a node to the group at index i. If no group is at index i yet, check if adding a new group can reach i.
    // If yes, add a new group and add the operand. Otherwise, give error. 
    public OpNode addOperandToGroup(Node operand, int i) {
        if (operands.size() <= i) {
            System.out.println(operands.size() + " " + i);
            System.out.println("Error: Cannot add to target rule group. Index out of bound!");
            return this;
        }
        if (operands.get(i) == null) {
            System.out.println("Error: No valid rule group at index " + i);
            return this;
        }
        operands.get(i).add(operand);
        return this;
    }
    
    // Manually add a group of rules to operands.
    protected OpNode addRuleGroup(ArrayList<Node> rules) {
        operands.add(rules);
        return this;
    }

    // Set the operand at index i in group 0.
    public OpNode setOperand(Node operand, int i) {
        return setOperandInGroup(operand, i, 0);
    }
    
    // Set the operand at index i in group groupId.
    public OpNode setOperandInGroup(Node operand, int i, int groupId) {
        if (isValidAt(i, groupId)) {
            if (operand != null) { operand.parent = this; }
            operands.get(groupId).set(i, operand);
        }
        return this;
    }
    
    // Remove the operand at index i in group 0.
    public Node removeOperand(int i) {
        return removeOperandInGroup(i, 0);
    }
    
    public boolean removeOperand(Node node) {
        for (int i = 0; i < operands.size(); i++) {
            ArrayList<Node> temp = getRuleGroup(i); 
            if (temp.contains(node)) {
                temp.set(temp.indexOf(node), null);
                node.parent = null;
                return true;
            }
        }
        return false;
    }
    
    // Remove the operand at index i in group groupId.
    public Node removeOperandInGroup(int i, int groupId) {
        if (isValidAt(i, groupId)) {
            Node node = getOperandInGroup(i, groupId);
            node.parent = null;
            setOperandInGroup(null, i, groupId);
            return node;
        } else {
            return null;
        }        
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
        return getOperandInGroup(i, 0);
    }
    
    public Node getOperandInGroup(int i, int groupId) {
        return isValidAt(i, groupId) ? operands.get(groupId).get(i) : null;
    }
    
    public boolean isValidAt(int i, int groupId) {
        if (operands.size() <= groupId) {
            System.out.println("Error: Cannot find to target rule group. Index out of bound!");
            return false;
        }
        if (operands.get(groupId) == null) {
            System.out.println("Error: No valid rule group at index " + i);
            return false;
        } 
        if (operands.get(groupId).size() <= i) {
            System.out.println("Error: Cannot find valid position at " + i + " of group " + groupId +", index out of bounds!");
            return false;
        }
        return true;
    }
    
    // Required for object persistence
    public ArrayList<ArrayList<Node>> getAllOperands() { return operands; }
    public void setAllOperands(ArrayList<ArrayList<Node>> operands) { this.operands = operands; }
}
