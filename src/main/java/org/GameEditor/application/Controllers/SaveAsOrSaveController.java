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

public class SaveAsOrSaveController {
	
	@FXML
	private Button save;
	
	@FXML
	private Button saveAs;
	
	@FXML
	public void saveButton(ActionEvent event) {
		
	}
	
	@FXML
	public void saveAsButton(ActionEvent event) throws IOException {
		//maybe try creating a type of file or array filled with objects with the name *input* and create objects with the type of tile and it's xy coordinates
		//when it comes time to load, iterate through the objects and create them based on the attributes they have
		Parent editor = FXMLLoader.load(getClass().getResource("../fxmlFiles/SaveAs.fxml"));
		Scene editorScene = new Scene(editor);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editorScene);
		window.show();
	}

}
