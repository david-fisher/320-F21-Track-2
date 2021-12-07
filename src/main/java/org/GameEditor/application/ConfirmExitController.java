package application.Controllers;

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
    private Button save;
    
    @FXML
    private Button cancel;
    
    @FXML
    public void returnToMenu(ActionEvent event) throws IOException {
		Parent editor = FXMLLoader.load(getClass().getResource("TemplateOrFresh.fxml"));
		Scene editorScene = new Scene(editor);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editorScene);
		window.show();
    }
    
    @FXML
    public void saveButton(ActionEvent event) throws IOException {
		Parent editor = FXMLLoader.load(getClass().getResource("SaveAsOrSave.fxml"));
		Scene editorScene = new Scene(editor);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editorScene);
		window.show();
    }
    
    @FXML
    public void returnToEditor(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    
}
