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

public class TempOrFreshController {

    @FXML
    private Button back;

    @FXML
    private Button template;
    
    @FXML
    private Button newGame;
    
    @FXML
    //used to switch from the TemplateOrFresh class to the GameCreator class
    public void switchNewEditor(ActionEvent event) throws IOException {
		Parent editor = FXMLLoader.load(getClass().getResource("../fxmlFiles/GameCreator.fxml"));
		Scene editorScene = new Scene(editor);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editorScene);
		window.show();
    }
    
    public void switchToTemplateSelect(ActionEvent event) throws IOException {
		Parent editor = FXMLLoader.load(getClass().getResource("../fxmlFiles/PickTemplateOrExisting.fxml"));
		Scene editorScene = new Scene(editor);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editorScene);
		window.show();
    }

}
