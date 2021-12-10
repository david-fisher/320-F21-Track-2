package org.Editors.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import org.Editors.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import javafx.scene.input.MouseEvent; 
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import java.lang.NumberFormatException;
import java.util.Collections;

import org.Editors.blocks.*;
import org.RuleEngine.engine.*;
import org.RuleEngine.nodes.*;
import org.GameObjects.objects.*;

import java.util.ArrayList;

//TODO: Change handleIf and handleWhile to not solely use placeBlock
//TODO: Create verificaiton method in handleSaveBtn
//TODO: Create method for handling errorLabel

public class RuleEditorUIController implements Initializable {
  @FXML
  private AnchorPane editorPane;
  @FXML
  private Label errorLabel;

  double WIDTH = 400.00;
  double HEIGHT = 475.00;

  final Color SILVER = Color.rgb(200, 200, 200);
  final Color GREY = Color.rgb(55, 55, 55);
  final Color WHITE = Color.rgb(255, 255, 255);
  final int HEADER_SIZE = 15;

  protected double startLineX = -1;
  protected double startLineY = -1;
  protected double endLineX = -1;
  protected double endLineY = -1;

  private Block startBlock;
  private int operandIndex;
  private int currRuleGroupID;
  private boolean isBlueFirstRect = false; // allowing connect only blue and gray

