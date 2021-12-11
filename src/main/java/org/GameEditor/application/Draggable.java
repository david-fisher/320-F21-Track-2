package org.GameEditor.application;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Draggable {
	private double orgSceneX;
	private double orgSceneY;
	
	public void makeDraggable(Node shape, GameBoard gameBoard, Pane background, int[][] gridLayout, Tile tile) {
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
		  shape.setOnMouseReleased((t) -> { 
			Shape c = (Shape) (t.getSource());
			//identify the height and width of each cell
			int squareWidth = gameBoard.getCellWidth();
			int squareHeight = gameBoard.getCellHeight();
			//identify the global shape center on the gameBoard
			int shapeCenterX = (int) (Math.abs(c.getBoundsInParent().getMinX()) + (squareWidth/2));
			int shapeCenterY = (int) (Math.abs(c.getBoundsInParent().getMinY()) + (squareHeight/2));
			//identify which cell it should be placed in
			int gameBoardX = shapeCenterX / squareWidth;
			int gameBoardY = shapeCenterY / squareHeight;
			
			
			
			int newWidth = (gameBoardX-tile.getTileXInitial())*squareWidth;
			int newHeight = (gameBoardY-tile.getTileYInitial())*squareHeight;
			
			//check to make sure the grid you are putting the tile in is empty/that it is within gameboard bounds
			if (gridLayout[gameBoardX][gameBoardY] == 1) {
				//this finds the old location of the tile location
				gameBoardX = tile.getTileXLocation();
				gameBoardY = tile.getTileYLocation();

				//reestablish the newWidth/newHeight back to its old location
				newWidth = (gameBoardX-tile.getTileXInitial())*squareWidth;
				newHeight = (gameBoardY-tile.getTileYInitial())*squareHeight;
			}
			

			
			//check that the location it's being put in is within the gameBoard
			//relocate the cell
			draw(c, newWidth, newHeight);
			//update the gridLayout
			
			gridLayout[tile.getTileXLocation()][tile.getTileYLocation()] = 0;
			tile.setTileXLocation(gameBoardX);
			tile.setTileYLocation(gameBoardY);
			gridLayout[gameBoardX][gameBoardY] = 1;
		    });
	}
	
	public void draw(Shape c, int x, int y) {
        c.setTranslateX(x);
        c.setTranslateY(y);
	}

}
