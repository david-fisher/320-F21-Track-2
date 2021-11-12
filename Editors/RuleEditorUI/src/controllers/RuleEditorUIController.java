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

import editors.rule_editor_ui.blocks.RsetBlock;
import editors.rule_editor_ui.blocks.PsetBlock;
import editors.rule_editor_ui.blocks.GetBlock;

public class RuleEditorUIController implements Initializable {
  @FXML
  private AnchorPane editorPane;

  final Color SILVER = Color.rgb(200,200,200);
  final Color GREY = Color.rgb(55,55,55);
  final Color WHITE = Color.rgb(255,255,255);
  final int HEADER_SIZE = 15;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Implement
  }

  @FXML
  private void handleAddPsetBtn(ActionEvent event) {
    PsetBlock pset = new PsetBlock();

    //Place the block in top left of the screen
    pset.setTopLeft(editorPane);
    //Add the stackpane to the editor pane
    editorPane.getChildren().addAll(pset.getBlock());
  }

  @FXML
  private void handleAddRsetBtn(ActionEvent event) {
    RsetBlock rset = new RsetBlock();

    //Place the block in top left of the screen
    rset.setTopLeft(editorPane);
    //Add the stackpane to the editor pane
    editorPane.getChildren().addAll(rset.getBlock());
  }

  @FXML
  private void handleAddGetBtn(ActionEvent event) {
    GetBlock get = new GetBlock();

    //Place block in the top left of the screen
    get.setTopLeft(editorPane);
    //Add the stackpane to the editor pane
    editorPane.getChildren().addAll(get.getBlock());
  }
}
