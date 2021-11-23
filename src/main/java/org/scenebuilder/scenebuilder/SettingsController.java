package org.scenebuilder.scenebuilder;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingsController extends ScreenController {

    HBox settingsHBox;
    Label settingsLabel;
    private void initSettingsTitle() {

    }

    HBox fullScreenHBox;
    CheckBox fullScreenCheckBox;
    private void initFullScreen() {

    }

    HBox windowSizeHBox;
    Label windowSizeLabel;
    ComboBox windowSizeComboBox;
    private void initWindowSize() {

    }

    HBox themeHBox;
    Label themeLabel;
    ComboBox themeComboBox;
    private void initTheme() {

    }

    public void initialize(Stage stage) {
        super.initialize(stage);

        initSettingsTitle();
        initFullScreen();
        initWindowSize();
        initTheme();
    }
}
