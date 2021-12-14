package org.GameEditor.application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.GameObjects.objects.Tile;
import org.GamePlay.BasicApplication;

public class Rightclickable {
	
	public void makeRightClickable(Tile tile, Node shape, Pane gameBoardBackground, int[][] gridLayout, GameBoard gameBoard) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem deleteButton = new MenuItem(null, new Label("Delete"));
		MenuItem shapeEditor = new MenuItem(null, new Label("Shape Editor"));
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event)
	        {
	        	//identify the cell that you are deleting
	        	Shape c = (Shape) (shape);
				int squareWidth = gameBoard.getCellWidth();
				int squareHeight = gameBoard.getCellHeight();
				int shapeCenterX = (int) (Math.abs(c.getBoundsInParent().getMinX()) + (squareWidth/2));
				int shapeCenterY = (int) (Math.abs(c.getBoundsInParent().getMinY()) + (squareHeight/2));;
				int gameBoardX = shapeCenterX / squareWidth;
				int gameBoardY = shapeCenterY / squareHeight;
	        	//update the gridLayout
	        	gridLayout[gameBoardX][gameBoardY] = 0;
				BasicApplication.getProject().getIntiGS().getAllTiles().remove(tile);
	            gameBoardBackground.getChildren().remove(shape);
	        }
	    });
		
		shapeEditor.setOnAction(new EventHandler<ActionEvent>() {
			@FXML
			public void handle(ActionEvent event)
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("ShapeEditor.fxml"));
				try {
					loader.load();
					Parent p = loader.getRoot();
					Stage stage = new Stage();
					stage.setTitle("Shape Editor");
					stage.setScene(new Scene(p));
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ShapeAttributeController attributeController = loader.getController();
                attributeController.setTile(tile, gameBoardBackground.getScene());
			}
		});
		
		shape.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(shape, event.getScreenX(), event.getScreenY());
            }
    });
		
		
		contextMenu.getItems().add(deleteButton);
		contextMenu.getItems().add(shapeEditor);
		
		
	}

}
