package org.GamePlay.controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import org.GameObjects.objects.Button;
import org.RuleEngine.nodes.LiteralNode;
import org.RuleEngine.nodes.MoveByNode;
import org.GamePlay.BasicApplication;
import org.GamePlay.Display;
import org.GamePlay.GlobalCSSValues;
import org.RuleEngine.engine.*;
import org.GameObjects.objects.*;
import org.GameObjects.objects.Spinner;
import org.GamePlay.SetupData;

import java.util.ArrayList;

public class PlayController extends ScreenController {

    private ImageView playSettings;
    private static AnchorPane playParent;
    private static GameState activeGame;
    private ScrollPane decksPane;
    private ScrollPane buttonPane;
    private ScrollPane inventoryPane;
    private Label playerTurnIndicator;
    private Label decksLabel;
    private Label buttonLabel;
    private Label inventoryLabel;
    private Pane settingsPane;
    private HBox inventoryContainer;
    private Stage stage;
    private SetupData setupData;

    private ArrayList<Player> players;
    private Player currPlayer;

    double playWidth;
    double playHeight;

    Interpreter interpreter = new Interpreter();

    public void initialize(Stage stage) {

        super.initialize(stage);
        this.stage = stage;
        playWidth = stage.getWidth();
        playHeight = stage.getHeight();

        // load relevant data
        setupData = BasicApplication.getSetupData();
        players = setupData.getPlayers();
        if (players.size() == 0) {
            players.add(new Player("Player 1", Color.RED, new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true));
            players.add(new Player("Player 2", Color.BLUE, new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true));
            players.add(new Player("Player 3", Color.GREEN, new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true));
            setupData = new SetupData(players, false);
        }
        activeGame = BasicApplication.getSelectedGame();

        initPlayScreen();

        initGame(activeGame);

        Scene newScene = new Scene(playParent);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
    }

    private void playerTurnCycle() {
//        Label switchTurn = new Label();
//        switchTurn.setText("End Turn");
//        setStyle(switchTurn, "14", "Red", 100, 50);
//
//        switchTurn.setOnMouseClicked(e -> {
//            int nextPlayerIndex = players.indexOf(currPlayer);
//            Player nextPlayer = nextPlayerIndex == players.size()-1 ? players.get(0) : players.get(nextPlayerIndex + 1);
//            playerTurnIndicator.setText(nextPlayer.getLabel() + "'s Turn");
//            playerTurnIndicator.setStyle("-fx-border-radius: 5 5 5 5; " +
//                    "-fx-background-radius: 5 5 5 5; " +
//                    "-fx-font-family: Serif; " +
//                    "-fx-font-size: 16; " +
//                    "-fx-border-color: #000000;" +
//                    "-fx-background-color:" + toHexString(nextPlayer.getColor()) + ";");
//            // set current player
//            fillInventory(nextPlayer.getInventory());
//            currPlayer = nextPlayer;
//            playerTurnCycle();
//        });
    }

    private static AnchorPane boardPane;
    private void initGame(GameState gameStateInput) {

        if (boardPane == null) {
            boardPane = new AnchorPane();
        }
        boardPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);
        GameBoard gameBoard = gameStateInput.getGameBoard();
        if (gameBoard.getBoardID().equals("All Drawers")) {
            players = activeGame.getAllPlayers();
        }
        currPlayer = players.get(0);
        gameStateInput.setAllPlayers(players);

        initPlayerTurnIndicator();

        // TODO: do we do this?
        if (gameBoard.getBoardID().equals("All Drawers")) {
//            gameStateInput.addRegistry("currPlayer", currPlayer.getGamePieces().get(0));
        }


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
        initPlayers(gameStateInput.getAllPlayers());

        ArrayList<Deck> decks = gameStateInput.getAllDecks();
        ArrayList<Button> buttons = gameStateInput.getAllButtons();

        //players = gameState.getAllPlayers();

        int numDrawers = 0;
        boolean decksNeeded = decks.size() != 0;
        boolean buttonsNeeded = buttons.size() != 0;
        //TODO
        boolean inventoryNeeded = true; //no indicator yet

        numDrawers = decksNeeded ? numDrawers + 1 : numDrawers;
        numDrawers = buttonsNeeded ? numDrawers + 1 : numDrawers;
        numDrawers = inventoryNeeded ? numDrawers + 1 : numDrawers;

        if (decksNeeded) {
            initDeckDrawer(numDrawers);
            initDeckLabel(numDrawers);
            fillDeckDrawer(decks, decksPane);
        }

