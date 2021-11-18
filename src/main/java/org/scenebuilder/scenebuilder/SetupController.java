package org.scenebuilder.scenebuilder;

import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class SetupController extends ScreenController {

    HBox gameSetupLabelHBox;
    Label gameSetupLabel;
    private void initGameSetupLabel() {

        gameSetupLabelHBox = new HBox();
        gameSetupLabelHBox.setAlignment(Pos.CENTER);
        gameSetupLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        gameSetupLabelHBox.setStyle("-fx-border-color: black");
        gameSetupLabelHBox.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(gameSetupLabelHBox, new Insets(10, 5, 10, 5));

        gameSetupLabel = new Label("Game Setup");
        gameSetupLabel.setFont(new Font(45));

        gameSetupLabelHBox.getChildren().add(gameSetupLabel);
        screenVBox.getChildren().add(gameSetupLabelHBox);
    }

    HBox numPlayersHBox;
    Label numPlayersLabel;
    Button minusPlayerButton;
    TextField playerCountTextField;
    Button plusPlayerButton;
    private void initPlayerHeading() {

        numPlayersHBox = new HBox();
        numPlayersHBox.setAlignment(Pos.CENTER);
        numPlayersHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        numPlayersLabel = new Label("Number Of Players:");
        numPlayersLabel.setFont(new Font(24));
        HBox.setMargin(numPlayersLabel, new Insets(20, 100, 30, 10));

        minusPlayerButton = new Button();
        minusPlayerButton.setText("-");
        minusPlayerButton.setFont(new Font(24));
        HBox.setMargin(minusPlayerButton, new Insets(2, 2, 2, 2));

        minusPlayerButton.setOnAction(event -> {
            try {
                decPlayer(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        playerCountTextField = new TextField();
        playerCountTextField.setFont(new Font(24));
        playerCountTextField.setAlignment(Pos.CENTER);
        playerCountTextField.prefWidthProperty().bind(Bindings.multiply(minusPlayerButton.widthProperty(), 2));
        HBox.setMargin(playerCountTextField, new Insets(5, 5, 5, 5));

        plusPlayerButton = new Button();
        plusPlayerButton.setText("+");
        plusPlayerButton.setFont(new Font(24));
        HBox.setMargin(plusPlayerButton, HBox.getMargin(plusPlayerButton));

        plusPlayerButton.setOnAction(event -> {
            try {
                addPlayer(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        numPlayersHBox.getChildren().addAll(numPlayersLabel, minusPlayerButton, playerCountTextField, plusPlayerButton);
        screenVBox.getChildren().add(numPlayersHBox);
    }

    ScrollPane playersScrollPane;
    VBox playersVBox;
    private void initPlayersVBox() {

        playersScrollPane = new ScrollPane();
        playersScrollPane.setStyle("-fx-border-color: black");
        playersScrollPane.prefWidthProperty().bind(screenVBox.widthProperty());
        playersScrollPane.prefHeightProperty().bind(Bindings.multiply(screenVBox.heightProperty(), 0.7));

        playersVBox = new VBox();
        playersVBox.setAlignment(Pos.TOP_CENTER);
        playersVBox.prefHeightProperty().bind(Bindings.subtract(playersScrollPane.heightProperty(), 10));
        playersVBox.prefWidthProperty().bind(Bindings.subtract(playersScrollPane.widthProperty(), 10));
        playersVBox.setPadding(new Insets(20, 20, 20, 20));
        VBox.setMargin(playersVBox, new Insets(10, 5, 10, 5));

        playersScrollPane.setContent(playersVBox);
        screenVBox.getChildren().add(playersScrollPane);
    }

    HBox bottomHBox;
    Button backButton;
    CheckBox tutorialModeCheckBox;
    Button startGameButton;
    private void initBottomBar() {

        bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(bottomHBox, Priority.ALWAYS);

        backButton = new Button();
        backButton.setText("Back");
        backButton.setFont(new Font(24));
        HBox.setMargin(backButton, new Insets(10, 20, 10, 20));

        backButton.setOnAction(event -> {
            backFromSetup();
        });

        tutorialModeCheckBox = new CheckBox();
        tutorialModeCheckBox.setText("Enable Tutorial Mode");
        HBox.setMargin(tutorialModeCheckBox, new Insets(10, 20, 10, 20));

        startGameButton = new Button();
        startGameButton.setText("Start Game");
        startGameButton.setFont(new Font(24));
        HBox.setMargin(startGameButton, new Insets(10, 20, 10, 20));

        startGameButton.setOnAction(event -> {
            try {
                playFromSetup(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bottomHBox.getChildren().addAll(backButton, tutorialModeCheckBox, startGameButton);
        screenVBox.getChildren().add(bottomHBox);
    }

    public void initialize(Stage stage) {

        super.initialize(stage);

        initGameSetupLabel();
        initPlayerHeading();
        initPlayersVBox();
        initBottomBar();

        initStuff();
    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    // Stack to get the player info
    private Stack<HBox> playerNodeStack = new Stack<>();
    private int num_players;

    // game selected in selection scene
    private DummyGame selectedGame;

    private HashMap<Integer, DummyPlayer> playerHashMap = new HashMap<Integer, DummyPlayer>();
    int max_player = 8; // todo, read value from game settings
    private HashMap<DummyGameToken, Boolean> dummyTokenMap = generateNGameTokens(max_player);

    private void initStuff() {

        selectedGame = BasicApplication.getSelectedGame();

        // Set the default num players to the min players
        int min_player = 2; // todo, read value from game settings
        playerCountTextField.setText(Integer.toString(min_player));

        // For loop to create num_players player to the stack
        for(int i = 0; i < min_player; i++) {
            ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
            gameTokens.add(new DummyGameToken("Token " + (i+1), "Square"));
            DummyPlayer player = new DummyPlayer("Player " + (i+1), gameTokens, new DummyInventory("Inventory " + (i+1)), true);
            num_players+=1;
            playerHashMap.put(num_players, player);

            // add Player Node to VBox
            addPlayerNode();
        }


        int max_player = 8;// todo, read value from game settings
        System.out.println(dummyTokenMap);
    }

    public void addPlayer(ActionEvent event) throws IOException {

        // Add player to the stack
        if (num_players < 8) { // todo read value from game settings

            ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
            gameTokens.add(new DummyGameToken("Token " + (num_players+1), "Square"));

            DummyPlayer player = new DummyPlayer("Player " + (num_players+1), gameTokens, new DummyInventory("Inventory " + (num_players+1)), true);

            num_players += 1;
            playerCountTextField.setText(Integer.toString(num_players));

            playerHashMap.put(num_players, player);

            // add the player node to the scroll pane
            addPlayerNode();
        }
    }

    public void decPlayer(ActionEvent event) throws IOException {

        // Delete last player in the stack
        if (num_players > 2) { // todo read min value from game settings

            playerHashMap.remove(num_players);
            num_players -= 1;
            playerCountTextField.setText(Integer.toString(num_players));

            // remove player
            removePlayerNode();
        }
    }

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

        playerField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                DummyPlayer player = playerHashMap.get(Integer.valueOf(playerHBox.getId()));
                player.setPlayerID(t1);
                System.out.println("Changed called, s = " + s + " and t1 = " + t1);
                System.out.println(playerHashMap.toString());
                for(Map.Entry<Integer, DummyPlayer> p : playerHashMap.entrySet()) {
                    if (p.getValue().getPlayerID().equals(t1) && p.getKey() != Integer.valueOf(playerHBox.getId())) {
                        System.out.println("True tho");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error!");
                        alert.setHeaderText("Duplicate Name Detected!");
                        alert.setContentText("Players cannot have the same names, please change it.");
                        alert.showAndWait();
                    }
                }
            }
        });

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

        Separator playerSeparator2 = new Separator();
        playerSeparator2.setOrientation(Orientation.VERTICAL);
        playerSeparator2.setPrefHeight(27);
        playerSeparator2.setPrefWidth(84);

        // --- Combo Box Code ---
        final ComboBox comboBox = new ComboBox();
        comboBox.setStyle("-fx-font-family: serif;");
        // Adds all available token here
        comboBox.getItems().addAll(getAvailableToken());

        // Selects the top one for default and make it unavailable
        comboBox.getSelectionModel().select(0);
        Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
        dummyTokenMap.put((DummyGameToken) selectedItem, !dummyTokenMap.get(selectedItem));

        // On mouse click the combo box, we have to refresh the options
        comboBox.setOnMousePressed((e) -> {
            Object inSelectedItem = comboBox.getSelectionModel().getSelectedItem();
            if (inSelectedItem != null)
                dummyTokenMap.put((DummyGameToken) inSelectedItem, true);
            comboBox.getItems().clear();
            comboBox.getItems().addAll(getAvailableToken());
        });

        // On mouse clicked the items IN the combo box, we have to make that item unavailable
        comboBox.setOnAction((event) -> {
            Object inSelectedItem = comboBox.getSelectionModel().getSelectedItem();
            if (inSelectedItem != null)
                dummyTokenMap.put((DummyGameToken) inSelectedItem, !dummyTokenMap.get(inSelectedItem));
        });
        // --- Combo Box Code End ---

        playerHBox.getChildren().addAll(colorPicker, playerSeparator1, playerField, playerSeparator,
                humanToggleButton, aIToggleButton, playerSeparator2, comboBox);

        // add hbox storing all the player label, divider, and player/human controls
        playersVBox.getChildren().add(playerHBox);
        playerNodeStack.push(playerHBox);
    }

    public void removePlayerNode() {
        playersVBox.getChildren().remove(playerNodeStack.pop());
    }

    public void backFromSetup() {
        SelectionController controller = new SelectionController();
        controller.initialize(stage);
    }

    // Generate N (dummy) tokens for the players
    // This is a shit method, only for DEMO purposes. If this makes the final product then LOL
    private HashMap<DummyGameToken, Boolean> generateNGameTokens(int n){
        HashMap<DummyGameToken, Boolean> dummyTokenMap = new HashMap<>();

        for(int i = 0; i < n; i++){
            DummyGameToken gt = new DummyGameToken("Token " + (i+1), (Color) null, "Shape " + (i+1));
            dummyTokenMap.put(gt, true);
        }
        return dummyTokenMap;
    }

    private ArrayList<DummyGameToken> getAvailableToken(){
        Set<DummyGameToken> set = dummyTokenMap.keySet();
        ArrayList<DummyGameToken> availToken = new ArrayList<>();
        for (DummyGameToken gt : set){
            if(dummyTokenMap.get(gt)){availToken.add(gt);}
        }
        Collections.sort(availToken);
        return availToken;
    }

    public void playFromSetup(ActionEvent event) throws IOException {
    
        // Get modified name
        for ( Node h: playersVBox.getChildren()) {
            for (Node t: ((HBox) h).getChildren()) {
                DummyPlayer hboxPlayer = playerHashMap.get(Integer.valueOf(((HBox) h).getId()));
                // This is setting the player name
                if (t instanceof TextField)
                    hboxPlayer.setPlayerID(((TextField) t).getText());

                // This is setting the player's GameToken
                if (t instanceof ComboBox) {
                    // Set the color to the Token
                    DummyGameToken token = (DummyGameToken) ((ComboBox) t).getSelectionModel().getSelectedItem();
                    Color c = hboxPlayer.getGameTokens().get(0).getTokenColor();
                    token.setTokenColor(c);
                    System.out.println(token);

                    // Assign the token to the player
                    if(hboxPlayer.getGameTokens().size() == 1)
                        hboxPlayer.setToken(0, token);
                    else
                        hboxPlayer.addToken(token);
                }
            }
        }

        // Move all the players from the hashmaps to an array list
        Collection<DummyPlayer> values = playerHashMap.values();
        ArrayList<DummyPlayer> dummyPlayerArrayList = new ArrayList<>(values);

        BasicApplication.setSetupData(new SetupData(new ArrayList<>(dummyPlayerArrayList), tutorialModeCheckBox.isSelected()));
        PlayController controller = new PlayController();
        controller.initialize(stage);
    }

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

    public void setButtonSize(Button button, float prefWidth, float prefHeight, int fontSize) {
        button.setPrefWidth(prefWidth);
        button.setPrefHeight(prefHeight);

        button.setMinWidth(button.getPrefWidth());
        button.setMaxWidth(button.getPrefWidth());
        button.setMinHeight(button.getPrefHeight());
        button.setMaxHeight(button.getPrefHeight());

        button.setStyle("-fx-font-size: "+fontSize+"; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4;");
    }

}
