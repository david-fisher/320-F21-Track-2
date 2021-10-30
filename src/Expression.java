// A generic class for atomic expressions.
// Potential expressipn types include Double, String, and GameObjects.
public class Expression<T> implements OpTree {
    private T value;

    public Expression(T value) {
        this.value = value;
    }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }

    // In a sense, an atomic expression is a kind of rule.
    // When executed, it simply returns itself (base case).
    public Expression execute(GameState currState) {
        return this;
    }
}