        HBox container = new HBox();
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);

        if (buttonsNeeded) {
            initButtonDrawer(numDrawers);
            initButtonLabel(numDrawers);
            initButtons(gameStateInput);
        }

        //Have some indicator for whether inventory is needed
        if (inventoryNeeded) {
            initInventoryDrawer(numDrawers);
            initInventoryLabel(numDrawers);
            fillInventory(currPlayer.getInventory());
        }

    }

    private void initBoard(GameBoard gameBoard, AnchorPane boardPane) {
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

    private void initTiles(ArrayList<Tile> tiles, AnchorPane boardPane, GameBoard gameBoard) {
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
            initGamePiece(p.getGamePieces()); // todo, get specific game piece by reference
            //fill inventory
        });

        // for each player
        // set player info
        // TODO: add player stuff to inventory (later)
    }

    private void initButtons(GameState gameState) {
        ArrayList<Button> buttons = gameState.getAllButtons();
        System.out.println(buttons);
        HBox container = new HBox();
        container.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(-10);
        buttons.forEach(button -> {
            String event = button.getOnClick();
            Label label = new Label(button.getText());
            button.setParent(label);
            setStyle(label, "18", button.getColorString(), button.getText().length() * 12, button.getHeight());
            button.getParent().setOnMouseClicked(e -> {
                if (button.getEnabled()) {
                    interpreter.interpretEvent(gameState.events.get(event), gameState);
                }
            });
            container.getChildren().add(button.getParent());
            System.out.println("Added: " + button.getText());
            label.setCenterShape(true);
            container.setMargin(button.getParent(), new Insets(10, 10, 20, 10));
        });
        buttonPane.setContent(container);
        buttonPane.setStyle("-fx-border-color: black");
    }

    private void initGamePiece(ArrayList<Gamepiece> gamePieces) {

        gamePieces.forEach(gamePiece -> {
            drawPiece(gamePiece);
        });
        // for each player
        // for each piece
        // get piece
        // draw piece at its location
        // set other info..?
    }

    private void drawPiece(Gamepiece gamePiece) {
        Tile parent = activeGame.getAllTiles().get(0);
        gamePiece.setLocation(parent);
        Circle gp = new Circle(40, Color.WHITE);
        gp.setUserData(gamePiece);
        gamePiece.setParent(gp);
        boardPane.getChildren().add(gp);
        gp.setLayoutX(parent.getXPos() + parent.getWidth() / 2);
        gp.setLayoutY(parent.getYPos() + parent.getHeight() / 2);

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
    }

    private void initPlayerTurnIndicator() {
        playerTurnIndicator = new Label();
        playerTurnIndicator.setText(currPlayer.getLabel() + "'s Turn");

        playerTurnIndicator.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: 16; " +
                "-fx-font-color: BLACK; " +
                "-fx-border-color: #000000; " +
                "-fx-background-color:" + toHexString(currPlayer.getColor()) + ";");
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
        initLabel(decksLabel, "Decks", "decksLabel");
        playParent.setTopAnchor(decksLabel, (playHeight / 5) + 175 - 50 * Math.log(Math.pow(10, numDrawers - 1)));
        //tabsVBox.setMargin(decksLabel, new Insets(2, 0, 10, 0));
    }

    private void initButtonLabel(int numDrawers) {
        buttonLabel = new Label();
        initLabel(buttonLabel, "Buttons", "buttonLabel");
        playParent.setTopAnchor(buttonLabel, (playHeight / 5) + 175 - 20 * Math.log(Math.pow(10, (numDrawers - 1))) * Math.log(Math.pow(10, (3 - numDrawers))));
    }

    private void initInventoryLabel(int numDrawers) {
        inventoryLabel = new Label();
        initLabel(inventoryLabel, "Inventory", "inventoryLabel");
        playParent.setTopAnchor(inventoryLabel, (playHeight / 5) + 175 + 50 * Math.log(Math.pow(10, numDrawers - 1)));
    }

    private void initLabel(Label label, String text, String id) {
        label.setText(text);
        label.setStyle("-fx-font-family: Serif; " +
                "-fx-font-size: 24; " +
                "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                "; -fx-border-color: BLACK;");
        label.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        label.setId(id);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);

        label.setPrefWidth(140);
//        label.setMinWidth(inventoryLabel.getPrefWidth());
//        label.setMaxWidth(inventoryLabel.getPrefWidth());

        label.setPrefHeight(209);
//        label.setMinHeight(inventoryLabel.getPrefHeight());
//        label.setMaxHeight(inventoryLabel.getPrefHeight());

        label.setOnMouseClicked(e -> {
            slideOut(e);
        });
        initDarken(label);

        playParent.getChildren().addAll(label);
        playParent.setRightAnchor(label, 0.0);
    }

    private void initDeckDrawer(int numDrawers) {
        decksPane = new ScrollPane();
        initDrawer(decksPane);
        playParent.setTopAnchor(decksPane, (playHeight / 5) + 175 - 50 * Math.log(Math.pow(10, numDrawers - 1)));
    }

    private void initButtonDrawer(int numDrawers) {
        buttonPane = new ScrollPane();
        initDrawer(buttonPane);
        playParent.setTopAnchor(buttonPane, (playHeight / 5) + 175 - 20 * Math.log(Math.pow(10, (numDrawers - 1))) * Math.log(Math.pow(10, (3 - numDrawers))));
    }

    private void initInventoryDrawer(int numDrawers) {
        inventoryPane = new ScrollPane();
        initDrawer(inventoryPane);
        playParent.setTopAnchor(inventoryPane, (playHeight / 5) + 175 + 50 * Math.log(Math.pow(10, numDrawers - 1)));
    }

    private void initDrawer(ScrollPane pane) {
        pane.setFitToHeight(true);
        pane.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        playParent.setRightAnchor(pane, -215.0);
        pane.setPrefWidth(355);
        pane.setPrefHeight(209);
        playParent.getChildren().add(pane);
    }

    //A method to add all the decks to the deck slider
    private void fillDeckDrawer(ArrayList<Deck> decks, ScrollPane decksPane) {

        HBox container = new HBox();
        container.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(-10);
        decks.forEach(d -> {
            double width = d.getWidth() == 0 ? 100 : d.getWidth();
            double height = d.getHeight() == 0 ? 170 : d.getHeight();
            Rectangle deck = new Rectangle(width, height);
            deck.setUserData(d);

            if(d.getIcon() != null) {
//                deck.setFill(new ImagePattern(new Image(d.getIcon())));
            } else {
                deck.setFill(Color.RED);
            }
            //TODO: change this so it is integrated
            deck.setOnMouseClicked(e -> {
                Card card = d.drawTop();
//                ImageView cardImage = new ImageView(card.getIcon());
//
//                cardImage.setFitWidth(200);
//                cardImage.setFitHeight(340);
//                playParent.getChildren().add(cardImage);
//                cardImage.setX(playWidth / 2 - 100);
//                cardImage.setY(playHeight / 2 - 170);

//                final Timeline timeline = new Timeline();
//                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
//                        k -> playParent.getChildren().remove(cardImage)));
//                timeline.play();

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
        container.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);

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
//            ImageView die1 = new ImageView(new Image(dice.get(i).getIcon(), diceSize, diceSize, true, true));
//            ImageView die2 = new ImageView(new Image(dice.get(i).getIcon(), diceSize, diceSize, true, true));
//            die1.setUserData(dice.get(i));
//
//            die2.setUserData(dice.get(i));
//            die2.setFitWidth(diceSize);
//            die2.setFitHeight(diceSize);
//
//            diceView.getChildren().add(die1);
//            diceDisplay.getChildren().add(die2);
//            die1.setX(currX);
//            die1.setY(currY);
//            die2.setX(currX);
//            die2.setY(currY);
//            currX = (currX + diceSize) % 180;
//
//            diceCount++;
//            if (diceCount % rowMax == 0) {
//                currY += diceSize;
//            }
        }

        diceView.setOnMouseClicked(e -> {
            rollDice(e, dice, diceDisplay);
        });
//        container.getChildren().add(diceView);
    }

    public void placeSpinners(ArrayList<Spinner> spinners, ScrollPane rngPane, HBox container) {

        container.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
        spinners.forEach(d -> {
            double width = d.getWidth() == 0 ? 170 : d.getWidth();
            double height = d.getHeight() == 0 ? 170 : d.getHeight();
//            ImageView spinner = new ImageView(new Image(d.getIcon(), width, width, true, true));
//            spinner.setUserData(d);
//            //spinner.setFill(new ImagePattern(new Image(d.getIcon())));
//            spinner.setOnMouseClicked(e -> {
//                // spin this spinner if you can
//            });
//            container.getChildren().addAll(spinner);
//            container.setMargin(spinner, new Insets(10, 10, 20, 10));
        });
        //rngPane.setContent(container);
        rngPane.setStyle("-fx-border-color: BLACK;");
    }

    private void rollDice(MouseEvent e, ArrayList<Die> dice, AnchorPane diceDisplay) {
        AnchorPane source = (AnchorPane) e.getSource();
        source.setDisable(true);
        Die die = dice.get(0);
        LiteralNode<String> name = new LiteralNode<>("currPlayer");
        int roll = die.roll();
        LiteralNode<Integer> value = new LiteralNode<>(roll);

        Display.getDisplay().print("You rolled: " + roll);

        MoveByNode move = new MoveByNode();
        move.setOperand(name, 0).setOperand(value, 1);
        ArrayList<org.RuleEngine.nodes.Node> moveNodes = new ArrayList<>();
        moveNodes.add(move);
        interpreter.interpretEvent(moveNodes, activeGame);
        //TODO: standardize and make way more efficient
        Gamepiece gp = currPlayer.getGamePieces().get(0);
        Shape parent = (Shape) gp.getParent();
        Tile location = gp.getLocation();
        parent.setLayoutX(location.getXPos() + location.getWidth() / 2);
        parent.setLayoutY(location.getYPos() + location.getHeight() / 2);
        parent.toFront();
        source.setDisable(false);
    }

    private void addToInventory(GameObject object) {
        double width = object.getWidth() == 0 ? 100 : object.getWidth();
        double height = object.getHeight() == 0 ? 170 : object.getHeight();

        Rectangle inventoryObject = new Rectangle(width, height);
        inventoryObject.setUserData(object);

//        if(object.getIcon() != null) {
//            inventoryObject.setFill(new ImagePattern(new Image(object.getIcon())));
//        } else {
//            inventoryObject.setFill(Color.RED);
//        }
        inventoryObject.setOnMouseClicked(e -> {
            //Open this deck if you can // todo
        });
        currPlayer.getInventory().add(object);
        inventoryContainer.getChildren().addAll(inventoryObject);
        inventoryContainer.setMargin(inventoryObject, new Insets(10, 10, 20, 10));
    }

    private void fillInventory(ArrayList<GameObject> inventory) {
        inventoryContainer = new HBox();
        inventoryContainer.setAlignment(Pos.CENTER);
        inventoryContainer.setSpacing(-10);
        inventory.forEach(d -> {
            addToInventory(d);
        });
        inventoryPane.setContent(inventoryContainer);
        inventoryContainer.setStyle("-fx-border-color: black; -fx-background-color: " + GlobalCSSValues.secondary);
    }

    public void initPlayScreen() {
        if (playParent == null) {
            playParent = new AnchorPane();
        }
        playParent.setStyle("-fx-background-color: " + GlobalCSSValues.background);
        playParent.setPrefWidth(playWidth);
        playParent.setPrefHeight(playHeight);

        //initSettings;
        initSettings();
    }

    public void exitFromPlay() {
        Platform.exit();
    }

    public void clearPlayParent() { playParent = new AnchorPane();}

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

    public void slideOut(MouseEvent event) {
        Label parent = (Label) event.getSource();
        ScrollPane tab;
        if (parent.getId().equals("decksLabel")) {
            tab = decksPane;
        } else if (parent.getId().equals("buttonLabel")){
            tab = buttonPane;
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

        public void displayRestart(Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);

            Label restartMessage = new Label("Are you sure you want to restart? Your progress in the current game will be lost.");
            restartMessage.setTextFill(Color.valueOf(GlobalCSSValues.text));
            restartMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            restartMessage.setWrapText(true);
            restartMessage.setAlignment(Pos.CENTER);
            restartMessage.setPrefWidth(250);

            borderPane.setAlignment(restartMessage, Pos.CENTER);
            borderPane.setCenter(restartMessage);

            HBox buttons = new HBox(10);

            //TODO: Change these actions to actually handle restarting
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

        public void displayExitWithoutSave(Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);

            Label exitMessage = new Label("Are you sure you want to exit to desktop? Your progress will be lost.");
            exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            exitMessage.setTextFill(Color.valueOf(GlobalCSSValues.text));
            exitMessage.setWrapText(true);
            exitMessage.setAlignment(Pos.CENTER);
            exitMessage.setPrefWidth(250);

            borderPane.setAlignment(exitMessage, Pos.CENTER);
            borderPane.setCenter(exitMessage);

            HBox buttons = new HBox(10);

            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
                clearPlayParent();
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

        public void displayMainMenuWithoutSave(Stage parentPopup){
            Stage popupWindow = new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);

            Label exitMessage = new Label("Are you sure you want to return to the Main Menu? Your progress will be lost.");
            exitMessage.setStyle("-fx-font-size: 25; -fx-font-family: serif;");
            exitMessage.setTextFill(Color.valueOf(GlobalCSSValues.text));
            exitMessage.setWrapText(true);
            exitMessage.setAlignment(Pos.CENTER);
            exitMessage.setPrefWidth(250);

            borderPane.setAlignment(exitMessage, Pos.CENTER);
            borderPane.setCenter(exitMessage);

            HBox buttons = new HBox(10);

            yes.setOnMouseClicked(e-> {
                popupWindow.close();
                parentPopup.close();
                clearPlayParent();
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

        public void displaySettingsPopup() {
            Stage popupWindow = new Stage();

            yes = new Label("Yes");
            yes.setStyle("-fx-border-radius: 2 2 2 2; " +
                    "-fx-background-radius: 2 2 2 2; " +
                    "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                    "; -fx-text-fill: " + GlobalCSSValues.buttonText +
                    "; -fx-font-size: 25; -fx-font-family: serif; -fx-border-color: BLACK;");
            yes.setAlignment(Pos.CENTER);
            yes.setPrefWidth(100);
            yes.setPrefHeight(35);
            no = new Label("No");
            no.setStyle(yes.getStyle());
            no.setAlignment(Pos.CENTER);
            no.setPrefWidth(yes.getPrefWidth());
            no.setPrefHeight(yes.getPrefHeight());
            no.setStyle(yes.getStyle());

            outlineYesNo(yes);
            outlineYesNo(no);

            popupWindow.initModality(Modality.APPLICATION_MODAL);

            Label saveButton = new Label("Save");
            setStyle(saveButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
            saveButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

            Label exitButton = new Label("Exit");
            setStyle(exitButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
            exitButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

            Label restartButton = new Label("Restart");
            setStyle(restartButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
            restartButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

            Label mainMenuButton = new Label("Main Menu");
            setStyle(mainMenuButton, "30", GlobalCSSValues.buttonBackground, 170, 80);
            mainMenuButton.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));

            saveButton.setOnMouseClicked(e->{
                saved = true;
                System.out.println("Save");
            });

            exitButton.setOnMouseClicked(e->{
                if (!saved) {
                    displayExitWithoutSave(popupWindow);
                } else {
                    clearPlayParent();
                    popupWindow.close();
                    exitFromPlay();
                }
            });

            restartButton.setOnMouseClicked(e->{
                displayRestart(popupWindow);
            });

            mainMenuButton.setOnMouseClicked(e -> {
                if (!saved) {
                    displayMainMenuWithoutSave(popupWindow);
                } else {
                    clearPlayParent();
                    popupWindow.close();
                    mainMenuFromPlay();
                }
            });

            VBox layout = new VBox(10);
            layout.setStyle("-fx-background-color: " + GlobalCSSValues.secondary);
            layout.getChildren().addAll(saveButton, restartButton, mainMenuButton, exitButton);
            layout.setAlignment(Pos.CENTER);
            Scene exitScene = new Scene(layout, 350, 400);
            exitScene.setFill(Color.MAROON);
            popupWindow.setScene(exitScene);
            popupWindow.showAndWait();
        }
    }

    public void displayPopup(MouseEvent event) {
        Popup popup = new Popup();
        popup.displaySettingsPopup();
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
            label.setStyle("-fx-border-radius: 2 2 2 2; " +
                    "-fx-background-radius: 2 2 2 2; " +
                    "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                    "; -fx-text-fill: " + GlobalCSSValues.buttonText +
                    "; -fx-font-size: 25; -fx-font-family: serif; -fx-border-color: "+ GlobalCSSValues.accent);
        });

        label.setOnMouseExited(e -> {
            label.styleProperty().setValue("-fx-border-radius: 2 2 2 2; " +
                    "-fx-background-radius: 2 2 2 2; " +
                    "-fx-background-color: " + GlobalCSSValues.buttonBackground +
                    "; -fx-text-fill: " + GlobalCSSValues.buttonText +
                    "; -fx-font-size: 25; -fx-font-family: serif; -fx-border-color: Black;");
        });
    }

    public AnchorPane getPlayParent() { return playParent; }
    public AnchorPane getBoardPane() { return boardPane; }
    public GameState getGameState() { return activeGame; }
}

