package application;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Rightclickable {
	
	public void makeRightClickable(Tile tile, AnchorPane canvas) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem deleteButton = new MenuItem(null, new Label("Delete"));
		MenuItem shapeEditor = new MenuItem(null, new Label("Shape Editor"));
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event)
	        {
	            canvas.getChildren().remove(tile.tileShape);
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
                attributeController.getTile(tile);
			}
		});
		
		tile.tileShape.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(tile.tileShape, event.getScreenX(), event.getScreenY());
            }
		});
		
		
		contextMenu.getItems().add(deleteButton);
		contextMenu.getItems().add(shapeEditor);
		
		
	}

}
