// The interface for both atomic expressions and rule statements.
// This allows the operands of a rule to be either an atomic expression or another statement.
public abstract class Node {
    public LiteralNode execute(GameState currState);
}