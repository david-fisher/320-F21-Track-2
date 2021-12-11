package org.Editors.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.Editors.MainMenu;
import org.GameObjects.objects.Savable;
import org.GamePlay.controllers.MainController;
import org.GamePlay.controllers.ScreenController;

public class MainMenuController extends ScreenController {
    public void changeScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }

    @FXML private void changeToGameObjectEditor(ActionEvent event) throws IOException {
        changeScene(event,"GameObjectEditor.fxml");
    }

    @FXML private void changeToRuleEditor(ActionEvent event) throws IOException {
        changeScene(event,"RuleEditor.fxml");
    }

    @FXML private void changeToGameBoardEditor(ActionEvent event) throws IOException {
        changeScene(event, "/org/GameEditor/application/GameCreator.fxml");
    }

    @FXML private void returnToMain(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Savable.closeDB();
        MainController main = new MainController();
        main.initialize(stage);
    }

}
