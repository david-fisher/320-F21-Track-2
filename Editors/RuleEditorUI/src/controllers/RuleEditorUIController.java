package editors.rule_editor_ui.controllers;

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

import editors.main_menu.MainMenu;
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

import editors.rule_editor_ui.blocks.*;
import engine.*;
import nodes.*;
import objects.*;

import java.util.ArrayList;

public class RuleEditorUIController implements Initializable {
  @FXML
  private AnchorPane editorPane;
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
  private boolean isBlueFirstRect = false; // allowing connect only blue and gray

  //temp
  private Block psetParent;

  //temp
  //List of the literl nodes made so far
  private ArrayList<TextBlock> textBlockList = new ArrayList<TextBlock>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Implement
  }

  //temp
  @FXML 
  private void handleSaveBtn(ActionEvent event) {
    //Initializaiton of GameState stuff
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

    //Set values of literal nodes to the values in their text boxes
    for(int i = 0; i < textBlockList.size(); i++) {
      String text = textBlockList.get(i).getFieldText();
      textBlockList.get(i).getLiteralNode().setValue(text);
      //System.out.println(textBlockList.get(i).getLiteralNode().value);
    }

    //Run the rules
    interpreter.interpretRule(psetParent.getNode(), state);
    System.out.println("Content of register: " + state.registers.toString());
    System.out.println("Player2 money is currently at: " + state.findObject("gamepiece02").getTrait("money"));
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
    // start to draw line
    if (startLineX == -1){
      startLineX = block.getBlock().getTranslateX();
      startLineY = block.getBlock().getTranslateY() + block.getBlockHeight()/2;
      startBlock = block;
      isBlueFirstRect = true;
    }
    // this happens after we clicked on the first rect, this is the second click
    else if (startLineX != -1 && endLineX == -1 && !isBlueFirstRect){
      endLineX = block.getBlock().getTranslateX();
      endLineY = block.getBlock().getTranslateY() + block.getBlockHeight()/2;
      Line link = new Line (startLineX, startLineY, endLineX, endLineY);
      editorPane.getChildren().add(link);
      startLineX = startLineY = endLineX = endLineY = -1;

      //temp
      System.out.println("Block " + startBlock + " connected to " + block);

      if (block instanceof TextBlock) {
        //We must check this because only TextBlock has the method getLiteralNode().
        ((OpNode)startBlock.getNode()).setOperand(((TextBlock)block).getLiteralNode(), operandIndex);
      }
      else if (startBlock.getNode() instanceof OpNode) {
        ((OpNode)startBlock.getNode()).setOperand(block.getNode(), operandIndex);
      }
      // now we can do somethings with 2 blocks. the first one is startBlock, second is block (the result block)
    }
  }

  private void drawLineGrayRect(Block block, final int order, final int opIndex){
    if (startLineX == -1){
      startLineX = block.getBlock().getTranslateX() + block.getBlockWidth();
      startLineY = block.getBlock().getTranslateY() + 20+order*10 + (order-1)*block.getGreyRectHeight() + 1/2*block.getGreyRectHeight();

      //temp
      startBlock = block;
      operandIndex = opIndex;
    }
    else if (startLineX != -1 && endLineX == -1 && isBlueFirstRect){
      endLineX = block.getBlock().getTranslateX() + block.getBlockWidth();
      endLineY = block.getBlock().getTranslateY() + 20+order*10 + (order-1)*block.getGreyRectHeight() + 1/2*block.getGreyRectHeight();
      Line link = new Line (startLineX, startLineY, endLineX, endLineY);
      editorPane.getChildren().add(link);
      startLineX = startLineY = endLineX = endLineY = -1;
      isBlueFirstRect = false;
      // now we can do somethings with 2 blocks. the first one is startBlock, second is block (the result block)
    }
  }

  private void drawLine(Block block){
    //Check this because SequenceNode doesn't have a result rectangle
    if (block.getResultRect() != null) {
      block.getResultRect().setOnMouseClicked(e -> {
        drawLineResultRect(block);
      });
    }

    ObservableList<Node> listGrayRect = block.getGrayRect();
    for (int i = 0; i < listGrayRect.size(); i++){
      Node node = listGrayRect.get(i);
      final int order = i + 1;
      final int index = i;
      node.setOnMouseClicked(e -> {
        drawLineGrayRect(block, order, index);
      });
    }
  }

  private void blockActions(Block block){
    placeBlock(block);
    drawLine(block);
    resizeAnchorPane();
  }

  @FXML
  private void handleAddPsetBtn(ActionEvent event) {
    PSetBlock pset = new PSetBlock();
    this.psetParent = pset;
    blockActions(pset);
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
  private void handleAddCondition(ActionEvent event) {
    // ConditionBlock condtBlock = new ConditionBlock();

    // condtBlock.setTopLeft(editorPane);
    // editorPane.getChildren().addAll(condtBlock.getBlock());
    // resizeAnchorPane();
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
    //blockActions(new SequenceBlock());
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
