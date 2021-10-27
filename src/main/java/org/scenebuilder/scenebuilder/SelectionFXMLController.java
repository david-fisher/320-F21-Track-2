package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SelectionFXMLController {

    @FXML
    private Label selectionNewGamesLabel;
    private int count = 0;

    @FXML
    private HBox newGamesHBox;
    @FXML
    private HBox savedGamesHBox;

    private Stage stage;

    public void initialize() {
        // populate scroll panes with options
        //populateSelectionMenus();
    }

    @FXML
    public void backFromSelection(ActionEvent event) throws IOException {
        switchScene(event, "mainFXML.fxml");
    }

    @FXML
    public void newFromSelection(ActionEvent event) throws IOException {
        switchScene(event, "setupFXML.fxml");
    }

    @FXML
    public void loadFromSelection(ActionEvent event) throws IOException {
        switchScene(event, "playFXML.fxml");
    }

    @FXML
    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void populateSelectionMenus() {

        // get the stuff we are going to populate the selection menus with
        ArrayList<DummyGame> newGames = BasicApplication.getNewGames();
        ArrayList<DummyGame> savedGames = BasicApplication.getSavedGames();

        // clear the menus
        newGamesHBox.getChildren().clear();
        savedGamesHBox.getChildren().clear();

        // convert games to nodes
        ArrayList<Node> newGameNodes = gamesToNodes(newGames);
        ArrayList<Node> savedGameNodes = gamesToNodes(savedGames);

        // populate the menus
        newGameNodes.forEach((n) -> newGamesHBox.getChildren().add(n));
        savedGameNodes.forEach((n) -> savedGamesHBox.getChildren().add(n));
    }

    @FXML
    public ArrayList<Node> gamesToNodes(ArrayList<DummyGame> games) {

        ArrayList<Node> nodes = new ArrayList<>();

        games.forEach(
                (n) -> {
                    Rectangle tempRect = new Rectangle();
                    tempRect.setWidth(100.0);
                    tempRect.setHeight(100.0);
                    nodes.add(tempRect);
                }
        );

        return nodes;
    }

}
