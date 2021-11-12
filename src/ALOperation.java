// A static class that provides the actual arithmetic-logic operations.
public final class ALOperation {
    private ALOperation() {}

    // TODO: Is there a way to work around this mess? Generic, inheritance, overloading, visitor... None of which seem to work.
    public static LiteralNode add(Object op1, Object op2) {
        if (op1 instanceof Integer && op2 instanceof Integer) {
            return new LiteralNode<Integer>((Integer)op1 + (Integer)op2);
        }
        if (op1 instanceof Double && op2 instanceof Integer) {
            return new LiteralNode<Double>((Double)op1 + (Integer)op2);
        }
        if (op1 instanceof Integer && op2 instanceof Double) {
            return new LiteralNode<Double>((Integer)op1 + (Double)op2);
        }
        if (op1 instanceof Double && op2 instanceof Double) {
            return new LiteralNode<Double>((Double)op1 + (Double)op2);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode multiply(Object op1, Object op2) {
        if (op1 instanceof Integer && op2 instanceof Integer) {
            return new LiteralNode<Integer>((Integer)op1 * (Integer)op2);
        }
        if (op1 instanceof Double && op2 instanceof Integer) {
            return new LiteralNode<Double>((Double)op1 * (Integer)op2);
        }
        if (op1 instanceof Integer && op2 instanceof Double) {
            return new LiteralNode<Double>((Integer)op1 * (Double)op2);
        }
        if (op1 instanceof Double && op2 instanceof Double) {
            return new LiteralNode<Double>((Double)op1 * (Double)op2);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }
}