package org.GameEditor.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.GameObjects.objects.Tile;

import javax.swing.*;

public class ShapeAttributeController {
	
	Tile importedTile;
    Scene scene;
	
	Stage stage = new Stage();
	private FileChooser fileChooser = new FileChooser();
	
    @FXML
    private ColorPicker tileColor;

    @FXML
    private Button tileImage;

    @FXML
    private TextField tileName;
    
    @FXML
    private Button backBtn;

    private Shape shape;

    @FXML
    private MenuBar currentMenuBar;

    @FXML
    private MenuBar addMenuBar;

    @FXML
    private MenuBar removeMenuBar;

    @FXML
    private Menu currentConnectionsMenu;

    @FXML
    private Menu addConnectionsMenu;

    @FXML
    private Menu removeConnectionsMenu;

    @FXML
    private MenuItem addConnections;

    private ArrayList<Tile> existingTiles;

    private int[][] gridLayout;
    
    public void setTile(Tile tile, Scene scene) {
        this.importedTile = tile;
        this.scene = scene;
        shape = (Shape) scene.lookup("#" + importedTile.getId());
    }
    
    @FXML
    void setName(ActionEvent event) {
    	importedTile.setTileName(tileName.getText());
    }
    
    @FXML
    void setColor(ActionEvent event) {
        shape.setFill(tileColor.getValue());
        importedTile.setColor(tileColor.getValue());
    	importedTile.hasImage = false;
    }

    @FXML
    void setImage(ActionEvent event) {
    	File file = fileChooser.showOpenDialog(stage);
    	if (file != null) {
    		String name = file.getName();
    		int i = name.lastIndexOf('.');
    		String ext = name.substring(i + 1);
    		if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {
    			Image img = new Image(file.toURI().toString());
                shape.setFill(new ImagePattern(img));
    			importedTile.setIcon((new ImagePattern(img)).toString());
    			importedTile.setTileImage(name);
    			importedTile.hasImage = true;
    		}
    	}
    }
    
    @FXML
    public void returnToEditor(ActionEvent event) throws IOException {
    	Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }

    public void getGrid(int[][] gridLayout, ArrayList<Tile> existingTiles) {
        this.gridLayout = gridLayout;
        this.existingTiles = existingTiles;
    }

    @FXML
    private void currentConnection(ActionEvent event) throws IOException {
        for (int i = 0; i < currentConnectionsMenu.getItems().size(); i++) {
            if (i != 0) {
                currentConnectionsMenu.getItems().remove(i);
            }
        }

        importedTile.getConnect();
        int x = 0;
        int y = 0;
        for (int i = 0; i < importedTile.getConnect().size(); i++ ) {
            x = importedTile.getConnect().get(i).getTileXLocation();
            y = importedTile.getConnect().get(i).getTileYLocation();
            MenuItem place = new MenuItem(String.valueOf(x) + " " + String.valueOf(y));

            currentConnectionsMenu.getItems().add(place);
        }
    }

    @FXML
    private void addConnection(ActionEvent even) throws IOException {
        int x = importedTile.getTileXLocation();
        int y = importedTile.getTileYLocation();

        for (int i = 0; i < addConnectionsMenu.getItems().size(); i++) {
            if (i != 0) {
                addConnectionsMenu.getItems().remove(i);
            }
        }
        //remove the update menuitems from current/remove menus

        for (int i = 0; i < gridLayout.length; i++) {
            for (int j = 0; j < gridLayout[i].length; j++) {
                Tile temp = findSelected(i, j);
                if (gridLayout[i][j] == 1 && !importedTile.getConnect().contains(temp)) {
                    if (x == i && y == j) {
                    }
                    else {
                        MenuItem place = new MenuItem(String.valueOf(i) + " " + String.valueOf(j));

                        place.setOnAction(new EventHandler<ActionEvent>() {
                            @FXML
                            public void handle(ActionEvent event) {
                                //functionality to the buttons created needs to be added here
                                //add to the current/removable menus
                                //actually add it to the existing connections for that tile
                                String[] parsed = place.getText().split(" ", 2);
                                int selectedX = Integer.parseInt(parsed[0]);
                                int selectedY = Integer.parseInt(parsed[1]);
                                Tile selected = findSelected(selectedX, selectedY);
                                System.out.println(selected.getTileXLocation() + " " + selected.getTileYLocation());
                                importedTile.addConnect(selected);

                                addConnectionsMenu.getItems().remove(place);
                                //currentConnectionsMenu.getItems().add(place);
                                //removeConnectionsMenu.getItems().add(place);
                            }
                        });

                        addConnectionsMenu.getItems().add(place);
                    }
                }
            }
        }
    }

    @FXML
    private void removeConnection(ActionEvent event) throws IOException {
        //importedTile.deleteConnect(importedTile);

        for (int i = 0; i < removeConnectionsMenu.getItems().size(); i++) {
            if (i != 0) {
                removeConnectionsMenu.getItems().remove(i);
            }
        }

        importedTile.getConnect();
        int x = 0;
        int y = 0;
        for (int i = 0; i < importedTile.getConnect().size(); i++ ) {
            x = importedTile.getConnect().get(i).getTileXLocation();
            y = importedTile.getConnect().get(i).getTileYLocation();
            MenuItem place = new MenuItem(String.valueOf(x) + " " + String.valueOf(y));

            place.setOnAction(new EventHandler<ActionEvent>() {
                @FXML
                public void handle(ActionEvent event) {
                    //functionality to the buttons created needs to be added here
                    //remove from the removable menu
                    //actually remove it from the existing connections for that tile
                    String[] parsed = place.getText().split(" ", 2);
                    int selectedX = Integer.parseInt(parsed[0]);
                    int selectedY = Integer.parseInt(parsed[1]);
                    Tile selected = findSelected(selectedX, selectedY);
                    importedTile.deleteConnect(selected);
                    removeConnectionsMenu.getItems().remove(selected);
                }
            });

            removeConnectionsMenu.getItems().add(place);
        }
    }

    private Tile findSelected(int x, int y) {
        Tile selected = importedTile;
        for (int i = 0; i < existingTiles.size(); i++) {
            Tile tileFromList = existingTiles.get(i);
            if (tileFromList.getTileXLocation() == x && tileFromList.getTileYLocation() == y) {
                selected = tileFromList;
                return selected;
            }
        }
        return selected;
    }

}