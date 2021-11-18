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
        int min_player = 2; // todo, read value from game settings
        numPlayersTextField.setText(Integer.toString(min_player));

        // For loop to create num_players player to the stack
        for(int i = 0; i< min_player; i++) {
            ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
            gameTokens.add(new DummyGameToken("Token " + (i+1), "Square"));
            DummyPlayer player = new DummyPlayer("Player " + (i+1), gameTokens, new DummyInventory("Inventory " + (i+1)), true);
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
        if (num_players < 8) { // todo read value from game settings

            ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
            gameTokens.add(new DummyGameToken("Token " + (num_players+1), "Square"));

            DummyPlayer player = new DummyPlayer("Player " + (num_players+1), gameTokens, new DummyInventory("Inventory " + (num_players+1)), true);

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
        if (num_players > 2) { // todo read min value from game settings

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

        DummyPlayer hboxPlayer = playerHashMap.get(Integer.valueOf(playerHBox.getId()));

        Color color = hboxPlayer.getGameTokens().get(0).getTokenColor(); // todo get game piece by reference
        String hex = hboxPlayer.getGameTokens().get(0).getTokenHex();

        ColorPicker colorPicker = new ColorPicker(color);
        // Set bg color and disable text
        colorPicker.setStyle("-fx-background-color: " + hex +  "; -fx-font-family: serif; -fx-color-label-visible: false ; ");


        // Add listener for Color Picker
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                hboxPlayer.getGameTokens().get(0).setTokenColor(colorPicker.getValue()); // todo get game piece by reference
                String hex = hboxPlayer.getGameTokens().get(0).getTokenHex();
                colorPicker.setStyle("-fx-background-color: " + hex +  "; -fx-font-family: serif; -fx-color-label-visible: false;");
            }
        });


        Separator playerSeparator1 = new Separator();
        playerSeparator1.setOrientation(Orientation.VERTICAL);
        playerSeparator1.setPrefHeight(27);
        playerSeparator1.setPrefWidth(84);

        TextField playerField = new TextField();
        playerField.setAlignment(Pos.CENTER);
        playerField.setText(hboxPlayer.getPlayerID());
        playerField.setFont(new Font(16));
        playerField.setStyle("-fx-font-family: serif;");
        playerField.setPrefWidth(114);

        // playerField.textProperty().addListener(new ChangeListener<String>() {
        //     TODO: Actually make this work... (Nic)
        //     @Override
        //     public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        //         if (playerHashMap.containsValue(s)) {
        //             Stage popupWindow = new Stage();
        //             popupWindow.initModality(Modality.APPLICATION_MODAL);
        //             Label nameCheckError = new Label("Duplicate name detected! Pick a different name!");
        //             Button exitButton = new Button("Exit");
        //             setButtonSize(exitButton, 170, 80, 40);
        //             exitButton.setOnAction(e->{
        //                 popupWindow.close();
        //             });
        //         }
        //     }
        // });

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

        humanToggleButton.setSelected(hboxPlayer.getIsHuman());

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
                hboxPlayer.setIsHuman(playerText.equals("Human"));
            }
        });

        humanToggleButton.setToggleGroup(group);
        aIToggleButton.setToggleGroup(group);

        playerHBox.getChildren().addAll(colorPicker, playerSeparator1, playerField, playerSeparator, humanToggleButton, aIToggleButton);

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

        // Get modified name
        for ( Node h: setupVBox.getChildren()) {
            for (Node t: ((HBox) h).getChildren()) {
                DummyPlayer hboxPlayer = playerHashMap.get(Integer.valueOf(((HBox) h).getId()));
                if(t instanceof TextField)
                    hboxPlayer.setPlayerID(((TextField) t).getText());}
        }

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
