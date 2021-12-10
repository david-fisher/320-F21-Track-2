package org.Editors.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import org.Editors.MainMenu;

public class MainMenuController {
    private void changeScene(String fxmlFilename) {
        URL location = getClass().getResource(fxmlFilename);
        try {

            System.out.println(location);
            Parent root = (Parent)FXMLLoader.load(location);
            MainMenu.stage.getScene().setRoot(root);
            MainMenu.stage.show();
        } catch (IOException e){ 
            e.printStackTrace();
        }
    }

    
    @FXML private void changeToGameObjectEditor(ActionEvent event) {
        changeScene("/GameObjectEditor.fxml");
    }

    @FXML private void changeToRuleEditor(ActionEvent event) {
        changeScene("/RuleEditor.fxml");
    }
}
