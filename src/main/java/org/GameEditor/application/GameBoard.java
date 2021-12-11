package org.GameEditor.application;


import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameBoard {

	//Rectangle[][] board;
	int boardWidth = 634;
	int boardHeight = 560;
	int width = 8;
	int height = 8;
	int cellWidth = boardWidth / width;
	int cellHeight = boardHeight / height;

	TextField nameTf;
	TextField colorTf;
	TextField imgTf;
	
	public GameBoard() {
		//board = new Rectangle[width][height];
	}

	public void sendNameColorImg(TextField nameTf, TextField colorTf, TextField imgTf) {
		this.nameTf = nameTf;
		this.colorTf = colorTf;
		this.imgTf = imgTf;
	}
	
	public void draw(GameBoard gameBoard, Pane gameBoardBackground, int w, int h, int[][] gridLayout, ArrayList<Tile> existingTiles) {
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
			Draggable drag = new Draggable();
			Leftclickable left = new Leftclickable();
			Rightclickable right = new Rightclickable();

			//create new rectangle with the new size
			//copy all values of the old rectangle/circle (maybe .getFill) and copy over to new rectangle
			//establish
			//establish if it's a rectangle or circle
			Rectangle rec = new Rectangle(cellWidth,cellHeight);
			rec.setFill(t.tileShape.getFill());
			t.tileShape = rec;
			left.makeLeftclickable(t, nameTf, colorTf, imgTf);
			right.makeRightClickable(t, t.tileShape, gameBoardBackground, gridLayout, gameBoard);
			drag.makeDraggable(t.tileShape, gameBoard, gameBoardBackground, gridLayout, t);


        	existingTiles.set(i, t);
        	
        	//this is so it reevaluates the offset from the origin
        	t.setTileXInitial(t.getTileXLocation());
        	t.setTileYInitial(t.getTileYLocation());
        	
        	
        	if (t.getTileXLocation() < width && t.getTileYLocation() < height) {
        		gameBoardBackground.getChildren().add(t.tileShape);
        		t.tileShape.setLayoutX((t.getTileXLocation() * cellWidth));
				t.tileShape.setLayoutY((t.getTileYLocation() * cellHeight));
				gridLayout[t.getTileXLocation()][t.getTileYLocation()] = 1;
        	}
        	else {
        		//remove the tile from the arrayList
        		existingTiles.remove(t);
        	}
        	
        }
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
	
	public int getBoardWidth() {
		return boardWidth;
	}
	
	public int getBoardHeight() {
		return boardHeight;
	}
	
	
	//done
	public void setSizeX(int w) {
		width = w;
	}
	
	//done
	public void setSizeY(int h) {
		width = h;
	}
	
}
