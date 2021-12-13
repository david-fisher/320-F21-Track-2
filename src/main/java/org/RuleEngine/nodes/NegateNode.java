package org.RuleEngine.nodes;

import org.RuleEngine.engine.GameState;

@SuppressWarnings("rawtypes")
public class NegateNode extends OpNode {
	public NegateNode() {
		super();
		this.addOperand(null);
	}
	
	/*
	 * A Note: right now the negate node only has functionality for the negation of one operand, but it could be extended fairly easily.
	 */
	@Override
	public LiteralNode execute(GameState currState) {
		LiteralNode op0 = getOperand(0).execute(currState);
		if (op0 == null) {
		    NodeUtil.OperandError(this, 0);
		    return null;
		}		
		
		// TODO: needs some testing
		Object val = op0.getValue();
		if (val instanceof Integer) {
			return new LiteralNode<Integer>(-(Integer)val);
		}
		if (val instanceof Double) {
			return new LiteralNode<Double>(-(Double)val);
		}
		if (val instanceof Boolean) {
			return new LiteralNode<Boolean>(!((Boolean)val));
		}
		//else
		NodeUtil.InputTypeError(this, 0, "Integer | Double | Boolean");
		return null;
	}

}
