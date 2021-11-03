package GameObjectUI.controllers;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/*
fx:controller="GameObjectUI.MyController" xmlns:fx="http://javafx.com/fxml"
*/
public class MyController {
    public Button doneButton;
    public Button cancelButton;

    public void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
}
