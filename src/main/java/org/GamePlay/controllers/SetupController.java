package org.GamePlay.controllers;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.GameObjects.objects.GameObject;
import org.GameObjects.objects.Gamepiece;
import org.GameObjects.objects.Player;
import org.GameObjects.objects.Project;
import org.RuleEngine.engine.GameState;
import org.GamePlay.BasicApplication;
import org.GamePlay.GlobalCSSValues;
import org.GamePlay.SetupData;

import java.util.*;

public class SetupController extends ScreenController {

    // GUI creation ---------------------------------

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
    Label minusPlayerButton;
    Label playerCountLabel;
    Label plusPlayerButton;
    private void initPlayerHeading() {

        numPlayersHBox = new HBox();
        numPlayersHBox.setAlignment(Pos.CENTER);
        numPlayersHBox.prefWidthProperty().bind(screenVBox.widthProperty());

        numPlayersLabel = new Label("Number Of Players:");
        numPlayersLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        numPlayersLabel.setFont(new Font(24));
        HBox.setMargin(numPlayersLabel, new Insets(20, 100, 30, 10));

        minusPlayerButton = new Label("-");
        minusPlayerButton.setAlignment(Pos.CENTER);
        minusPlayerButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        minusPlayerButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        minusPlayerButton.setFont(new Font(24));
        minusPlayerButton.setPadding(new Insets(10, 20, 10, 20));
        HBox.setMargin(minusPlayerButton, new Insets(2, 2, 2, 2));

        playerCountLabel = new Label();
        playerCountLabel.setAlignment(Pos.CENTER);
        playerCountLabel.setStyle("-fx-background-color: " + GlobalCSSValues.secondary + "; -fx-text-fill: " + GlobalCSSValues.buttonText);
        playerCountLabel.setFont(new Font(24));
        playerCountLabel.setAlignment(Pos.CENTER);
        playerCountLabel.setPadding(minusPlayerButton.getPadding());
        HBox.setMargin(playerCountLabel, new Insets(5, 5, 5, 5));

        plusPlayerButton = new Label("+");
        plusPlayerButton.setAlignment(Pos.CENTER);
        plusPlayerButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        plusPlayerButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        plusPlayerButton.setFont(new Font(24));
        plusPlayerButton.setPadding(minusPlayerButton.getPadding());
        HBox.setMargin(plusPlayerButton, HBox.getMargin(plusPlayerButton));

        minusPlayerButton.prefWidthProperty().bind(plusPlayerButton.widthProperty());
        playerCountLabel.prefWidthProperty().bind(Bindings.multiply(plusPlayerButton.widthProperty(), 2));

        numPlayersHBox.getChildren().addAll(numPlayersLabel, minusPlayerButton, playerCountLabel, plusPlayerButton);
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
    HBox centralHBox;
    Label tutorialLabel;
    Label tutorialToggleLabel;
    Label playerOrderLabel;
    Label playerOrderToggleLabel;
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
        backButton.setPadding(new Insets(10, 30, 10, 30));
        HBox.setMargin(backButton, new Insets(10, 20, 10, 20));

        centralHBox = new HBox();
        centralHBox.setAlignment(Pos.CENTER);
        centralHBox.setStyle("-fx-background-color: transparent");
        HBox.setHgrow(centralHBox, Priority.ALWAYS);

        tutorialLabel = new Label("Tutorial Mode: ");
        tutorialLabel.setFont(new Font(24));
        tutorialLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        HBox.setMargin(tutorialLabel, new Insets(0, 0, 0, 20));

        tutorialToggleLabel = new Label("Disabled");
        tutorialToggleLabel.setAlignment(Pos.CENTER);
        tutorialToggleLabel.setFont(tutorialLabel.getFont());
        tutorialToggleLabel.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        tutorialToggleLabel.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        tutorialToggleLabel.prefHeightProperty().bind(tutorialLabel.heightProperty());
        tutorialToggleLabel.prefWidthProperty().bind(tutorialLabel.widthProperty());
        tutorialToggleLabel.setPadding(new Insets(10, 30, 10, 30));
        HBox.setMargin(tutorialToggleLabel, new Insets(0, 0, 0, 40));

        playerOrderLabel = new Label("Turn Order: ");
        playerOrderLabel.setFont(tutorialLabel.getFont());
        playerOrderLabel.setTextFill(tutorialLabel.getTextFill());
        playerOrderLabel.prefHeightProperty().bind(tutorialLabel.heightProperty());
        HBox.setMargin(playerOrderLabel, new Insets(0, 0, 0, 100));

        playerOrderToggleLabel = new Label("Random");
        playerOrderToggleLabel.setAlignment(Pos.CENTER);
        playerOrderToggleLabel.setFont(playerOrderLabel.getFont());
        playerOrderToggleLabel.setTextFill(tutorialToggleLabel.getTextFill());
        playerOrderToggleLabel.setStyle(tutorialToggleLabel.getStyle());
        playerOrderToggleLabel.prefHeightProperty().bind(playerOrderLabel.heightProperty());
        playerOrderToggleLabel.prefWidthProperty().bind(Bindings.multiply(tutorialLabel.widthProperty(), 1.2));
        playerOrderToggleLabel.setPadding(tutorialToggleLabel.getPadding());
        HBox.setMargin(playerOrderToggleLabel, HBox.getMargin(tutorialToggleLabel));

        centralHBox.getChildren().addAll(tutorialLabel, tutorialToggleLabel, playerOrderLabel, playerOrderToggleLabel);

        startGameButton = new Button();
        startGameButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        startGameButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        startGameButton.setText("Start Game");
        startGameButton.setFont(new Font(24));
        startGameButton.setPadding(new Insets(10, 30, 10, 30));
        HBox.setMargin(startGameButton, new Insets(10, 20, 10, 20));

        bottomHBox.getChildren().addAll(backButton, centralHBox, startGameButton);
        screenVBox.getChildren().add(bottomHBox);
    }

