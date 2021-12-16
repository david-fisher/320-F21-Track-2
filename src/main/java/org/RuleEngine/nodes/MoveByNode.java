package org.RuleEngine.nodes;

import java.util.ArrayList;
import java.util.LinkedList;
import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.*;
import org.GamePlay.Display;

// Usage: Operand 0 - game piece name.
//		  Operand 1 - distance (LiteralNode<Integer>)
public class MoveByNode extends OpNode {
    public MoveByNode() { 
        super();
        addOperand(null).addOperand(null);
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
    
        if (!(op1.getValue() instanceof Integer)) {
            NodeUtil.InputTypeError(this, 1, "Integer");
            return null;
        }
        
        Integer dis = (Integer)op1.getValue();
        GameObject go = NodeUtil.processNodeToObj(op0, currState);
    
        if (go == null || !(go instanceof Gamepiece)) {
            NodeUtil.InputTypeError(this, 0, "Valid Gamepiece");
            return null;
        }
        
        // This will send a list of possible destinations to Minjex, who makes the user choose a destination.
        // The engine then promptly set the location of the gamepiece.
        // TODO AI: Configure this so it calls some other function if the player is AI.
        // TODO restore
        // Tile playerChoice = Display.getDisplay().moveOptions(findTargetTiles((Tile)go.getTrait("location"), dis));
        ArrayList<Tile> targets = findTargetTiles((Tile)go.getTrait("location"), dis);
        System.out.println(targets.get(0).getTrait("onLand"));
        if (targets.size() > 0) {
            Tile playerChoice = targets.get(0);
            Gamepiece gp = (Gamepiece) go;
            if (!gp.setLocation(playerChoice, currState)) {
                NodeUtil.OtherError("Failed to move object " + op0.getValue());
            } else {
            }
        }
        return null;
	}
	
	// This method returns a list of reachable tiles at distance tDis from the tile t.
	// Only tiles at the exact distance is included. If possible path is shorter than tDis, the end of that path
	// is not included.
    private ArrayList<Tile> findTargetTiles(Tile t, Integer tDis) {
        LinkedList<Tile> tiles = new LinkedList<Tile>();
        ArrayList<Tile> processed = new ArrayList<Tile>();
        LinkedList<Integer> distances = new LinkedList<Integer>();
        ArrayList<Tile> targets = new ArrayList<Tile>();
        tiles.add(t);
        distances.add(0);

        while(distances.size() > 0 && distances.peek() <= tDis) {
            Integer currDis = distances.poll();
            Tile currTile = tiles.poll();
            processed.add(currTile);
            for (Tile c : currTile.getConnect()) {
                if (tiles.indexOf(c) < 0 && processed.indexOf(c) < 0) {
                    distances.add(currDis+1);
                    tiles.add(c);
                }
            }
            if (currDis == tDis) { targets.add(currTile); }
        }
        return targets;
    }
}
