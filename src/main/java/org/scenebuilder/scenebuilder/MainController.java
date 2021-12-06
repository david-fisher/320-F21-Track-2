package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends ScreenController {

    HBox settingsButtonHBox;
    private void initSettings() {

        Label settingsButton = new Label("Settings");
        setStyle(settingsButton, "45", "LightGrey", 175, 70);


        settingsButtonHBox = new HBox();
        settingsButtonHBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(settingsButtonHBox, new Insets(15, 15, 10, 10));
        VBox.setVgrow(settingsButtonHBox, Priority.ALWAYS);

        settingsButton.setOnMouseClicked(event -> {
            settingsFromMain();
        });

        settingsButtonHBox.getChildren().add(settingsButton);
        screenVBox.getChildren().add(settingsButtonHBox);
    }


    HBox fillHBox;
    private void initButtons() {

        Label playButton = new Label("Play Game");
        setStyle(playButton, "45", "LimeGreen", 275, 80);

        VBox.setMargin(playButton, new Insets(10, 10, 20, 10));

        playButton.setOnMouseClicked(event -> {
            playFromMain();
        });

        Label newButton = new Label("Create Game");
        setStyle(newButton, "45", "Blue", 275, 80);

        VBox.setMargin(newButton, VBox.getMargin(playButton));

        newButton.setOnMouseClicked(event -> {
            try {
                newFromMain(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Label editButton = new Label("Edit Game");
        setStyle(editButton, "45", "DarkMagenta", 275, 80);

        VBox.setMargin(editButton, VBox.getMargin(playButton));

        editButton.setOnMouseClicked(event -> {
            try {
                editFromMain(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Label exitButton = new Label("Exit");
        setStyle(exitButton, "45", "Red", 275, 80);

        VBox.setMargin(exitButton, VBox.getMargin(playButton));

        exitButton.setOnMouseClicked(event -> {
            exitFromMain();
        });

        fillHBox = new HBox();
        VBox.setVgrow(fillHBox, Priority.ALWAYS);

        screenVBox.getChildren().addAll(playButton, newButton, editButton, exitButton, fillHBox);
    }

    public void initialize(Stage stage) {

        super.initialize(stage);

        screenVBox.setAlignment(Pos.CENTER);
        initSettings();
        initButtons();
    }

    // event handlers
    public void settingsFromMain() {
        SettingsController controller = new SettingsController();
        controller.initialize(stage);
    }
    public void exitFromMain() {
        System.exit(0);
    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    public void playFromMain() {

        // update playable and saved games in selection panes
        BasicApplication.loadNewGames();
        BasicApplication.loadSavedGames();

        SelectionController controller = new SelectionController();
        controller.initialize(stage);
    }

    // todo implement new button
    public void newFromMain(MouseEvent event) throws IOException {
        System.out.println("New");
    }

    // todo implement edit button
    public void editFromMain(MouseEvent event) throws IOException {
        System.out.println("Edit");
    }


    // todo do we need this still?
    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
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

