package org.scenebuilder.scenebuilder;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        newGamesScrollPane.setStyle("-fx-background-color: transparent");
        HBox.setMargin(newGamesScrollPane, new Insets(10, 10, 10, 10));

        newGamesHBox = new HBox();
        newGamesHBox.prefWidthProperty().bind(Bindings.subtract(newGamesScrollPane.widthProperty(), 2));
        newGamesHBox.prefHeightProperty().bind(Bindings.subtract(newGamesScrollPane.heightProperty(), 2));
        newGamesHBox.setStyle("-fx-border-color: black");
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
        savedGamesHBox.prefWidthProperty().bind(newGamesHBox.widthProperty());
        savedGamesHBox.prefHeightProperty().bind(newGamesHBox.heightProperty());
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

        fillerPane = new Pane();
        HBox.setHgrow(fillerPane, Priority.ALWAYS);

        selectGameButton = new Button();
        selectGameButton.setText("Select A Game");
        selectGameButton.setTextFill(backButton.getTextFill());
        selectGameButton.setFont(backButton.getFont());
        selectGameButton.setPadding(backButton.getPadding());
        selectGameButton.setStyle(backButton.getStyle());
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

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
    }
}
