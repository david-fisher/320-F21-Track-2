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

  //Holds user-provided name for an event if they create one
  private String eventName;
  
  private void changeScene(String fxmlFilename, boolean creatingEvent) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(fxmlFilename));
    try {
        Parent root = (Parent)loader.load();
        //If we're changing an event, get the RuleEditorUIController and use the setEventName method to set the eventName
        //in the RuleEditorUIController to the user-provided event name
        if (creatingEvent) {
          RuleEditorUIController controller = loader.getController();
          controller.setEventName(this.eventName);
        }
        MainMenu.stage.getScene().setRoot(root);
        MainMenu.stage.show();
    } catch (IOException e){ 
        e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("event1", "event2");
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
      this.eventName = item;
      changeScene("../../../resources/RuleEditor.fxml", true);
    }
  }
  
  //Handles Back button
  @FXML private void changeToMainMenu(ActionEvent event) {
    changeScene("../../../resources/MainMenuScreen.fxml", false);
  }

  //Helper for createNewEvent
  //Checks whether or not the user-provided event name is valid
  private boolean verifyCreateEvent(Optional<String> result) {
    //Return false if the user did not provide a name for the event
    if (result.get().equals("")) {
      errorLabel.setText("Event name must be non-empty");
      errorLabel.setVisible(true);
      return false;
    }
    ObservableList<String> list = eventsList.getItems();
    //If the user-provided event name matches an existing event name return false
    if (list.filtered(str -> str.equalsIgnoreCase(result.get())).size() != 0) {
      errorLabel.setText("Event name must be unique");
      errorLabel.setVisible(true);
      return false;
    }
    return true;
  }

  //Handles "Create new event" button
  @FXML private void createNewEvent(ActionEvent event) {
    TextInputDialog dialog = new TextInputDialog();

    dialog.setHeaderText("Create a new event");
    dialog.setContentText("Event name:");

    Optional<String> result = dialog.showAndWait();
    //If cancel was not pressed
    if (result.isPresent()) {
      if (verifyCreateEvent(result)) {
        this.eventName = result.get();
        changeScene("../../../resources/RuleEditor.fxml", true);
      }
    }
  }
}