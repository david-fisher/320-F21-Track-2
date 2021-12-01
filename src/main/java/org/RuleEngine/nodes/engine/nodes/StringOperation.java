package org.RuleEngine.nodes.engine.nodes;

public final class StringOperation {
    private StringOperation() {}

    public static LiteralNode concat(Object op1, Object op2) {
        if (op1 instanceof String && op2 instanceof String) {
            String str1 = (String)op1;
            String str2 = (String)op2;
            return new LiteralNode<String>(str1.concat(str2));
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode length(Object op1) {
        if (op1 instanceof String) {
            String str = (String)op1;
            return new LiteralNode<Integer>(str.length());
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode makeUpperCase(Object op1) {
        if (op1 instanceof String) {
            String str = (String)op1;
            return new LiteralNode<String>(str.toUpperCase());
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    // TODO: Why is this needed?

    public static LiteralNode isUpperCase(Object op1) {
    /*
        if (op1 instanceof String) {
            String str = (String)op1;
            Boolean flag = true;
            for(int i = 0; i < str.length(); i++) {
                char c1 = str.charAt(i);
                String c2 = String.valueOf(c1);
                if(c2.equals(c2.toLowerCase())) {
                    flag = false;
                }
            }
            return new LiteralNode<Boolean>(flag);
        }
        System.out.println("Error: Unsupported operand type.");

        return null;
    */
        return null;
    }

    public static LiteralNode makeLowerCase(Object op1) {
        if (op1 instanceof String) {
            String str = (String)op1;
            return new LiteralNode<String>(str.toLowerCase());
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    // TODO: Why is this needed?

    public static LiteralNode isLowerCase(Object op1) {
    /*
        if (op1 instanceof String) {
            String str = (String)op1;
            Boolean flag = true;
            for(int i = 0; i < str.length(); i++) {
                char c1 = str.charAt(i);
                String c2 = String.valueOf(c1);
                if(c2.equals(c2.toUpperCase())) {
                    flag = false;
                }
            }
            return new LiteralNode<Boolean>(flag);
        }
        System.out.println("Error: Unsupported operand type.");

        return null;
    */
        return null;
    }


    public static LiteralNode equivalence(Object op1, Object op2) {
        if (op1 instanceof String && op2 instanceof String) {
            String str1 = (String)op1;
            String str2 = (String)op2;
            Boolean eq = str1.equals(str2);
            return new LiteralNode<Boolean>(eq);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }
    

}
