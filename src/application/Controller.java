package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button exitButton;
    
    @FXML
    private AnchorPane shapeCanvas;
    
    @FXML
    private Button genCircleButton;
    
    @FXML
    private Button genSquareButton;
    

    @FXML
    void exitProgram(ActionEvent event) {
    	Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    Draggable draggable = new Draggable();
    
    @FXML
    public void initialize() {
    	exitButton.setCursor(Cursor.HAND);
    }
    
    @FXML
    void genCircle(ActionEvent event) {
    	Circle c;
    	c = new Circle(312, 300, 30, Color.BLACK);
    	shapeCanvas.getChildren().add(c);
    	draggable.makeDraggable(c);
    }
    
    @FXML
    void genSquare(ActionEvent event) {
    	Rectangle r;
    	r = new Rectangle(60,60,Color.BLACK);
    	r.setX(312);
    	r.setY(300);
    	shapeCanvas.getChildren().add(r);
    	draggable.makeDraggable(r);

    }
}
