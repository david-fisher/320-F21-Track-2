package org.GameEditor.application;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TileClass {
	public Stage stage;
	private TilePane tilePane;
	private Scene scene;
	private Button[] buttons;
	
	public void MainView(Stage stage) {
		this.stage = stage;
		buildUI();
	}
	
	private void buildUI() {
		
		//Initializing the TilePane with tiles
		tilePane = new TilePane();
		tilePane.setPadding(new Insets(10));
		
		for (int i = 1; i < 21; i++) {
			Button button = new Button(Integer.toString(i));
			button.setPrefWidth(50);
			button.setPrefHeight(50);
			tilePane.getChildren().add(button);
		}
		
		tilePane.setOrientation(Orientation.VERTICAL);
		
		tilePane.setHgap(5);
		tilePane.setVgap(5);
		
		tilePane.setPrefColumns(10);
		
	}
}
