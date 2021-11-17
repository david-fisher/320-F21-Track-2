package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;

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
