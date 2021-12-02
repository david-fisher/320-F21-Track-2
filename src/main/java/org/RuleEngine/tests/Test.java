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
        System.out.println(go1.setTrait("money", 100, true));
        go2.setTrait("money", 50, true);
        state.addRegistry("currPlayer", go1);
        
        System.out.println("Player1 money is currently at: " + go1.getTrait("money"));
        System.out.println("Player2 money is currently at: " + go2.getTrait("money"));

        // money trait in both gamepieces
        // display money in go1
        // display money in go2
        // get money from go1
        // set money in go2 to the money or go1
        // display money in go2
        
        // pset -- "money"
        //
        //      -- "_gamepiece02"
        //
        //      -- get -- "money"
        //             -- "currPlayer"

        OpNode get = NodeMaker.makeNode("get");
        OpNode pset = NodeMaker.makeNode("pset");
        
        LiteralNode p1Name = new LiteralNode<String>("currPlayer");
        LiteralNode TraitName = new LiteralNode<String>("money");
        LiteralNode p2Name = new LiteralNode<String>("_gamepiece02");

        get.setOperand(TraitName, 0).setOperand(p1Name, 1);
        pset.setOperand(TraitName, 0).setOperand(p2Name, 1).setOperand(get, 2);

        interpreter.interpretRule(pset, state);
        System.out.println("Content of register: " + state.registers.toString());
        System.out.println("Player2 money is currently at: " + state.findObject("gamepiece02").getTrait("money"));

    }
}