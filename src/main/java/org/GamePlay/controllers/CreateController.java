package org.GamePlay.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.GameObjects.objects.Project;
import org.GameObjects.objects.Savable;
import org.RuleEngine.engine.GameState;
import org.GamePlay.BasicApplication;

import java.io.IOException;


public class CreateController extends ScreenController {

    @FXML
    private TextField projectName;

    public void createAndEdit(ActionEvent event) throws IOException {
        Savable.intitDB();
        BasicApplication.setProject(Savable.createProject(projectName.getText()));
        changeScene(event, "/org/Editors/controllers/MainMenuScreen.fxml");
    }

    public void changeToMain(ActionEvent event) {
        MainController main = new MainController();
        main.initialize(BasicApplication.newStage(new Stage()));
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

