package GameObjectUI.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

/*
fx:controller="GameObjectUI.MyController" xmlns:fx="http://javafx.com/fxml"
*/
public class GameObjectUIController {
    @FXML private Button doneButton;
    @FXML private Button cancelButton;
    @FXML private TextField cardName;
    @FXML private TextField textureFilename;
    @FXML private ColorPicker cardColor;
    @FXML private MenuButton action;

    @FXML
    private void saveCard(ActionEvent event) {
        String cardNameString = cardName.getCharacters();
        String textureFilenameString = textureFilename.getCharacters();
        Color color = cardColor.getValue();
        ObservableList<MenuItem> list = action.getItems();
        System.out.println(list);

    }
}
