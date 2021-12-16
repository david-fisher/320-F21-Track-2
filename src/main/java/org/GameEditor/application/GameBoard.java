package org.GameEditor.application;


import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.GameObjects.objects.GameObject;
import org.GameObjects.objects.Tile;
import org.GamePlay.BasicApplication;
import org.RuleEngine.engine.GameState;

public class GameBoard extends GameObject {

	//Rectangle[][] board;
	int boardWidth = 560;
	int boardHeight = 560;
	int width = 8;
	int height = 8;
	int gridLayout[][];

	int cellWidth = boardWidth / width;
	int cellHeight = boardHeight / height;
	
	public GameBoard() {
		//board = new Rectangle[width][height];
		setHeight((double) boardHeight);
		setWidth((double) boardWidth);
	}
	
	public void resize(Rectangle[][] rectangle) {
		
	}
	
	public void draw(Pane gameBoardBackground, int w, int h, int[][] gridLayout, ArrayList<Tile> existingTiles,
					 TextField nameTf, TextField imageTf, TextField colorTf, TextField connectionsTf, Scene scene) {
		gameBoardBackground.getChildren().clear();
		width = w;
		height = h;
		cellWidth = boardWidth/width;
		cellHeight = boardHeight/height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
            	int x = i * cellWidth;
            	int y = j * cellHeight;
                Rectangle r = new Rectangle(x, y, cellWidth, cellHeight);
                
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                gameBoardBackground.getChildren().add(r);
            }
        }
        
        //this is to redraw the tiles themselves
        for (int i = 0; i < existingTiles.size(); i++) {
        	Tile t = existingTiles.get(i);
			Shape tileShape;
			if (t.getShape().equals("Rectangle")) {
				tileShape = new Rectangle(cellWidth, cellHeight, t.getColor());
			} else {
				int min = Math.min(cellWidth, cellHeight) / 2;
				tileShape = new Circle(min, t.getColor());
			}

			tileShape.setId(Long.toString(t.getId()));
        	existingTiles.set(i, t);
        	
        	//need to change the original tile location of each t
        	t.setTileXInitial(t.getTileXLocation());
        	t.setTileYInitial(t.getTileYLocation());
        	
        	
        	if (t.getTileXLocation() < width || t.getTileYLocation() < height) {
        		gameBoardBackground.getChildren().add(tileShape);

				//Make new tiles clickable because they won't be regenerated
				(new Draggable()).makeDraggable(tileShape, this, gameBoardBackground, gridLayout, t);
				(new Rightclickable()).makeRightClickable(t, tileShape, gameBoardBackground, gridLayout, this, existingTiles);
				(new Leftclickable()).makeLeftclickable(t, tileShape.getId(), nameTf, imageTf, colorTf, connectionsTf, scene);
        		draw(tileShape, t);
        	}
        	else {
        		//remove the tile from the arrayList
        		existingTiles.remove(t);
        	}
        	
        }
	}

	private void draw(Shape tileShape, Tile t) {
		tileShape.setLayoutX(t.getTileXLocation() * cellWidth);
		tileShape.setLayoutY(t.getTileYLocation() * cellHeight);
	}
	
	//done
	public int getSizeX() {
		return width;
	}
	
	//done
	public int getSizeY() {
		return height;
	}
	
	
	//done
	public int getCellWidth() {
		return cellWidth;
	}
	//done
	public int getCellHeight() {
		return cellHeight;
	}
	
	public int[][] getGridLayout() { return gridLayout; }
	public void setGridLayout(int [][] grid) { this.gridLayout = grid; }
	//done
	public void setSizeX(int w) {
		width = w;
	}
	
	//done
	public void setSizeY(int h) {
		width = h;
	}
	
}
