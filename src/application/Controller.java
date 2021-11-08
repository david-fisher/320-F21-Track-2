package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
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
    
    
    //Morgan Created These    
    
    @FXML
    private Button newSave;
    
    @FXML
    private Button save;
    
    
    @FXML
    //refers to the save button
    public void chooseSaveType(ActionEvent event) throws IOException {//this currently switches the scene, we want to create a new one
    	Parent editor = FXMLLoader.load(getClass().getResource("SaveAsOrSave.fxml"));
    	Scene saveScene = new Scene(editor);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(saveScene);
    	window.show();
    }
    
    //End of Morgan Section

    @FXML
    void exitProgram(ActionEvent event) throws IOException {//This currently quits out of the system. We want it to quit the editor.
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ConfirmExit.fxtml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    Draggable draggable = new Draggable();
    
    @FXML
    public void initialize() {
        draggable.makeShapeDraggable(defaultCircle);
        draggable.makeShapeDraggable(defaultSquare);
        draggable.makeShapeDraggable(defaultTriangle);
    }
    
    
}
