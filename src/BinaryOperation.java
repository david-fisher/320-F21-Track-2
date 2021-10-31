public final class BinaryOperation {
    private BinaryOperation() {}

    public static Expression add(Integer op1, Integer op2) {
        return new Expression<Integer>(op1 + op2);
    }

    public static Expression add(Integer op1, Double op2) {
        return new Expression<Double>(Double.valueOf(op1) + op2);
    }

    public static Expression add(Double op1, Integer op2) {
        return new Expression<Double>(op1 + Double.valueOf(op2));
    }

    public static Expression add(Double op1, Double op2) {
        return new Expression<Double>(op1 + op2);
    }

    public static Expression add(String op1, String op2) {
        return new Expression<String>(op1.concat(op2));
    }

    public static Expression add(Object op1, Object op2) {
        System.out.println("Error: Unsupported operand for add operation.");
        return null;
    }
}