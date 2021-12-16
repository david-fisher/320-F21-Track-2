package org.RuleEngine.nodes;

import org.RuleEngine.engine.*;
import org.GameObjects.objects.*;

public class NodeUtil {
    private NodeUtil() {}
    
    public static GameObject processNodeToObj(LiteralNode<?> node, GameState currState) {
        Object val = node.getValue();
                
        if (val instanceof String) {
            String str = (String)val;
            if (str.charAt(0) == '_') {
                return currState.findObject(str.substring(1));
            } else {
                return currState.getRegistry(str);
            } 
        } else if (val instanceof GameObject) {
            return (GameObject)val;
        }
        System.out.println("Error: Cannot process node into GameObject.");
        return null;
    }
    
    public static void OperandError(OpNode node, int i) {
        System.out.println("Error: Failed to process operand " + i + " of " + node.getClass().getSimpleName());
    }
    public static void InputTypeError(OpNode node, int i, String type) {
        System.out.println("Error: The " + i + "th input of " + node.getClass().getSimpleName() + " has to be type " + type);
    }
    public static void OtherError(String msg) {
        System.out.println("Error: " + msg);
    }
}