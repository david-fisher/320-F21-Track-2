package org.Editors.controllers;

import org.Editors.MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

public class SelectEventController implements Initializable {
  @FXML
  private ListView<String> eventsList;
  @FXML
  private Label errorLabel;
  
  private void changeScene(String fxmlFilename) {
    URL location = getClass().getResource(fxmlFilename);
    try {
        Parent root = (Parent)FXMLLoader.load(location);
        MainMenu.stage.getScene().setRoot(root);
        MainMenu.stage.show();
    } catch (IOException e){ 
        e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.add("event");
    eventsList.setItems(list);
  }

  //Handles OK button
  @FXML private void changeToRuleEditor(ActionEvent event) {
    String item = eventsList.getSelectionModel().getSelectedItem();
    if (item == null) {
      errorLabel.setText("Must select an event first");
      errorLabel.setVisible(true);
    }
    else {
      changeScene("../../../resources/RuleEditor.fxml");
    }
  }
  
  //Handles Back button
  @FXML private void changeToMainMenu(ActionEvent event) {
    changeScene("../../../resources/MainMenuScreen.fxml");
  }

  //Handles "Create new event" button
  @FXML private void createNewEvent(ActionEvent event) {
    TextInputDialog dialog = new TextInputDialog();

    dialog.setHeaderText("Create a new event");
    dialog.setContentText("Event name:");

    Optional<String> result = dialog.showAndWait();
    //If cancel was not pressed
    if (result.isPresent()) {
      if (result.get().equals("")) {
        errorLabel.setText("Event name must be non-empty");
        errorLabel.setVisible(true);
      }
      else {
        changeScene("../../../resources/RuleEditor.fxml");
      }
    }
  }
}
