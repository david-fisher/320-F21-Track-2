package testsEngine;
import java.util.ArrayList;
import nodes.*;
import engine.*;

public class DemoTest {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        GameObject go1 = new GameObject("player1");
        GameObject go2 = new GameObject("player2");
        state.gameObjects.add(go1);
        state.gameObjects.add(go2);

        LiteralNode exp1 = new LiteralNode<Integer>(5);
        LiteralNode exp2 = new LiteralNode<Double>(2.2);
        System.out.println("Created LiteralNode exp1: Integer 5 and LiteralNode exp2: Double 2.2");
        OpNode st1 = NodeMaker.makeNode("addition");
        System.out.println("Created addition node statement1");
        waitForKey();
        st1.addOperand(exp1).addOperand(exp2);
        System.out.println("Added exp1 and exp2 to statement1");
        System.out.println("statement1: 5 + 2.2");
        waitForKey();
        interpreter.interpretRule(st1, state);
        waitForKey();

        OpNode st2 = NodeMaker.makeNode("multiplication");
        System.out.println("Created multiplication node statement2");
        waitForKey();
        st2.addOperand(exp2).addOperand(st1);
        System.out.println("Added exp2 and statement1 to statement2");
        System.out.println("statement2: 2.2 * st1");
        waitForKey();
        interpreter.interpretRule(st2, state);
        waitForKey();

        exp1 = new LiteralNode<String>("currPlayer");
        exp2 = new LiteralNode<String>("_player1");
        OpNode st3 = NodeMaker.makeNode("rset");
        st3.addOperand(exp1).addOperand(exp2);
        System.out.println("Making rule: rset [currPlayer] to [_player1]");
        System.out.println("GameObjects in currentState: player1, player2 ");
        System.out.println("Content of register: " + state.registers.toString());
        waitForKey();

        interpreter.interpretRule(st3, state);
        System.out.println("Interpreting rule: rset [currPlayer] to [_player1]");
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
        System.out.print("\n");
    }
}