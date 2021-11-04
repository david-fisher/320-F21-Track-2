import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        GameObject go1 = new GameObject("player1");
        GameObject go2 = new GameObject("player2");
        state.gameObjects.add(go1);
        state.gameObjects.add(go2);

        LiteralNode exp1 = new LiteralNode<Integer>(5);
        LiteralNode exp2 = new LiteralNode<Double>(2.2);
        ALNode st1 = new ALNode("+");
        st1.getRuleGroup(0).add(exp1);
        st1.getRuleGroup(0).add(exp2);
        System.out.println("Interpreting statement1: 5 + 2.2");
        waitForKey();
        interpreter.interpretRule(st1, state);
        waitForKey();

        ALNode st2 = new ALNode("*");
        st2.getRuleGroup(0).add(exp2);
        st2.getRuleGroup(0).add(st1);
        System.out.println("Interpreting statement2: 2.2 * statement1");
        waitForKey();
        interpreter.interpretRule(st2, state);
        waitForKey();

        exp1 = new LiteralNode<String>("currPlayer");
        exp2 = new LiteralNode<String>("_player1");
        OpNode st3 = NodeMaker.makeNode("rset");
        st3.addOperand(exp1);
        st3.addOperand(exp2);
        System.out.println("Content of register: " + state.registers.toString());
        waitForKey();

        interpreter.interpretRule(st3, state);
        System.out.println("Interpreting rule: rset [currPlayer] to [_player1]...");
        waitForKey();

        System.out.println("Content of register: " + state.registers.toString());
        System.out.println("Label of the object in register \"currPlayer\": " + state.registers.get("currPlayer").label);
        waitForKey();

        System.out.println("Changing the label of object1 from \"player1\" to \"player3\"");
        go1.label = "player3";
        System.out.println("Label of the object in register \"currPlayer\": " + state.registers.get("currPlayer").label);
        waitForKey();
    }

    public static void waitForKey() {
        try{System.in.read();}
        catch(Exception e){}
        System.out.print("\n\n");
    }
}