import java.util.function.BiFunction;

public enum RuleType {
	INTEGER((optree, state) -> {
		return optree;
	}),
	ADD((optree, state) -> {
		optree = (Rule)optree;
		return new IntExp(optree.getOperand(0).execute(state).value + optree.getOperand(1).execute(state).value);
	});

	RuleType(BiFunction<OpTree, GameState, OpTree> operation) {
		this.operation = operation;
    }
	private final BiFunction<OpTree, GameState, OpTree> operation;
	public OpTree execute(OpTree optree, GameState state) {
		return operation.apply(optree, state);
    }
}