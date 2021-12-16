package org.Editors.controllers;

import org.Editors.MainMenu;
import org.GamePlay.BasicApplication;
import org.RuleEngine.engine.GameState;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SelectEventController implements Initializable {
  @FXML
  private ListView<String> eventsList;
  @FXML
  private Label errorLabel;

  //Holds user-provided name for an event if they create one
  private String eventName;

  private GameState gameState = BasicApplication.getProject().getIntiGS();
   
  private void changeScene(ActionEvent event, String nextScene, boolean creatingEvent) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(nextScene));
    Parent root = (Parent)loader.load();
    //If we're changing an event, get the RuleEditorUIController and use the setEventName method to set the eventName
    //in the RuleEditorUIController to the user-provided event name
    if (creatingEvent) {
      RuleEditorUIController controller = loader.getController();
      controller.setEventName(this.eventName);
    }
    MainMenu.stage = (Stage)((Node)event.getSource()).getScene().getWindow();

    Scene scene = new Scene(root);
    MainMenu.stage.setScene(scene);
    MainMenu.stage.centerOnScreen();
    scene.getRoot().setStyle("-fx-font-family: 'serif'");
    MainMenu.stage.show();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Initialize events list to hold the current events in the events HashMap
    addEventsToEventsList();
  }

  private void addEventsToEventsList() {
    ObservableList<String> list = FXCollections.observableArrayList();
    //Add the names of the events from the hashmap to the events list
    for (String key : gameState.getAllEvents().keySet()) {
      list.addAll(key);
    }
    this.eventsList.setItems(list);
  }

  //Handles OK button
  @FXML private void changeToRuleEditor(ActionEvent event) throws IOException {
    String item = eventsList.getSelectionModel().getSelectedItem();
    if (item == null) {
      errorLabel.setText("Must select an event first");
      errorLabel.setVisible(true);
    }
    else {
      this.eventName = item;
      changeScene(event, "RuleEditor.fxml", true);
    }
  }
  
  //Handles Back button
  @FXML private void changeToMainMenu(ActionEvent event) throws IOException {
    //changeScene(event, "MainMenuScreen.fxml", false);
    changeScene(event, "MainMenuScreen.fxml", false);
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
  @FXML private void createNewEvent(ActionEvent event) throws IOException {
    TextInputDialog dialog = new TextInputDialog();

    dialog.setHeaderText("Create a new event");
    dialog.setContentText("Event name:");

    Optional<String> result = dialog.showAndWait();
    //If cancel was not pressed
    if (result.isPresent()) {
      if (verifyCreateEvent(result)) {
        this.eventName = result.get();
        changeScene(event, "RuleEditor.fxml", true);
      }
    }
  }

  //Handles "Remove" button
  @FXML private void handleRemoveBtn(ActionEvent event) {
    String item = eventsList.getSelectionModel().getSelectedItem();
    if (item == null) {
      //User has not selected an item
      errorLabel.setText("Must select an event to remove");
      errorLabel.setVisible(true);
    }
    else {
      //Remove the currently selected item in the events list from the HashMap of events
      HashMap<String, ArrayList<org.RuleEngine.nodes.Node>> currEvents = this.gameState.getAllEvents();
      currEvents.remove(item);
      this.gameState.setAllEvents(currEvents);
      //Remove that item from the events list so it visually disappears for the user
      addEventsToEventsList();
    }
  }
}
