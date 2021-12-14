package org.GameEditor.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.GameObjects.objects.Tile;

public class Leftclickable {
	
	public void makeLeftclickable(Tile tile, String id, TextField nameTf, TextField imageTf, TextField colorTf, Scene scene) {
		Shape shape = (Shape) scene.lookup("#" + id);
		shape.setOnMouseClicked((t) -> {
			nameTf.setText(tile.getTileName());
			if (!tile.hasImage) {
				colorTf.setText(shape.getFill().toString());
				imageTf.setText("None");
			}else {
				colorTf.setText("None");
				imageTf.setText(tile.getTileImage());
			}
		    });
	}

}
