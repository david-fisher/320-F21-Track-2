package org.scenebuilder.scenebuilder;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class BasicFXMLController {

    public void initialize() {
        // TODO
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainScene(ActionEvent event) throws IOException {
        switchScene(event, "mainFXML.fxml");
    }

    public void switchToSelectionScene(ActionEvent event) throws IOException {
        switchScene(event, "selectionFXML.fxml");
    }

    public void switchToSetupScene(ActionEvent event) throws IOException {
        switchScene(event, "setupFXML.fxml");
    }

    public void switchToPlayScene(ActionEvent event) throws IOException {
        switchScene(event, "playFXML.fxml");
    }

    public void switchToTutorialScene(ActionEvent event) throws IOException {
        switchScene(event, "playFXML.fxml");
    }

    public void exitFromPlay(ActionEvent event) throws  IOException {
        switchScene(event, "mainFXML.fxml");
    }

    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}