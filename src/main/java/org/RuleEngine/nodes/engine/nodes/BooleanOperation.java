package org.RuleEngine.nodes.engine.nodes;

public final class BooleanOperation {
    private BooleanOperation() {}

    public static LiteralNode or(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return new LiteralNode<Boolean>((Boolean) op1 || (Boolean) op2);
        }

        System.out.println("object not boolean type");
        return null;
    }
    public static LiteralNode and(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return new LiteralNode<Boolean>((Boolean) op1 && (Boolean) op2);
        }

        System.out.println("object not boolean type");
        return null;
    }
    public static LiteralNode not(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return new LiteralNode<Boolean>((Boolean) op1 != (Boolean) op2);
        }

        System.out.println("object not boolean type");
        return null;
    }
    public static LiteralNode xor(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return new LiteralNode<Boolean>((Boolean) op1 ^ (Boolean) op2);
        }
        
        System.out.println("object not boolean type");
        return null;
    }
}
