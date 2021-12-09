package org.RuleEngine.nodes;

import org.RuleEngine.engine.GameState;

@SuppressWarnings("rawtypes")
public class NegateNode extends OpNode {
	private LiteralNode node;
	public NegateNode(LiteralNode node) {
		super();
		this.node = node;
		this.addOperand(null).addOperand(null);
	}
	
	/*
	 * A Note: right now the negate node only has functionality for the negation of one operand, but it could be extended fairly easily.
	 */
	@Override
	public LiteralNode execute(GameState currState) {
//		LiteralNode e1 = getOperand(0).execute(currState);
//		if (e1 == null) {
//			System.out.println("Error: NegateNode initialized with null");
//			return null;
//		}		
//		Object op1 = e1.getValue();
		Object op1 = node.getValue();
		
		// TODO: needs some testing
		if (op1 instanceof Integer) {
			return new LiteralNode<Integer>(-(Integer)op1);
		}
		if (op1 instanceof Double) {
			return new LiteralNode<Double>(-(Double)op1);
		}
		if (op1 instanceof Boolean) {
			return new LiteralNode<Boolean>(!((Boolean) op1));
		}
		//else
		System.out.println("Error: Unsupported operand type for NegateNode.");
		return null;
	}

}
