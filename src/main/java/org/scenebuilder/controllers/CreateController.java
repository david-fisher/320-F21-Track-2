package org.scenebuilder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.GameObjects.objects.Savable;
import org.RuleEngine.engine.GameState;
import org.scenebuilder.BasicApplication;

import java.io.IOException;


public class CreateController extends ScreenController {

    @FXML
    private TextField projectName;

    public void createAndEdit(ActionEvent event) throws IOException {
        Savable.intitDB();
        Savable.createProject(projectName.getText());
        Savable.closeDB();
        changeScene(event, "/org/Editors/controllers/MainMenuScreen.fxml");
    }

    public void changeToMain(MouseEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        BasicApplication.initScreenStyle(stage);
        MainController main = new MainController();
        main.initialize(stage);
    }

    public void changeScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }
}

