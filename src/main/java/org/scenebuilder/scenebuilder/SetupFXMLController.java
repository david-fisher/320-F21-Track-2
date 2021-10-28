package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class SetupFXMLController {

    @FXML
    private TextField numPlayersField;
    @FXML
    private VBox setupVBox;

    private Stage stage;

    // Stack to get the player info
    private Stack<DummyPlayer> playerStack = new Stack<DummyPlayer>();
    private Stack<HBox> playerNodeStack = new Stack<>();
    private int num_players;

    // game selected in selection scene
    private DummyGame selectedGame;

    public void initialize() {

        System.out.println("In setup screen");
        selectedGame = BasicApplication.getSelectedGame();
        num_players = selectedGame.getMinPlayers(); // Set the default num players to the min players

        numPlayersField.setText(Integer.toString(num_players));

        // For loop to create num_players player to the stack
        for(int i = 0; i< num_players; i++){
            DummyPlayer player = new DummyPlayer("Player " + (i+1), true, "Blue");
            playerStack.add(player);
            addPlayerNode();
            System.out.println(player);

        }
    }

    @FXML
    public void addPlayer(ActionEvent event) throws IOException {

        // Add player to the stack
        if (num_players < selectedGame.getMaxPlayers()) {
            DummyPlayer player = new DummyPlayer("Player " + (num_players + 1), true, "Blue");
            num_players += 1;
            numPlayersField.setText(Integer.toString(num_players));
            playerStack.add(player);

            // add the player node to the scroll pane
            addPlayerNode();
        }
    }

    @FXML
    public void decPlayer(ActionEvent event) throws IOException {
        System.out.println("Dec player clicked");
        // Delete last player in the stack
        if (num_players > selectedGame.getMinPlayers()) {
            num_players -= 1;
            numPlayersField.setText(Integer.toString(num_players));
            String nameOfRemovedPlayer = playerStack.pop().getPlayerName();

            // remove player
            removePlayerNode(nameOfRemovedPlayer);
        }
        System.out.println("Num Players: "+ num_players);
        System.out.println(playerStack);
    }

    @FXML
    public void addPlayerNode() {

        HBox playerHBox = new HBox();
        playerHBox.setAlignment(Pos.CENTER);

        Label playerLabel = new Label();
        playerLabel.setAlignment(Pos.CENTER);
        playerLabel.setText(playerStack.peek().getPlayerName());
        playerLabel.setFont(new Font(16));
        playerLabel.setStyle("-fx-font-family: serif;");
        playerLabel.setPrefWidth(114);

        Separator playerSeparator = new Separator();
        playerSeparator.setOrientation(Orientation.VERTICAL);
        playerSeparator.setPrefHeight(27);
        playerSeparator.setPrefWidth(84);

        ToggleButton humanToggleButton = new ToggleButton();
        humanToggleButton.setMnemonicParsing(false);
        humanToggleButton.setFont(new Font(16));
        humanToggleButton.setStyle("-fx-font-family: serif;");
        humanToggleButton.setText("Human");
        humanToggleButton.setPrefHeight(32);
        humanToggleButton.setPrefWidth(72);

        HBox.setMargin(humanToggleButton, new Insets(2, 2, 2, 2));

        ToggleButton aIToggleButton = new ToggleButton();
        aIToggleButton.setMnemonicParsing(false);
        aIToggleButton.setFont(new Font(16));
        aIToggleButton.setStyle("-fx-font-family: serif;");
        aIToggleButton.setText("AI");
        aIToggleButton.setPrefHeight(32);
        aIToggleButton.setPrefWidth(48);

        playerHBox.getChildren().addAll(playerLabel, playerSeparator, humanToggleButton, aIToggleButton);

        // add hbox storing all the player label, divider, and player/human controls
        setupVBox.getChildren().add(playerHBox);
        playerNodeStack.push(playerHBox);
    }

    @FXML
    public void removePlayerNode(String nameOfRemovedPlayer) {
        setupVBox.getChildren().remove(playerNodeStack.pop());
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