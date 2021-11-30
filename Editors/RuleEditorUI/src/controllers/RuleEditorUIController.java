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

import editors.rule_editor_ui.blocks.Block;
import editors.rule_editor_ui.blocks.RSetBlock;
import editors.rule_editor_ui.blocks.PSetBlock;
import editors.rule_editor_ui.blocks.GetBlock;
import editors.rule_editor_ui.blocks.NotBlock;
import editors.rule_editor_ui.blocks.BinOpBlock;
import editors.rule_editor_ui.blocks.LengthBlock;
import editors.rule_editor_ui.blocks.GetTileIndexBlock;
import editors.rule_editor_ui.blocks.MoveToTileIndexBlock;
import editors.rule_editor_ui.blocks.MoveByStepsBlock;
import editors.rule_editor_ui.blocks.GetPlayerByIndexBlock;
import editors.rule_editor_ui.blocks.GetNextPlayerBlock;
import editors.rule_editor_ui.blocks.InvokeBlock;
import editors.rule_editor_ui.blocks.DeckDrawBlock;
import editors.rule_editor_ui.blocks.DeckPutBlock;
import editors.rule_editor_ui.blocks.DeckShuffleBlock;
import editors.rule_editor_ui.blocks.UseDieSpinnerBlock;
import editors.rule_editor_ui.blocks.TextBlock;

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

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Implement
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
    if (startLineX == -1){
      startLineX = block.getBlock().getTranslateX();
      startLineY = block.getBlock().getTranslateY() + block.getBlockHeight()/2;
    }
    else if (startLineX != -1 && endLineX == -1){
      endLineX = block.getBlock().getTranslateX();
      endLineY = block.getBlock().getTranslateY() + block.getBlockHeight()/2;
      Line link = new Line (startLineX, startLineY, endLineX, endLineY);
      editorPane.getChildren().add(link);
      startLineX = startLineY = endLineX = endLineY = -1;
    }
  }

  private void drawLineGrayRect(Block block, final int order){
    if (startLineX == -1){
      startLineX = block.getBlock().getTranslateX() + block.getBlockWidth();
      startLineY = block.getBlock().getTranslateY() + order*block.getGreyRectHeight();
    }
    else if (startLineX != -1 && endLineX == -1){
      endLineX = block.getBlock().getTranslateX() + block.getBlockWidth();
      endLineY = block.getBlock().getTranslateY() + order*block.getGreyRectHeight();
      Line link = new Line (startLineX, startLineY, endLineX, endLineY);
      editorPane.getChildren().add(link);
      startLineX = startLineY = endLineX = endLineY = -1;
    }
  }

  private void drawLine(Block block){
    block.getResultRect().setOnMouseClicked(e -> {
      drawLineResultRect(block);
    });

    ObservableList<Node> listGrayRect = block.getGrayRect();
    for (int i = 0; i < listGrayRect.size(); i++){
      Node node = listGrayRect.get(i);
      final int order = i +1;
      node.setOnMouseClicked(e -> {
        drawLineGrayRect(block, order);
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
    blockActions(new TextBlock());
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
