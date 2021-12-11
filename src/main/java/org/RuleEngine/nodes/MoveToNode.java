package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Operand 0 - The gamepiece
//        Operand 1 - The tile
public class MoveToNode extends OpNode {
    public MoveToNode() {
        super();
        addOperand(null).addOperand(null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing MoveTo operation");
            return null;
        }
    
        if (!(e1.getValue() instanceof String) || !(e2.getValue() instanceof String)) {
            System.out.println("Error: MoveTo operation takes two strings as input!");
            return null;
        }
        
        String pieceName = (String)e1.getValue();
        String tileName = (String)e2.getValue();
        GameObject piece = (pieceName.charAt(0) == '_') ? currState.findObject(pieceName.substring(1)) : currState.getRegistry(pieceName);
        GameObject tile = (tileName.charAt(0) == '_') ? currState.findObject(tileName.substring(1)) : currState.getRegistry(tileName);
        if (piece == null || !(piece instanceof Gamepiece)) {
            System.out.println("Error: Cannot find GAMEPIECE of label " + pieceName);
        }
        if (tile == null || !(tile instanceof Tile)) {
            System.out.println("Error: Cannot find TILE of label " + tileName);
        }
        
        if (!((Gamepiece)piece).setLocation((Tile)tile)) {
            System.out.println("Error: Cannot move gamepiece " + pieceName + " to " + tileName);
        }
        
        return null;
    }
    
    
}