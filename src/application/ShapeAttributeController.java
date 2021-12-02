package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class ShapeAttributeController {

    @FXML
    private ColorPicker tileColor;

    @FXML
    private Button tileImage;

    @FXML
    private TextField tileName;
    
    @FXML
    void setName(ActionEvent event) {
    	tileName.getText();
    }
    
    @FXML
    void setColor(ActionEvent event) {
    	
    }

    @FXML
    void setImage(ActionEvent event) {

    }

}