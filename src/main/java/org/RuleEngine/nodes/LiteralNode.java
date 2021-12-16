package org.RuleEngine.nodes;
import org.RuleEngine.engine.GameState;
// A generic class for atomic expressions.
// Potential expression types include Double, String, and org.GameObjects.
public class LiteralNode<T> extends Node {
    private T value;

    public LiteralNode() {};

    public LiteralNode(T value) {
        this.value = value;
    }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }

    // In a sense, an atomic expression is a kind of rule.
    // When executed, it simply returns itself (base case).
    public LiteralNode<T> execute(GameState currState) {
        return this;
    }
}