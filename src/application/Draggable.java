package application;

import javafx.scene.Node;

public class Draggable {
	private double anchorX;
	private double anchorY;
	
	public void makeShapeDraggable(Node node) {
		node.setOnMousePressed(mouseEvent -> {
			anchorX = mouseEvent.getX();
			anchorY = mouseEvent.getY();
		});
		
		node.setOnMouseDragged(mouseEvent -> {
			node.setLayoutX(mouseEvent.getSceneX() - anchorX);
			node.setLayoutY(mouseEvent.getSceneY() - anchorY);
		});
	}

}
