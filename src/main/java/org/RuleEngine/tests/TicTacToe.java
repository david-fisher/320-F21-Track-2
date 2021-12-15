package org.RuleEngine.tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;
// WIP
/* 0 1 2
 * 3 4 5
 * 6 7 8
 */

public class TicTacToe {

    public HashMap<String, ArrayList<Node>> makeEvents(GameState gameState) {

        HashMap<String, ArrayList<Node>> events = gameState.getAllEvents();

        LiteralNode<String> colorTrait = NodeMaker.makeStringNode("color");
        LiteralNode<String> currPlayer = NodeMaker.makeStringNode("currPlayer");
        LiteralNode<String> enabledTrait = NodeMaker.makeStringNode("enabled");
        LiteralNode<Boolean> falseVal = new LiteralNode<Boolean>(false);

        // Rename these with the actual button name setup.
        LiteralNode<String> buttonTL = NodeMaker.makeStringNode("buttonTL");
        LiteralNode<String> buttonTM = NodeMaker.makeStringNode("buttonTM");
        LiteralNode<String> buttonTR = NodeMaker.makeStringNode("buttonTR");
        LiteralNode<String> buttonML = NodeMaker.makeStringNode("buttonML");
        LiteralNode<String> buttonMM = NodeMaker.makeStringNode("buttonMM");
        LiteralNode<String> buttonMR = NodeMaker.makeStringNode("buttonMR");
        LiteralNode<String> buttonBL = NodeMaker.makeStringNode("buttonBL");
        LiteralNode<String> buttonBM = NodeMaker.makeStringNode("buttonBM");
        LiteralNode<String> buttonBR = NodeMaker.makeStringNode("buttonBR");

        // Rename these with the actual tile name setup.
        LiteralNode<String> tileTL = NodeMaker.makeStringNode("tileTL");
        LiteralNode<String> tileTM = NodeMaker.makeStringNode("tileTM");
        LiteralNode<String> tileTR = NodeMaker.makeStringNode("tileTR");
        LiteralNode<String> tileML = NodeMaker.makeStringNode("tileML");
        LiteralNode<String> tileMM = NodeMaker.makeStringNode("tileMM");
        LiteralNode<String> tileMR = NodeMaker.makeStringNode("tileMR");
        LiteralNode<String> tileBL = NodeMaker.makeStringNode("tileBL");
        LiteralNode<String> tileBM = NodeMaker.makeStringNode("tileBM");
        LiteralNode<String> tileBR = NodeMaker.makeStringNode("tileBR");

        // Win game rule
        OpNode makeWinner = NodeMaker.makeNode("rset");
        LiteralNode<String> winner = NodeMaker.makeStringNode("winner");
        makeWinner.setOperand(winner, 0).setOperand(currPlayer, 1);

        // End turn rule
        OpNode nextTurn = NodeMaker.makeNode("rset");
        OpNode nextPlayer = NodeMaker.makeNode("nextPlayer");
        nextTurn.setOperand(currPlayer, 0).setOperand(nextPlayer, 1);

        // This node gets the color of the currPlayer
        OpNode getCurrColor = NodeMaker.makeNode("get");
        getCurrColor.setOperand(colorTrait, 0).setOperand(currPlayer, 1);

        // These nodes get the color of each of the nine tiles
        OpNode getTLColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileTL, 1);
        OpNode getTMColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileTM, 1);
        OpNode getTRColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileTR, 1);
        OpNode getMLColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileML, 1);
        OpNode getMMColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileMM, 1);
        OpNode getMRColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileMR, 1);
        OpNode getBLColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileBL, 1);
        OpNode getBMColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileBM, 1);
        OpNode getBRColor = NodeMaker.makeNode("get");
        getTLColor.setOperand(colorTrait, 0).setOperand(tileBR, 1);

        // The color condition for each tile.
        OpNode equalTL = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getTLColor, 0).setOperand(getCurrColor, 1);
        OpNode equalTM = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getTMColor, 0).setOperand(getCurrColor, 1);
        OpNode equalTR = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getTRColor, 0).setOperand(getCurrColor, 1);
        OpNode equalML = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getMLColor, 0).setOperand(getCurrColor, 1);
        OpNode equalMM = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getMMColor, 0).setOperand(getCurrColor, 1);
        OpNode equalMR = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getMRColor, 0).setOperand(getCurrColor, 1);
        OpNode equalBL = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getBLColor, 0).setOperand(getCurrColor, 1);
        OpNode equalBM = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getBMColor, 0).setOperand(getCurrColor, 1);
        OpNode equalBR = ((ALNode)NodeMaker.makeNode("AL")).setOperator("=").setOperand(getBRColor, 0).setOperand(getCurrColor, 1);

        // If all tiles of certain row or column is of the same color as the currPlayer.
        OpNode topRow = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTL, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTM, 0).setOperand(equalTR, 1), 1);
        OpNode ifTopRow = NodeMaker.makeNode("if").setOperand(topRow, 0).addOperandToGroup(makeWinner, 1);

        OpNode midRow = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalML, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalMM, 0).setOperand(equalMR, 1), 1);
        OpNode ifMidRow = NodeMaker.makeNode("if").setOperand(midRow, 0).addOperandToGroup(makeWinner, 1);

        OpNode botRow = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalBL, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalBM, 0).setOperand(equalBR, 1), 1);
        OpNode ifBotRow = NodeMaker.makeNode("if").setOperand(botRow, 0).addOperandToGroup(makeWinner, 1);

        OpNode leftCol = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTL, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalML, 0).setOperand(equalBL, 1), 1);
        OpNode ifLeftCol = NodeMaker.makeNode("if").setOperand(leftCol, 0).addOperandToGroup(makeWinner, 1);

        OpNode midCol = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTM, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalMM, 0).setOperand(equalBM, 1), 1);
        OpNode ifMidCol = NodeMaker.makeNode("if").setOperand(midCol, 0).addOperandToGroup(makeWinner, 1);

        OpNode rightCol = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTR, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalMR, 0).setOperand(equalBR, 1), 1);
        OpNode ifRightCol = NodeMaker.makeNode("if").setOperand(rightCol, 0).addOperandToGroup(makeWinner, 1);

        OpNode backDiag = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTL, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalMM, 0).setOperand(equalBR, 1), 1);
        OpNode ifBackDiag = NodeMaker.makeNode("if").setOperand(backDiag, 0).addOperandToGroup(makeWinner, 1);

        OpNode forwardDiag = ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalTR, 0).setOperand(
                ((ALNode)NodeMaker.makeNode("AL")).setOperator("&&").setOperand(equalMM, 0).setOperand(equalBL, 1), 1);
        OpNode ifForwardDiag = NodeMaker.makeNode("if").setOperand(forwardDiag, 0).addOperandToGroup(makeWinner, 1);


        ArrayList<Node> TLEvent = new ArrayList<Node>();
        OpNode setTLColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileTL, 1).setOperand(getCurrColor, 2);
        OpNode disableTL = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonTL, 1).setOperand(falseVal, 2);
        TLEvent.add(setTLColor);
        TLEvent.add(disableTL);
        TLEvent.add(ifTopRow);
        TLEvent.add(ifLeftCol);
        TLEvent.add(ifBackDiag);
        TLEvent.add(nextTurn);

        ArrayList<Node> TMEvent = new ArrayList<Node>();
        OpNode setTMColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileTM, 1).setOperand(getCurrColor, 2);
        OpNode disableTM = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonTM, 1).setOperand(falseVal, 2);
        TMEvent.add(setTMColor);
        TMEvent.add(disableTM);
        TLEvent.add(ifTopRow);
        TLEvent.add(ifMidCol);
        TMEvent.add(nextTurn);

        ArrayList<Node> TREvent = new ArrayList<Node>();
        OpNode setTRColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileTR, 1).setOperand(getCurrColor, 2);
        OpNode disableTR = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonTR, 1).setOperand(falseVal, 2);
        TREvent.add(setTRColor);
        TREvent.add(disableTR);
        TREvent.add(ifTopRow);
        TREvent.add(ifRightCol);
        TREvent.add(ifForwardDiag);
        TREvent.add(nextTurn);

        ArrayList<Node> MLEvent = new ArrayList<Node>();
        OpNode setMLColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileML, 1).setOperand(getCurrColor, 2);
        OpNode disableML = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonML, 1).setOperand(falseVal, 2);
        MLEvent.add(setMLColor);
        MLEvent.add(disableML);
        MLEvent.add(ifMidRow);
        MLEvent.add(ifLeftCol);
        MLEvent.add(nextTurn);

        ArrayList<Node> MMEvent = new ArrayList<Node>();
        OpNode setMMColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileMM, 1).setOperand(getCurrColor, 2);
        OpNode disableMM = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonMM, 1).setOperand(falseVal, 2);
        MMEvent.add(setMMColor);
        MMEvent.add(disableMM);
        MMEvent.add(ifMidRow);
        MMEvent.add(ifMidCol);
        MMEvent.add(ifBackDiag);
        MMEvent.add(ifForwardDiag);
        MLEvent.add(nextTurn);

        ArrayList<Node> MREvent = new ArrayList<Node>();
        OpNode setMRColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileMR, 1).setOperand(getCurrColor, 2);
        OpNode disableMR = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonMR, 1).setOperand(falseVal, 2);
        MREvent.add(setMRColor);
        MREvent.add(disableMR);
        MREvent.add(ifMidRow);
        MREvent.add(ifRightCol);
        MREvent.add(nextTurn);

        ArrayList<Node> BLEvent = new ArrayList<Node>();
        OpNode setBLColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileBL, 1).setOperand(getCurrColor, 2);
        OpNode disableBL = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonBL, 1).setOperand(falseVal, 2);
        BLEvent.add(setBLColor);
        BLEvent.add(disableBL);
        BLEvent.add(ifBotRow);
        BLEvent.add(ifLeftCol);
        BLEvent.add(ifForwardDiag);
        BLEvent.add(nextTurn);

        ArrayList<Node> BMEvent = new ArrayList<Node>();
        OpNode setBMColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileBM, 1).setOperand(getCurrColor, 2);
        OpNode disableBM = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonBM, 1).setOperand(falseVal, 2);
        BMEvent.add(setBMColor);
        BMEvent.add(disableBM);
        BMEvent.add(ifBotRow);
        BMEvent.add(ifMidCol);
        BMEvent.add(nextTurn);

        ArrayList<Node> BREvent = new ArrayList<Node>();
        OpNode setBRColor = NodeMaker.makeNode("pset").setOperand(colorTrait, 0).setOperand(tileBR, 1).setOperand(getCurrColor, 2);
        OpNode disableBR = NodeMaker.makeNode("pset").setOperand(enabledTrait, 0).setOperand(buttonBR, 1).setOperand(falseVal, 2);
        BREvent.add(setBRColor);
        BREvent.add(disableBR);
        BREvent.add(ifBotRow);
        BREvent.add(ifRightCol);
        BREvent.add(ifBackDiag);
        BREvent.add(nextTurn);

        events.put("TLEvent", TLEvent);
        events.put("TMEvent", TMEvent);
        events.put("TREvent", TREvent);
        events.put("MLEvent", MLEvent);
        events.put("MMEvent", MMEvent);
        events.put("MREvent", MREvent);
        events.put("BLEvent", BLEvent);
        events.put("BMEvent", BMEvent);
        events.put("BREvent", BREvent);

        return events;
    }
}