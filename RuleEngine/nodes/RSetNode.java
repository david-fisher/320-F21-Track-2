package nodes;
import java.util.ArrayList;
import java.util.Map;
import engine.*;

// Usage: Operand 0 - target (register name)
//        Operand 1 - source name (register or gameobject)
public class RSetNode extends OpNode {
    public RSetNode() { super(); }
    public RSetNode(ArrayList<Node> operands) {
        super();
        this.operands.set(0, operands);
    }

    public LiteralNode execute(GameState currState) {
        GameObject go;
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);

        if (e1 == null || e2 == null) {
            System.out.println("Error: Something went wrong processing rset operation");
            return null;
        }

        if (!(e1.getValue() instanceof String) || !(e2.getValue() instanceof String)) {
            System.out.println("Error: rset operation only takes strings!");
            return null;
        }
        String str1 = (String)e1.getValue();
        String str2 = (String)e2.getValue();

        if (str2.charAt(0) == '_') {
            go = currState.findObject(str2.substring(1));
        } else {
            go = currState.registers.get(str2);
        }
        currState.registers.put(str1, go);
        return null;
    }
}