  //List of the TextBlocks made so far.
  //Need this to get the text from them and set LiteralNode values once save button is pressed.
  private ArrayList<TextBlock> textBlockList = new ArrayList<TextBlock>();
  //List of the SequenceBlocks made so far.
  //Need this to parse all the trees once the save button is pressed.
  private ArrayList<SequenceBlock> seqBlockList = new ArrayList<SequenceBlock>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Implement
    errorLabel.setOnMouseClicked((evt) -> errorLabel.setOpacity(0.0));
  }

  /**
   * This method to increase the size of the editorPane. It is used when we add
   * more blocks to editor pane.
   */
  private void resizeAnchorPane() {
    WIDTH = WIDTH + 30;
    HEIGHT = HEIGHT + 30;
    editorPane.setPrefSize(WIDTH, HEIGHT);
  }

  //Helper to place blocks in the editorpane
  private void placeBlock(Block block) {
    //Place the block in top left of the screen
    block.setTopLeft(editorPane);
    //Add the stackpane of the block to the editor pane
    editorPane.getChildren().addAll(block.getBlock());
  }

  private void drawLineResultRect(Block block){
    // this happens after we clicked on the first rect, this is the second click
    if (startLineX != -1 && endLineX == -1 && !isBlueFirstRect && block != startBlock){
      endLineX = block.getBlock().getTranslateX();
      endLineY = block.getBlock().getTranslateY() + block.getBlockHeight()/2;
      Line link = new Line (startLineX, startLineY, endLineX, endLineY);
      editorPane.getChildren().add(link);
      startLineX = startLineY = endLineX = endLineY = -1;

      //temp
      System.out.println("Block " + startBlock + " connected to " + block);
      System.out.println("currGroupID: " + currRuleGroupID);
      //If startBlock is a SequenceBlock it has null node field so we don't want to run setOperand,
      //we simply want to set the parentPtr of the SequenceBlock
      if (startBlock instanceof SequenceBlock) {
        //Check for instanceof TextBlock because only TextBlock has getLiteralNode() method
        if (block instanceof TextBlock) {
          ((SequenceBlock)startBlock).setParentPtr(((TextBlock)block).getLiteralNode());
        }
        else if (block.getNode() instanceof OpNode) {
          ((SequenceBlock)startBlock).setParentPtr(block.getNode());
        }
      }
      else if (block instanceof TextBlock) {
        //We must check this because only TextBlock has the method getLiteralNode().
        ((OpNode)startBlock.getNode()).setOperandInGroup(((TextBlock)block).getLiteralNode(), operandIndex, currRuleGroupID);
      }
      else if (startBlock.getNode() instanceof OpNode) {
        ((OpNode)startBlock.getNode()).setOperandInGroup(block.getNode(), operandIndex, currRuleGroupID);
      }
      // now we can do somethings with 2 blocks. the first one is startBlock, second is block (the result block)
    }
  }

  private void drawLineGrayRect(Block block, final int order, final int opIndex, final int ruleGroupID){
    if (startLineX == -1){
      startLineX = block.getBlock().getTranslateX() + block.getBlockWidth();
      startLineY = block.getBlock().getTranslateY() + 20+order*10 + (order-1)*block.getGreyRectHeight() + 1/2*block.getGreyRectHeight();

      //Keep track of the block we want to draw to when the user clicks the blue connection
      startBlock = block;
      //Keep track of which gray connection block the user clicked
      operandIndex = opIndex;
      //Keep track of the rule group that the gray connection block is apart of
      currRuleGroupID = ruleGroupID;
    }
  }

  private void drawLine(Block block){
    //Check this because SequenceNode doesn't have a result rectangle
    if (block.getResultRect() != null) {
      block.getResultRect().setOnMouseClicked(e -> {
        drawLineResultRect(block);
      });
    }

    //Get the list of rule groups from this block
    ObservableList<ObservableList<javafx.scene.Node>> ruleGroupList = block.getRuleGroupList();
    for (int i = 0; i < ruleGroupList.size(); i++) {
      ObservableList<javafx.scene.Node> ruleGroup = ruleGroupList.get(i);
      for(int j = 0; j < ruleGroup.size(); j++) {
        //For calculating the positioning of lines when making connections
        final int order = j + 1;
        //Pass the index for when user adds lines out of order
        final int index = j;
        //Index of the rulegroup this connection block is apart of
        final int ruleGroupID = i;
        //If we have a While or If block, we need to add some null operands to their operand lists for 
        //their additional rule groups. This is because their operands lists for rule groups greater than 0
        //are of size 0 initially. Rule group 0 always has only 1 operand and it's already initialized for us
        //so we don't need to add to that one.
        if (((block instanceof WhileBlock) || (block instanceof IfBlock)) && (i > 0)) {
          if (block.getNode() instanceof OpNode) {
            ((OpNode)block.getNode()).addOperandToGroup(null, i);
          }
        }
        javafx.scene.Node node = ruleGroup.get(j);
        node.setOnMouseClicked(e -> {
          drawLineGrayRect(block, order, index, ruleGroupID);
        });
      }
    }
  }

  private void blockActions(Block block){
    placeBlock(block);
    drawLine(block);
    resizeAnchorPane();
  }
  
  //Display the error message in the error label
  private void displayError(String errMsg) {
    errorLabel.setText(errMsg);
    errorLabel.setOpacity(1.0);
  }

  //temp
  @FXML 
  private void handleSaveBtn(ActionEvent event) {
    if (startBlock.getNode() instanceof OpNode) {
      ArrayList<ArrayList<org.RuleEngine.nodes.Node>> operandsList = ((OpNode)startBlock.getNode()).getAllOperands();
      for(int i = 0; i < operandsList.size(); i++) {
        System.out.print(i + " rule group:");
        System.out.println(" (size = " + operandsList.get(i).size() + ")");
        for(int j = 0; j < operandsList.get(i).size(); j++) {
          System.out.println(operandsList.get(i).get(j));
        }
      }
    }

    //Set values of literal nodes to the values in their text boxes
    for(int i = 0; i < textBlockList.size(); i++) {
      String text = textBlockList.get(i).getFieldText();
      textBlockList.get(i).getLiteralNode().setValue(text);
      //System.out.println(textBlockList.get(i).getLiteralNode().value);
    }

    //Will hold values from sequence blocks as ints
    ArrayList<Integer> seqBlockVals = new ArrayList<Integer>();
    //Verify that sequenceBlocks have only numeric values & that they're > 0 & collect them into seqBlockVals
    for(int i = 0; i < seqBlockList.size(); i++) {
      Integer fieldVal = parseIntOrNull(seqBlockList.get(i).getFieldText());
      //Check that no sequence block contains a non-numeric value
      if (fieldVal == null) {
        displayError("Sequence block must only contain numeric values.");
        return;
      }
      else if (fieldVal <= 0) {
        displayError("Sequence block must only contain numbers greater than 0.");
        return;
      }
      seqBlockVals.add(fieldVal);
    }
    //Sort the vals from the sequence blocks
    Collections.sort(seqBlockVals);
    //Verify that the values start at 1
    if (seqBlockVals.size() > 0) {
      if (seqBlockVals.get(0) != 1) {
        displayError("Sequence blocks must start from 1.");
        return;
      }
    }
    //Verify that the values are sequential
    for(int i = 1; i < seqBlockVals.size(); i++) {
      if (seqBlockVals.get(i) - seqBlockVals.get(i-1) != 1) {
        displayError("Sequence blocks must contain numbers that consecutively have difference of only 1.");
        return;
      }
    }
  }

  @FXML
  private void handleAddPsetBtn(ActionEvent event) {
    blockActions(new PSetBlock());
  }

  @FXML
  private void handleAddRsetBtn(ActionEvent event) {
    blockActions(new RSetBlock());
  }

  @FXML
  private void handleAddGetBtn(ActionEvent event) {
    blockActions(new GetBlock());
  }

  @FXML
  private void handleAddBinOpBtn(ActionEvent event) {
    blockActions(new BinOpBlock());
  }

  @FXML
  private void handleAddNotBtn(ActionEvent event) {
    blockActions(new NotBlock());
  }

  @FXML
  private void handleAddLengthBtn(ActionEvent event) {
    blockActions(new LengthBlock());
  }

  @FXML
  private void handleAddGetTileIndexBtn(ActionEvent event) {
    blockActions(new GetTileIndexBlock());
  }

  @FXML
  private void handleAddMoveToTileIndexBtn(ActionEvent event) {
    blockActions(new MoveToTileIndexBlock());
  }

  @FXML
  private void handleAddMoveByStepsBtn(ActionEvent event) {
    blockActions(new MoveByStepsBlock());
  }

  @FXML
  private void handleAddGetPlayerByIndexBtn(ActionEvent event) {
    blockActions(new GetPlayerByIndexBlock());
  }

  @FXML
  private void handleAddGetNextPlayerBtn(ActionEvent event) {
    blockActions(new GetNextPlayerBlock());
  }

  @FXML
  private void handleAddInvokeBtn(ActionEvent event) {
    blockActions(new InvokeBlock());
  }

  @FXML
  private void handleAddDeckDrawBtn(ActionEvent event) {
    blockActions(new DeckDrawBlock());
  }

  @FXML
  private void handleAddDeckPutBtn(ActionEvent event) {
    blockActions(new DeckPutBlock());
  }

  @FXML
  private void handleAddDeckShuffleBtn(ActionEvent event) {
    blockActions(new DeckShuffleBlock());
  }

  @FXML
  private void handleAddUseDieSpinnerBtn(ActionEvent event) {
    blockActions(new UseDieSpinnerBlock());
  }

  @FXML 
  private void handleAddTextNodeBtn(ActionEvent event) {
    TextBlock txtBlock = new TextBlock();
    textBlockList.add(txtBlock);
    blockActions(txtBlock);
  }

  @FXML
  private void handleAddSeqNodeBtn(ActionEvent event) {
    SequenceBlock seqBlock = new SequenceBlock();
    seqBlockList.add(seqBlock);
    blockActions(seqBlock);
  }

  private Integer parseIntOrNull(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private Integer dialogParseIntOrNull(Optional<String> value) {
    try {
      return Integer.parseInt(value.get());
    } catch (NumberFormatException e) {
      return null;
    }
  }

  //Helps get number of statements for while block from user
  private Integer getWhileStmntsInputDialog() {
    TextInputDialog dialog = new TextInputDialog();

    dialog.setHeaderText("Input # of statements");
    dialog.setContentText("Statements:");

    Optional<String> result = dialog.showAndWait();
    return dialogParseIntOrNull(result);
  }

  private String getIfStmntsInputDialog() {
    TextInputDialog dialog = new TextInputDialog();

    dialog.setHeaderText("Input # of statements for if & else sep. by comma");
    dialog.setContentText("Statements:");

    Optional<String> result = dialog.showAndWait();
    return result.get();
  }

  @FXML
  private void handleAddWhileBtn(ActionEvent event) {
    Integer numStmnts = getWhileStmntsInputDialog();
    if (numStmnts != null) {
      blockActions(new WhileBlock(numStmnts));
    }
    else if (numStmnts == null) {
      displayError("Input must be a number.");
    }
  }

  @FXML
  private void handleAddIfBtn(ActionEvent event) {
    String[] strInput = getIfStmntsInputDialog().split(",");
    //Check that user provided correct number of inputs
    if (strInput.length != 2) {
      displayError("Invalid length: must input exactly 2 numbers.");
      return;
    }
    //Check that user only input numbers
    for(int i = 0; i < strInput.length; ++i) {
      //Remove any spaces within the string
      strInput[i] = strInput[i].replaceAll("\\s", "");
      //Check that string only contains numeric chars
      if (!strInput[i].matches("[0-9]+")) {
        displayError("Invalid character(s): must input only numeric values.");
        return;
      }
    }
    Integer numIfStmnts = parseIntOrNull(strInput[0]);
    Integer numElseStmnts = parseIntOrNull(strInput[1]);
    if ((numIfStmnts != null) && (numElseStmnts != null)) {
      blockActions(new IfBlock((int)numIfStmnts, (int)numElseStmnts));
    }
    else {
      System.out.println("Unknown error encountered in handleAddIfBtn");
    }
    //blockActions(new IfBlock());
  }

  private void constructBlockTree(org.RuleEngine.nodes.OpNode tree) {
      ArrayList<ArrayList<org.RuleEngine.nodes.Node>> children = tree.getAllOperands();
  }

  @FXML
  private void handleBackButton(ActionEvent event) {
    URL location = getClass().getResource("../../../resources/MainMenuScreen.fxml");
    try {
      Parent root = (Parent) FXMLLoader.load(location);
      MainMenu.stage.getScene().setRoot(root);
      MainMenu.stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

