package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Rightclickable {
	
	public void makeRightClickable(Node shape, AnchorPane canvas) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem deleteButton = new MenuItem(null, new Label("Delete"));
		MenuItem shapeEditor = new MenuItem(null, new Label("Shape Editor"));
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event)
	        {
	            canvas.getChildren().remove(shape);
	        }
	    });
		
		shapeEditor.setOnAction(new EventHandler<ActionEvent>() {
			@FXML
			public void handle(ActionEvent event)
			{
				FXMLLoader root = new FXMLLoader();
				root.setLocation(getClass().getResource("ShapeEditor.fxml"));
				Scene scene;
				try {
					scene = new Scene(root.load(), 800, 365);
					Stage stage = new Stage();
					stage.setTitle("Shape Editor");
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