    // constructor -----------------------------------
    public void initialize(Stage stage) {

        super.initialize(stage);

        initGameSetupLabel();
        initPlayerHeading();
        initPlayersVBox();
        initBottomBar();

        // stuff that needs to happen -------------

        // load settings from BasicApplication
        loadSetupFromBasicApplication();

        // instantiate setup object
        initSetupObject();

        // initialize visuals with their values
        initPlayerNodesInVBox();
        updateTutorialToggleValue();
        initPlayerOrderToggleValue();

        // add event handlers -
        initEventHandlers();
    }

    // constants -----------------

    private final String[] TUTORIAL_STRINGS = { "Enabled", "Disabled" };
    private final String[] PLAYER_ORDER_STRINGS = { "In Order", "Randomized" };

    // class attributes ----------

    // the project selected in selectionScreen // todo determine what the point of this is... do we need it?
    Project selectedProject;

    // the selected game
    GameState selectedGame;

    // the setup data that is passed back to BasicApplication to be read by PlayScreen
    SetupData setupData;

    // init functions
    private void loadSetupFromBasicApplication() {

        // load in the selected project from BasicApplication // todo what is this for?
        selectedProject = BasicApplication.getProject();

        // load in the selected game from BasicApplication
        selectedGame = BasicApplication.getSelectedGame();
    }
    private void initSetupObject() {

        // instantiate new setupData object
        setupData = new SetupData();

        // set players to default -> min num players
        // todo read real min num players from project settings
        int minNumPlayers = 2;
        for(int i = 0; i < minNumPlayers; ++i) {

            // add a player to the list
            addPlayer();
        }

        // set tutorial mode to be initially disabled
        setupData.setIsTutorialMode(false);
    }
    private void initPlayerNodesInVBox() {

        // clear (init means start fresh as opposed to add)
        playersVBox.getChildren().clear();

        // for each player, add a corresponding node
        setupData.getPlayers().forEach( (player) -> {
            addPlayerNode();
        });
    }
    private void initPlayerOrderToggleValue() {

        playerOrderToggleLabel.setText(PLAYER_ORDER_STRINGS[0]);
    }
    private void initEventHandlers() {

        minusPlayerButton.setOnMouseClicked( (event -> {
            minusButtonPressed();
        }));

        plusPlayerButton.setOnMouseClicked( (event -> {
            plusButtonPressed();
        }));

        tutorialToggleLabel.setOnMouseClicked( (event -> {
            tutorialButtonPressed();
        }));

        playerOrderToggleLabel.setOnMouseClicked( (event -> {
            playerOrderButtonPressed();
        }));

        backButton.setOnMouseClicked( (event -> {
            backFromSetup();
        }));

        startGameButton.setOnMouseClicked( (event -> {
            playFromSetup();
        }));
    }

