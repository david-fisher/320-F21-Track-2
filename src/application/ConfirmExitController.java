package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmExitController {

    @FXML
    private Button exit;
    
    @FXML
    private Button cancel;
    
    @FXML
    public void returnToMenu(ActionEvent event) /*throws IOException*/ {
    	//close this scene, change it back to the menu
    }
    
    @FXML
    public void returnToEditor(ActionEvent event) throws IOException {
    	Parent editor = FXMLLoader.load(getClass().getResource("GameCreator.fxml"));
    	Scene saveScene = new Scene(editor);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(saveScene);
    	window.show();
    }
    
}
