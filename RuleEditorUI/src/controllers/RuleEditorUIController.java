package RuleEditorUI.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RuleEditorUIController implements Initializable {
  
  @FXML
  private Button addSqrBtn;
  @FXML
  private Button addCrclBtn;
  @FXML
  private AnchorPane editorPane;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Implement
  }

  @FXML
  private void handleAddSqrBtn(ActionEvent event) {
    Rectangle rect = new Rectangle(120, 120, Color.GREY);
    StackPane stack = new StackPane();
    Label label = new Label("Set Property");
    label.setTextFill(Color.WHITE);
    TextField textField = new TextField();
    textField.setMaxWidth(95);
    textField.setPrefWidth(95);

    stack.getChildren().addAll(rect, label, textField);

    //Align the label and text field inside of the rectangle
    stack.setAlignment(label, Pos.TOP_CENTER);
    stack.setAlignment(textField, Pos.CENTER);

    //Add stackpane to top left portion of editorPane
    stack.setTranslateX(editorPane.getWidth() / 4);
    stack.setTranslateY(editorPane.getHeight() / 4);

    editorPane.getChildren().addAll(stack);

    //Make the stackpane draggable
    makeDraggable(stack);
  }

  @FXML
  private void handleAddCrclBtn(ActionEvent event) {
    StackPane stackPane = new StackPane();
    //Label label = new Label("Hi");
    //Text label = new Text("text");
    // label.setBoundsType(TextBoundsType.VISUAL);
    // Circle crcl = new Circle(45, 45, 45, Color.GREY);
    // //Add circle to top left portion of editorPane
    // crcl.setTranslateX(editorPane.getWidth() / 4);
    // crcl.setTranslateY(editorPane.getHeight() / 4);
    // //crcl.radiusProperty().bind(label.widthProperty());
    // //crcl.setRadius(label.getWidth() / 2 + 10);

    stackPane.getChildren().addAll(new Circle(100,100,100,Color.GREY), new Label("GO!"));
    
    //editorPane.getChildren().addAll(crcl);
    editorPane.getChildren().addAll(stackPane);
    
    //Make the circle draggable
    //makeDraggable(crcl);
    makeDraggable(stackPane);
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