    // update functions
    private void updateTutorialToggleValue() {

        boolean tutorialEnabled = setupData.isTutorialMode();

        if(tutorialEnabled == true) {

            tutorialToggleLabel.setText(TUTORIAL_STRINGS[0]);

        } else {

            tutorialToggleLabel.setText(TUTORIAL_STRINGS[1]);
        }

    }

    // helpers
    // todo implement helpers
    private void addPlayer() {

        int curNumPlayers = setupData.getPlayers().size();

        // return if addPlayer cannot be performed
        if (curNumPlayers == 10) { // todo read real max players value
            return;
        }

        // define player name
        String playerName = "Player " + (curNumPlayers + 1);

        // define list of player's pieces
        ArrayList<Gamepiece> gamePieces = new ArrayList<>();
        gamePieces.add(new Gamepiece());

        // define list of player's game objects
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        // define default isHuman
        boolean isHuman = false;
        if (curNumPlayers == 0) {

            // make first player a human ; otherwise make them ai
            isHuman = true;
        }

        Player newPlayer = new Player(playerName, gamePieces, gameObjects, isHuman);
        setupData.addPlayer(newPlayer);
    }
    private void addPlayerNode() {


    }
    private void removePlayer() {

        int curNumPlayers = setupData.getPlayers().size();

        // return if addPlayer cannot be performed
        if (curNumPlayers == 2) { // todo read real min players value
            return;
        }

        // get player to remove
        int lastPlayerIndex = curNumPlayers - 1;

        // remove player
        setupData.removePlayer(lastPlayerIndex);
    }
    private void removePlayerNode() {


    }
    private void dealWithPlayOrderOnLeaveSetup() {

        // if randomized is selected
        if("Randomized".equals(playerOrderToggleLabel.getText())) {

            // randomize the order of the players
            Collections.shuffle(setupData.getPlayers());
        }
    }

