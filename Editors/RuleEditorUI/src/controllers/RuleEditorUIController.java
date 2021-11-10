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
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RuleEditorUIController implements Initializable {
  
  @FXML
  private Button addPsetBtn;
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
    //Pane for placing the controls and text for the block
    GridPane grid = new GridPane();
    //Padding of top, right, & bottom to 10px; padding of left 0px
    grid.setPadding(new Insets(10, 0, 10, 0));
    grid.setMinSize(170, 140);
    grid.setPrefSize(170, 140);
    //Vertical gap between columns 10px; horizontal gap 5px
    grid.setVgap(10);
    grid.setHgap(5);
    //Set width of column 0 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set width of column 1 to 30px
    grid.getColumnConstraints().add(new ColumnConstraints(30));
    //Set width of column 2 to 100px
    grid.getColumnConstraints().add(new ColumnConstraints(85));
    //Set width of column 3 to 20px
    grid.getColumnConstraints().add(new ColumnConstraints(20));
    //Set height of row 0 to 5px
    grid.getRowConstraints().add(new RowConstraints(5));

    //Defining row 1 of grid
    Text prop = new Text("Prop.:");
    prop.setFill(WHITE);
    Rectangle propConnect = new Rectangle(19, 30, SILVER);
    TextField propField = new TextField();
    propField.setPrefColumnCount(10);
    grid.add(prop, 1, 1);
    grid.add(propConnect, 0, 1);
    grid.add(propField, 2, 1);
    
    //Defining row 2 of grid
    Text from = new Text("From:");
    from.setFill(WHITE);
    TextField fromField = new TextField();
    fromField.setPrefColumnCount(10);
    grid.add(from, 1, 2);
    grid.add(fromField, 2, 2);

    //Defining row 3 of grid
    Text value = new Text("Value:");
    value.setFill(WHITE);
    TextField valueField = new TextField();
    valueField.setPrefColumnCount(10);
    Rectangle valConnect = new Rectangle(19, 30, SILVER);
    Rectangle valOut = new Rectangle(19, 30, SILVER);
    grid.add(value, 1, 3);
    grid.add(valueField, 2, 3);
    grid.add(valConnect, 0, 3);
    grid.add(valOut, 3, 3);

    //Pane on which we stack the base Rectangle and grid GridPane
    StackPane stackPane = new StackPane();
    //Base visual of the stackpane
    Rectangle base = new Rectangle(170, 140, GREY);

    //Defining row 0 of grid
    Text name = new Text("pset");
    name.setFont(Font.font("Verdana", FontWeight.BOLD, HEADER_SIZE));
    name.setFill(WHITE);
    stackPane.setAlignment(name, Pos.TOP_CENTER);

    //Stack the base Rectangle, grid GridPane, and name of the block on the pane
    stackPane.getChildren().addAll(base, grid, name);
    //Make the stackpane draggable
    makeDraggable(stackPane);

    //Set the stackpane's position to the top left of the editor pane
    stackPane.setTranslateX(editorPane.getWidth() / 4);
    stackPane.setTranslateY(editorPane.getHeight() / 4);
    //Add the stackpane to the editor pane
    editorPane.getChildren().addAll(stackPane);
  }

  //This stuff should probably not be in this class

  private double startX;
  private double startY;

  private void makeDraggable(Node node) {
    node.setOnMousePressed(e -> {
      //calculate offset
      startX = e.getSceneX() - node.getTranslateX();
      startY = e.getSceneY() - node.getTranslateY();
    });

    node.setOnMouseDragged(e -> {
      //set new position
      node.setTranslateX(e.getSceneX() - startX);
      node.setTranslateY(e.getSceneY() - startY);
    });
  }
}
