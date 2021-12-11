package org.GameEditor.application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.GamePlay.controllers.MainController;
import org.GamePlay.controllers.ScreenController;

public class Controller extends ScreenController {
	
	@FXML
	private Button exitButton;

	@FXML
	private Button helpButton;

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
    
    @FXML
    private TextField rowSize;
    
    @FXML
    private TextField columnSize;
    
    @FXML
    private Pane gameBoardBackground;


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
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		MainController main = new MainController();
		main.initialize(stage);
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
	Leftclickable leftclickable = new Leftclickable();

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
		//shapeCanvas.
		
		Tile c = new Tile();
		
		int x = gameBoard.getCellWidth();
		int y = gameBoard.getCellHeight();
		int radius = Math.min(x, y) / 2;
		
		c.tileShape = new Circle(radius, Color.BLACK);
		
		draggable.makeDraggable(c.tileShape, gameBoard, gameBoardBackground, gridLayout, c);
		rightclickable.makeRightClickable(c,c.tileShape, gameBoardBackground, gridLayout, gameBoard);
		leftclickable.makeLeftclickable(c, nameTf, imageTf, colorTf);
		
		for (int j = 0; j < gameBoard.getSizeY(); j++) {
			for (int i = 0; i < gameBoard.getSizeX(); i++) {
				if (gridLayout[i][j] != 1) {
					
					gameBoardBackground.getChildren().add(c.tileShape);
					c.tileShape.setLayoutX((i * gameBoard.getCellWidth() + x/2));
					c.tileShape.setLayoutY((j * gameBoard.getCellHeight() + y/2));
					gridLayout[i][j] = 1;
					c.setTileXLocation(i);
					c.setTileYLocation(j);
					c.setTileXInitial(i);
					c.setTileYInitial(j);
					existingTiles.add(c);
					break;
				}
			}
		}
		
		gameBoardBackground.getChildren().add(c.tileShape);
	}

	@FXML
	void genSquare(ActionEvent event) {
		
		Tile r = new Tile();
		
		int x = gameBoard.getCellWidth();
		int y = gameBoard.getCellHeight();
		
		r.tileShape = new Rectangle(x, y, Color.BLACK);
		
		draggable.makeDraggable(r.tileShape, gameBoard, gameBoardBackground, gridLayout, r);
		rightclickable.makeRightClickable(r,r.tileShape, gameBoardBackground, gridLayout, gameBoard);
		leftclickable.makeLeftclickable(r, nameTf, imageTf, colorTf);
		
		for (int j = 0; j < gameBoard.getSizeY(); j++) {
			for (int i = 0; i < gameBoard.getSizeX(); i++) {
				if (gridLayout[i][j] != 1) {
					gameBoardBackground.getChildren().add(r.tileShape);
					r.tileShape.setLayoutX((i * gameBoard.getCellWidth()));
					r.tileShape.setLayoutY((j * gameBoard.getCellHeight()));
					gridLayout[i][j] = 1;
					r.setTileXLocation(i);
					r.setTileYLocation(j);
					r.setTileXInitial(i);
					r.setTileYInitial(j);
					existingTiles.add(r);
					break;
				}
			}
		}
	}
	
	
	
	@FXML
	void GridRowSelector(ActionEvent event) {
		int height = Integer.valueOf(rowSize.getText());
		int width = Integer.valueOf(columnSize.getText());
		gridLayout = new int[width][height];
		gameBoard.draw(gameBoardBackground, width, height, gridLayout, existingTiles);
	}
	

	
	@FXML
	void GridColumnSelector(ActionEvent event) {
		int height = Integer.valueOf(rowSize.getText());
		int width = Integer.valueOf(columnSize.getText());
		gridLayout = new int[width][height];
		gameBoard.draw(gameBoardBackground, width, height, gridLayout, existingTiles);
	}
	
	
	
	
	//draw the gameboard
	
	private GameBoard gameBoard;
	
    private int[][] gridLayout;
    
    ArrayList<Tile> existingTiles = new ArrayList<Tile>();
    
    
    @FXML
    private Button begin;
    
    @FXML
    private void showGameBoard(ActionEvent event) {
    	gameBoard = new GameBoard();
    	int y = Integer.valueOf(rowSize.getText());
    	int x = Integer.valueOf(columnSize.getText());
    	gridLayout = new int[x][y];
    	gameBoard.draw(gameBoardBackground, x, y, gridLayout, existingTiles);
    	//also need to hide the old button
    	//also need to hide the text
    }
	
    
	
	
	
	//end of draw the gameboard stuff
	
	
	
	
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
