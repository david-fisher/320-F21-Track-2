package org.GameEditor.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.GameObjects.objects.Tile;

public class Leftclickable {
	
	public void makeLeftclickable(Tile tile, TextField nameTf, TextField imageTf, TextField colorTf) {
		Shape shape;
		if (tile.getShape().equals("Rectangle")) {
			shape = new Rectangle(tile.getWidth(), tile.getHeight(), tile.getColor());
		} else {
			shape = new Circle(tile.getWidth(), tile.getColor());
		}
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
