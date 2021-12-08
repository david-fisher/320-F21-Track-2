package org.RuleEngine.nodes;
import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.Savable;
//import objects;

// The interface for both atomic expressions and rule statements.
// This allows the operands of a rule to be either an atomic expression or another statement.
public abstract class Node extends Savable{
    public OpNode parent;
    @SuppressWarnings("rawtypes")
    public abstract LiteralNode execute(GameState currState);
}