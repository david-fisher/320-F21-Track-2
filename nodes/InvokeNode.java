package nodes;

import java.util.ArrayList;
import engine.*;

// Usage: Operand 0: Name of the event to be invoked
public class InvokeNode extends OpNode {
    public InvokeNode() { super(); }
    public InvokeNode(ArrayList<Node> operands) {
        super();
        this.operands.set(0, operands);
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        
        if (e1 == null || !(e1.getValue() instanceof String)) {
            System.out.println("Error: Invoke only accepts a string as input.");
        }
        
        String name = (String)e1.getValue();
        ArrayList<Node> event = currState.getEvent(name);
        if (event == null) {
            System.out.println("Error: No event of name " + name);
        }
        Interpreter interpreter = new Interpreter();
        interpreter.interpretEvent(event, currState);
        return null;
    }
}