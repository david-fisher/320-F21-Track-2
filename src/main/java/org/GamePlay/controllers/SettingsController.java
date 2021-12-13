package org.GamePlay.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.GamePlay.BasicApplication;
import org.GamePlay.GlobalCSSValues;
import org.GamePlay.SettingsObject;

import java.util.Arrays;

public class SettingsController extends ScreenController {

    HBox settingsHBox;
    Label settingsLabel;
    private void initSettingsTitle() {

        settingsHBox = new HBox();
        settingsHBox.setAlignment(Pos.CENTER);
        settingsHBox.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 10));
        settingsHBox.setStyle("-fx-border-color: " + GlobalCSSValues.text + "; -fx-background-color: " + GlobalCSSValues.secondary);
        VBox.setMargin(settingsHBox, new Insets(10, 5, 10, 5));

        settingsLabel = new Label("Settings");
        settingsLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
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
        fullScreenCheckBox.setTextFill(Color.valueOf(GlobalCSSValues.text));
        HBox.setMargin(fullScreenCheckBox, new Insets(30, 30, 30, 50));

        fullScreenHBox.getChildren().add(fullScreenCheckBox);
        screenVBox.getChildren().add(fullScreenHBox);
    }

    // this stuff is currently not being used ----------
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

        windowSizeComboBox.setItems(FXCollections.observableList(Arrays.asList(SettingsObject.RESOLUTION_STRING_TABLE)));
        windowSizeComboBox.prefHeightProperty().bind(windowSizeLabel.heightProperty());
        HBox.setMargin(windowSizeComboBox, new Insets(0, 0, 0, 0));

        windowSizeHBox.getChildren().addAll(windowSizeLabel, windowSizeComboBox);
        //screenVBox.getChildren().add(windowSizeHBox);
    }
    // -------------------------------------------------

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
        themeLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        themeLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(themeLabel, new Insets(0, 30, 0, 50));

        themeComboBox = new ComboBox();
        themeComboBox.setStyle("-fx-font-size: 24pt; -fx-background-color: " + GlobalCSSValues.buttonBackground + "; -fx-text-fill: " + GlobalCSSValues.buttonText);

        themeComboBox.setItems(FXCollections.observableList(Arrays.asList(SettingsObject.THEME_STRING_TABLE)));
        themeComboBox.prefHeightProperty().bind(windowSizeLabel.heightProperty());
        HBox.setMargin(themeComboBox, new Insets(0, 0, 0, 0));

        themeHBox.getChildren().addAll(themeLabel, themeComboBox);
        screenVBox.getChildren().add(themeHBox);
    }

    HBox buttonsHBox;
    Label backButton;
    Label saveButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_LEFT);
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Label("Back");
        Style.setStyle(backButton, "36", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 200, 70);
        //backButton.setFont(new Font(36));
        backButton.setPadding(new Insets(5, 10, 5, 10));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnMouseClicked(event -> {
            backFromSettings();
        });

//        initButtonCSS(backButton);
//        initDarken(backButton);

        saveButton = new Label("Save");
        Style.setStyle(saveButton, "36", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText, 200, 70);
//        saveButton.setFont(backButton.getFont());
//        saveButton.setText("Save");
        saveButton.setPadding(backButton.getPadding());
        HBox.setMargin(saveButton, HBox.getMargin(backButton));

        saveButton.setOnMouseClicked(event -> {
           saveFromSettings();
        });

//        initButtonCSS(saveButton);
//        initDarken(saveButton);

        buttonsHBox.getChildren().addAll(backButton, saveButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    private void initValues() {

        // full screen
        fullScreenCheckBox.setSelected(BasicApplication.getSettingsObject().getIsFullScreen());

        // window size
        windowSizeComboBox.setValue(BasicApplication.getSettingsObject().getWindowSizeString());

        // theme
        themeComboBox.getSelectionModel().select(BasicApplication.getSettingsObject().getTheme());
    }

    public void initialize(Stage stage) {
        super.initialize(stage);

        initSettingsTitle();
        initFullScreen();
        initWindowSize();
        initTheme();
        initButtons();

        initValues();
    }

    // action event handlers
    private void backFromSettings() {
        MainController controller = new MainController();
        controller.initialize(stage);
    }
    private void saveFromSettings() {

        // save changes
        boolean isFullscreen = fullScreenCheckBox.isSelected();
        String windowSize = (String)windowSizeComboBox.getValue();
        String theme = (String)themeComboBox.getValue();

        SettingsObject newSettingsObject = new SettingsObject(isFullscreen, windowSize, theme);
        BasicApplication.setSettingsObject(newSettingsObject);

        stage = BasicApplication.restartStage(stage);

        MainController controller = new MainController();
        controller.initialize(stage);
    }

    // css
    private void initButtonCSS(Label button) {
        button.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        button.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
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
}
