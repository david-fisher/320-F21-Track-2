package org.scenebuilder.scenebuilder;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class SelectionController {

    private static Color textColor;
    private static Color primaryColor;
    private static Color secondaryColor;
    private static Color accentColor;

    AnchorPane anchorPane;
    VBox screenVBox;
    private void initAnchorPane() {

        anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-border-color: black; -fx-background-color: #" + secondaryColor.toString().substring(2));

        screenVBox = new VBox();
        AnchorPane.setTopAnchor(screenVBox, 3.0);
        AnchorPane.setBottomAnchor(screenVBox, 3.0);
        AnchorPane.setLeftAnchor(screenVBox, 3.0);
        AnchorPane.setRightAnchor(screenVBox, 3.0);
        screenVBox.setStyle("-fx-border-color: black; -fx-background-color: #" + primaryColor.toString().substring(2));

        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();   // screen - task bar
        //Rectangle2D fullScreenDimensions = Screen.getPrimary().getBounds();     // full screen

        anchorPane.setPrefWidth(screenDimensions.getWidth());
        anchorPane.setPrefHeight(screenDimensions.getHeight());

        anchorPane.getChildren().add(screenVBox);
    }

    HBox newGamesLabelHBox;
    Label newGamesLabel;
    private void initNewGamesLabel() {

        // bind HBox to vbox width to represent a row
        newGamesLabelHBox = new HBox();
        newGamesLabelHBox.setStyle("-fx-background-color: transparent");
        newGamesLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        newGamesLabel = new Label("New Games");
        newGamesLabel.setFont(new Font(36));
        newGamesLabel.setTextFill(textColor);
        HBox.setMargin(newGamesLabel, new Insets(10, 10, 10, 10));

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
        savedGamesLabel.setFont(newGamesLabel.getFont());
        savedGamesLabel.setTextFill(textColor);
        HBox.setMargin(savedGamesLabel, HBox.getMargin(newGamesLabel));

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
    Button backButton;
    Pane fillerPane;
    Button selectGameButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonsHBox.setStyle("-fx-background-color: transparent");
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Button();
        backButton.setText("Back");
        //backButton.setTextFill(Color.WHITE);
        backButton.setFont(new Font(24));
        backButton.setPadding(new Insets(5, 20, 5, 20));
        //backButton.setStyle("-fx-background-color: #" + accentColor.toString().substring(2));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnAction(event -> {
            try {
                backFromSelection(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fillerPane = new Pane();
        HBox.setHgrow(fillerPane, Priority.ALWAYS);

        selectGameButton = new Button();
        selectGameButton.setText("Select A Game");
        selectGameButton.setTextFill(backButton.getTextFill());
        selectGameButton.setFont(backButton.getFont());
        selectGameButton.setPadding(backButton.getPadding());
        selectGameButton.setStyle(backButton.getStyle());
        selectGameButton.setDisable(true);
        HBox.setMargin(selectGameButton, HBox.getMargin(backButton));

        buttonsHBox.getChildren().addAll(backButton, fillerPane, selectGameButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    private Stage stage;

    private void initColors() {

        textColor = Color.BLACK;
        primaryColor = Color.WHITE;
        secondaryColor = Color.WHITE;
        accentColor = Color.WHITE;
    }

    public void initialize(Stage stage) {

        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED); // remove title bar

        initColors();

        initAnchorPane();
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

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
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

    public void backFromSelection(ActionEvent event) throws IOException {
        switchScene(event, "mainFXML.fxml");
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
                selectGameButton.setOnAction(event -> {
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

                selectGameButton.setDisable(false);
                selectGameButton.setText("Load Saved Game");
                selectGameButton.setOnAction(event -> {
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
                    tempLabel.setText("tempGameName");
                    tempLabel.setFont(new Font(16));

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
}
