package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ShapeAttributeController {
	
	Stage stage = new Stage();
	private FileChooser fileChooser = new FileChooser();
	
    @FXML
    private ColorPicker tileColor;

    @FXML
    private Button tileImage;

    @FXML
    private TextField tileName;
    
    @FXML
    private Button backBtn;
    
    @FXML
    void setName(ActionEvent event) {
    	System.out.println("Name for tile is: " + tileName.getText());
    }
    
    @FXML
    void setColor(ActionEvent event) {
    	System.out.println("Color selected is: " + tileColor.getValue());
    }

    @FXML
    void setImage(ActionEvent event) {
    	File file = fileChooser.showOpenDialog(stage);
    	if (file != null) {
    		String name = file.getName();
    		int i = name.lastIndexOf('.');
    		String ext = name.substring(i + 1);
    		if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {
    			System.out.println("Image file name is: " + name);
    		}
    	}
    }
    
    @FXML
    public void returnToEditor(ActionEvent event) throws IOException {
    	Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }
//<Button fx:id="backBtn" layoutX="375.0" layoutY="307.0" mnemonicParsing="false" onAction="#returnToEditor" style="-fx-background-color: #e06666; -fx-border-color: #595959; -fx-border-radius: 4; -fx-border-width: 2; -fx-background-radius: 6;" text="Done" />
}