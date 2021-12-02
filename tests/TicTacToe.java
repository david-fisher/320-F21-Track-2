package org.RuleEngine.tests;

import java.util.ArrayList;
import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;
// WIP
/* 0 1 2
 * 3 4 5
 * 6 7 8
 */

public class TicTacToe {
/*
    public static void main(String args[]) {
        Interpreter interpreter = new Interpreter();

        // Initializing Game
        GameState state = new GameState();
        GameObject gameBoard = new GameObject("gameBoard");
        ArrayList<Node> boardXNodes = new ArrayList<Node>();
        ArrayList<Node> boardONodes = new ArrayList<Node>();
        ArrayList<Node> boardEmptyNodes = new ArrayList<Node>();
        for (int i = 0; i < 9; i++) {
            gameBoard.setTrait(Integer.toString(i), " ");
        }
        state.gameObjects.add(gameBoard);
        
        GameObject player = new GameObject("player");
        player.setTrait("currentPlayer", "1");
        state.gameObjects.add(player);
        
        // Game Start
        for (int i = 0; i < 9; i++) {
            GetNode g = new GetNode();
            g.addOperand(new LiteralNode<String>(Integer.toString(i)));
            g.addOperand(new LiteralNode<String>("_gameBoard"));

            StringNode eqX = new StringNode("equivalence");
            eqX.addOperand(g);
            eqX.addOperand(new LiteralNode<String>("X"));           
            boardXNodes.add(eqX);

            StringNode eqO = new StringNode("equivalence");
            eqO.addOperand(g);
            eqO.addOperand(new LiteralNode<String>("O"));
            boardONodes.add(eqO);
            
            StringNode eqEmpty = new StringNode("equivalence");
            eqEmpty.addOperand(g);
            eqEmpty.addOperand(new LiteralNode<String>(" "));
            boardEmptyNodes.add(eqEmpty);
        }

        BooleanNode and0 = new BooleanNode("&&");
        BooleanNode and00 = new BooleanNode("&&");
        
        BooleanNode and01 = new BooleanNode("&&");
        
        StringNode eq = new StringNode("equivalence");
    }
*/
}