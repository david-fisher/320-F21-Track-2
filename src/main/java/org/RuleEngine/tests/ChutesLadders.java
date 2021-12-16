package org.RuleEngine.tests;

import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.GameObjects.objects.*;

public class ChutesLadders {
    public GameState makeChutes(GameState currState) {
//        Player player1 = new Player();
//        player1.setLabel("player01")
//        Player player2 = new Player("player02", new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true);
//        player1.setTrait("distance", 0, true);
//        player1.setTrait("value", 0, true);
//        player2.setTrait("distance", 0, true);
//        player2.setTrait("value", 0, true);
//        Gamepiece gp1 = new Gamepiece();
//        Gamepiece gp2 = new Gamepiece();
//        player1.addPiece(gp1);
//        player2.addPiece(gp2);
        Die die = new Die();
        die.setTrait("label", "gameDie");
        die.setNumSides(5);
        Button roll = new Button();
        Button end = new Button();
        roll.setTrait("label", "rollButton");
        roll.setTrait("onClick", "rollAndMove");
        end.setTrait("label", "endButton");
        end.setTrait("onClick", "endTurn");
        Card m1Card = new Card();
        Card m4Card = new Card();
        Card m6Card = new Card();
        Card d2Card = new Card();
        m1Card.setTrait("onPlay", "move1Card");
        m4Card.setTrait("onPlay", "move4Card");
        m6Card.setTrait("onPlay", "move6Card");
        d2Card.setTrait("onPlay", "draw2Card");
        Deck gameDeck = new Deck();
        gameDeck.addCard(m1Card, 6);
        gameDeck.addCard(m4Card, 3);
        gameDeck.addCard(m6Card, 1);
        gameDeck.addCard(d2Card, 2);
        gameDeck.shuffle();
//        for (int i = 0; i < 24; i++) {
//            Tile tile = new Tile();
//            if (i == 0) {
//                gp1.setLocation(tile);
//                gp2.setLocation(tile);
//            }
//            if (i == 5) {
//                tile.setTrait("onLand", "winGame");
//                currState.tiles.get(i-1).addConnect(tile);
//            } else if (i > 0) {
//                tile.setTrait("onLand", "drawCard");
//                currState.tiles.get(i-1).addConnect(tile);
//            }
//            currState.tiles.add(tile);
//        }
        currState.dice.add(die);
        currState.buttons.add(roll);
        currState.buttons.add(end);
        currState.decks.add(gameDeck);
        currState.addRegistry("deck", gameDeck);
//        currState.addRegistry("currPlayer", player1);

        // Constants
        LiteralNode<String> currPlayer = new LiteralNode<String>("currPlayer");
        LiteralNode<String> rollButton = new LiteralNode<String>("_rollButton");
        LiteralNode<String> endButton = new LiteralNode<String>("_endButton");
        // TODO: Put in register
        LiteralNode<String> deckName = new LiteralNode<String>("deck");
        LiteralNode<String> enabledTrait = new LiteralNode<String>("enabled");
        LiteralNode<String> distanceTrait = new LiteralNode<String>("distance");
        LiteralNode<String> valueTrait = new LiteralNode<String>("value");

        // Next turn event
        OpNode nextTurn = NodeMaker.makeNode("rset").setOperand(currPlayer, 0).setOperand(NodeMaker.makeNode("nextPlayer"), 1);

        // Win game event
        OpNode winGame = NodeMaker.makeNode("rset").setOperand(new LiteralNode<String>("winner"), 0).setOperand(currPlayer, 1);

        // Get currPlayer gamepiece
        OpNode getPiece = NodeMaker.makeNode("getGamepiece").setOperand(new LiteralNode<Integer>(0), 0).setOperand(currPlayer, 1);

        // Get distance of player
        OpNode getDis = NodeMaker.makeNode("get").setOperand(distanceTrait, 0).setOperand(currPlayer, 1);

        // Get value of player
        OpNode getVal = NodeMaker.makeNode("get").setOperand(valueTrait, 0).setOperand(currPlayer, 1);

        // Get new distance
        OpNode newDis = ((ALNode)NodeMaker.makeNode("AL")).setOperator("+").setOperand(getDis, 0).setOperand(getVal, 1);

        // Put last used back to deck
        OpNode putBack = NodeMaker.makeNode("put").setOperand(NodeMaker.makeNode("getLastUsed").setOperand(new LiteralNode<String>("currPlayer"), 0), 0).setOperand(deckName, 1);

        // Roll and move event
        ArrayList<Node> rollAndMove = new ArrayList<Node>();
        LiteralNode<String> gameDie = new LiteralNode<String>("_gameDie");
        OpNode rollDie = NodeMaker.makeNode("use").setOperand(gameDie, 0);
        OpNode setValue = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(rollDie, 2);
        OpNode movePlayer = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(getVal, 1);
        OpNode updateDis = NodeMaker.makeNode("pset").setOperand(distanceTrait, 0).setOperand(currPlayer, 1).setOperand(newDis, 2);
        OpNode disableRollButton = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(rollButton, 1).setOperand(new LiteralNode<Boolean>(false), 2);
        rollAndMove.add(setValue);
        rollAndMove.add(movePlayer);
        rollAndMove.add(updateDis);
        rollAndMove.add(disableRollButton);
        currState.addEvent("rollAndMove", rollAndMove);

        // End turn event
        ArrayList<Node> endTurn  = new ArrayList<Node>();
        OpNode enableRollButton = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(rollButton, 1).setOperand(new LiteralNode<Boolean>(true), 2);
        endTurn.add(nextTurn);
        endTurn.add(enableRollButton);
        currState.addEvent("endTurn", endTurn);

        // Win tile event
        ArrayList<Node> winTile = new ArrayList<Node>();
        winTile.add(winGame);
        currState.addEvent("winGame", winTile);

        // Draw card tile event
        ArrayList<Node> drawCard = new ArrayList<Node>();
        OpNode draw = NodeMaker.makeNode("draw").setOperand(deckName, 0);
        OpNode putInPlayer = NodeMaker.makeNode("putInInventory").setOperand(draw, 0).setOperand(currPlayer, 1);
        drawCard.add(putInPlayer);
        currState.addEvent("drawCard", drawCard);

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
        currState.addEvent("move1Card", move1Card);

        // Move 4 card event
        ArrayList<Node> move4Card = new ArrayList<Node>();
        OpNode move4 = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(new LiteralNode<Integer>(4), 1);
        OpNode setValue2 = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(new LiteralNode<Integer>(4), 2);
        move4Card.add(move4);
        move4Card.add(setValue2);
        move4Card.add(updateDis);
        move4Card.add(putBack);
        currState.addEvent("move4Card", move4Card);

        // Move 6 card event
        ArrayList<Node> move6Card = new ArrayList<Node>();
        OpNode move6 = NodeMaker.makeNode("moveBy").setOperand(getPiece, 0).setOperand(new LiteralNode<Integer>(6), 1);
        OpNode setValue3 = NodeMaker.makeNode("pset").setOperand(valueTrait, 0).setOperand(currPlayer, 1).setOperand(new LiteralNode<Integer>(6), 2);
        move6Card.add(move6);
        move6Card.add(setValue3);
        move6Card.add(updateDis);
        move6Card.add(putBack);
        currState.addEvent("move6Card", move6Card);

        // Draw 2 card event
        ArrayList<Node> draw2Card = new ArrayList<Node>();
        draw2Card.add(putInPlayer);
        draw2Card.add(putInPlayer);
        draw2Card.add(putBack);
        currState.addEvent("draw2Card", draw2Card);

        String input = "";
        Scanner myObj = new Scanner(System.in);
        Interpreter interpreter = Interpreter.getInstance();
        while(!input.equals("exit")) {
            input = myObj.nextLine();
            switch(input) {
                case "roll":
                    if (!roll.getEnabled()) {
                        System.out.println("Roll button disabled. Try something else!");
                        break;
                    }
                    interpreter.interpretEvent(currState.getEvent(roll.getTrait("onClick").toString()), currState);
                    break;
                case "done":
                    if (!end.getEnabled()) {
                        System.out.println("End button disabled. Try something else!");
                        break;
                    }
                    interpreter.interpretEvent(currState.getEvent(end.getTrait("onClick").toString()), currState);
                    break;
                case "card":
                    ArrayList<GameObject> inv = ((Player)currState.getRegistry("currPlayer")).getInventory();
                    if (inv.size() > 0) {
                        interpreter.interpretEvent(currState.getEvent(inv.get(0).getTrait("onPlay").toString()), currState);
                    } else {
                        System.out.println("No card to play!");
                    }
                    break;
            }
            if (currState.getRegistry("winner") != null) {
                System.out.println("Winner is: " + currState.getRegistry("winner").getTrait("label"));
                break;
            }
        }
        return currState;
    }

}