package org.RuleEngine.nodes;

import org.GameObjects.objects.*;
import org.RuleEngine.engine.GameState;

// Usage: Returns the next player as ordered in the player list in game state. 
public class NextPlayerNode extends OpNode {
    public NextPlayerNode() {
        super();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        int pid = currState.players.indexOf(currState.getRegistry("currState"));
        int size = currState.players.size();
        int newId = (pid+1) % size;
        return new LiteralNode<Player>(currState.players.get(newId));
    }
}