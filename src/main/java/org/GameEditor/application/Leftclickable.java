package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;
import application.Controller;

public class Leftclickable {
	
	public void makeLeftclickable(Tile tile, TextField nameTf, TextField imageTf, TextField colorTf) {
		tile.tileShape.setOnMouseClicked((t) -> {
			nameTf.setText(tile.getTileName());
			colorTf.setText(tile.tileShape.getFill().toString());
			imageTf.setText(tile.getTileImage());
			
		    });
	}

}
