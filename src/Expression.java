public class Expression<T> implements OpTree {
    private T value;

    public Expression(T value) {
        this.value = value;
    }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }

    public Expression execute(GameState currState) {
        return this;
    }
}