import java.util.ArrayList;

public class BooleanNode extends OpNode {
    private String operator;
    
    public BooleanNode(String op) {
        super();
        operator = op;
    }

    public BooleanNode(String op, ArrayList<ArrayList<Node>> operands) {
        super(operands);
        operator = op;
    }
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing binary operation " + operator);
            return null;
        }

        switch (operator) {
        }
        return null;
    }
}
