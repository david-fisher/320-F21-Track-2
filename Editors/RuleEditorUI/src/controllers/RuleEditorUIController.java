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

import editors.main_menu.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class RuleEditorUIController implements Initializable {
  @FXML
  private AnchorPane editorPane;
  double WIDTH = 400.00;
  double HEIGHT = 475.00;

  final Color SILVER = Color.rgb(200, 200, 200);
  final Color GREY = Color.rgb(55, 55, 55);
  final Color WHITE = Color.rgb(255, 255, 255);
  final int HEADER_SIZE = 15;

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

  @FXML
  private void handleAddPsetBtn(ActionEvent event) {
    placeBlock(new PSetBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddRsetBtn(ActionEvent event) {
    placeBlock(new RSetBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddGetBtn(ActionEvent event) {
    placeBlock(new GetBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddBinOpBtn(ActionEvent event) {
    placeBlock(new BinOpBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddNotBtn(ActionEvent event) {
    placeBlock(new NotBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddLengthBtn(ActionEvent event) {
    placeBlock(new LengthBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddGetTileIndexBtn(ActionEvent event) {
    placeBlock(new GetTileIndexBlock());
    resizeAnchorPane();
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
    placeBlock(new MoveToTileIndexBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddMoveByStepsBtn(ActionEvent event) {
    placeBlock(new MoveByStepsBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddGetPlayerByIndexBtn(ActionEvent event) {
    placeBlock(new GetPlayerByIndexBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddGetNextPlayerBtn(ActionEvent event) {
    placeBlock(new GetNextPlayerBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddInvokeBtn(ActionEvent event) {
    placeBlock(new InvokeBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddDeckDrawBtn(ActionEvent event) {
    placeBlock(new DeckDrawBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddDeckPutBtn(ActionEvent event) {
    placeBlock(new DeckPutBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddDeckShuffleBtn(ActionEvent event) {
    placeBlock(new DeckShuffleBlock());
    resizeAnchorPane();
  }

  @FXML
  private void handleAddUseDieSpinnerBtn(ActionEvent event) {
    placeBlock(new UseDieSpinnerBlock());
    resizeAnchorPane();
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
