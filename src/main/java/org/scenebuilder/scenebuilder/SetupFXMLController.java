package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class SetupFXMLController {

    private Stage stage;
    private DummyGame selectedGame;
    private int num_players;
    private Stack<DummyPlayer> playerStack = new Stack<DummyPlayer>();

    public void initialize() {

        System.out.println("In setup screen");
        selectedGame = BasicApplication.getSelectedGame();
        num_players = selectedGame.getMinPlayers();

        for(int i = 0; i< num_players; i++){
            DummyPlayer player = new DummyPlayer("Player " + (i+1), true, "Blue");
            playerStack.add(player);
            System.out.println(player);
        }

    }

    @FXML
    public void addPlayer(ActionEvent event) throws IOException {
        System.out.println("Add player clicked");
        if (num_players < selectedGame.getMaxPlayers()) {
            DummyPlayer player = new DummyPlayer("Player " + (num_players + 1), true, "Blue");
            num_players += 1;
            playerStack.add(player);
        }
        System.out.println("Num Players: "+ num_players);
        System.out.println(playerStack);
    }

    @FXML
    public void decPlayer(ActionEvent event) throws IOException {
        System.out.println("Dec player clicked");
        if (num_players > selectedGame.getMinPlayers()) {
            num_players -= 1;
            playerStack.pop();
        }
        System.out.println("Num Players: "+ num_players);
        System.out.println(playerStack);
    }

    @FXML
    public void backFromSetup(ActionEvent event) throws IOException {
        switchScene(event, "selectionFXML.fxml");
    }

    @FXML
    public void playFromSetup(ActionEvent event) throws IOException {
        switchScene(event, "playFXML.fxml");
    }
    
    @FXML
    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();

    }
}
