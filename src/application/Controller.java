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
    private Button saveButton;
    
    @FXML
    private Button testButton;
    
    @FXML
    private Button objectEditor;
    
    
    @FXML
    //refers to the save button
    public void chooseSaveType(ActionEvent event) throws IOException {//this currently switches the scene, we want to create a new one
    	//first check that a save exists. If it doesn't, have the FXML load in to
    	//the saveAs.fxml file first. 
    	FXMLLoader root = new FXMLLoader();
    	if (true) {
    		
    	}
        root.setLocation(getClass().getResource("SaveAsOrSave.fxml"));
        Scene scene = new Scene(root.load(), 300, 200);
        Stage stage = new Stage();
        stage.setTitle("Confirm Save");
        stage.setScene(scene);
        stage.show();
    }
    
    //for the test button
    public void testGame(ActionEvent event) {
    	//this needs to open the gameplayer with the current rules/tiles/etc.
    }
    
    //for the object editor button
    public void objectEdit(ActionEvent event) {
    	//this needs to tie in with the objecteditor UI team
    }
    
    //End of Morgan Section

    @FXML
    void exitProgram(ActionEvent event) throws IOException {//This currently quits out of the system. We want it to quit the editor.
        FXMLLoader root = new FXMLLoader();
        root.setLocation(getClass().getResource("ConfirmExit.fxml"));
        Scene scene = new Scene(root.load(), 300, 200);
        Stage stage = new Stage();
        stage.setTitle("Confirm Exit");
        stage.setScene(scene);
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
