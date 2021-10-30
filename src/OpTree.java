// The interface for both atomic expressions and rule statements.
// This allows the operands of a rule to be either an atomic expression or another statement.
public interface OpTree {
    public Expression execute(GameState currState);
}