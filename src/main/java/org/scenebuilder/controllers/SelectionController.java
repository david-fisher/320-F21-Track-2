package org.scenebuilder.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.scenebuilder.dummy.DummyGame;
import org.scenebuilder.BasicApplication;
import org.scenebuilder.SetupData;

import java.io.IOException;
import java.util.ArrayList;

public class SelectionController extends ScreenController {

    private void initNewGamesLabel() {

        // bind HBox to vbox width to represent a row
        HBox newGamesLabelHBox = new HBox();
        newGamesLabelHBox.setStyle("-fx-background-color: transparent");
        newGamesLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        Label newGamesLabel = new Label("New Games");
        newGamesLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 45;");
        HBox.setMargin(newGamesLabel, new Insets(10, 10, 10, 15));

        newGamesLabelHBox.getChildren().add(newGamesLabel);
        screenVBox.getChildren().add(newGamesLabelHBox);
    }

    HBox newGamesScrollPaneHBox;
    ScrollPane newGamesScrollPane;
    HBox newGamesHBox;
    private void initNewGamesScrollPane() {

        newGamesScrollPaneHBox = new HBox();
        newGamesScrollPaneHBox.setStyle("-fx-background-color: transparent");
        newGamesScrollPaneHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        newGamesScrollPane = new ScrollPane();
        newGamesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        newGamesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        newGamesScrollPane.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 20));
        newGamesScrollPane.prefHeightProperty().bind(Bindings.multiply(screenVBox.heightProperty(), 0.35));
        newGamesScrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: black");
        HBox.setMargin(newGamesScrollPane, new Insets(10, 10, 10, 10));

        newGamesHBox = new HBox();
        newGamesHBox.setAlignment(Pos.CENTER_LEFT);
        newGamesHBox.prefWidthProperty().bind(Bindings.subtract(newGamesScrollPane.widthProperty(), 2));
        newGamesHBox.prefHeightProperty().bind(Bindings.subtract(newGamesScrollPane.heightProperty(), 2));
        //newGamesHBox.setStyle("-fx-background-color: #" + secondaryColor.toString().substring(2));

        newGamesScrollPane.setContent(newGamesHBox);
        newGamesScrollPaneHBox.getChildren().add(newGamesScrollPane);
        screenVBox.getChildren().add(newGamesScrollPaneHBox);
    }

    HBox savedGamesLabelHBox;
    Label savedGamesLabel;
    private void initSavedGamesLabel() {
        savedGamesLabelHBox = new HBox();
        savedGamesLabelHBox.setStyle("-fx-background-color: transparent");
        savedGamesLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        savedGamesLabel = new Label("Saved Games");
        savedGamesLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 45;");
        HBox.setMargin(savedGamesLabel, new Insets(10, 10, 10, 15));

        savedGamesLabelHBox.getChildren().add(savedGamesLabel);
        screenVBox.getChildren().add(savedGamesLabelHBox);
    }

    HBox savedGamesScrollPaneHBox;
    ScrollPane savedGamesScrollPane;
    HBox savedGamesHBox;
    private void initSavedGamesScrollPane() {

        savedGamesScrollPaneHBox = new HBox();
        savedGamesScrollPaneHBox.setStyle(newGamesScrollPaneHBox.getStyle());
        savedGamesScrollPaneHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        savedGamesScrollPane = new ScrollPane();
        savedGamesScrollPane.setVbarPolicy(newGamesScrollPane.getVbarPolicy());
        savedGamesScrollPane.setHbarPolicy(newGamesScrollPane.getHbarPolicy());
        savedGamesScrollPane.prefWidthProperty().bind(newGamesScrollPane.widthProperty());
        savedGamesScrollPane.prefHeightProperty().bind(newGamesScrollPane.heightProperty());
        savedGamesScrollPane.setStyle(newGamesScrollPane.getStyle());
        HBox.setMargin(savedGamesScrollPane, HBox.getMargin(newGamesScrollPane));

        savedGamesHBox = new HBox();
        savedGamesHBox.setAlignment(newGamesHBox.getAlignment());
        savedGamesHBox.prefWidthProperty().bind(Bindings.subtract(savedGamesScrollPane.widthProperty(), 2));
        savedGamesHBox.prefHeightProperty().bind(Bindings.subtract(savedGamesScrollPane.heightProperty(), 2));
        savedGamesHBox.setStyle(newGamesHBox.getStyle());

        savedGamesScrollPane.setContent(savedGamesHBox);
        savedGamesScrollPaneHBox.getChildren().add(savedGamesScrollPane);
        screenVBox.getChildren().add(savedGamesScrollPaneHBox);
    }

    HBox buttonsHBox;
    Label backButton;
    Pane fillerPane;
    Label selectGameButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonsHBox.setStyle("-fx-background-color: transparent");
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Label("Back");
        setStyle(backButton, "40", "LightGrey", 200, 70);

        backButton.setPadding(new Insets(5, 20, 5, 20));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnMouseClicked(event -> {
            MainController controller = new MainController();
            controller.initialize(stage);
        });

        fillerPane = new Pane();
        HBox.setHgrow(fillerPane, Priority.ALWAYS);

        selectGameButton = new Label("Select a Game");
        setStyle(selectGameButton, "40", "LightGrey", 290, 70);
        selectGameButton.setPadding(backButton.getPadding());
        selectGameButton.setDisable(true);

        HBox.setMargin(selectGameButton, HBox.getMargin(backButton));

        buttonsHBox.getChildren().addAll(backButton, fillerPane, selectGameButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    public void initialize(Stage stage) {

        super.initialize(stage);

        initNewGamesLabel();
        initNewGamesScrollPane();
        initSavedGamesLabel();
        initSavedGamesScrollPane();
        initButtons();

        // todo, artifacts from original implementation
        BasicApplication.loadNewGames();
        BasicApplication.loadSavedGames();
        newGames = BasicApplication.getNewGames();
        savedGames = BasicApplication.getSavedGames();
        populateSelectionMenus(newGames, savedGames);

    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    private ArrayList<DummyGame> newGames = new ArrayList<>();
    private ArrayList<DummyGame> savedGames = new ArrayList<>();
    private DummyGame selectedGame;

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

    public void populateSelectionMenus(ArrayList<DummyGame> newGames, ArrayList<DummyGame> savedGames) {

        // convert games to nodes
        ArrayList<Node> newGameNodes = gamesToNodes(newGames);
        ArrayList<Node> savedGameNodes = gamesToNodes(savedGames);

        // populate the menus
        newGameNodes.forEach((n) -> {

            n.setOnMouseClicked(mouseEvent -> {

                selectGameButton.setDisable(false);
                selectGameButton.setText("Start New Game");
                setStyle(selectGameButton, "40", "LimeGreen", 320, 70);

                selectGameButton.setOnMouseClicked(event -> {
                    setSelectedGame((VBox)n);
                    BasicApplication.setSelectedGame(selectedGame);

                    SetupController controller = new SetupController();
                    controller.initialize(stage);

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

                selectGameButton.setDisable(false);
                selectGameButton.setText("Load Saved Game");
                setStyle(selectGameButton, "40", "Blue", 350, 70);

                selectGameButton.setOnMouseClicked(event -> {
                    setSelectedGame((VBox)n);
                    BasicApplication.setSelectedGame(selectedGame);
                    BasicApplication.setSetupData(new SetupData(new ArrayList<>(), false));
                    PlayController controller = new PlayController();
                    controller.initialize(stage);
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

    public ArrayList<Node> gamesToNodes(ArrayList<DummyGame> games) {

        ArrayList<Node> nodes = new ArrayList<>();

        games.forEach(
                (n) -> {

                    VBox tempVBox = new VBox();
                    tempVBox.setAlignment(Pos.CENTER);

                    tempVBox.prefHeightProperty().bind(Bindings.subtract(newGamesScrollPane.heightProperty(), 20));
                    tempVBox.minHeightProperty().bind(tempVBox.prefHeightProperty());
                    tempVBox.maxHeightProperty().bind(tempVBox.prefHeightProperty());
                    tempVBox.prefWidthProperty().bind(tempVBox.heightProperty());
                    tempVBox.minWidthProperty().bind(tempVBox.heightProperty());
                    tempVBox.setStyle("-fx-border-color: red;-fx-border-style: dashed;");

                    // store the game in the selection VBox
                    tempVBox.setUserData(n);

                    ImageView tempImageView = new ImageView();
                    tempImageView.setPreserveRatio(true);
                    tempImageView.setFitHeight(150);
                    tempImageView.setFitWidth(200);
                    //tempImageView.setImage(n.getIcon());

                    Label tempLabel = new Label();
                    tempLabel.setText(n.getGameName());
                    tempLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 20;");

                    tempVBox.getChildren().addAll(tempImageView, tempLabel);

                    HBox.setMargin(tempVBox, new Insets(5,5,5,5));

                    // add node to list of nodes
                    nodes.add(tempVBox);
                }
        );

        return nodes;
    }

    public void setSelectedGame(VBox vbox) {
        selectedGame = (DummyGame)vbox.getUserData();
    }

    public void initDarken(Label label) {
        label.setOnMouseEntered(e -> {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.2);
            label.setEffect(colorAdjust);
        });

        label.setOnMouseExited(e -> {
            label.setEffect(null);
        });
    }

    public void setStyle(Label label, String size, String color, double width, double height) {
        label.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: " + size + "; " +
                "-fx-background-color: " + color + "; " +
                "-fx-border-color: BLACK;");
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        initDarken(label);
    }
}
