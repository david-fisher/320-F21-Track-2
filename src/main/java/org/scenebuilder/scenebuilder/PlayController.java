package org.scenebuilder.scenebuilder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.engine.GameState;
import org.objects.*;
import org.objects.Spinner;
import org.scenebuilder.scenebuilder.dummy.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PlayController extends ScreenController {

    @FXML
    private Label playGameLabel;
    @FXML
    private Label playSetupLabel;
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

    Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
    double playWidth = screenDimensions.getWidth();
    double playHeight = screenDimensions.getHeight();

    @FXML
    public void initialize(Stage stage) {

        super.initialize(stage);
        this.stage = stage;

        // load relevant data
        setupData = BasicApplication.getSetupData();
        activeGame = BasicApplication.getSelectedGame();

        initializePlayScreen();
        initGame(activeGame);

        Scene newScene = new Scene(playParent);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
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

    //        boardPane.setMaxHeight(playHeight - 188);
    //        boardPane.setMaxWidth(playWidth - 160);

        playParent.getChildren().add(boardPane);

        playParent.setLeftAnchor(boardPane, (playWidth - boardWidth - 140)/2);
        playParent.setTopAnchor(boardPane, (playHeight - boardHeight - 20)/2);
        playParent.setBottomAnchor(boardPane, (playHeight - boardHeight - 20)/2);

        initBoard(gameBoard, boardPane);
        initTiles(gameBoard.getTiles(), boardPane, gameBoard);

        ArrayList<Deck> decks = gameState.getAllDecks();
        ArrayList<Die> dice = gameState.getAllDice();
        ArrayList<Spinner> spinners = gameState.getAllSpinners();
        ArrayList<Player> players = gameState.getAllPlayers();

        int numDrawers = 0;
        boolean decksNeeded = Objects.nonNull(decks);
        boolean diceNeeded = Objects.nonNull(dice);
        boolean spinnersNeeded = Objects.nonNull(spinners);
        boolean inventoryNeeded = true; //no indicator yet

        numDrawers = decksNeeded ? numDrawers + 1 : numDrawers;
        numDrawers = diceNeeded || spinnersNeeded ? numDrawers + 1 : numDrawers;
        numDrawers = inventoryNeeded ? numDrawers + 1 : numDrawers;

        if (Objects.nonNull(decks)) {
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
        });
        // for each tile
        // create tile
        // set tile values
        // add tile to anchorPane
    }

    private void initPlayers(ArrayList<Player> players) {

        players.forEach(p -> {
            initGamePiece(p.getGameTokens().get(0)); // todo, get specific game piece by reference
            //fill inventory
        });

        // for each player
        // set player info
        // add player stuff to inventory (later)
    }

    private void initGamePiece(Token gamePiece) {
        // for each player
        // for each piece
        // get piece
        // draw piece at its location
        // set other info..?
    }

    private void initInventory(DummyInventory inventory) {
        fillInventoryDrawer(inventory);
    }

    private void initSettingsAndPlayerIndicatorHBox() {
        sptiHBox = new HBox();
        sptiHBox.setStyle("-fx-background-color: transparent; -fx-border-color: BLACK; ");
        playParent.getChildren().addAll(sptiHBox);
        playParent.setRightAnchor(sptiHBox, 0.0);
        playParent.setTopAnchor(sptiHBox, 0.0);
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
        playParent.setTopAnchor(settingsPane, 15.0);
//        sptiHBox.getChildren().addAll(settingsPane);
//        sptiHBox.setMargin(settingsPane, new Insets(25, 10, 10, 20));
    }

    private void initPlayerTurnIndicator() {
        playerTurnIndicator = new Label();
        playerTurnIndicator.setText("Player 1's Turn");
        playerTurnIndicator.setStyle("-fx-font-family: Serif; -fx-font-size: 14; -fx-border-color: #000000;");
        playerTurnIndicator.setId("playerTurnIndicator");
        playerTurnIndicator.setWrapText(true);
        playerTurnIndicator.setTextAlignment(TextAlignment.CENTER);
        playerTurnIndicator.setAlignment(Pos.CENTER);

        playerTurnIndicator.setPrefWidth(70);
        playerTurnIndicator.setMinWidth(playerTurnIndicator.getPrefWidth());
        playerTurnIndicator.setMaxWidth(playerTurnIndicator.getPrefWidth());

        playerTurnIndicator.setPrefHeight(70);
        playerTurnIndicator.setMinHeight(playerTurnIndicator.getPrefHeight());
        playerTurnIndicator.setMaxHeight(playerTurnIndicator.getPrefHeight());

        playParent.getChildren().addAll(playerTurnIndicator);
        playParent.setRightAnchor(playerTurnIndicator, 70.0);
        playParent.setTopAnchor(playerTurnIndicator, 2.0);

//        sptiHBox.getChildren().addAll(playerTurnIndicator);
//        sptiHBox.setMargin(playerTurnIndicator, new Insets(10, 0, 10, 0));
    }

    private void initDeckLabel(int numDrawers) {
        decksLabel = new Label();
        decksLabel.setText("Decks");
        decksLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 24; -fx-background-color: DarkSeaGreen; -fx-border-color: BLACK;");
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

        playParent.setRightAnchor(decksLabel, 0.0);
        playParent.setTopAnchor(decksLabel, (playHeight / 5) + 175 - 50 * Math.log(Math.pow(10, numDrawers - 1)));
        //tabsVBox.setMargin(decksLabel, new Insets(2, 0, 10, 0));
    }

    private void initRNGLabel(int numDrawers) {
        rngLabel = new Label();
        rngLabel.setText("RNG");
        rngLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 24; -fx-background-color: DarkSeaGreen; -fx-border-color: BLACK;");
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

        playParent.setRightAnchor(rngLabel, 0.0);
        playParent.setTopAnchor(rngLabel, (playHeight / 5) + 175 - 20 * Math.log(Math.pow(10, (numDrawers - 1))) * Math.log(Math.pow(10, (3 - numDrawers))));

        //tabsVBox.getChildren().addAll(rngLabel);
        //tabsVBox.setMargin(rngLabel, new Insets(2, 0, 10, 0));
    }

    private void initInventoryLabel(int numDrawers) {
        inventoryLabel = new Label();
        inventoryLabel.setText("Inventory");
        inventoryLabel.setStyle("-fx-font-family: Serif; -fx-font-size: 24; -fx-background-color: DarkSeaGreen; -fx-border-color: BLACK;");
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
        //parent.toFront();
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

            Label yes = new Label("Yes");
            yes.setStyle("-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
            yes.setAlignment(Pos.CENTER);
            yes.setPrefWidth(70);

            Label no = new Label("No");
            no.setStyle("-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
            no.setAlignment(Pos.CENTER);
            no.setPrefWidth(70);

            //Change these actions to actually handle restarting
            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
            });
            no.setOnMouseClicked(e->popupWindow.close());

            buttons.getChildren().addAll(yes, no);
            buttons.setMargin(yes, new Insets(0, 0, 10, 0));
            buttons.setMargin(no, new Insets(0, 0, 10, 0));

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

            Label exitMessage = new Label("Are you sure you want to exit? Your progress will be lost.");
            exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            exitMessage.setWrapText(true);
            exitMessage.setAlignment(Pos.CENTER);
            exitMessage.setPrefWidth(250);

            borderPane.setAlignment(exitMessage, Pos.CENTER);
            borderPane.setCenter(exitMessage);

            HBox buttons = new HBox(10);

            Label yes = new Label("Yes");
            yes.setStyle("-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
            yes.setAlignment(Pos.CENTER);
            yes.setPrefWidth(70);

            Label no = new Label("No");
            no.setStyle("-fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
            no.setAlignment(Pos.CENTER);
            no.setPrefWidth(70);

            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
                exitFromPlay();
            });
            no.setOnMouseClicked(e->popupWindow.close());

            buttons.getChildren().addAll(yes, no);
            buttons.setMargin(yes, new Insets(0, 0, 10, 0));
            buttons.setMargin(no, new Insets(0, 0, 10, 0));

            buttons.setAlignment(Pos.CENTER);
            borderPane.setAlignment(buttons, Pos.BOTTOM_CENTER);
            borderPane.setBottom(buttons);
            Scene exitScene = new Scene(borderPane, 300, 250);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
        public void displayExit(Stage baseStage) {
            Stage popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);

            Label saveButton = new Label("Save");
            saveButton.setStyle("-fx-font-family: Serif; -fx-font-size: 40; -fx-background-color: Green; -fx-border-color: BLACK;");
            saveButton.setTextFill(Color.BLACK);
            saveButton.setAlignment(Pos.CENTER);
            saveButton.setPrefWidth(170);
            saveButton.setPrefHeight(80);

            Label exitButton = new Label("Exit");
            exitButton.setStyle("-fx-font-family: Serif; -fx-font-size: 40; -fx-background-color: Red; -fx-border-color: BLACK;");
            exitButton.setTextFill(Color.BLACK);
            exitButton.setAlignment(Pos.CENTER);
            exitButton.setPrefWidth(170);
            exitButton.setPrefHeight(80);

            Label restartButton = new Label("Restart");
            restartButton.setStyle("-fx-font-family: Serif; -fx-font-size: 40; -fx-background-color: Gray; -fx-border-color: BLACK;");
            restartButton.setTextFill(Color.BLACK);
            restartButton.setAlignment(Pos.CENTER);
            restartButton.setPrefWidth(170);
            restartButton.setPrefHeight(80);

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

            VBox layout = new VBox(10);
            layout.getChildren().addAll(saveButton);
            layout.getChildren().addAll(exitButton);
            layout.getChildren().addAll(restartButton);
            layout.setAlignment(Pos.CENTER);
            Scene exitScene = new Scene(layout, 300, 400);
            exitScene.setFill(Color.MAROON);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
    }

    @FXML
    public void displayPopup(MouseEvent event) {
        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Popup popup = new Popup();
        popup.displayExit(curStage);
    }
}

