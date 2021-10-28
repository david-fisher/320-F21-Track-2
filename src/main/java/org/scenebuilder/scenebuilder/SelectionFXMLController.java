package org.scenebuilder.scenebuilder;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
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
    private ScrollPane selectionNewScrollPane;
    @FXML
    private ScrollPane selectionSavedScrollPane;
    @FXML
    private HBox selectionButtonHBox;
    @FXML
    private Button selectionPlayButton;

    private Stage stage;

    public void initialize() {

        // turn off scroll pane borders
        //selectionNewScrollPane.setStyle("-fx-focus-color: transparent ; -fx-faint-focus-color: transparent;");
        selectionNewScrollPane.setStyle("-fx-background-color: transparent;");
        selectionSavedScrollPane.setStyle("-fx-background-color: transparent;");

        // populate scroll panes with options
        populateSelectionMenus();
    }

    @FXML
    public void backFromSelection(ActionEvent event) throws IOException {
        switchScene(event, "mainFXML.fxml");
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

            n.setOnMouseClicked(mouseEvent -> {

                selectionPlayButton.setDisable(false);
                selectionPlayButton.setText("Start New Game");
                selectionPlayButton.setOnAction(event -> {
                    try {
                        switchScene(event, "setupFXML.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // deselect all rectangles
                ObservableList<Node> children = newGamesHBox.getChildren();
                children.forEach((n12) -> ((Rectangle) n12).setFill(Color.AQUAMARINE));
                children = savedGamesHBox.getChildren();
                children.forEach((n12) -> ((Rectangle) n12).setFill(Color.AQUAMARINE));

                // select this rectangles
                ((Rectangle)n).setFill(Color.RED);

                // focus the node
                //n.requestFocus();
            });
            newGamesHBox.getChildren().add(n);
        });

        savedGameNodes.forEach((n) -> {

            n.setOnMouseClicked(mouseEvent -> {

                selectionPlayButton.setDisable(false);
                selectionPlayButton.setText("Load Saved Game");
                selectionPlayButton.setOnAction(event -> {
                    try {
                        switchScene(event, "playFXML.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // deselect all rectangles
                ObservableList<Node> children = newGamesHBox.getChildren();
                children.forEach((n1) -> ((Rectangle) n1).setFill(Color.AQUAMARINE));
                children = savedGamesHBox.getChildren();
                children.forEach((n1) -> ((Rectangle) n1).setFill(Color.AQUAMARINE));

                // select this rectangles
                ((Rectangle)n).setFill(Color.RED);

                // focus the node
                //n.requestFocus();
            });
            savedGamesHBox.getChildren().add(n);
        });

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

                    HBox.setMargin(tempRect, new Insets(5,5,5,5));

                    // give node highlight on focus
                    //tempRect.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;");
                    //tempRect.setStyle("-fx-focus-color: transparent ; -fx-faint-focus-color: rgba(255,0,0,1);");

                    // add node to list of nodes
                    nodes.add(tempRect);
                }
        );

        return nodes;
    }

}