    // event handlers
    private void minusButtonPressed() {

        // remove player from list
        removePlayer();

        // remove player node from vbox
        removePlayerNode();
    }
    private void plusButtonPressed() {

        // add player to list
        addPlayer();

        // add player node
        addPlayerNode();
    }
    private void tutorialButtonPressed() {

        // establish what the current setting is
        int curSelection = Arrays.asList(TUTORIAL_STRINGS).indexOf(tutorialToggleLabel.getText());

        // increment selection by 1 (next selection option)
        curSelection += 1;

        // check if we are at the end of the options list
        if (curSelection == TUTORIAL_STRINGS.length) {

            // move back to the beginning of the options list
            curSelection = 0;
        }

        // set new text
        tutorialToggleLabel.setText(TUTORIAL_STRINGS[curSelection]);
    }
    private void playerOrderButtonPressed() {

        // establish what its current setting was
        int curSelection = Arrays.asList(PLAYER_ORDER_STRINGS).indexOf(playerOrderToggleLabel.getText());

        // increment selection by 1 (next selection option)
        curSelection += 1;

        // check if we are at the end of the options list
        if (curSelection == PLAYER_ORDER_STRINGS.length) {

            // move back to the beginning of the options list
            curSelection = 0;
        }

        // set new text
        playerOrderToggleLabel.setText(PLAYER_ORDER_STRINGS[curSelection]);
    }
    private void backFromSetup() {

        SelectionController controller = new SelectionController();
        controller.initialize(stage);
    }
    private void playFromSetup() {

        // deal with playerOrder
        dealWithPlayOrderOnLeaveSetup();

        // save data somehow
        // todo: We have two things: list of players, isTutorial
        // todo: we need to pass this information along to playScreen somehow
        // todo: idk how to do it

        // we can get the above information with the follow functions
        setupData.getPlayers();
        setupData.isTutorialMode();

        // switch screens
        PlayController controller = new PlayController();
        controller.initialize(stage);
    }

//    public void playFromSetup(ActionEvent event) throws IOException {
//
//        // Get modified name
//        for ( Node h: playersVBox.getChildren()) {
//            for (Node t: ((HBox) h).getChildren()) {
//                Player hboxPlayer = playerHashMap.get(Integer.valueOf(((HBox) h).getId()));
//                // This is setting the player name
//                if (t instanceof TextField)
//                    hboxPlayer.setLabel(((TextField) t).getText());
//
//                // This is setting the player's GameToken
//                if (t instanceof ComboBox) {
//                    // Set the color to the Token
//                    Gamepiece piece = (Gamepiece) ((ComboBox) t).getSelectionModel().getSelectedItem();
//                    Color c = hboxPlayer.getGamePieces().get(0).getColor();
//                    piece.setColor(c);
//                    System.out.println(piece);
//
//                    // Assign the token to the player
//                    if(hboxPlayer.getGamePieces().size() == 1)
//                        hboxPlayer.setPiece(0, piece);
//                    else
//                        hboxPlayer.addPiece(piece);
//                }
//            }
//        }
//
//        // Move all the players from the hashmaps to an array list
//        Collection<Player> values = playerHashMap.values();
//        ArrayList<Player> dummyPlayerArrayList = new ArrayList<>(values);
//
//        BasicApplication.setSetupData(new SetupData(new ArrayList<Player>(dummyPlayerArrayList), tutorialModeCheckBox.isSelected()));
//        PlayController controller = new PlayController();
//        controller.initialize(stage);
//    }

    // ----------------------- imported stuff from the original write (ugly) -------------------------------------

