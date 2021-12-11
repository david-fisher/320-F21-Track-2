package org.RuleEngine.nodes;

import java.util.ArrayList;
import java.util.LinkedList;
import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.*;
import org.scenebuilder.Display;

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
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);
    
        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing MoveBy operation");
            return null;
        }
    
        if (!(e1.getValue() instanceof String) || !(e2.getValue() instanceof Integer)) {
            System.out.println("Error: MoveBy operation takes a string and an integer as input!");
            return null;
        }
        
        String name = (String)e1.getValue();
        Integer dis = (Integer)e2.getValue();
        GameObject go = (name.charAt(0) == '_') ? currState.findObject(name.substring(1)) : currState.getRegistry(name);
    
        if (go == null || !(go instanceof Gamepiece)) {
            System.out.println("Error: Cannot find GAMEPIECE of label " + name);
            return null;
        }
        
        // This will send a list of possible destinations to Minjex, who makes the user choose a destination.
        // The engine then promptly set the location of the gamepiece.
        // TODO AI: Configure this so it calls some other function if the player is AI.
        Tile playerChoice = Display.getDisplay().moveOptions(findTargetTiles((Tile)go.getTrait("location"), dis));
        Gamepiece gp = (Gamepiece) go;
        if (!gp.setLocation(playerChoice)) {
            System.out.println("Error: Failed to move object " + name);
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

        while(distances.peek() <= tDis) {
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
