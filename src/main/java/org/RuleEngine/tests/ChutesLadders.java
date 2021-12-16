package org.RuleEngine.tests;

import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;

import java.util.ArrayList;

import org.GameObjects.objects.*;

public class ChutesLadders {
    public static void main(String[] args) {
        GameState currState = new GameState();
        
        // Constants
        LiteralNode<String> currPlayer = new LiteralNode<String>("currPlayer");
        LiteralNode<String> rollButton = new LiteralNode<String>("_rollButton");
        LiteralNode<String> endButton = new LiteralNode<String>("_endButton");
        // TODO: Put in register
        LiteralNode<String> deckName = new LiteralNode<String>("deck");
        LiteralNode<String> enabledTrait = new LiteralNode<String>("enabled");
        LiteralNode<Integer> distanceTrait = new LiteralNode<Integer>(0);
        LiteralNode<Integer> valueTrait = new LiteralNode<Integer>(0);
        
        // Next turn event
        OpNode nextTurn = NodeMaker.makeNode("rset").setOperand(currPlayer, 0).setOperand(NodeMaker.makeNode("nextPlayer"), 1);
        
        // Win game event
        OpNode winGame = NodeMaker.makeNode("rset").setOperand(new LiteralNode<String>("winner"), 0).setOperand(currPlayer, 1);
        
        // Get currPlayer gamepiece
        OpNode getPiece = NodeMaker.makeNode("getGamepiece").setOperand(new LiteralNode<Integer>(1), 0).setOperand(currPlayer, 1);
        
        // Get distance of player
        OpNode getDis = NodeMaker.makeNode("get").setOperand(distanceTrait, 0).setOperand(currPlayer, 1);
        
        // Get value of player
        OpNode getVal = NodeMaker.makeNode("get").setOperand(valueTrait, 0).setOperand(currPlayer, 1);
        
        // Get new distance
        OpNode newDis = ((ALNode)NodeMaker.makeNode("AL")).setOperator("+").setOperand(getDis, 1).setOperand(getVal, 2);
        
        // Put last used back to deck
        OpNode putBack = NodeMaker.makeNode("put").setOperand(NodeMaker.makeNode("getLastUsed"), 0).setOperand(deckName, 1);
        
        // Roll and move event
        ArrayList<Node> rollAndMove = new ArrayList<Node>();
        LiteralNode<String> die = new LiteralNode<String>("gameDie");
        OpNode rollDie = NodeMaker.makeNode("use").setOperand(die, 0);
        OpNode setValue = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(rollDie, 2);
        OpNode movePlayer = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(getVal, 1);
        OpNode updateDis = NodeMaker.makeNode("pset").setOperand(distanceTrait, 0).setOperand(currPlayer, 1).setOperand(newDis, 2);
        OpNode disableRollButton = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(rollButton, 1).setOperand(new LiteralNode<Boolean>(false), 2);
        rollAndMove.add(setValue);
        rollAndMove.add(movePlayer);
        rollAndMove.add(updateDis);
        rollAndMove.add(disableRollButton);
        
        // End turn event
        ArrayList<Node> endTurn  = new ArrayList<Node>();
        OpNode enableRollButton = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(rollButton, 1).setOperand(new LiteralNode<Boolean>(true), 2);
        endTurn.add(nextTurn);
        endTurn.add(enableRollButton);
        
        // Win tile event
        ArrayList<Node> winTile = new ArrayList<Node>();
        winTile.add(winGame);
        
        // Draw card tile event
        ArrayList<Node> drawCard = new ArrayList<Node>();
        OpNode draw = NodeMaker.makeNode("draw").setOperand(deckName, 0);
        OpNode putInPlayer = NodeMaker.makeNode("putInInventory").setOperand(draw, 0).setOperand(currPlayer, 1);
        drawCard.add(putInPlayer);
        
        // Teleport tile event1
        ArrayList<Node> teleport1 = new ArrayList<Node>();
        LiteralNode<String> tile1 = new LiteralNode<String>("_target1");
        OpNode telePlayer1 = NodeMaker.makeNode("moveTo").setOperand(currPlayer, 0).setOperand(tile1, 1);
        teleport1.add(telePlayer1);
        
        // Teleport tile event2
        ArrayList<Node> teleport2 = new ArrayList<Node>();
        LiteralNode<String> tile2 = new LiteralNode<String>("_target2");
        OpNode telePlayer2 = NodeMaker.makeNode("moveTo").setOperand(currPlayer, 0).setOperand(tile2, 1);
        teleport2.add(telePlayer2);
        
        // Teleport tile event3
        ArrayList<Node> teleport3 = new ArrayList<Node>();
        LiteralNode<String> tile3 = new LiteralNode<String>("_target3");
        OpNode telePlayer3 = NodeMaker.makeNode("moveTo").setOperand(currPlayer, 0).setOperand(tile3, 1);
        teleport3.add(telePlayer3);
        
        // Teleport tile event3
        ArrayList<Node> teleport4 = new ArrayList<Node>();
        LiteralNode<String> tile4 = new LiteralNode<String>("_target4");
        OpNode telePlayer4 = NodeMaker.makeNode("moveTo").setOperand(currPlayer, 0).setOperand(tile4, 1);
        teleport3.add(telePlayer4);
        
        // Move 1 card event
        ArrayList<Node> move1Card = new ArrayList<Node>();
        OpNode move1 = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(new LiteralNode<Integer>(1), 1);
        OpNode setValue1 = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(new LiteralNode<Integer>(1), 2);
        move1Card.add(move1);
        move1Card.add(setValue1);
        move1Card.add(updateDis);
        move1Card.add(putBack);
        
        // Move 4 card event
        ArrayList<Node> move4Card = new ArrayList<Node>();
        OpNode move4 = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(new LiteralNode<Integer>(4), 1);
        OpNode setValue2 = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(new LiteralNode<Integer>(4), 2);
        move4Card.add(move4);
        move4Card.add(setValue2);
        move4Card.add(updateDis);
        move4Card.add(putBack);
        
        // Move 6 card event
        ArrayList<Node> move6Card = new ArrayList<Node>();
        OpNode move6 = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(new LiteralNode<Integer>(6), 1);
        OpNode setValue3 = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(new LiteralNode<Integer>(6), 2);
        move6Card.add(move6);
        move6Card.add(setValue3);
        move6Card.add(updateDis);
        move6Card.add(putBack);
        
        // Draw 2 card event
        ArrayList<Node> draw2Card = new ArrayList<Node>();
        draw2Card.add(putInPlayer);
        draw2Card.add(putInPlayer);
        draw2Card.add(putBack);
        
        
    }
}