package editors.main_menu.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import editors.main_menu.MainMenu;

public class MainMenuController {
    private void changeScene(String fxmlFilename) {
        URL location = getClass().getResource(fxmlFilename);
        System.out.println(location.toString());
        try {
            Parent root = (Parent)FXMLLoader.load(location);
            MainMenu.stage.getScene().setRoot(root);
            MainMenu.stage.show();
        } catch (IOException e){ 
            e.printStackTrace();
        }
    }

    
    @FXML private void changeToGameObjectEditor(ActionEvent event) {
        changeScene("../../../resources/GameObjectEditor.fxml");
    }

    @FXML private void changeToRuleEditor(ActionEvent event) {
        changeScene("../../../resources/RuleEditor.fxml");
    }
}
