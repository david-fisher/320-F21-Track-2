package nodes;

import java.util.ArrayList;
import java.util.LinkedList;
import engine.GameState;
import objects.*;

// Usage: Operand 0 - game piece name (or, more generally, any gameobject with a location trait of type Tile).
//				Operand 1 - distance (LiteralNode<Integer>)
public class MoveNode extends OpNode {
    public MoveNode() { super(); }
    public MoveNode(ArrayList<Node> operands) {
        super();
        this.operands.set(0, operands);
    }

	@Override
	public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);
    
        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing rset operation");
            return null;
        }
    
        if (!(e1.getValue() instanceof String) || !(e2.getValue() instanceof Integer)) {
            System.out.println("Error: rset operation only takes strings!");
            return null;
        }
        
        String name = (String)e1.getValue();
        Integer dis = (Integer)e2.getValue();
        GameObject go = (name.charAt(0) == '_') ? go = currState.findObject(name) : currState.getRegistry(name);
    
        if (go == null) {
            System.out.println("Error: Cannot find object of label " + name);
            return null;
        }
        if (go.getTrait("location") == null || !(go.getTrait("location") instanceof Tile)) {
            System.out.println("Error: Object " + name + " to be moved does not have an appropriate location trait.");
            return null;
        }
        
        Tile playerChoice = Mystery(findTargetTiles((Tile)go.getTrait("location"), dis));
        if (!go.setTrait("location", playerChoice)) {
            System.out.println("Error: Failed to move object " + name);
        }
        return null;
	}
	
	// TODO: This method will be implemented by Minjex. 
	private Tile Mystery(ArrayList<Tile> tiles) { return null; }
	
	private ArrayList<Tile> findTargetTiles(Tile t, Integer tDis) {
	    LinkedList<Tile> tiles = new LinkedList<Tile>();
	    LinkedList<Integer> distances = new LinkedList<Integer>();
	    ArrayList<Tile> targets = new ArrayList<Tile>();
	    tiles.add(t);
	    distances.add(0);
	    
	    while(distances.peek() <= tDis) {
	        Integer currDis = distances.poll();
	        Tile currTile = tiles.poll();
	        for (Tile c : currTile.getConnect()) {
	            distances.add(currDis+1);
	            tiles.add(c);
	        }
	        if (currDis == tDis) { targets.add(currTile); }
	    }
	    
	    return targets;
	}
}