package nodes;
import java.util.ArrayList;
import engine.GameState;

//Probably will be changed a bit
public class StringNode extends OpNode {
    private String operator;
    
    public StringNode(String op) {
        super();
        operator = op;
        this.addOperand(null).addOperand(null);
    }
    
    public String getOperator() { return operator; }
    public void setOperator(String op) { operator = op; }
    
    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing String operation " + operator);
            return null;
        }

        switch (operator) {
            case "concat":  return StringOperation.concat(e1.getValue(), e2.getValue());
            
            case "length":  return StringOperation.length(e1.getValue());

            case "makeUpperCase": return StringOperation.makeUpperCase(e1.getValue());

            case "isUpperCase": return StringOperation.isUpperCase(e1.getValue());

            case "makeLowerCase":  return StringOperation.makeLowerCase(e1.getValue());

            case "isLowerCase":  return StringOperation.isLowerCase(e1.getValue());

            case "equivalence":  return StringOperation.equivalence(e1.getValue(), e2.getValue());
        }
        return null;
    }
}
