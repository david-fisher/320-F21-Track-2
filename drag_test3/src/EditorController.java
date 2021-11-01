import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class EditorController implements Initializable {
  
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
    Rectangle rect = new Rectangle(80, 80, Color.GREY);
    //Add square to top left portion of editorPane
    rect.setTranslateX(editorPane.getWidth() / 4);
    rect.setTranslateY(editorPane.getHeight() / 4);
    
    editorPane.getChildren().addAll(rect);
    
    //Make the rectangle draggable
    makeDraggable(rect);
  }

  @FXML
  private void handleAddCrclBtn(ActionEvent event) {
    Circle crcl = new Circle(45, 45, 45, Color.GREY);
    //Add circle to top left portion of editorPane
    crcl.setTranslateX(editorPane.getWidth() / 4);
    crcl.setTranslateY(editorPane.getHeight() / 4);
    
    editorPane.getChildren().addAll(crcl);
    
    //Make the circle draggable
    makeDraggable(crcl);
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
