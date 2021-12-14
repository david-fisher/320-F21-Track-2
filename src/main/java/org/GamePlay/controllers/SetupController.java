package org.GamePlay.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
        Style.setStyle(minusPlayerButton, "24", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,0, 0);
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
        Style.setStyle(plusPlayerButton, "24", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,0, 0);
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
    Label backButton;
    HBox centralHBox;
    Label tutorialLabel;
    Label tutorialToggleLabel;
    Label playerOrderLabel;
    Label playerOrderToggleLabel;
    Label startGameButton;
    private void initBottomBar() {

        bottomHBox = new HBox();
        bottomHBox.setStyle("-fx-background-color: transparent");
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(bottomHBox, Priority.ALWAYS);

        backButton = new Label("Back");
        Style.setStyle(backButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,175, 70);
        HBox.setMargin(backButton, new Insets(10, 10, 10, 20));

        centralHBox = new HBox();
        centralHBox.setAlignment(Pos.CENTER);
        centralHBox.setStyle("-fx-background-color: transparent");
        HBox.setHgrow(centralHBox, Priority.ALWAYS);

        tutorialLabel = new Label("Tutorial Mode: ");
        tutorialLabel.setFont(new Font(24));
        tutorialLabel.setTextFill(Color.valueOf(GlobalCSSValues.text));
        HBox.setMargin(tutorialLabel, new Insets(0, 10, 0, 10));

        tutorialToggleLabel = new Label("Disabled");
        Style.setStyle(tutorialToggleLabel, ""+tutorialLabel.getFont().getSize()+"", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,tutorialLabel.getWidth(), tutorialLabel.getHeight());
        tutorialToggleLabel.prefHeightProperty().bind(tutorialLabel.heightProperty());
        tutorialToggleLabel.prefWidthProperty().bind(tutorialLabel.widthProperty());
        tutorialToggleLabel.setPadding(new Insets(10, 20, 10, 20));
        HBox.setMargin(tutorialToggleLabel, new Insets(0, 20, 0, 20));

        playerOrderLabel = new Label("Turn Order: ");
        playerOrderLabel.setFont(tutorialLabel.getFont());
        playerOrderLabel.setTextFill(tutorialLabel.getTextFill());
        playerOrderLabel.prefHeightProperty().bind(tutorialLabel.heightProperty());
        HBox.setMargin(playerOrderLabel, new Insets(0, 10, 0, 10));

        playerOrderToggleLabel = new Label("Random");
        Style.setStyle(playerOrderToggleLabel, ""+playerOrderLabel.getFont().getSize()+"", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,playerOrderLabel.getWidth(), playerOrderLabel.getHeight());
        playerOrderToggleLabel.prefHeightProperty().bind(playerOrderLabel.heightProperty());
        playerOrderToggleLabel.prefWidthProperty().bind(Bindings.multiply(tutorialLabel.widthProperty(), 1.2));
        playerOrderToggleLabel.setPadding(tutorialToggleLabel.getPadding());
        HBox.setMargin(playerOrderToggleLabel, HBox.getMargin(tutorialToggleLabel));

        centralHBox.getChildren().addAll(tutorialLabel, tutorialToggleLabel, playerOrderLabel, playerOrderToggleLabel);

        startGameButton = new Label("Start Game");
        Style.setStyle(startGameButton, "40", GlobalCSSValues.buttonBackground, GlobalCSSValues.buttonText,270, 70);
        HBox.setMargin(startGameButton, new Insets(10, 20, 10, 10));

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
        setup();

        // initialize visuals with their values
        updatePlayerCountLabel();
        initPlayerNodesInVBox();
        updateTutorialToggleValue();
        initPlayerOrderToggleValue();

        // add event handlers -
        initEventHandlers();
    }

    // constants -----------------

    private final String[] HUMAN_AI_STRINGS = {"Human", "AI"};
    private final String[] TUTORIAL_STRINGS = {"Enabled", "Disabled"};
    private final String[] PLAYER_ORDER_STRINGS = {"In Order", "Randomized"};

    // class attributes ----------

    // the project selected in selectionScreen // todo determine what the point of this is... do we need it?
    Project selectedProject;

    // the selected game
    GameState selectedGame;

    ArrayList<Player> players;

    int numPieces;

    // init functions
    private void loadSetupFromBasicApplication() {

        // load in the selected project from BasicApplication // todo what is this for?
        selectedProject = BasicApplication.getProject();

        // load in the selected game from BasicApplication
        selectedGame = BasicApplication.getProject().getIntiGS();
    }
    private void setup() {

        // instantiate new setupData object
        players = selectedGame.getAllPlayers();
        numPieces = players.get(0).getGamePieces().size();

        // set players to default -> min num players
        // todo read real min num players from project settings
        int minNumPlayers = selectedGame.getMinPlayer();
        for (int i = 1; i < minNumPlayers; ++i) {
            // add a player to the list
            addPlayer();
        }

        // set tutorial mode to be initially disabled
        selectedGame.setTutorialEnabled(false);
    }
    private void initPlayerNodesInVBox() {

        // clear (init means start fresh as opposed to add)
        playersVBox.getChildren().clear();

        // for each player, add a corresponding node
        players.forEach((player) -> addPlayerNode(player));
    }
    private void initPlayerOrderToggleValue() {

        playerOrderToggleLabel.setText(PLAYER_ORDER_STRINGS[0]);
    }
    private void initEventHandlers() {

        minusPlayerButton.setOnMouseClicked((event -> minusButtonPressed()));

        plusPlayerButton.setOnMouseClicked((event -> plusButtonPressed()));

        tutorialToggleLabel.setOnMouseClicked((event -> tutorialButtonPressed()));

        playerOrderToggleLabel.setOnMouseClicked((event -> playerOrderButtonPressed()));

        backButton.setOnMouseClicked((event -> backFromSetup()));

        startGameButton.setOnMouseClicked((event -> playFromSetup()));
    }

    // update functions
    private void updateTutorialToggleValue() {

        boolean tutorialEnabled = selectedGame.getTutorialEnabled();

        if (tutorialEnabled) {

            tutorialToggleLabel.setText(TUTORIAL_STRINGS[0]);

        } else {

            tutorialToggleLabel.setText(TUTORIAL_STRINGS[1]);
        }

    }
    private void updatePlayerCountLabel() {

        // get number of players
        int numPlayers = players.size();

        // update text
        playerCountLabel.setText(Integer.toString(numPlayers));
    }

    // helpers
    private void addPlayer() {

        int curNumPlayers = players.size();

        if (curNumPlayers == selectedGame.getMaxPlayer()) {
            return;
        }
        // define player name
        String playerName = "Player " + (curNumPlayers + 1);
        // define list of player's pieces
        ArrayList<Gamepiece> gamePieces = new ArrayList<>();

        if (players.get(0).getGamePieces().size() != 0) {
            ArrayList<Gamepiece> allPieces = selectedGame.getAllGamePieces();
            int start = curNumPlayers * (numPieces);
            for (int i = start; i < start + numPieces; i++) {
                gamePieces.add(allPieces.get(i));
            }
        }

        Player newPlayer = new Player();
        newPlayer.setLabel(playerName);
        newPlayer.setGamePieces((gamePieces));
        players.add(newPlayer);
    }

    private void addPlayerNode(Player player) {

        // outer box to encapsulate all the data corresponding to a single player
        HBox playerHBox = new HBox();
        playerHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(playerHBox, new Insets(0, 10, 50, 10));

        // store a reference to this hbox's player in the hbox
        playerHBox.setUserData(player);

        // color picker
        Color defaultColor;
        if (players.get(0).equals(player)) {
            defaultColor = player.getColor();
        } else {
            defaultColor = getRandomColor();
        }
        ColorPicker colorPicker = new ColorPicker(defaultColor);
        colorPicker.setStyle("-fx-background-color: " + getHexFromColor(defaultColor) + "; -fx-font-family: serif;" +
                " -fx-color-label-visible: false ; ");
        colorPicker.setOnAction(event -> colorPickerOnAction(event));

        // piece selection
        Label pieceSelectionButton = new Label("Select A Piece");
        pieceSelectionButton.setFont(new Font(24));
        pieceSelectionButton.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        pieceSelectionButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        pieceSelectionButton.setAlignment(Pos.CENTER);
        pieceSelectionButton.setPadding(new Insets(10, 20, 10, 20));
        pieceSelectionButton.setOnMouseClicked(event -> pieceSelectionButtonPressed());
        HBox.setMargin(pieceSelectionButton, new Insets(0, 0, 0, 20));

        colorPicker.prefHeightProperty().bind(pieceSelectionButton.heightProperty());

        // player name
        TextField playerName = new TextField();
        playerName.setFont(pieceSelectionButton.getFont());
        playerName.setText(player.getLabel());
        playerName.setAlignment(Pos.CENTER);
        HBox.setMargin(playerName, new Insets(0, 0, 0, 80));

        // player name change listener for duplicate name correction
        playerName.focusedProperty().addListener((observableValue, aBoolean, t1) -> {

            // if focus was lost
            if(!t1) {

                // get new text
                String newText = playerName.getText();
                System.out.println(newText);

                // get old text
                String oldText = player.getLabel();
                System.out.println(oldText);

                // if the new text is the same as another player's
                players.forEach( p -> {

                    // skip current player
                    if (p != player && newText.trim().equals(p.getLabel().trim())) {
                        // revert text
                        playerName.setText(oldText);

                        // show alert
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error!");
                        alert.setHeaderText("Duplicate Name Detected!");
                        alert.setContentText("Players cannot have the same names, please change it.");
                        alert.showAndWait();

                        // refocus textfield
                        playerName.requestFocus();
                    }
                });

                // otherwise, update player name
                player.setLabel(newText);
            }
        });


        // human / ai toggle switch
        Button humanAIToggleLabel = new Button();
        humanAIToggleLabel.setFont(pieceSelectionButton.getFont());
        humanAIToggleLabel.setStyle("-fx-background-color: " + GlobalCSSValues.buttonBackground);
        humanAIToggleLabel.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        humanAIToggleLabel.prefWidthProperty().bind(playerCountLabel.widthProperty());
        humanAIToggleLabel.setOnAction(event -> humanAITogglePressed(event));
        HBox.setMargin(humanAIToggleLabel, new Insets(0, 0, 0, 80));

        // set human ai value
        if (player.getIsHuman()) {
            humanAIToggleLabel.setText(HUMAN_AI_STRINGS[0]);
        } else {
            humanAIToggleLabel.setText(HUMAN_AI_STRINGS[1]);
        }

        // add all the components to the hbox
        playerHBox.getChildren().addAll(colorPicker, pieceSelectionButton, playerName, humanAIToggleLabel);

        // add player to player vbox
        playersVBox.getChildren().add(playerHBox);


//        // -- Color picker Code Starts --
//
//        // Add listener for Color Picker
//        colorPicker.setOnAction(
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
    }
    private void removePlayer() {

        int curNumPlayers = players.size();

        // return if addPlayer cannot be performed
        if (curNumPlayers == selectedGame.getMinPlayer()) { // todo read real min players value
            return;
        }

        // get player to remove
        int lastPlayerIndex = curNumPlayers - 1;

        // remove player
        players.remove(lastPlayerIndex);
    }
    private void removePlayerNode() {

        // return if we are at minimum num players
        if (playersVBox.getChildren().size() == selectedGame.getMinPlayer()) { // todo read real min value from gamestate
            return;
        }

        // remove last player node
        int lastPlayerNodeIndex = playersVBox.getChildren().size() - 1;
        playersVBox.getChildren().remove(lastPlayerNodeIndex);
    }
    private void dealWithPlayOrderOnLeaveSetup() {

        // if randomized is selected
        if ("Randomized".equals(playerOrderToggleLabel.getText())) {

            // randomize the order of the players
            Collections.shuffle(players);
        }
    }
    private Color getRandomColor() {

        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        return  Color.rgb(red, green, blue, .99);
    }
    public String getHexFromColor(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    // event handlers
    private void minusButtonPressed() {

        // return if min players has been reached
        if (players.size() == selectedGame.getMinPlayer()) { // todo read real min players value
            return;
        }

        // remove player from list
        removePlayer();

        // remove player node from vbox
        removePlayerNode();

        // update player count
        updatePlayerCountLabel();
    }
    private void plusButtonPressed() {

        // return if max players has been reached
        if (players.size() == selectedGame.getMaxPlayer()) { // todo read real max players value
            return;
        }

        // add player to list
        addPlayer();

        // add player node
        Player mostRecentlyAddedPlayer = players.get(players.size() - 1);
        addPlayerNode(mostRecentlyAddedPlayer);

        // update textfield
        updatePlayerCountLabel();
    }
    private void colorPickerOnAction(ActionEvent event) {

        System.out.println("Color Picker Action");

        // get source information
        ColorPicker colorPicker = (ColorPicker) event.getSource();
        HBox curPlayerHBox = (HBox) colorPicker.getParent();
        Player curPlayer = (Player) curPlayerHBox.getUserData();

        // store color that was chosen
        Color chosenColor = colorPicker.getValue();

        // scan players for the chosen color
        for (Player player : players) {

            // give error message and revert if color is already taken
            if (player.getColor().equals(chosenColor)) {

                System.out.println("Duplicate Detected");

                // show alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Duplicate Color Detected!");
                alert.setContentText("Players cannot have the same colors, please change it.");
                alert.showAndWait();

                // get player's original color
                Color prevColor = curPlayer.getColor();

                // revert color picker back to original color
                colorPicker.setValue(prevColor);

                // update colorpicker's color
                colorPicker.setStyle("-fx-background-color: " +
                        getHexFromColor(prevColor) + "; -fx-font-family: serif; -fx-color-label-visible: false;");

                // exit from function
                return;
            }
        }

        System.out.println("No Duplicate");

        // otherwise -- color is not a duplicate

        // update player's color
        curPlayer.setColor(chosenColor);

        // update colorpicker's color
        colorPicker.setStyle("-fx-background-color: " +
                getHexFromColor(chosenColor) + "; -fx-font-family: serif; -fx-color-label-visible: false;");

    }
    private void pieceSelectionButtonPressed() { // todo implement piece selection

        // open selection window; allow user to select from a list of options and hit ok closing the window
    }
    private void humanAITogglePressed(ActionEvent event) {

        // get the button that was pressed
        Button curButton = ((Button) event.getSource());

        // get the text on that button
        String curText = curButton.getText();

        // switch text
        if (curText.equals(HUMAN_AI_STRINGS[0])) {

            curButton.setText(HUMAN_AI_STRINGS[1]);
        } else {

            curButton.setText(HUMAN_AI_STRINGS[0]);
        }
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
            selectedGame.setTutorialEnabled(true);
        } else {
            selectedGame.setTutorialEnabled(false);
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

        // we can get the above information with the follow functions
        players.forEach(p -> {
            p.getGamePieces().forEach(gp -> {
                gp.setColor(p.getColor());
            });
        });
        //TODO: is this even necessary?
        selectedGame.setAllPlayers(players);

        // switch screens
        PlayController controller = new PlayController();
        controller.initialize(stage);
    }





    
    // stuff that I havent added to the clean write ------------------------------------------------------------------

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
//
//


}
