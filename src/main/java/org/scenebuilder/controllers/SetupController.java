package org.scenebuilder.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.GameObjects.objects.GameObject;
import org.GameObjects.objects.Gamepiece;
import org.GameObjects.objects.Player;
import org.scenebuilder.BasicApplication;
import org.scenebuilder.GlobalCSSValues;
import org.scenebuilder.SetupData;
import org.scenebuilder.dummy.DummyGame;
import org.scenebuilder.dummy.DummyInventory;

import java.io.IOException;
import java.util.*;

public class SetupController extends ScreenController {

    HBox gameSetupLabelHBox;
    Label gameSetupLabel;
    private void initGameSetupLabel() {

        gameSetupLabelHBox = new HBox();
        gameSetupLabelHBox.setAlignment(Pos.CENTER);
        gameSetupLabelHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        gameSetupLabelHBox.setStyle("-fx-border-color: " + GlobalCSSValues.text);
        gameSetupLabelHBox.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(gameSetupLabelHBox, new Insets(10, 5, 10, 5));

        gameSetupLabel = new Label("Game Setup");
        gameSetupLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
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
        numPlayersLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        numPlayersLabel.setFont(new Font(24));
        HBox.setMargin(numPlayersLabel, new Insets(20, 100, 30, 10));

        minusPlayerButton = new Button();
        minusPlayerButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        minusPlayerButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
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
        playerCountTextField.setStyle("-fx-background-color: " + GlobalCSSValues.secondary + "; -fx-text-fill: " + GlobalCSSValues.buttonText);
        playerCountTextField.setDisable(true);
        playerCountTextField.setFont(new Font(24));
        playerCountTextField.setAlignment(Pos.CENTER);
        playerCountTextField.prefWidthProperty().bind(Bindings.multiply(minusPlayerButton.widthProperty(), 2));
        HBox.setMargin(playerCountTextField, new Insets(5, 5, 5, 5));

        plusPlayerButton = new Button();
        plusPlayerButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        plusPlayerButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
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
        playersScrollPane.setStyle("-fx-border-color: " + GlobalCSSValues.text);
        playersScrollPane.prefWidthProperty().bind(screenVBox.widthProperty());
        playersScrollPane.prefHeightProperty().bind(Bindings.multiply(screenVBox.heightProperty(), 0.7));

        playersVBox = new VBox();
        playersVBox.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
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
    HBox checkBoxHBox;
    CheckBox tutorialModeCheckBox;
    Button startGameButton;
    private void initBottomBar() {

        bottomHBox = new HBox();
        bottomHBox.setStyle("-fx-background-color: transparent");
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(bottomHBox, Priority.ALWAYS);

        backButton = new Button();
        backButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        backButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        backButton.setText("Back");
        backButton.setFont(new Font(24));
        HBox.setMargin(backButton, new Insets(10, 20, 10, 20));

        backButton.setOnAction(event -> {
            backFromSetup();
        });

        checkBoxHBox = new HBox();
        checkBoxHBox.setAlignment(Pos.CENTER);

        tutorialModeCheckBox = new CheckBox();
        tutorialModeCheckBox.setTextFill(Color.valueOf(GlobalCSSValues.text));
        tutorialModeCheckBox.setText("Enable Tutorial Mode");
        HBox.setMargin(tutorialModeCheckBox, new Insets(10, 20, 10, 20));

        checkBoxHBox.getChildren().add(tutorialModeCheckBox);

        startGameButton = new Button();
        startGameButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        startGameButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
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

        bottomHBox.getChildren().addAll(backButton, checkBoxHBox, startGameButton);
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

    private HashMap<Integer, Player> playerHashMap = new HashMap<Integer, Player>();
    int max_player = 8; // todo, read value from game settings
    private HashMap<Gamepiece, Boolean> dummyTokenMap = generateNGameTokens(max_player);

    private void initStuff() {

        selectedGame = BasicApplication.getSelectedGame();

        // Set the default num players to the min players
        int min_player = 2; // todo, read value from game settings
        playerCountTextField.setText(Integer.toString(min_player));

        // For loop to create num_players player to the stack
        for(int i = 0; i < min_player; i++) {
            ArrayList<Gamepiece> gamePieces = new ArrayList<>();
            //"Token " + (i+1), "Square"
            Player player = new Player("Player " + (i+1), gamePieces, new DummyInventory("Inventory " + (i+1), new ArrayList<GameObject>()), true);
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

            ArrayList<Gamepiece> gamePieces = new ArrayList<>();
            // gamePieces.add(new Gamepiece());
            //"Token " + (num_players+1), "Square"

            Player player = new Player("Player " + (num_players+1), gamePieces, new DummyInventory("Inventory " + (num_players+1), new ArrayList<GameObject>()), true);

            num_players += 1;
            playerCountTextField.setText(Integer.toString(num_players));

            playerHashMap.put(num_players, player);

            // add the player node to the scroll pane
            addPlayerNode();
            System.out.println("From addPlayer()");
            System.out.println(playerHashMap);
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
        playerHBox.setMinHeight(75);

        Player hboxPlayer = playerHashMap.get(Integer.valueOf(playerHBox.getId()));

        ArrayList<Gamepiece> pGamePiece = hboxPlayer.getGamePieces();

        // --- Combo Box Code ---
        final ComboBox comboBox = new ComboBox();
        comboBox.setId("comboBox"+playerHBox.getId());
        comboBox.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground + "; -fx-start-margin: 10px; -fx-padding: 5px;\n" +
                "    -fx-border-insets: 5px;\n" +
                "    -fx-background-insets: 5px;");
        // Adds all available token here
        comboBox.getItems().addAll(getAvailableToken());

        // Selects the top one for default and make it unavailable
        comboBox.getSelectionModel().select(0);
        Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
        // Add to player's gamepiece
        playerHashMap.get(Integer.valueOf(playerHBox.getId())).addPiece((Gamepiece) selectedItem);
        dummyTokenMap.put((Gamepiece) selectedItem, !dummyTokenMap.get(selectedItem));

        // On mouse click the combo box, we have to refresh the options
        comboBox.setOnMousePressed((e) -> {
            Object inSelectedItem = comboBox.getSelectionModel().getSelectedItem();
            if (inSelectedItem != null)
                dummyTokenMap.put((Gamepiece) inSelectedItem, true);
            comboBox.getItems().clear();
            comboBox.getItems().addAll(getAvailableToken());
        });

        // On mouse clicked the items IN the combo box, we have to make that item unavailable
        comboBox.setOnAction((event) -> {
            Object inSelectedItem = comboBox.getSelectionModel().getSelectedItem();
            if (inSelectedItem != null)
                dummyTokenMap.put((Gamepiece) inSelectedItem, !dummyTokenMap.get(inSelectedItem));
        });
        // --- Combo Box Code End ---

        // -- Color picker Code Starts --
        Color color = getRandomColor();
//        if (pGamePiece.size() > 0){color = pGamePiece.get(0).getColor();}// todo get game piece by reference

        ColorPicker colorPicker = new ColorPicker(color);
        // Set bg color and disable text
        colorPicker.setStyle("-fx-background-color: " + hex(color) +  "; -fx-font-family: serif;" +
                " -fx-color-label-visible: false ; ");

        // Add listener for Color Picker
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                System.out.println("Something");
                Player player = playerHashMap.get(Integer.valueOf(playerHBox.getId()));
                Color initColor = player.getColor();
                Integer ID = Integer.valueOf(playerHBox.getId());
                for(Map.Entry<Integer, Player> p : playerHashMap.entrySet()) {
                    if (p.getValue().getColor().equals(colorPicker.getValue())) {
                        if(p.getKey() == ID){
                            continue;
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error!");
                        alert.setHeaderText("Duplicate Color Detected!");
                        alert.setContentText("Players cannot have the same colors, please change it.");
                        player.setColor(initColor);
                        Rectangle rec = (Rectangle) colorPicker.lookup("Rectangle");
                        rec.setFill(initColor);
                        hboxPlayer.getGamePieces().get(0).setColor(initColor); // todo get game piece by reference
//                        String hex = hboxPlayer.getGameTokens().get(0).getTokenHex();
                        colorPicker.setStyle("-fx-background-color: " + hex(initColor) +  "; -fx-font-family: serif; -fx-color-label-visible: false;");
                        alert.showAndWait();
                        break;
                    }
                    else{
                        hboxPlayer.getGamePieces().get(0).setColor(colorPicker.getValue()); // todo get game piece by reference
                        Color c = colorPicker.getValue();
                        player.setColor(c);
//                        String hex = hboxPlayer.getGameTokens().get(0).getTokenHex();
                        colorPicker.setStyle("-fx-background-color: " + hex(c) +  "; -fx-font-family: serif; -fx-color-label-visible: false;");
                    }
                }
                System.out.println(playerHashMap);
                System.out.println(initColor);
            }
        });
        // -- Color picker Code Ends --

        Separator playerSeparator1 = new Separator();
        playerSeparator1.setOrientation(Orientation.VERTICAL);
        playerSeparator1.setPrefHeight(27);
        playerSeparator1.setPrefWidth(84);

        TextField playerField = new TextField();
        playerField.setAlignment(Pos.CENTER);
        playerField.setText(hboxPlayer.getPlayerID());
        playerField.setFont(new Font(16));
        playerField.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground + "; -fx-text-fill: " + GlobalCSSValues.buttonText);
        playerField.setPrefWidth(114);
        playerField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                Integer ID = Integer.valueOf(playerHBox.getId());
                Player player = playerHashMap.get(ID);
                String text = playerField.getText();
                if(!t1) {
                    player.setPlayerID(text);
                    for (Map.Entry<Integer, Player> p : playerHashMap.entrySet()) {
                        if (p.getKey() == ID) {
                            continue;
                        }
                        if (p.getValue().getPlayerID().equals(text)) {

//                          OPTIONAL: Alert box below.
                            System.out.println("Old State: " + playerHashMap.entrySet());

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error!");
                            alert.setHeaderText("Duplicate Name Detected!");
                            alert.setContentText("Players cannot have the same names, please change it.");
                            alert.showAndWait();
                            playerField.setText((text.substring(0, text.length() - 1)));
                            player.setPlayerID(playerField.getText());
                            System.out.println("New State: " + playerHashMap.entrySet());
                            playerField.requestFocus();
                        }
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
        humanToggleButton.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground);
        humanToggleButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        humanToggleButton.setText("Human");
        humanToggleButton.setPrefHeight(32);
        humanToggleButton.setPrefWidth(72);

        humanToggleButton.setSelected(hboxPlayer.getIsHuman());

        HBox.setMargin(humanToggleButton, new Insets(2, 2, 2, 2));

        ToggleButton aIToggleButton = new ToggleButton();
        aIToggleButton.setMnemonicParsing(false);
        aIToggleButton.setFont(new Font(16));
        aIToggleButton.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground);
        aIToggleButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        aIToggleButton.setText("AI");
        aIToggleButton.setPrefHeight(32);
        aIToggleButton.setPrefWidth(48);

