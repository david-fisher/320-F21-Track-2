package org.GameEditor.application;

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
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ShapeAttributeController {
	
	Tile importedTile = new Tile();
	
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
    
    public void getTile(Tile tile) {
        this.importedTile = tile;
    }
    
    @FXML
    void setName(ActionEvent event) {
    	importedTile.setTileName(tileName.getText());
    }
    
    @FXML
    void setColor(ActionEvent event) {
    	importedTile.tileShape.setFill(tileColor.getValue());
    	importedTile.hasImage = false;
    }

    @FXML
    void setImage(ActionEvent event) {
    	File file = fileChooser.showOpenDialog(stage);
    	if (file != null) {
    		String name = file.getName();
    		int i = name.lastIndexOf('.');
    		String ext = name.substring(i + 1);
    		if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {
    			Image img = new Image(file.toURI().toString());
    			importedTile.tileShape.setFill(new ImagePattern(img));
    			importedTile.setTileImage(name);
    			importedTile.hasImage = true;
    		}
    	}
    }
    
    @FXML
    public void returnToEditor(ActionEvent event) throws IOException {
    	Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }

}