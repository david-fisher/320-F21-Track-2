package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private Button exitButton;

	@FXML
	private Button helpButton;

	@FXML
	private AnchorPane shapeCanvas;

	@FXML
	private Button genCircleButton;

	@FXML
	private Button genSquareButton;

	// Morgan Created These

	@FXML
	private Button saveButton;

	@FXML
	private Button testButton;

	@FXML
	private Button objectEditor;
	
	@FXML
    private TextField colorTf;
	
	@FXML
    private TextField imageTf;

    @FXML
    private TextField nameTf;

	// group root to store all the children shapes/tiles
	@FXML
	private Group root = new Group();
	
	@FXML
	private TextField inputField;

	@FXML
	// refers to the save button
	public void chooseSaveType(ActionEvent event) throws IOException {// this currently switches the scene, we want to
																		// create a new one
		// first check that a save exists. If it doesn't, have the FXML load in to
		// the saveAs.fxml file first.
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

	// for the test button
	public void testGame(ActionEvent event) {
		// this needs to open the gameplayer with the current rules/tiles/etc.
	}

	// for the object editor button
	public void objectEdit(ActionEvent event) {
		// this needs to tie in with the objecteditor UI team
	}

	// End of Morgan Section

	@FXML
	void exitProgram(ActionEvent event) throws IOException {// This currently quits out of the system. We want it to
															// quit the editor.
		FXMLLoader root = new FXMLLoader();
		root.setLocation(getClass().getResource("ConfirmExit.fxml"));
		Scene scene = new Scene(root.load(), 300, 200);
		Stage stage = new Stage();
		stage.setTitle("Confirm Exit");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openHelp(ActionEvent event) {
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		webEngine.load(getClass().getResource("helpdoc.html").toString());
		Scene scene = new Scene(webView, 600, 600);
		Stage stage = new Stage();
		stage.setTitle("Help Document");
		stage.setScene(scene);
		stage.show();
	}

	Draggable draggable = new Draggable();
	Rightclickable rightclickable = new Rightclickable();
//	Leftclickable leftclickable = new Leftclickable(); TODO: Leftclickable needs fixing

	@FXML
	public void initialize() {
		exitButton.setCursor(Cursor.HAND);
		helpButton.setCursor(Cursor.HAND);
		genCircleButton.setCursor(Cursor.HAND);
		genSquareButton.setCursor(Cursor.HAND);
		saveButton.setCursor(Cursor.HAND);
		testButton.setCursor(Cursor.HAND);
	}

	// TODO Placeholder for adding shape to group root
	@FXML
	public void allTiles() {
		root.getChildren();
	}

	@FXML
	void genCircle(ActionEvent event) {
		TileWave c = new TileWave();
		c.tileShape = new Circle(312, 300, 30, Color.BLACK);
		shapeCanvas.getChildren().add(c.tileShape);
		draggable.makeDraggable(c.tileShape);
		rightclickable.makeRightClickable(c.tileShape, shapeCanvas);
//		leftclickable.makeLeftclickable(c, nameTf, imageTf, colorTf);
	}

	@FXML
	void genSquare(ActionEvent event) {
		TileWave r = new TileWave();
		r.tileShape = new Rectangle(60, 60, Color.BLACK);
		((Rectangle) r.tileShape).setX(312);
		((Rectangle) r.tileShape).setY(300);
		shapeCanvas.getChildren().add(r.tileShape);
		draggable.makeDraggable(r.tileShape);
		rightclickable.makeRightClickable(r.tileShape, shapeCanvas);
//		leftclickable.makeLeftclickable(r, nameTf, imageTf, colorTf);
	}
	
	
	@FXML
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Customer Information");

		Label labelfirst = new Label("Enter your name");
		Label label = new Label();

		Button button = new Button("Submit");
		TextField text = new TextField();
		button.setOnAction(e -> {

			label.setText("The name you entered is " + text.getText());
		});

		VBox layout = new VBox(5);

		layout.getChildren().addAll(labelfirst, text, button, label);

		Scene scene1 = new Scene(layout, 300, 250);
		primaryStage.setScene(scene1);

		primaryStage.show();
	}
	
	
}