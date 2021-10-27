package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SelectionFXMLController {

    @FXML
    private HBox newGamesHBox;
    @FXML
    private HBox savedGamesHBox;
    @FXML
    private Button selectionBack;
    @FXML
    private Button selectionNewGame;
    @FXML
    private Button selectionLoadGame;

    private Stage stage;

    public void initialize() {
        // populate scroll panes with options
        populateSelectionMenus();
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

        // convert games to nodes
        ArrayList<Node> newGameNodes = gamesToNodes(newGames);
        ArrayList<Node> savedGameNodes = gamesToNodes(savedGames);

        // populate the menus
        newGameNodes.forEach((n) -> {

            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    // disable load game button
                    selectionLoadGame.setDisable(true);

                    // enable new game button
                    selectionNewGame.setDisable(false);
                }
            });
            newGamesHBox.getChildren().add(n);
        });
        savedGameNodes.forEach((n) -> {

            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    // disable new game button
                    selectionNewGame.setDisable(true);

                    // enable load game button
                    selectionLoadGame.setDisable(false);
                }
            });
            savedGamesHBox.getChildren().add(n);
        });

        System.out.println("Size: " + newGamesHBox.getChildren().size());
        System.out.println("Size: " + savedGamesHBox.getChildren().size());
    }

    @FXML
    public ArrayList<Node> gamesToNodes(ArrayList<DummyGame> games) {

        ArrayList<Node> nodes = new ArrayList<>();

        games.forEach(
                (n) -> {
                    Rectangle tempRect = new Rectangle();

                    double dim = 300;

                    tempRect.setArcHeight(5);
                    tempRect.setArcWidth(5);
                    tempRect.setFill(Color.AQUAMARINE);
                    tempRect.setHeight(dim);
                    tempRect.setStroke(Color.BLACK);
                    tempRect.setStrokeType(StrokeType.INSIDE);
                    tempRect.setWidth(dim);

                    //HBox.setHgrow(tempRect, Priority.ALWAYS);
                    HBox.setMargin(tempRect, new Insets(5,5,5,5));

                    nodes.add(tempRect);
                }
        );

        return nodes;
    }

}
