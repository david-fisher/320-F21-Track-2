package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Circle defaultCircle;

    @FXML
    private Rectangle defaultSquare;

    @FXML
    private Polygon defaultTriangle;

    @FXML
    private Button exitButton;

    @FXML
    void exitProgram(ActionEvent event) {
    	Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    Draggable draggable = new Draggable();
    
    @FXML
    public void initialize() {
        draggable.makeShapeDraggable(defaultCircle);
        draggable.makeShapeDraggable(defaultSquare);
        draggable.makeShapeDraggable(defaultTriangle);
    }
}
