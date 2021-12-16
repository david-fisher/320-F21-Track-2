package org.RuleEngine.tests;

import java.util.ArrayList;
import java.util.Scanner;
import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;
import org.GameObjects.objects.*;
// WIP
/* 0 1 2
 * 3 4 5
 * 6 7 8
 */

public class TicTacToe {
    public static void main(String[] args) {
        // To be loading from Dining's save
        GameState state = new GameState(); 
        Interpreter interpreter = Interpreter.getInstance();
        
        Player player1 = new Player("player01", new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true);
        Player player2 = new Player("player02", new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true);
        
        Button bTL = new Button();
        bTL.setLabel("buttonTL");
        Button bTM = new Button();
        bTM.setLabel("buttonTM");
        Button bTR = new Button();
        bTR.setLabel("buttonTR");
        Button bML = new Button();
        bML.setLabel("buttonML");
        Button bMM = new Button();
        bMM.setLabel("buttonMM");
        Button bMR = new Button();
        bMR.setLabel("buttonMR");
        Button bBL = new Button();
        bBL.setLabel("buttonBL");
        Button bBM = new Button();
        bBM.setLabel("buttonBM");
        Button bBR = new Button();
        bBR.setLabel("buttonBR");
        
        Tile tTL = new Tile();
        tTL.setLabel("tileTL");
        Tile tTM = new Tile();
        tTM.setLabel("tileTM");
        Tile tTR = new Tile();
        tTR.setLabel("tileTR");
        Tile tML = new Tile();
        tML.setLabel("tileML");
        Tile tMM = new Tile();
        tMM.setLabel("tileMM");
        Tile tMR = new Tile();
        tMR.setLabel("tileMR");
        Tile tBL = new Tile();
        tBL.setLabel("tileBL");
        Tile tBM = new Tile();
        tBM.setLabel("tileBM");
        Tile tBR = new Tile();
        tBR.setLabel("tileBR");
        
        state.players.add(player1);
        state.players.add(player2);
        state.addRegistry("currPlayer", player1);
        
        state.buttons.add(bTL);
        state.buttons.add(bTM);
        state.buttons.add(bTR);
        state.buttons.add(bML);
        state.buttons.add(bMM);
        state.buttons.add(bMR);
        state.buttons.add(bBL);
        state.buttons.add(bBM);
        state.buttons.add(bBR);
        
        state.tiles.add(tTL);
        state.tiles.add(tTM);
        state.tiles.add(tTR);
        state.tiles.add(tML);
        state.tiles.add(tMM);
        state.tiles.add(tMR);
        state.tiles.add(tBL);
        state.tiles.add(tBM);
        state.tiles.add(tBR);

                
        LiteralNode<String> colorTrait = NodeMaker.makeStringNode("color");
        LiteralNode<String> currPlayer = NodeMaker.makeStringNode("currPlayer");
        LiteralNode<String> enabledTrait = NodeMaker.makeStringNode("enabled");
        LiteralNode<Boolean> falseVal = new LiteralNode<Boolean>(false);
        
        // Rename these with the actual button name setup.
        LiteralNode<String> buttonTL = NodeMaker.makeStringNode("_buttonTL");
        LiteralNode<String> buttonTM = NodeMaker.makeStringNode("_buttonTM");
        LiteralNode<String> buttonTR = NodeMaker.makeStringNode("_buttonTR");
        LiteralNode<String> buttonML = NodeMaker.makeStringNode("_buttonML");
        LiteralNode<String> buttonMM = NodeMaker.makeStringNode("_buttonMM");
        LiteralNode<String> buttonMR = NodeMaker.makeStringNode("_buttonMR");
        LiteralNode<String> buttonBL = NodeMaker.makeStringNode("_buttonBL");
        LiteralNode<String> buttonBM = NodeMaker.makeStringNode("_buttonBM");
        LiteralNode<String> buttonBR = NodeMaker.makeStringNode("_buttonBR");
        
        // Rename these with the actual tile name setup.
        LiteralNode<String> tileTL = NodeMaker.makeStringNode("_tileTL");
        LiteralNode<String> tileTM = NodeMaker.makeStringNode("_tileTM");
        LiteralNode<String> tileTR = NodeMaker.makeStringNode("_tileTR");
        LiteralNode<String> tileML = NodeMaker.makeStringNode("_tileML");
        LiteralNode<String> tileMM = NodeMaker.makeStringNode("_tileMM");
        LiteralNode<String> tileMR = NodeMaker.makeStringNode("_tileMR");
        LiteralNode<String> tileBL = NodeMaker.makeStringNode("_tileBL");
        LiteralNode<String> tileBM = NodeMaker.makeStringNode("_tileBM");
        LiteralNode<String> tileBR = NodeMaker.makeStringNode("_tileBR");

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
        
        String input = "";
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while (!input.equals("end")) {
            input = myObj.nextLine();
            System.out.println(state.getRegistry("currPlayer").getTrait("label") + " selected: " + input);
            switch(input) {
                case "TL":
                    if (!((Button)state.findObject("buttonTL")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(TLEvent, state);
                    System.out.println(((Button)state.findObject("buttonTL")).getEnabled());
                    break;
                case "TM":
                    if (!((Button)state.findObject("buttonTM")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(TMEvent, state);
                    break;
                case "TR":
                    if (!((Button)state.findObject("buttonTR")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(TREvent, state);
                    break;
                case "ML":
                    if (!((Button)state.findObject("buttonML")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(MLEvent, state);
                    break;
                case "MM":
                    if (!((Button)state.findObject("buttonMM")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(MMEvent, state);
                    break;
                case "MR":
                    if (!((Button)state.findObject("buttonMR")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(MREvent, state);
                    break;
                case "BL":
                    if (!((Button)state.findObject("buttonBL")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(BLEvent, state);
                    break;
                case "BM":
                    if (!((Button)state.findObject("buttonBM")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(BMEvent, state);
                    break;
                case "BR":
                    if (!((Button)state.findObject("buttonBR")).getEnabled()) {
                        System.out.println("Spot occupied. Please select another.");
                        break;
                    }
                    interpreter.interpretEvent(BREvent, state);
                    break;
                default:
                    if (!input.equals("end"))
                        System.out.println("Unknow input, try again.");
                        
            }
        }
        myObj.close();
    }
}