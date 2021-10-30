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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

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
    private ArrayList<DummyGame> newGames = new ArrayList<>();
    private ArrayList<DummyGame> savedGames = new ArrayList<>();
    private DummyGame selectedGame;

    public void initialize() {

        // turn off scroll pane borders
        //selectionNewScrollPane.setStyle("-fx-focus-color: transparent ; -fx-faint-focus-color: transparent;");
        selectionNewScrollPane.setStyle("-fx-background-color: transparent;");
        selectionSavedScrollPane.setStyle("-fx-background-color: transparent;");

        newGames = BasicApplication.getNewGames();
        savedGames = BasicApplication.getSavedGames();

        // populate scroll panes with options
        populateSelectionMenus(newGames, savedGames);
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
    public void populateSelectionMenus(ArrayList<DummyGame> newGames, ArrayList<DummyGame> savedGames) {

        // convert games to nodes
        ArrayList<Node> newGameNodes = gamesToNodes(newGames);
        ArrayList<Node> savedGameNodes = gamesToNodes(savedGames);

        // populate the menus
        newGameNodes.forEach((n) -> {

            n.setOnMouseClicked(mouseEvent -> {

                selectionPlayButton.setDisable(false);
                selectionPlayButton.setText("Start New Game");
                selectionPlayButton.setOnAction(event -> {
                    setSelectedGame((VBox)n);
                    BasicApplication.setSelectedGame(selectedGame);
                    try {
                        switchScene(event, "setupFXML.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // deselect all rectangles
                ObservableList<Node> children = newGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: red;-fx-border-style: dashed;"));
                children = savedGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: red;-fx-border-style: dashed;"));

                // select this rectangles
                n.setStyle("-fx-border-color: blue;-fx-border-style: dashed;");

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
                    setSelectedGame((VBox)n);
                    BasicApplication.setSelectedGame(selectedGame);
                    BasicApplication.setSetupData(new SetupData(new ArrayList<>(), false));
                    try {
                        switchScene(event, "playFXML.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // deselect all rectangles
                ObservableList<Node> children = newGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: red;-fx-border-style: dashed;"));
                children = savedGamesHBox.getChildren();
                children.forEach((n1) -> n1.setStyle("-fx-border-color: red;-fx-border-style: dashed;"));

                // select this rectangles
                n.setStyle("-fx-border-color: blue;-fx-border-style: dashed;");

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

                    double dim = 300;

                    VBox tempVBox = new VBox();
                    tempVBox.setAlignment(Pos.CENTER);
                    tempVBox.setPrefHeight(dim);
                    tempVBox.setPrefWidth(dim);
                    tempVBox.setStyle("-fx-border-color: red;-fx-border-style: dashed;");

                    // store the game in the selection VBox
                    tempVBox.setUserData(n);

                    ImageView tempImageView = new ImageView();
                    tempImageView.setPreserveRatio(true);
                    tempImageView.setFitHeight(150);
                    tempImageView.setFitWidth(200);
                    tempImageView.setImage(n.getIcon());

                    Label tempLabel = new Label();
                    tempLabel.setText(n.getName());
                    tempLabel.setFont(new Font(16));

                    tempVBox.getChildren().addAll(tempImageView, tempLabel);

                    HBox.setMargin(tempVBox, new Insets(5,5,5,5));

                    // add node to list of nodes
                    nodes.add(tempVBox);
                }
        );

        return nodes;
    }

    @FXML
    public void setSelectedGame(VBox vbox) {
        selectedGame = (DummyGame)vbox.getUserData();
    }
}
