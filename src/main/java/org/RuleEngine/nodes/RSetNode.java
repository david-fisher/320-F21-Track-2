package org.RuleEngine.nodes;

import org.GameObjects.objects.Player;
import org.GamePlay.Display;
import org.RuleEngine.engine.*;
import org.GameObjects.objects.GameObject;

// Usage: Operand 0 - target (register name)
//        Operand 1 - source name (register or gameobject)
public class RSetNode extends OpNode {
    public RSetNode() { 
        super();
        this.addOperand(null).addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode op0 = getOperand(0).execute(currState);
        LiteralNode op1 = getOperand(1).execute(currState);

        if (op0 == null) {
            NodeUtil.OperandError(this, 0);
            return null;
        }
        if (op1 == null) {
            NodeUtil.OperandError(this, 1);
            return null;
        }
        if (!(op0.getValue() instanceof String)) {
            NodeUtil.InputTypeError(this, 0, "String");
            return null;
        }
        
        String registryName = (String)op0.getValue();
        GameObject go = NodeUtil.processNodeToObj(op1, currState);
        
        if (go == null) {
            NodeUtil.InputTypeError(this, 1, "Valid GameObject");
            return null;
        }
        if (!currState.registers.containsKey(registryName)) {
            NodeUtil.OtherError("Registry " + registryName + "does not exist and cannot be set!");
            return null;
        }

        //Update currPlayer for turn indicator
        if (registryName.equals("currPlayer")) {
            Display.getDisplay().updateCurrPlayer((Player) go);
        }

        //Catch winning condition met
        if (registryName.equals("winner")) {
            Display.getDisplay().displayWinnerPopup((Player) go);
        }

        currState.registers.put(registryName, go);

        return null;
    }
}