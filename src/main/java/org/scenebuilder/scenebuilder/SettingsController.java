package org.scenebuilder.scenebuilder;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsController extends ScreenController {

    HBox settingsHBox;
    Label settingsLabel;
    private void initSettingsTitle() {

        settingsHBox = new HBox();
        settingsHBox.setAlignment(Pos.CENTER);
        settingsHBox.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 10));
        settingsHBox.setStyle("-fx-border-color: black");
        VBox.setMargin(settingsHBox, new Insets(10, 5, 10, 5));

        settingsLabel = new Label("Settings");
        settingsLabel.setFont(new Font(50));

        settingsHBox.getChildren().add(settingsLabel);
        screenVBox.getChildren().add(settingsHBox);
    }

    HBox fullScreenHBox;
    CheckBox fullScreenCheckBox;
    private void initFullScreen() {

        fullScreenHBox = new HBox();
        fullScreenHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        fullScreenHBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(fullScreenHBox, new Insets(10, 0, 10, 0));

        fullScreenCheckBox = new CheckBox();
        fullScreenCheckBox.setText("Full Screen Mode");
        fullScreenCheckBox.setFont(new Font(36));
        HBox.setMargin(fullScreenCheckBox, new Insets(30, 30, 30, 50));

        fullScreenHBox.getChildren().add(fullScreenCheckBox);
        screenVBox.getChildren().add(fullScreenHBox);
    }

    HBox windowSizeHBox;
    Label windowSizeLabel;
    ComboBox windowSizeComboBox;
    private void initWindowSize() {

        windowSizeHBox = new HBox();
        windowSizeHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        windowSizeHBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(windowSizeHBox, new Insets(10, 0, 10, 0));

        windowSizeLabel = new Label("Window Size: ");
        windowSizeLabel.setFont(new Font(36));
        windowSizeLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(windowSizeLabel, new Insets(0, 30, 0, 50));

        windowSizeComboBox = new ComboBox();
        windowSizeComboBox.setStyle("-fx-font-size: 24pt");

        windowSizeComboBox.setItems(FXCollections.observableList(Arrays.asList(SettingsObject.RESOLUTION_TABLE)));
        windowSizeComboBox.prefHeightProperty().bind(windowSizeLabel.heightProperty());
        HBox.setMargin(windowSizeComboBox, new Insets(0, 0, 0, 0));

        windowSizeHBox.getChildren().addAll(windowSizeLabel, windowSizeComboBox);
        screenVBox.getChildren().add(windowSizeHBox);
    }

    HBox themeHBox;
    Label themeLabel;
    ComboBox themeComboBox;
    private void initTheme() {

        themeHBox = new HBox();
        themeHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        themeHBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(themeHBox, new Insets(10, 0, 10, 0));

        themeLabel = new Label("Theme: ");
        themeLabel.setFont(new Font(36));
        themeLabel.setAlignment(Pos.CENTER);
        themeLabel.prefWidthProperty().bind(windowSizeLabel.widthProperty());
        HBox.setMargin(themeLabel, new Insets(0, 30, 0, 50));

        themeComboBox = new ComboBox();
        themeComboBox.setStyle("-fx-font-size: 24pt");

        ArrayList<String> themeOptions = new ArrayList<>();
        themeOptions.add("Default");
        themeOptions.add("Dark");
        themeComboBox.setItems(FXCollections.observableList(themeOptions));
        themeComboBox.prefHeightProperty().bind(windowSizeLabel.heightProperty());
        HBox.setMargin(themeComboBox, new Insets(0, 0, 0, 0));

        themeHBox.getChildren().addAll(themeLabel, themeComboBox);
        screenVBox.getChildren().add(themeHBox);
    }

    HBox buttonsHBox;
    Button backButton;
    Button saveButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_LEFT);
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Button();
        backButton.setFont(new Font(36));
        backButton.setText("Back");
        backButton.setPadding(new Insets(5, 10, 5, 10));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnAction(event -> {
            backFromSettings();
        });

        saveButton = new Button();
        saveButton.setFont(backButton.getFont());
        saveButton.setText("Save");
        saveButton.setPadding(backButton.getPadding());
        HBox.setMargin(saveButton, HBox.getMargin(backButton));

        saveButton.setOnAction(event -> {
           saveFromSettings();
        });

        buttonsHBox.getChildren().addAll(backButton, saveButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    public void initialize(Stage stage) {
        super.initialize(stage);

        initSettingsTitle();
        initFullScreen();
        initWindowSize();
        initTheme();
        initButtons();
    }

    // action event handlers
    private void backFromSettings() {
        MainController controller = new MainController();
        controller.initialize(stage);
    }
    private void saveFromSettings() {
        // todo save changes
        MainController controller = new MainController();
        controller.initialize(stage);
    }
}
