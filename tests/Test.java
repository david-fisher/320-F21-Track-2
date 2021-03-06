package org.RuleEngine.tests;

import java.util.ArrayList;
import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;
import org.GameObjects.objects.*;

public class Test {
    public static void main(String[] args) {

        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        Gamepiece go1 = new Gamepiece();
        Gamepiece go2 = new Gamepiece();
        state.gamepieces.add(go1);
        state.gamepieces.add(go2);

        OpNode ifSt = NodeMaker.makeNode("if");
        OpNode rset1 = NodeMaker.makeNode("rset");
        OpNode rset2 = NodeMaker.makeNode("rset");

        LiteralNode con = new LiteralNode<Boolean>(false);
        LiteralNode reg = new LiteralNode<String>("currPlayer");
        LiteralNode p1 = new LiteralNode<String>("_gamepiece01");
        LiteralNode p2 = new LiteralNode<String>("_gamepiece02");

        rset1.setOperand(reg, 0).setOperand(p1, 1);
        rset2.setOperand(reg, 0).setOperand(p2, 1);
        ifSt.setOperand(con, 0).addOperandToGroup(rset1, 1).addOperandToGroup(rset2, 2);

        interpreter.interpretRule(ifSt, state);
        System.out.println("Content of register: " + state.registers.toString());
        System.out.println("Label of the object in register \"currPlayer\": " + state.registers.get("currPlayer").getTrait("label"));

    }
}