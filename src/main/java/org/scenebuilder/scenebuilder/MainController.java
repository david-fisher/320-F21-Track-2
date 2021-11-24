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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends ScreenController {

    HBox settingsButtonHBox;
    Button settingsButton;
    private void initSettings() {

        settingsButtonHBox = new HBox();
        settingsButtonHBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(settingsButtonHBox, new Insets(10, 10, 10, 10));
        VBox.setVgrow(settingsButtonHBox, Priority.ALWAYS);

        settingsButton = new Button();
        settingsButton.setText("Settings");
        settingsButton.setFont(new Font(36));

        settingsButton.setOnAction(event -> {
            settingsFromMain();
        });

        settingsButtonHBox.getChildren().add(settingsButton);
        screenVBox.getChildren().add(settingsButtonHBox);
    }

    Button playButton;
    Button newButton;
    Button editButton;
    Button exitButton;
    HBox fillHBox;
    private void initButtons() {

        playButton = new Button();
        playButton.setText("Play Game");
        playButton.setFont(new Font(36));
        VBox.setMargin(playButton, new Insets(10, 10, 10, 10));

        playButton.setOnAction(event -> {
            playFromMain();
        });

        newButton = new Button();
        newButton.setText("New Game");
        newButton.setFont(playButton.getFont());
        VBox.setMargin(newButton, VBox.getMargin(playButton));

        newButton.setOnAction(event -> {
            try {
                newFromMain(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton = new Button();
        editButton.setText("Edit Game");
        editButton.setFont(playButton.getFont());
        VBox.setMargin(editButton, VBox.getMargin(playButton));

        editButton.setOnAction(event -> {
            try {
                editFromMain(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton = new Button();
        exitButton.setText("Exit To Desktop");
        exitButton.setFont(playButton.getFont());
        VBox.setMargin(exitButton, VBox.getMargin(playButton));

        exitButton.setOnAction(event -> {
            exitFromMain();
        });

        fillHBox = new HBox();
        VBox.setVgrow(fillHBox, Priority.ALWAYS);

        playButton.prefWidthProperty().bind(exitButton.widthProperty());
        newButton.prefWidthProperty().bind(exitButton.widthProperty());
        editButton.prefWidthProperty().bind(exitButton.widthProperty());

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
    public void newFromMain(ActionEvent event) throws IOException {
        System.out.println("New");
    }

    // todo implement edit button
    public void editFromMain(ActionEvent event) throws IOException {
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
}
