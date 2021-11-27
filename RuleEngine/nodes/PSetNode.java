package nodes;
import java.util.ArrayList;
import java.util.Map;
import engine.*;
import objects.*;

// Usage: Operand 0 - property name
//        Operand 1 - source(register or gameobject)
//        Operand 2 - value
public class PSetNode extends OpNode {
    public PSetNode() { super(); }
    public PSetNode(ArrayList<Node> operands) {
        super();
        this.operands.set(0, operands);
    }

    public LiteralNode execute(GameState currState) {
        LiteralNode e1 = getOperand(0).execute(currState);
        LiteralNode e2 = getOperand(1).execute(currState);
        LiteralNode e3 = getOperand(2).execute(currState);

        if (e1 == null || e2 == null || e3 == null) {
            System.out.println("Error: Something went wrong processing rset operation");
            return null;
        }

        if (!(e1.getValue() instanceof String) || !(e2.getValue() instanceof String)) {
            System.out.println("Error: rset operation only takes strings!");
            return null;
        }

        String str1 = (String)e1.getValue();
        String str2 = (String)e2.getValue();

        GameObject go;
        if (str2.charAt(0) == '_') {
            go = currState.findObject(str2.substring(1));
        } else {
            go = currState.registers.get(str2);
        }

        //TODO: activate and test after UMass Dining pushes new GameObject definition.
        if (!go.setTrait(str1, e3.getValue())) {
            System.out.println("Error: Something went wrong when setting " + str1 + " property of " + str2 + " to " + e3.getValue().toString());
        }

        return null;
    }
}