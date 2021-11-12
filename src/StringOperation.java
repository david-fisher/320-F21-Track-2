public final class StringOperation {
    private StringOperation() {}

    public static LiteralNode concat(Object op1, Object op2) {
        if (op1 instanceof String && op2 instanceof String) {
            return new LiteralNode<String>(op1.concat(op2));
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static int length(Object op1) {
        if (op1 instanceof String) {
            LiteralNode<String> op1Str = new LiteralNode<String>(op1);
            return op1Str.length;
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode makeUpperCase(Object op1) {
        if (op1 instanceof String) {
            int length = length(op1);
            for(int i = 0; i < length; i++) {
                op1.charAt(i) = op1.charAt(i).toUpperCase();
            }
            return new LiteralNode<String>(op1);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static Boolean isUpperCase(Object op1) {
        if (op1 instanceof String) {
            int length = length(op1);
            for(int i = 0; i < length; i++) {
                if(op1.charAt(i) == op1.charAt(i).toLowerCase()) {
                    return false;
                }
            }
            return true;
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode makeLowerCase(Object op1) {
        if (op1 instanceof String) {
            int length = length(op1);
            for(int i = 0; i < length; i++) {
                op1.charAt(i) = op1.charAt(i).toLowerCase();
            }
            return new LiteralNode<String>(op1);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static Boolean isLowerCase(Object op1) {
        if (op1 instanceof String) {
            int length = length(op1);
            for(int i = 0; i < length; i++) {
                if(op1.charAt(i) == op1.charAt(i).toUpperCase()) {
                    return false;
                }
            }
            return true;
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

}
