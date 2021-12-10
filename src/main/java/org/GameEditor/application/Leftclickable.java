package org.GameEditor.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;

public class Leftclickable {
	
	public void makeLeftclickable(Tile tile, TextField nameTf, TextField imageTf, TextField colorTf) {
		tile.tileShape.setOnMouseClicked((t) -> {
			nameTf.setText(tile.getTileName());
			if (!tile.hasImage) {
				colorTf.setText(tile.tileShape.getFill().toString());
				imageTf.setText("None");
			}else {
				colorTf.setText("None");
				imageTf.setText(tile.getTileImage());
			}
		    });
	}

}
