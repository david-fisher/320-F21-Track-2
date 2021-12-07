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

public class PickTemplateOrExistingController {

	@FXML
	private Button backToMenu;
	
    @FXML
    public void goBack(ActionEvent event) throws IOException {
		Parent editor = FXMLLoader.load(getClass().getResource("../fxmlFiles/TemplateOrFresh.fxml"));
		Scene editorScene = new Scene(editor);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editorScene);
		window.show();
    }
	
}
