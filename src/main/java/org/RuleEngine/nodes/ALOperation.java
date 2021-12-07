package org.RuleEngine.nodes;
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

    public static LiteralNode subtract(Object op1, Object op2) {
        if (op1 instanceof Integer && op2 instanceof Integer) {
            return new LiteralNode<Integer>((Integer)op1 - (Integer)op2);
        }
        if (op1 instanceof Double && op2 instanceof Integer) {
            return new LiteralNode<Double>((Double)op1 - (Integer)op2);
        }
        if (op1 instanceof Integer && op2 instanceof Double) {
            return new LiteralNode<Double>((Integer)op1 - (Double)op2);
        }
        if (op1 instanceof Double && op2 instanceof Double) {
            return new LiteralNode<Double>((Double)op1 - (Double)op2);
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

    public static LiteralNode divide(Object op1, Object op2) {
        if (op1 instanceof Integer && op2 instanceof Integer) {
            return new LiteralNode<Integer>((Integer) op1 / (Integer) op2);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode modulo(Object op1, Object op2) {
        if ((op1 instanceof Integer || op1 instanceof Double) && (op2 instanceof Integer || op2 instanceof Double)) {
            return new LiteralNode<Double>((Double) op1 % (Double) op2);
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }

    public static LiteralNode pow(Object op1, Object op2) {
        if ((op1 instanceof Integer || op1 instanceof Double) && (op2 instanceof Integer || op2 instanceof Double)) {
            return new LiteralNode<Double>(Math.pow((Double) op1, (Double) op2));
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }
/*
    public static LiteralNode factorial(Object op1) {
        if (op1 instanceof Integer) {
            return new LiteralNode<Integer>(Math.factorial((Integer)op1));
        }
        System.out.println("Error: Unsupported operand type.");
        return null;
    }
*/
    public static double arithmetic_compare(Object op1, Object op2) {
        // returns op1 - op2 (this will equal 0 if they are equivalent, be < 0 if op1<op2, be >0 if op1>op2)
        if ((op1 instanceof Integer || op1 instanceof Double) && (op2 instanceof Integer || op2 instanceof Double)) {
            return (Double)op1 - (Double)op2;
        }
        System.out.println("Error: Unsupported operand type.");
        return 0;
    }
}
