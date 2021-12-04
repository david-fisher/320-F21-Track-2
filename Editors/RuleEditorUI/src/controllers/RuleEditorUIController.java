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

  //List of the TextBlocks made so far.
  //Need this to get the text from them and set LiteralNode values once save button is pressed.
  private ArrayList<TextBlock> textBlockList = new ArrayList<TextBlock>();
  //List of the SequenceBlocks made so far.
  //Need this to parse all the trees once the save button is pressed.
  private ArrayList<SequenceBlock> seqBlockList = new ArrayList<SequenceBlock>();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Implement
  }

  //temp
  @FXML 
  private void handleSaveBtn(ActionEvent event) {
    //Set values of literal nodes to the values in their text boxes
    for(int i = 0; i < textBlockList.size(); i++) {
      String text = textBlockList.get(i).getFieldText();
      textBlockList.get(i).getLiteralNode().setValue(text);
      //System.out.println(textBlockList.get(i).getLiteralNode().value);
    }
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

      //If startBlock is a SequenceBlock it has null node field so we don't want to run setOperand,
      //we simply want to set the parentPtr of the SequenceBlock
      if (startBlock instanceof SequenceBlock) {
        //Check for instanceof TextBlock because only TextBlock has getLiteralNode() method
        if (block instanceof TextBlock) {
          ((SequenceBlock)startBlock).setParentPtr(((TextBlock)block).getLiteralNode());
          System.out.println(((SequenceBlock)startBlock).getParentPtr());
        }
        else if (block.getNode() instanceof OpNode) {
          ((SequenceBlock)startBlock).setParentPtr(block.getNode());
          System.out.println(((SequenceBlock)startBlock).getParentPtr());
        }
      }
      else if (block instanceof TextBlock) {
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
    SequenceBlock seqBlock = new SequenceBlock();
    seqBlockList.add(seqBlock);
    blockActions(seqBlock);
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
