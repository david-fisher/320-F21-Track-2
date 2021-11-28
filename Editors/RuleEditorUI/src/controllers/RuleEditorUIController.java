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

import editors.rule_editor_ui.blocks.RSetBlock;
import editors.rule_editor_ui.blocks.PSetBlock;
import editors.rule_editor_ui.blocks.GetBlock;
import editors.rule_editor_ui.blocks.NotBlock;
import editors.rule_editor_ui.blocks.BinOpBlock;
import editors.rule_editor_ui.blocks.LengthBlock;
// import editors.rule_editor_ui.blocks.GetTileIndexBlock;
// import editors.rule_editor_ui.blocks.GetTileConnBlock;
// import editors.rule_editor_ui.blocks.ConditionBlock;
// import editors.rule_editor_ui.blocks.MoveToTileIndexBlock;
// import editors.rule_editor_ui.blocks.MoveByStepsBlock;
// import editors.rule_editor_ui.blocks.MoveToPosBlock;
import editors.rule_editor_ui.blocks.Block;

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
    // GetTileIndexBlock tileIndex = new GetTileIndexBlock();

    // tileIndex.setTopLeft(editorPane);
    // editorPane.getChildren().addAll(tileIndex.getBlock());
    // resizeAnchorPane();
  }

  @FXML
  private void handleAddGetTileConnBtn(ActionEvent event) {
    // GetTileConnBlock conn = new GetTileConnBlock();

    // conn.setTopLeft(editorPane);
    // editorPane.getChildren().addAll(conn.getBlock());
    // resizeAnchorPane();
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
    // MoveToTileIndexBlock tileMove = new MoveToTileIndexBlock();

    // tileMove.setTopLeft(editorPane);
    // editorPane.getChildren().addAll(tileMove.getBlock());
    // resizeAnchorPane();
  }

  @FXML
  private void handleAddMoveByStepsBtn(ActionEvent event) {
    // MoveByStepsBlock steps = new MoveByStepsBlock();

    // steps.setTopLeft(editorPane);
    // editorPane.getChildren().addAll(steps.getBlock());
    // resizeAnchorPane();
  }

  @FXML
  private void handleAddMoveToPosBtn(ActionEvent event) {
    // MoveToPosBlock pos = new MoveToPosBlock();

    // pos.setTopLeft(editorPane);
    // editorPane.getChildren().addAll(pos.getBlock());
    // resizeAnchorPane();
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