        ToggleGroup group = new ToggleGroup();

        // This bit of code prevents the toggle button from not being selected
        group.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });

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

        playerHBox.getChildren().addAll(colorPicker,comboBox, playerSeparator1, playerField, playerSeparator,
                humanToggleButton, aIToggleButton);

        // add hbox storing all the player label, divider, and player/human controls
        playersVBox.getChildren().add(playerHBox);
        playerNodeStack.push(playerHBox);
    }

    public void removePlayerNode() {
        // -- Make the selected shape Available Again (Combo Box Stuff) --
        HBox player = playerNodeStack.pop();
        ComboBox x = (ComboBox) player.getChildren().get(1);
        //dummyTokenMap.remove(x.getValue());
        dummyTokenMap.put((Gamepiece) x.getValue(), Boolean.TRUE);
        // Combo Box Cod End

        playersVBox.getChildren().remove(player);
    }

    public void backFromSetup() {
        SelectionController controller = new SelectionController();
        controller.initialize(stage);
    }

    // Generate N (dummy) tokens for the players
    // This is a shit method, only for DEMO purposes. If this makes the final product then LOL
    private HashMap<Gamepiece, Boolean> generateNGameTokens(int n){
        HashMap<Gamepiece, Boolean> dummyTokenMap = new HashMap<>();

        for(int i = 0; i < n; i++){
            Gamepiece gt = new Gamepiece();
            dummyTokenMap.put(gt, true);
        }
        return dummyTokenMap;
    }

    private ArrayList<Gamepiece> getAvailableToken(){
        Set<Gamepiece> set = dummyTokenMap.keySet();
        ArrayList<Gamepiece> availToken = new ArrayList<>();
        for (Gamepiece gt : set){
            if(dummyTokenMap.get(gt)){availToken.add(gt);}
        }
//        Collections.sort(availToken);
        return availToken;
    }

    public void playFromSetup(ActionEvent event) throws IOException {

        System.out.println(playerHashMap);

        // Get modified name
        for ( Node h: playersVBox.getChildren()) {
            for (Node t: ((HBox) h).getChildren()) {
                Player hboxPlayer = playerHashMap.get(Integer.valueOf(((HBox) h).getId()));
                // This is setting the player name
                if (t instanceof TextField)
                    hboxPlayer.setPlayerID(((TextField) t).getText());

                // This is setting the player's GameToken
                if (t instanceof ComboBox) {
                    // Set the color to the Token
                    Gamepiece piece = (Gamepiece) ((ComboBox) t).getSelectionModel().getSelectedItem();
                    Color c = hboxPlayer.getGamePieces().get(0).getColor();
                    piece.setColor(c);
                    System.out.println(piece);

                    // Assign the token to the player
                    if(hboxPlayer.getGamePieces().size() == 1)
                        hboxPlayer.setPiece(0, piece);
                    else
                        hboxPlayer.addPiece(piece);
                }
            }
        }

        // Move all the players from the hashmaps to an array list
        Collection<Player> values = playerHashMap.values();
        ArrayList<Player> dummyPlayerArrayList = new ArrayList<>(values);

        BasicApplication.setSetupData(new SetupData(new ArrayList<Player>(dummyPlayerArrayList), tutorialModeCheckBox.isSelected()));
        PlayController controller = new PlayController();
        controller.initialize(stage);
    }

    public static String hex( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

    public Color getRandomColor(){
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        return Color.rgb(red, green, blue, .99);
    }

}
