package org.scenebuilder.scenebuilder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

public class SetupFXMLController {

    @FXML
    private TextField numPlayersTextField;
    @FXML
    private VBox setupVBox;
    @FXML
    private CheckBox setupIsTutorial;

    private Stage stage;

    // Stack to get the player info
    private Stack<HBox> playerNodeStack = new Stack<>();
    private int num_players;

    // game selected in selection scene
    private DummyGame selectedGame;

    private HashMap<Integer, DummyPlayer> playerHashMap = new HashMap<Integer, DummyPlayer>();

    public void initialize() {

        selectedGame = BasicApplication.getSelectedGame();

        // Set the default num players to the min players
        int min_player = selectedGame.getMinPlayers();
        numPlayersTextField.setText(Integer.toString(min_player));

        // For loop to create num_players player to the stack
        for(int i = 0; i< min_player; i++) {
            DummyPlayer player = new DummyPlayer("Player " + (i+1), new DummyGameToken("Square"), true);
//            playerStack.add(player);
            num_players+=1;
            playerHashMap.put(num_players, player);

            // add Player Node to VBox
            addPlayerNode();
        }
    }

    @FXML
    public void addPlayer(ActionEvent event) throws IOException {

        // Add player to the stack
        if (num_players < selectedGame.getMaxPlayers()) {

            DummyPlayer player = new DummyPlayer("Player " + (num_players+1), new DummyGameToken( "Square"), true);

            num_players += 1;
            numPlayersTextField.setText(Integer.toString(num_players));

            playerHashMap.put(num_players, player);

            // add the player node to the scroll pane
            addPlayerNode();
        }
    }

    @FXML
    public void decPlayer(ActionEvent event) throws IOException {

        // Delete last player in the stack
        if (num_players > selectedGame.getMinPlayers()) {

            playerHashMap.remove(num_players);
            num_players -= 1;
            numPlayersTextField.setText(Integer.toString(num_players));

            // remove player
            removePlayerNode();
        }
    }

    @FXML
    public void addPlayerNode() {
        HBox playerHBox = new HBox();
        playerHBox.setId(Integer.toString(num_players));

        playerHBox.setAlignment(Pos.CENTER);

        Color color = playerHashMap.get(Integer.valueOf(playerHBox.getId())).getPlayerToken().getTokenColor();
        String hex = playerHashMap.get(Integer.valueOf(playerHBox.getId())).getPlayerToken().getTokenHex();

        ColorPicker colorPicker = new ColorPicker(color);
        colorPicker.setStyle("-fx-background-color: " + hex +  "; -fx-font-family: serif; ");

        // Add listener for Color Picker
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                playerHashMap.get(Integer.valueOf(playerHBox.getId())).getPlayerToken().setTokenColor(colorPicker.getValue());
                String hex = playerHashMap.get(Integer.valueOf(playerHBox.getId())).getPlayerToken().getTokenHex();
                colorPicker.setStyle("-fx-background-color: " + hex +  "; -fx-font-family: serif; ");
            }
        });

        Label playerLabel = new Label();
        playerLabel.setAlignment(Pos.CENTER);
        playerLabel.setText(playerHashMap.get(Integer.valueOf(playerHBox.getId())).getPlayerName());
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

        humanToggleButton.setSelected(playerHashMap.get(Integer.valueOf(playerHBox.getId())).getIsHuman());

        HBox.setMargin(humanToggleButton, new Insets(2, 2, 2, 2));

        ToggleButton aIToggleButton = new ToggleButton();
        aIToggleButton.setMnemonicParsing(false);
        aIToggleButton.setFont(new Font(16));
        aIToggleButton.setStyle("-fx-font-family: serif;");
        aIToggleButton.setText("AI");
        aIToggleButton.setPrefHeight(32);
        aIToggleButton.setPrefWidth(48);

        ToggleGroup group = new ToggleGroup();

        // Add listener for the toggle group to change the corresponding player's property
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle toggle, Toggle new_toggle) {
                ToggleButton selectedToggleButton =
                        (ToggleButton) group.getSelectedToggle();

                String playerText = selectedToggleButton.getText();
                playerHashMap.get(Integer.valueOf(playerHBox.getId())).setIsHuman(playerText.equals("Human"));
            }
        });

        humanToggleButton.setToggleGroup(group);
        aIToggleButton.setToggleGroup(group);

        playerHBox.getChildren().addAll(colorPicker, playerLabel, playerSeparator, humanToggleButton, aIToggleButton);

        // add hbox storing all the player label, divider, and player/human controls
        setupVBox.getChildren().add(playerHBox);
        playerNodeStack.push(playerHBox);
    }

    @FXML
    public void removePlayerNode() {
        setupVBox.getChildren().remove(playerNodeStack.pop());
    }

    @FXML
    public void backFromSetup(ActionEvent event) throws IOException {
        switchScene(event, "selectionFXML.fxml");
    }

    @FXML
    public void playFromSetup(ActionEvent event) throws IOException {

        // Move all the players from the hashmaps to an array list
        Collection<DummyPlayer> values = playerHashMap.values();
        ArrayList<DummyPlayer> dummyPlayerArrayList = new ArrayList<>(values);

        BasicApplication.setSetupData(new SetupData(new ArrayList<>(dummyPlayerArrayList), setupIsTutorial.isSelected()));
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
