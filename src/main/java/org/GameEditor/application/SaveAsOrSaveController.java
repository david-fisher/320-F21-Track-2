package org.GameEditor.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.GameObjects.objects.Savable;

import java.io.IOException;

public class SaveAsOrSaveController {
	
	@FXML
	private Button save;
	
	@FXML
	private Button saveAs;
	
	@FXML
	public void saveButton(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		Savable.closeDB();
	}
	
	@FXML
	public void saveAsButton(ActionEvent event) {
		System.out.println("New Save");
		//maybe try creating a type of file or array filled with objects with the name *input* and create objects with the type of tile and it's xy coordinates
		//when it comes time to load, iterate through the objects and create them based on the attributes they have
	}
}