    // Stack to get the player info
//    private Stack<HBox> playerNodeStack = new Stack<>();
//    private int num_players;
//
//    // game selected in selection scene
//    private GameState selectedGame;
//
//    private HashMap<Integer, Player> playerHashMap = new HashMap<Integer, Player>();
//    int max_player = 8; // todo, read value from game settings
//    private HashMap<Gamepiece, Boolean> dummyTokenMap = generateNGameTokens(max_player);
//
//    private void initStuff() {
//
//        selectedGame = BasicApplication.getSelectedGame();
//
//        // Set the default num players to the min players
//        int min_player = 2; // todo, read value from game settings
//        playerCountTextField.setText(Integer.toString(min_player));
//
//        // For loop to create num_players player to the stack
//        for(int i = 0; i < min_player; i++) {
//            ArrayList<Gamepiece> gamePieces = new ArrayList<>();
//            //"Token " + (i+1), "Square"
//            Player player = new Player("Player " + (i+1), gamePieces, new ArrayList<GameObject>(), true);
//            num_players+=1;
//            playerHashMap.put(num_players, player);
//
//            // add Player Node to VBox
//            addPlayerNode();
//        }
//
//
//        int max_player = 8;// todo, read value from game settings
//        System.out.println(dummyTokenMap);
//    }
//
//    public void addPlayer(ActionEvent event) throws IOException {
//
//        // Add player to the stack
//        if (num_players < 8) { // todo read value from game settings
//
//            ArrayList<Gamepiece> gamePieces = new ArrayList<>();
//            // gamePieces.add(new Gamepiece());
//            //"Token " + (num_players+1), "Square"
//
//            Player player = new Player("Player " + (num_players+1), gamePieces, new ArrayList<GameObject>(), true);
//
//            num_players += 1;
//            playerCountTextField.setText(Integer.toString(num_players));
//
//            playerHashMap.put(num_players, player);
//
//            // add the player node to the scroll pane
//            addPlayerNode();
//            System.out.println("From addPlayer()");
//            System.out.println(playerHashMap);
//        }
//    }
//
//    public void decPlayer(ActionEvent event) throws IOException {
//
//        // Delete last player in the stack
//        if (num_players > 2) { // todo read min value from game settings
//
//            playerHashMap.remove(num_players);
//            num_players -= 1;
//            playerCountTextField.setText(Integer.toString(num_players));
//
//            // remove player
//            removePlayerNode();
//        }
//    }
//
//    public void addPlayerNode() {
//
//        HBox playerHBox = new HBox();
//        playerHBox.setId(Integer.toString(num_players));
//
//        playerHBox.setAlignment(Pos.CENTER);
//        playerHBox.setMinHeight(75);
//
//        Player hboxPlayer = playerHashMap.get(Integer.valueOf(playerHBox.getId()));
//
//        ArrayList<Gamepiece> pGamePiece = hboxPlayer.getGamePieces();
//
//        // --- Combo Box Code ---
//        final ComboBox comboBox = new ComboBox();
//        comboBox.setId("comboBox"+playerHBox.getId());
//        comboBox.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground + "; -fx-start-margin: 10px; -fx-padding: 5px;\n" +
//                "    -fx-border-insets: 5px;\n" +
//                "    -fx-background-insets: 5px;");
//        // Adds all available token here
//        comboBox.getItems().addAll(getAvailableToken());
//
//        // Selects the top one for default and make it unavailable
//        comboBox.getSelectionModel().select(0);
//        Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
//        // Add to player's gamepiece
//        playerHashMap.get(Integer.valueOf(playerHBox.getId())).addPiece((Gamepiece) selectedItem);
//        dummyTokenMap.put((Gamepiece) selectedItem, !dummyTokenMap.get(selectedItem));
//
//        // On mouse click the combo box, we have to refresh the options
//        comboBox.setOnMousePressed((e) -> {
//            Object inSelectedItem = comboBox.getSelectionModel().getSelectedItem();
//            if (inSelectedItem != null)
//                dummyTokenMap.put((Gamepiece) inSelectedItem, true);
//            comboBox.getItems().clear();
//            comboBox.getItems().addAll(getAvailableToken());
//        });
//
//        // On mouse clicked the items IN the combo box, we have to make that item unavailable
//        comboBox.setOnAction((event) -> {
//            Object inSelectedItem = comboBox.getSelectionModel().getSelectedItem();
//            if (inSelectedItem != null)
//                dummyTokenMap.put((Gamepiece) inSelectedItem, !dummyTokenMap.get(inSelectedItem));
//        });
//        // --- Combo Box Code End ---
//
//        // -- Color picker Code Starts --
//        Color color = getRandomColor();
////        if (pGamePiece.size() > 0){color = pGamePiece.get(0).getColor();}// todo get game piece by reference
//
//        ColorPicker colorPicker = new ColorPicker(color);
//        // Set bg color and disable text
//        colorPicker.setStyle("-fx-background-color: " + hex(color) +  "; -fx-font-family: serif;" +
//                " -fx-color-label-visible: false ; ");
//
//        // Add listener for Color Picker
//        colorPicker.setOnAction(new EventHandler() {
//            public void handle(Event t) {
//                System.out.println("Something");
//                Player player = playerHashMap.get(Integer.valueOf(playerHBox.getId()));
//                Color initColor = player.getColor();
//                Integer ID = Integer.valueOf(playerHBox.getId());
//                for(Map.Entry<Integer, Player> p : playerHashMap.entrySet()) {
//                    if (p.getValue().getColor().equals(colorPicker.getValue())) {
//                        if(p.getKey() == ID){
//                            continue;
//                        }
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Error!");
//                        alert.setHeaderText("Duplicate Color Detected!");
//                        alert.setContentText("Players cannot have the same colors, please change it.");
//                        player.setColor(initColor);
//                        Rectangle rec = (Rectangle) colorPicker.lookup("Rectangle");
//                        rec.setFill(initColor);
//                        hboxPlayer.getGamePieces().get(0).setColor(initColor); // todo get game piece by reference
////                        String hex = hboxPlayer.getGameTokens().get(0).getTokenHex();
//                        colorPicker.setStyle("-fx-background-color: " + hex(initColor) +  "; -fx-font-family: serif; -fx-color-label-visible: false;");
//                        alert.showAndWait();
//                        break;
//                    }
//                    else{
//                        hboxPlayer.getGamePieces().get(0).setColor(colorPicker.getValue()); // todo get game piece by reference
//                        Color c = colorPicker.getValue();
//                        player.setColor(c);
////                        String hex = hboxPlayer.getGameTokens().get(0).getTokenHex();
//                        colorPicker.setStyle("-fx-background-color: " + hex(c) +  "; -fx-font-family: serif; -fx-color-label-visible: false;");
//                    }
//                }
//                System.out.println(playerHashMap);
//                System.out.println(initColor);
//            }
//        });
//        // -- Color picker Code Ends --
//
//        Separator playerSeparator1 = new Separator();
//        playerSeparator1.setOrientation(Orientation.VERTICAL);
//        playerSeparator1.setPrefHeight(27);
//        playerSeparator1.setPrefWidth(84);
//
//        TextField playerField = new TextField();
//        playerField.setAlignment(Pos.CENTER);
//        playerField.setText(hboxPlayer.getLabel());
//        playerField.setFont(new Font(16));
//        playerField.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground + "; -fx-text-fill: " + GlobalCSSValues.buttonText);
//        playerField.setPrefWidth(114);
//        playerField.focusedProperty().addListener(new ChangeListener<Boolean>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
//                Integer ID = Integer.valueOf(playerHBox.getId());
//                Player player = playerHashMap.get(ID);
//                String text = playerField.getText();
//                if(!t1) {
//                    player.setLabel(text);
//                    for (Map.Entry<Integer, Player> p : playerHashMap.entrySet()) {
//                        if (p.getKey() == ID) {
//                            continue;
//                        }
//                        if (p.getValue().getLabel().equals(text)) {
//
////                          OPTIONAL: Alert box below.
//                            System.out.println("Old State: " + playerHashMap.entrySet());
//
//                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                            alert.setTitle("Error!");
//                            alert.setHeaderText("Duplicate Name Detected!");
//                            alert.setContentText("Players cannot have the same names, please change it.");
//                            alert.showAndWait();
//                            playerField.setText((text.substring(0, text.length() - 1)));
//                            player.setLabel(playerField.getText());
//                            System.out.println("New State: " + playerHashMap.entrySet());
//                            playerField.requestFocus();
//                        }
//                    }
//                }
//            }
//        });
//
//        Separator playerSeparator = new Separator();
//        playerSeparator.setOrientation(Orientation.VERTICAL);
//        playerSeparator.setPrefHeight(27);
//        playerSeparator.setPrefWidth(84);
//
//        ToggleButton humanToggleButton = new ToggleButton();
//        humanToggleButton.setMnemonicParsing(false);
//        humanToggleButton.setFont(new Font(16));
//        humanToggleButton.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground);
//        humanToggleButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
//        humanToggleButton.setText("Human");
//        humanToggleButton.setPrefHeight(32);
//        humanToggleButton.setPrefWidth(72);
//
//        humanToggleButton.setSelected(hboxPlayer.getIsHuman());
//
//        HBox.setMargin(humanToggleButton, new Insets(2, 2, 2, 2));
//
//        ToggleButton aIToggleButton = new ToggleButton();
//        aIToggleButton.setMnemonicParsing(false);
//        aIToggleButton.setFont(new Font(16));
//        aIToggleButton.setStyle("-fx-font-family: serif; -fx-background-color: " + GlobalCSSValues.buttonBackground);
//        aIToggleButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
//        aIToggleButton.setText("AI");
//        aIToggleButton.setPrefHeight(32);
//        aIToggleButton.setPrefWidth(48);
//
//        ToggleGroup group = new ToggleGroup();
//
//        // This bit of code prevents the toggle button from not being selected
//        group.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
//            if (newVal == null)
//                oldVal.setSelected(true);
//        });
//
//        // Add listener for the toggle group to change the corresponding player's property
//        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//            public void changed(ObservableValue<? extends Toggle> ov,
//                                Toggle toggle, Toggle new_toggle) {
//                ToggleButton selectedToggleButton =
//                        (ToggleButton) group.getSelectedToggle();
//
//                String playerText = selectedToggleButton.getText();
//                hboxPlayer.setIsHuman(playerText.equals("Human"));
//            }
//        });
//
//        humanToggleButton.setToggleGroup(group);
//        aIToggleButton.setToggleGroup(group);
//
//        playerHBox.getChildren().addAll(colorPicker,comboBox, playerSeparator1, playerField, playerSeparator,
//                humanToggleButton, aIToggleButton);
//
//        // add hbox storing all the player label, divider, and player/human controls
//        playersVBox.getChildren().add(playerHBox);
//        playerNodeStack.push(playerHBox);
//    }
//
//    public void removePlayerNode() {
//        // -- Make the selected shape Available Again (Combo Box Stuff) --
//        HBox player = playerNodeStack.pop();
//        ComboBox x = (ComboBox) player.getChildren().get(1);
//        //dummyTokenMap.remove(x.getValue());
//        dummyTokenMap.put((Gamepiece) x.getValue(), Boolean.TRUE);
//        // Combo Box Cod End
//
//        playersVBox.getChildren().remove(player);
//    }
//
//    public void backFromSetup() {
//        SelectionController controller = new SelectionController();
//        controller.initialize(stage);
//    }
//
//    // Generate N (dummy) tokens for the players
//    // This is a shit method, only for DEMO purposes. If this makes the final product then LOL
//    private HashMap<Gamepiece, Boolean> generateNGameTokens(int n){
//        HashMap<Gamepiece, Boolean> dummyTokenMap = new HashMap<>();
//
//        for(int i = 0; i < n; i++){
//            Gamepiece gt = new Gamepiece();
//            dummyTokenMap.put(gt, true);
//        }
//        return dummyTokenMap;
//    }
//
//    private ArrayList<Gamepiece> getAvailableToken(){
//        Set<Gamepiece> set = dummyTokenMap.keySet();
//        ArrayList<Gamepiece> availToken = new ArrayList<>();
//        for (Gamepiece gt : set){
//            if(dummyTokenMap.get(gt)){availToken.add(gt);}
//        }
////        Collections.sort(availToken);
//        return availToken;
//    }
//
//
//
//    public static String hex( Color color ) {
//        return String.format( "#%02X%02X%02X",
//                (int)( color.getRed() * 255 ),
//                (int)( color.getGreen() * 255 ),
//                (int)( color.getBlue() * 255 ) );
//    }
//
//    public Color getRandomColor() {
//        Random rand = new Random(System.currentTimeMillis());
//
//        int red = rand.nextInt(255);
//        int green = rand.nextInt(255);
//        int blue = rand.nextInt(255);
//
//        return Color.rgb(red, green, blue, .99);
//    }

}
