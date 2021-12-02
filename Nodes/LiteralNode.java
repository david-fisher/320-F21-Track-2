package nodes;
import engine.GameState;
// A generic class for atomic expressions.
// Potential expression types include Double, String, and org.GameObjects.
public class LiteralNode<T> extends Node {
    public T value;

    public LiteralNode(T value) {
        this.value = value;
    }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }

    // In a sense, an atomic expression is a kind of rule.
    // When executed, it simply returns itself (base case).
    public LiteralNode execute(GameState currState) {
        return this;
    }
}