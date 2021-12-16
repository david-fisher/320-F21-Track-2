package org.RuleEngine.nodes;

import org.RuleEngine.engine.*;
import org.GameObjects.objects.GameObject;

// Usage: Operand 0 - property name
//        Operand 1 - source(register or gameobject)
//        Operand 2 - value
public class PSetNode extends OpNode {
    public PSetNode() {
        super();
        this.addOperand(null).addOperand(null).addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode op0 = getOperand(0).execute(currState);
        LiteralNode op1 = getOperand(1).execute(currState);
        LiteralNode op2 = getOperand(2).execute(currState);

        if (op0 == null) {
            NodeUtil.OperandError(this, 0);
            return null;
        }
        if (op1 == null) {
            NodeUtil.OperandError(this, 1);
            return null;
        }
        if (op2 == null) {
            NodeUtil.OperandError(this, 2);
            return null;
        }
        if (!(op0.getValue() instanceof String)) {
            NodeUtil.InputTypeError(this, 0, "String");
            return null;
        }

        String traitName = (String)op0.getValue();
        GameObject go = NodeUtil.processNodeToObj(op1, currState);
        if (go == null) {
            NodeUtil.InputTypeError(this, 1, "Valid GameObject");
            return null;
        }

        if (!go.setTrait(traitName, op2.getValue())) {
            NodeUtil.OtherError("Something went wrong when setting " + traitName + " property of " + op1.getValue() +
                    " to " + op2.getValue().toString());
        }

        return null;
    }
}