package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button exitButton;
    
    @FXML
    private AnchorPane shapeCanvas;
    
    @FXML
    private Button genCircleButton;
    
    @FXML
    private Button genSquareButton;
    
    
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
    
//	  TODO: need to make a Rule Edit button:
//    we are simply display the rules, we would need to have a button
//    linked to the rule editor so the user can edit the rules there
    
    
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
    	Alert exit = new Alert(AlertType.CONFIRMATION);
    	exit.setTitle("Exit?");
    	exit.setHeaderText("Are you sure you want to exit? Make sure any unsaved changes are saved!");
    	if(exit.showAndWait().get() == ButtonType.OK)
    	{
 
    		Parent editor = FXMLLoader.load(getClass().getResource("TemplateOrFresh.fxml"));
    		Scene editorScene = new Scene(editor);
    		
    		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		window.setScene(editorScene);
    		window.show();
    	}
    }

    Draggable draggable = new Draggable();
    
    @FXML
    public void initialize() {
    	exitButton.setCursor(Cursor.HAND);
    }
    
    @FXML
    void genCircle(ActionEvent event) {
    	Circle c;
    	c = new Circle(312, 300, 30, Color.BLACK);
    	shapeCanvas.getChildren().add(c);
    	draggable.makeDraggable(c);
    }
    
    @FXML
    void genSquare(ActionEvent event) {
    	Rectangle r;
    	r = new Rectangle(60,60,Color.BLACK);
    	r.setX(312);
    	r.setY(300);
    	shapeCanvas.getChildren().add(r);
    	draggable.makeDraggable(r);

    }
    
}
