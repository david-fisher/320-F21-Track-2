package application;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Draggable {
	private double orgSceneX;
	private double orgSceneY;
	
	public void makeDraggable(Node shape) {
		  shape.setCursor(Cursor.HAND);
		  shape.setOnMousePressed((t) -> {
		    orgSceneX = t.getSceneX();
		    orgSceneY = t.getSceneY();
		    Shape c = (Shape) (t.getSource());
		    c.toFront();
		    });
		  shape.setOnMouseDragged((t) -> {
		    double offsetX = t.getSceneX() - orgSceneX;
		    double offsetY = t.getSceneY() - orgSceneY;
		    Shape c = (Shape) (t.getSource());
		    c.setTranslateX(c.getTranslateX() + offsetX);
		    c.setTranslateY(c.getTranslateY() + offsetY);
		    orgSceneX = t.getSceneX();
		    orgSceneY = t.getSceneY();
		    });
	}

}
