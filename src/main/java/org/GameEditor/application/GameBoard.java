package org.GameEditor.application;


import java.util.ArrayList;

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
	
	public GameBoard() {
		//board = new Rectangle[width][height];
	}
	
	public void resize(Rectangle[][] rectangle) {
		
	}
	
	public void draw(Pane gameBoardBackground, int w, int h, int[][] gridLayout, ArrayList<Tile> existingTiles) {
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
                
                //board[i / cellWidth][j / cellHeight] = r;
                
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                gameBoardBackground.getChildren().add(r);
            }
        }
        
        //this is to redraw the tiles themselves
        for (int i = 0; i < existingTiles.size(); i++) {
        	Tile t = existingTiles.get(i);

        	
        	t.tileShape.resize(cellWidth, cellHeight);//doesn't work
        	existingTiles.set(i, t);
        	
        	//need to change the original tile location of each t
        	t.setTileXInitial(t.getTileXLocation());
        	t.setTileYInitial(t.getTileYLocation());
        	
        	
        	if (t.getTileXLocation() < width || t.getTileYLocation() < height) {
        		gameBoardBackground.getChildren().add(t.tileShape);
        		t.tileShape.setLayoutX((t.getTileXLocation() * cellWidth));
				t.tileShape.setLayoutY((t.getTileYLocation() * cellHeight));
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
