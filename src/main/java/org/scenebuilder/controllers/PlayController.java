package org.scenebuilder.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.scenebuilder.BasicApplication;
import org.scenebuilder.dummy.DummyGame;
import org.scenebuilder.dummy.DummyGameBoard;
import org.scenebuilder.dummy.DummyInventory;
import org.RuleEngine.engine.*;
import org.GameObjects.objects.*;
import org.GameObjects.objects.Spinner;
import org.scenebuilder.SetupData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class PlayController extends ScreenController {

    @FXML
    private ImageView playSettings;
    @FXML
    private AnchorPane playParent;
    @FXML
    private ScrollPane decksPane;
    @FXML
    private ScrollPane rngPane;
    @FXML
    private ScrollPane inventoryPane;

    @FXML
    private Label playerTurnIndicator;
    @FXML
    private Label decksLabel;
    @FXML
    private Label rngLabel;
    @FXML
    private Label inventoryLabel;
    @FXML
    private Pane settingsPane;
    @FXML
    private HBox inventoryContainer;

    private Stage stage;
    private SetupData setupData;
    private DummyGame activeGame;
    private ArrayList<Player> players;
    private Player curPlayer;

    double playWidth;
    double playHeight;

    @FXML
    public void initialize(Stage stage) {

        super.initialize(stage);
        this.stage = stage;
        playWidth = stage.getWidth();
        playHeight = stage.getHeight();

        // load relevant data
        setupData = BasicApplication.getSetupData();
        if (setupData.playerList.size() == 0) {
            ArrayList<Player> dummyPlayers = new ArrayList<Player>();
            dummyPlayers.add(new Player("Player 1", Color.RED, new ArrayList<Gamepiece>(), new DummyInventory("1", new ArrayList<GameObject>()), true));
            dummyPlayers.add(new Player("Player 2", Color.BLUE, new ArrayList<Gamepiece>(), new DummyInventory("2", new ArrayList<GameObject>()), true));
            dummyPlayers.add(new Player("Player 3", Color.GREEN, new ArrayList<Gamepiece>(), new DummyInventory("3", new ArrayList<GameObject>()), true));
            setupData = new SetupData(dummyPlayers, false);
        }
        activeGame = BasicApplication.getSelectedGame();
        players = setupData.playerList;
        curPlayer = players.get(0);

        initializePlayScreen();
        initGame(activeGame);

        Scene newScene = new Scene(playParent);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
    }

    private void playerTurnCycle() {
        Label switchTurn = new Label();
        switchTurn.setText("End Turn");
        setStyle(switchTurn, "14", "Red", 100, 50);

        switchTurn.setOnMouseClicked(e -> {
            int nextPlayerIndex = players.indexOf(curPlayer);
            Player nextPlayer = nextPlayerIndex == players.size()-1 ? players.get(0) : players.get(nextPlayerIndex + 1);
            playerTurnIndicator.setText(nextPlayer.getPlayerID() + "'s Turn");
            playerTurnIndicator.setStyle("-fx-border-radius: 5 5 5 5; " +
                    "-fx-background-radius: 5 5 5 5; " +
                    "-fx-font-family: Serif; " +
                    "-fx-font-size: 16; " +
                    "-fx-border-color: #000000;" +
                    "-fx-background-color:" + toHexString(nextPlayer.getColor()) + ";");
            // set current player
            fillInventoryDrawer(nextPlayer.getInventory());
            curPlayer = nextPlayer;
            playerTurnCycle();
        });
        playParent.getChildren().addAll(switchTurn);
        playParent.setLeftAnchor(switchTurn, 5.0);
        playParent.setTopAnchor(switchTurn, 5.0);
        switchTurn.setAlignment(Pos.CENTER);
    }
    private void initGame(DummyGame game) {
        DummyGameBoard gameBoard = game.getGameBoard();
        GameState gameState = game.getInitialGamestate();

        AnchorPane boardPane = new AnchorPane();

        double scaleWidth = (playWidth - 120) > gameBoard.getWidth() ? 1 : (playWidth - 120) / gameBoard.getWidth();
        double scaleHeight = (playHeight) > gameBoard.getHeight() ? 1 : playHeight / gameBoard.getHeight();
        double scale = scaleHeight >= scaleWidth ? scaleWidth : scaleHeight;

        double boardWidth = scale * gameBoard.getWidth();
        double boardHeight = scale * gameBoard.getHeight();
        //Set the boardPane's height and width so that it will not overlap with other elements on smaller screens
        boardPane.setPrefWidth(boardWidth);
        boardPane.setPrefHeight(boardHeight);

        playParent.getChildren().add(boardPane);

        playParent.setLeftAnchor(boardPane, (playWidth - boardWidth - 140)/2);
        playParent.setTopAnchor(boardPane, (playHeight - boardHeight - 20)/2);
        playParent.setBottomAnchor(boardPane, (playHeight - boardHeight - 20)/2);

        initBoard(gameBoard, boardPane);
        initTiles(gameBoard.getTiles(), boardPane, gameBoard);

        ArrayList<Deck> decks = gameState.getAllDecks();
        ArrayList<Die> dice = gameState.getAllDice();
        ArrayList<Spinner> spinners = gameState.getAllSpinners();
        //players = gameState.getAllPlayers();

        int numDrawers = 0;
        boolean decksNeeded = decks.size() != 0;
        boolean diceNeeded = dice.size() != 0;
        boolean spinnersNeeded = spinners.size() != 0;
        boolean inventoryNeeded = true; //no indicator yet

        numDrawers = decksNeeded ? numDrawers + 1 : numDrawers;
        numDrawers = diceNeeded || spinnersNeeded ? numDrawers + 1 : numDrawers;
        numDrawers = inventoryNeeded ? numDrawers + 1 : numDrawers;

        if (decksNeeded) {
            initializeDeckDrawer(numDrawers);
            initDeckLabel(numDrawers);
            fillDeckDrawer(decks, decksPane);
        }

        HBox container = new HBox();
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);

        if (diceNeeded || spinnersNeeded) {
            initializeRNGDrawer(numDrawers);
            initRNGLabel(numDrawers);
        }
        if (diceNeeded) {
            placeDice(dice, rngPane, container);
        }
        if (spinnersNeeded) {
            placeSpinners(spinners, rngPane, container);
        }

        if (Objects.nonNull(players)) {
            initPlayers(gameState.getAllPlayers());
            curPlayer = players.get(0);
            playerTurnCycle();
        }

        //Have some indicator for whether inventory is needed
        if (inventoryNeeded) {
            initializeInventoryDrawer(numDrawers);
            initInventoryLabel(numDrawers);
            initInventory(new DummyInventory("1", new ArrayList<GameObject>()));
        }

    }

    private void initBoard(DummyGameBoard gameBoard, AnchorPane boardPane) {
        Shape board;
        double width = boardPane.getPrefWidth();
        double height = boardPane.getPrefHeight();

        if (gameBoard.getShape().equals("Rectangle")) {
            board = new Rectangle(width, height);
        } else {
            board = new Circle(height / 2);
        }
        board.setFill(Color.AQUAMARINE);
        boardPane.getChildren().add(board);

        boardPane.setLeftAnchor(board, 0.0);
        boardPane.setTopAnchor(board, 0.0);
        boardPane.setRightAnchor(board, 0.0);
        boardPane.setBottomAnchor(board, 0.0);

        // create board anchorPane
        // set anchorPane values
    }

    private void initTiles(ArrayList<Tile> tiles, AnchorPane boardPane, DummyGameBoard gameBoard) {
        double scale = boardPane.getPrefWidth() / gameBoard.getWidth();
        tiles.forEach(t -> {
            Shape tile;
            double width = t.getWidth() * scale;
            double height = t.getHeight() * scale;

            if (t.getShape().equals("Rectangle")) {
                tile = new Rectangle(width, height);
            } else {
                tile = new Circle(width / 2);
            }
            tile.setUserData(t);
            tile.setFill(t.getColor());
            boardPane.getChildren().addAll(tile);
            tile.setLayoutX(t.getXPos());
            tile.setLayoutY(t.getYPos());
            t.setParent(tile);
        });
        // for each tile
        // create tile
        // set tile values
        // add tile to anchorPane
    }

    private void initPlayers(ArrayList<Player> players) {

        players.forEach(p -> {
            initGamePiece(p.getGamePieces().get(0)); // todo, get specific game piece by reference
            //fill inventory
        });

        // for each player
        // set player info
        // add player stuff to inventory (later)
    }

    private void initGamePiece(Gamepiece gamePiece) {
        // for each player
        // for each piece
        // get piece
        // draw piece at its location
        // set other info..?
    }

    private void initInventory(DummyInventory inventory) {
        fillInventoryDrawer(inventory);
    }

    public void initSettings() {
        playSettings = new ImageView();
        settingsPane = new Pane();
        playSettings.setImage(new Image("https://images-ext-1.discordapp.net/external/C1VkLgkVceGoEsJogTZ4Nfjo4W-cnZ2GF6FR-XFnIzk/https/cdn-icons-png.flaticon.com/512/61/61094.png?width=375&height=375",
                40, 40, true, true));
        settingsPane.getChildren().addAll(playSettings);
        settingsPane.setPrefWidth(40);
        settingsPane.setPrefHeight(40);

        settingsPane.setOnMouseClicked(e -> {
            displayPopup(e);
        });

        playParent.getChildren().addAll(settingsPane);
        playParent.setRightAnchor(settingsPane, 15.0);
        playParent.setTopAnchor(settingsPane, 25.0);
//        sptiHBox.getChildren().addAll(settingsPane);
//        sptiHBox.setMargin(settingsPane, new Insets(25, 10, 10, 20));
    }

    private void initPlayerTurnIndicator() {
        playerTurnIndicator = new Label();
        playerTurnIndicator.setText(curPlayer.getPlayerID() + "'s Turn");

        playerTurnIndicator.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: 16; " +
                "-fx-font-color: BLACK; " +
                "-fx-border-color: #000000; " +
                "-fx-background-color:" + toHexString(curPlayer.getColor()) + ";");
        playerTurnIndicator.setId("playerTurnIndicator");
        playerTurnIndicator.setWrapText(true);
        playerTurnIndicator.setTextAlignment(TextAlignment.CENTER);
        playerTurnIndicator.setAlignment(Pos.CENTER);
        playerTurnIndicator.setPadding(new Insets(2, 2, 2, 2));

        playerTurnIndicator.setPrefWidth(70);

        playerTurnIndicator.setPrefHeight(70);

        playParent.getChildren().addAll(playerTurnIndicator);
        playParent.setRightAnchor(playerTurnIndicator, 70.0);
        playParent.setTopAnchor(playerTurnIndicator, 10.0);
    }

    private void initDeckLabel(int numDrawers) {
        decksLabel = new Label();
        decksLabel.setText("Decks");
        decksLabel.setStyle("-fx-font-family: Serif; " +
                "-fx-font-size: 24; " +
                "-fx-background-color: DarkOliveGreen; " +
                "-fx-border-color: BLACK;");
        decksLabel.setTextFill(Color.WHITE);
        decksLabel.setId("decksLabel");
        decksLabel.setTextAlignment(TextAlignment.CENTER);
        decksLabel.setAlignment(Pos.CENTER);

        decksLabel.setPrefWidth(140);
        decksLabel.setMinWidth(decksLabel.getPrefWidth());
        decksLabel.setMaxWidth(decksLabel.getPrefWidth());

        decksLabel.setPrefHeight(209);
        decksLabel.setMinHeight(decksLabel.getPrefHeight());
        decksLabel.setMaxHeight(decksLabel.getPrefHeight());

        decksLabel.setOnMouseClicked(e -> {
            slideOut(e);
        });

        playParent.getChildren().addAll(decksLabel);
        initDarken(decksLabel);

        playParent.setRightAnchor(decksLabel, 0.0);
        playParent.setTopAnchor(decksLabel, (playHeight / 5) + 175 - 50 * Math.log(Math.pow(10, numDrawers - 1)));
        //tabsVBox.setMargin(decksLabel, new Insets(2, 0, 10, 0));
    }

    private void initRNGLabel(int numDrawers) {
        rngLabel = new Label();
        rngLabel.setText("RNG");
        rngLabel.setStyle("-fx-font-family: Serif; " +
                "-fx-font-size: 24; " +
                "-fx-background-color: DarkSlateGrey; " +
                "-fx-border-color: BLACK;");
        rngLabel.setTextFill(Color.WHITE);
        rngLabel.setId("rngLabel");
        rngLabel.setTextAlignment(TextAlignment.CENTER);
        rngLabel.setAlignment(Pos.CENTER);

        rngLabel.setPrefWidth(140);
        rngLabel.setMinWidth(rngLabel.getPrefWidth());
        rngLabel.setMaxWidth(rngLabel.getPrefWidth());

        rngLabel.setPrefHeight(209);
        rngLabel.setMinHeight(rngLabel.getPrefHeight());
        rngLabel.setMaxHeight(rngLabel.getPrefHeight());

        rngLabel.setOnMouseClicked(e -> {
            slideOut(e);
        });

        playParent.getChildren().add(rngLabel);
        initDarken(rngLabel);

        playParent.setRightAnchor(rngLabel, 0.0);
        playParent.setTopAnchor(rngLabel, (playHeight / 5) + 175 - 20 * Math.log(Math.pow(10, (numDrawers - 1))) * Math.log(Math.pow(10, (3 - numDrawers))));

        //tabsVBox.getChildren().addAll(rngLabel);
        //tabsVBox.setMargin(rngLabel, new Insets(2, 0, 10, 0));
    }

    private void initInventoryLabel(int numDrawers) {
        inventoryLabel = new Label();
        inventoryLabel.setText("Inventory");
        inventoryLabel.setStyle("-fx-font-family: Serif; " +
                "-fx-font-size: 24; " +
                "-fx-background-color: DarkGoldenRod; " +
                "-fx-border-color: BLACK;");
        inventoryLabel.setTextFill(Color.WHITE);
        inventoryLabel.setId("inventoryLabel");
        inventoryLabel.setTextAlignment(TextAlignment.CENTER);
        inventoryLabel.setAlignment(Pos.CENTER);

        inventoryLabel.setPrefWidth(140);
        inventoryLabel.setMinWidth(inventoryLabel.getPrefWidth());
        inventoryLabel.setMaxWidth(inventoryLabel.getPrefWidth());

        inventoryLabel.setPrefHeight(209);
        inventoryLabel.setMinHeight(inventoryLabel.getPrefHeight());
        inventoryLabel.setMaxHeight(inventoryLabel.getPrefHeight());

        inventoryLabel.setOnMouseClicked(e -> {
            slideOut(e);
        });
        initDarken(inventoryLabel);

        playParent.getChildren().addAll(inventoryLabel);

        playParent.setRightAnchor(inventoryLabel, 0.0);
        playParent.setTopAnchor(inventoryLabel, (playHeight / 5) + 175 + 50 * Math.log(Math.pow(10, numDrawers - 1)));
        //tabsVBox.getChildren().addAll(inventoryLabel);
        //tabsVBox.setMargin(inventoryLabel, new Insets(2, 0, 10, 0));
    }

    private void initializeDeckDrawer(int numDrawers) {
        decksPane = new ScrollPane();
        decksPane.setStyle("-fx-background-color: GREY;");
        decksPane.setId("decksDrawer");
        decksPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        decksPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        decksPane.setPrefWidth(355);
        decksPane.setPrefHeight(209);
        playParent.setRightAnchor(decksPane, -215.0);
        playParent.setTopAnchor(decksPane, (playHeight / 5) + 175 - 50 * Math.log(Math.pow(10, numDrawers - 1)));
        playParent.getChildren().addAll(decksPane);
    }

    private void initializeRNGDrawer(int numDrawers) {
        rngPane = new ScrollPane();
        rngPane.setStyle("-fx-background-color: GREY;");
        rngPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rngPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        playParent.setRightAnchor(rngPane, -215.0);
        playParent.setTopAnchor(rngPane, (playHeight / 5) + 175 - 20 * Math.log(Math.pow(10, (numDrawers - 1))) * Math.log(Math.pow(10, (3 - numDrawers))));
        rngPane.setPrefWidth(355);
        rngPane.setPrefHeight(209);
        playParent.getChildren().add(rngPane);
    }

    private void initializeInventoryDrawer(int numDrawers) {
        inventoryPane = new ScrollPane();
        inventoryPane.setStyle("-fx-background-color: GREY;");
        inventoryPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        inventoryPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        playParent.setRightAnchor(inventoryPane, -215.0);
        playParent.setTopAnchor(inventoryPane, (playHeight / 5) + 175 + 50 * Math.log(Math.pow(10, numDrawers - 1)));
        inventoryPane.setPrefWidth(355);
        inventoryPane.setPrefHeight(209);
        playParent.getChildren().add(inventoryPane);
    }

    //A method to add all the decks to the deck slider
    private void fillDeckDrawer(ArrayList<Deck> decks, ScrollPane decksPane) {

        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(-10);
        decks.forEach(d -> {
            double width = d.getWidth() == 0 ? 100 : d.getWidth();
            double height = d.getHeight() == 0 ? 170 : d.getHeight();
            Rectangle deck = new Rectangle(width, height);
            deck.setUserData(d);

            if(d.getIcon() != null) {
                deck.setFill(new ImagePattern(new Image(d.getIcon())));
            } else {
                deck.setFill(Color.RED);
            }
            deck.setOnMouseClicked(e -> {
                //Open this deck if you can // todo
                Card card = d.drawTop();
                ImageView cardImage = new ImageView(card.getIcon());

                cardImage.setFitWidth(200);
                cardImage.setFitHeight(340);
                playParent.getChildren().add(cardImage);
                cardImage.setX(playWidth / 2 - 100);
                cardImage.setY(playHeight / 2 - 170);

                final Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                        k -> playParent.getChildren().remove(cardImage)));
                timeline.play();

                //Only in some cases but added for now
                //inventoryPane.getChildren().add(card);
                addToInventory(card);
            });
            container.getChildren().addAll(deck);
            container.setMargin(deck, new Insets(10, 10, 20, 10));
        });
        decksPane.setContent(container);
        decksPane.setStyle("-fx-border-color: black");

        //decksPane.toFront();
        //tabsVBox.getChildren().addAll(decksPane);
    }

    public void placeDice(ArrayList<Die> dice, ScrollPane rngPane, HBox container) {

        rngPane.setContent(container);
        AnchorPane diceView = new AnchorPane();
        AnchorPane diceDisplay = new AnchorPane();
        diceDisplay.setPrefSize(180, 180);
        container.getChildren().add(diceView);
        container.setMargin(diceView, new Insets(10, 0, 20, 20));
        double rowMax = Math.ceil(Math.sqrt(dice.size()));
        double diceSize = 180 / rowMax;
        double currX = 0.0;
        double currY = 0.0;
        int diceCount = 0;

        for (int i = 0; i < dice.size(); i++) {
            ImageView die1 = new ImageView(new Image(dice.get(i).getIcon(), diceSize, diceSize, true, true));
            ImageView die2 = new ImageView(new Image(dice.get(i).getIcon(), diceSize, diceSize, true, true));
            die1.setUserData(dice.get(i));

            die2.setUserData(dice.get(i));
            die2.setFitWidth(diceSize);
            die2.setFitHeight(diceSize);

            diceView.getChildren().add(die1);
            diceDisplay.getChildren().add(die2);
            die1.setX(currX);
            die1.setY(currY);
            die2.setX(currX);
            die2.setY(currY);
            currX = (currX + diceSize) % 180;

            diceCount++;
            if (diceCount % rowMax == 0) {
                currY += diceSize;
            }
        }

        diceView.setOnMouseClicked(e -> {
            rollDice(e, dice, diceDisplay);
        });
//        container.getChildren().add(diceView);
    }

    public void placeSpinners(ArrayList<Spinner> spinners, ScrollPane rngPane, HBox container) {
        spinners.forEach(d -> {
            double width = d.getWidth() == 0 ? 170 : d.getWidth();
            double height = d.getHeight() == 0 ? 170 : d.getHeight();
            ImageView spinner = new ImageView(new Image(d.getIcon(), width, width, true, true));
            spinner.setUserData(d);
            //spinner.setFill(new ImagePattern(new Image(d.getIcon())));
            spinner.setOnMouseClicked(e -> {
                // spin this spinner if you can
            });
            container.getChildren().addAll(spinner);
            container.setMargin(spinner, new Insets(10, 10, 20, 10));
        });
        //rngPane.setContent(container);
        rngPane.setStyle("-fx-border-color: BLACK;");
    }

    private void rollDice(MouseEvent e, ArrayList<Die> dice, AnchorPane diceDisplay) {

        for (Node d: diceDisplay.getChildren()) {
            ImageView dieImage = (ImageView) d;
            Die die = (Die) dieImage.getUserData();
            int roll = die.roll();
            System.out.println((InputStream) getClass().getResourceAsStream("/Dice1.png"));
            switch (roll) {
                case 1:
                    dieImage.setImage(new Image(getClass().getResource("Dice1.png").toString(), die.getWidth(), die.getHeight(), true, true));
                    break;
                case 2:
                    dieImage.setImage(new Image(getClass().getResource("Dice2.png").toString(), die.getWidth(), die.getHeight(), true, true));
                    break;
                case 3:
                    dieImage.setImage(new Image(getClass().getResource("Dice3.png").toString(), die.getWidth(), die.getHeight(), true, true));
                    break;
                case 4:
                    dieImage.setImage(new Image(getClass().getResource("Dice4.png").toString(), die.getWidth(), die.getHeight(), true, true));
                    break;
                case 5:
                    dieImage.setImage(new Image(getClass().getResource("Dice5.png").toString(), die.getWidth(), die.getHeight(), true, true));
                    break;
                case 6:
                    dieImage.setImage(new Image(getClass().getResource("Dice6.png").toString(), die.getWidth(), die.getHeight(), true, true));
                    break;
            }
        }

        playParent.getChildren().add(diceDisplay);
        diceDisplay.setLayoutX(playWidth / 2 - 100);
        diceDisplay.setLayoutY(playHeight / 2 - 90);

        final Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500),
                k -> playParent.getChildren().remove(diceDisplay)));
        timeline.play();

    }

    private void addToInventory(GameObject object) {
        double width = object.getWidth() == 0 ? 100 : object.getWidth();
        double height = object.getHeight() == 0 ? 170 : object.getHeight();

        Rectangle inventoryObject = new Rectangle(width, height);
        inventoryObject.setUserData(object);

        if(object.getIcon() != null) {
            inventoryObject.setFill(new ImagePattern(new Image(object.getIcon())));
        } else {
            inventoryObject.setFill(Color.RED);
        }
        inventoryObject.setOnMouseClicked(e -> {
            //Open this deck if you can // todo
        });
        curPlayer.getInventory().getInventory().add(object);
        inventoryContainer.getChildren().addAll(inventoryObject);
        inventoryContainer.setMargin(inventoryObject, new Insets(10, 10, 20, 10));
    }

    private void fillInventoryDrawer(DummyInventory inventory) {
        inventoryContainer = new HBox();
        inventoryContainer.setAlignment(Pos.CENTER);
        inventoryContainer.setSpacing(-10);
        inventory.getInventory().forEach(d -> {
            addToInventory(d);
        });
        inventoryPane.setContent(inventoryContainer);
        inventoryPane.setStyle("-fx-border-color: black");
        //decksPane.toFront();
    }

    public void initializePlayScreen() {
        playParent = new AnchorPane();
        playParent.setPrefWidth(playWidth);
        playParent.setPrefHeight(playHeight);

        //initSettingsAndPlayerIndicatorHBox();
        initPlayerTurnIndicator();
        initSettings();
    }
    @FXML
    public void exitFromPlay() {
//        MainController controller = new MainController();
//        controller.initialize(stage);
        Platform.exit();
    }

    public void mainMenuFromPlay() {
        MainController controller = new MainController();
        controller.initialize(stage);
    }

    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed()     * 255)) << 24;
        int g = ((int) Math.round(color.getGreen()   * 255)) << 16;
        int b = ((int) Math.round(color.getBlue()    * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
    }
    @FXML
    public void switchScene(ActionEvent event, String nextScene, Stage baseStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        if (baseStage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = baseStage;
        }
        Scene scene = new Scene(root, playWidth, playHeight);
        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }

    @FXML
    public void slideOut(MouseEvent event) {
        Label parent = (Label) event.getSource();
        ScrollPane tab;
        if (parent.getId().equals("decksLabel")) {
            tab = decksPane;
        } else if (parent.getId().equals("rngLabel")){
            tab = rngPane;
        } else {
            tab = inventoryPane;
        }

        tab.toFront();
        parent.toFront();
        TranslateTransition tt = new TranslateTransition(Duration.millis(700), tab);
        tt.setOnFinished(e -> {
            if (tt.getToX() == -355f) {
                tab.setLayoutX(1100.8);
            } else {
                tab.setTranslateX(0);
                tab.setLayoutX(1495.8);
            }
        });
        if (tab.getTranslateX() == 0.0) {
            tt.setToX(-355f);
        } else {
            tt.setToX(355f);
        }
        tt.play();
    }

    public class Popup {
        Label yes;
        Label no;
        public boolean saved = false;
//        public void setButtonSize(Button button, float prefWidth, float prefHeight, int fontSize) {
//            button.setPrefWidth(prefWidth);
//            button.setPrefHeight(prefHeight);
//
//            button.setMinWidth(button.getPrefWidth());
//            button.setMaxWidth(button.getPrefWidth());
//            button.setMinHeight(button.getPrefHeight());
//            button.setMaxHeight(button.getPrefHeight());
//
//            button.setStyle("-fx-font-size: "+fontSize+"; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4;");
//        }
        public void displayRestart(Stage baseStage, Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();

            Label restartMessage = new Label("Are you sure you want to restart? Your progress in the current game will be lost.");
            restartMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            restartMessage.setWrapText(true);
            restartMessage.setAlignment(Pos.CENTER);
            restartMessage.setPrefWidth(250);

            borderPane.setAlignment(restartMessage, Pos.CENTER);
            borderPane.setCenter(restartMessage);

            HBox buttons = new HBox(10);

            //Change these actions to actually handle restarting
            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
            });
            no.setOnMouseClicked(e -> {
                popupWindow.close();
            });

            buttons.getChildren().addAll(yes, no);
            buttons.setMargin(yes, new Insets(0, 5, 10, 0));
            buttons.setMargin(no, new Insets(0, 0, 10, 5));

            buttons.setAlignment(Pos.CENTER);
            borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
            borderPane.setBottom(buttons);
            Scene exitScene = new Scene(borderPane, 300, 250);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayExitWithoutSave(Stage baseStage, Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();

            Label exitMessage = new Label("Are you sure you want to exit to desktop? Your progress will be lost.");
            exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            exitMessage.setWrapText(true);
            exitMessage.setAlignment(Pos.CENTER);
            exitMessage.setPrefWidth(250);

            borderPane.setAlignment(exitMessage, Pos.CENTER);
            borderPane.setCenter(exitMessage);

            HBox buttons = new HBox(10);

            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
                exitFromPlay();
            });
            no.setOnMouseClicked(e -> {
                popupWindow.close();
            });

            buttons.getChildren().addAll(yes, no);
            buttons.setMargin(yes, new Insets(0, 5, 10, 0));
            buttons.setMargin(no, new Insets(0, 0, 10, 5));

            buttons.setAlignment(Pos.CENTER);
            borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
            borderPane.setBottom(buttons);
            Scene exitScene = new Scene(borderPane, 300, 250);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayMainMenuWithoutSave(Stage baseStage, Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();

            Label exitMessage = new Label("Are you sure you want to return to the Main Menu? Your progress will be lost.");
            exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            exitMessage.setWrapText(true);
            exitMessage.setAlignment(Pos.CENTER);
            exitMessage.setPrefWidth(250);

            borderPane.setAlignment(exitMessage, Pos.CENTER);
            borderPane.setCenter(exitMessage);

            HBox buttons = new HBox(10);

            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
                mainMenuFromPlay();
            });
            no.setOnMouseClicked(e -> {
                popupWindow.close();
            });

            buttons.getChildren().addAll(yes, no);
            buttons.setMargin(yes, new Insets(0, 5, 10, 0));
            buttons.setMargin(no, new Insets(0, 0, 10, 5));

            buttons.setAlignment(Pos.CENTER);
            borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
            borderPane.setBottom(buttons);
            Scene exitScene = new Scene(borderPane, 300, 250);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayExit(Stage baseStage) {
            Stage popupWindow = new Stage();

            yes = new Label("Yes");
            yes.setStyle("-fx-border-radius: 2 2 2 2; " +
                    "-fx-background-radius: 2 2 2 2; " +
                    "-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
            yes.setAlignment(Pos.CENTER);
            yes.setPrefWidth(70);

            no = new Label("No");
            no.setStyle(yes.getStyle());
            no.setAlignment(Pos.CENTER);
            no.setPrefWidth(yes.getPrefWidth());

            no.setOnMouseClicked(e->popupWindow.close());
            outlineYesNo(yes);
            outlineYesNo(no);

            popupWindow.initModality(Modality.APPLICATION_MODAL);

            Label saveButton = new Label("Save");
            setStyle(saveButton, "30", "LimeGreen", 170, 80);

            Label exitButton = new Label("Exit");
            setStyle(exitButton, "30", "Red", 170, 80);

            Label restartButton = new Label("Restart");
            setStyle(restartButton, "30", "LightGrey", 170, 80);

            Label mainMenuButton = new Label("Main Menu");
            setStyle(mainMenuButton, "30", "Blue", 170, 80);

            saveButton.setOnMouseClicked(e->{
                saved = true;
                System.out.println("Save");
            });

            exitButton.setOnMouseClicked(e->{
                if (!saved) {
                    displayExitWithoutSave(baseStage, popupWindow);
                } else {
                    popupWindow.close();
                    exitFromPlay();
                }
            });

            restartButton.setOnMouseClicked(e->{
                displayRestart(baseStage, popupWindow);
            });

            mainMenuButton.setOnMouseClicked(e -> {
                if (!saved) {
                    displayMainMenuWithoutSave(baseStage, popupWindow);
                } else {
                    popupWindow.close();
                    mainMenuFromPlay();
                }
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(saveButton);
            layout.getChildren().addAll(restartButton);
            layout.getChildren().addAll(mainMenuButton);
            layout.getChildren().addAll(exitButton);
            layout.setAlignment(Pos.CENTER);
            Scene exitScene = new Scene(layout, 350, 400);
            exitScene.setFill(Color.MAROON);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayMainMenu(Stage backStage) {

        }
    }

    @FXML
    public void displayPopup(MouseEvent event) {
        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Popup popup = new Popup();
        popup.displayExit(curStage);
    }

    public void initDarken(Label label) {
        label.setOnMouseEntered(e -> {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.2);
            label.setEffect(colorAdjust);
        });

        label.setOnMouseExited(e -> {
            label.setEffect(null);
        });
    }

    public void setStyle(Label label, String size, String color, double width, double height) {
        label.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: " + size + "; " +
                "-fx-background-color: " + color + "; " +
                "-fx-border-color: BLACK;");
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        initDarken(label);
    }

    public void outlineYesNo(Label label) {
        label.setOnMouseEntered(e -> {
            label.setStyle("-fx-border-radius: 5 5 5 5; " +
                    "-fx-background-radius: 5 5 5 5; " +
                    "-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: Blue;");
        });

        label.setOnMouseExited(e -> {
            label.styleProperty().setValue("-fx-border-radius: 5 5 5 5; " +
                    "-fx-background-radius: 5 5 5 5; " +
                    "-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: Black;");
        });
    }
}

