package org.GamePlay.controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private GameState gameState;
    private ScrollPane decksPane;
    private ScrollPane buttonPane;
    private ScrollPane inventoryPane;
    private static Label playerTurnIndicator;
    private Label decksLabel;
    private Label buttonLabel;
    private Label inventoryLabel;
    private Pane settingsPane;
    private HBox inventoryContainer;
    private Stage stage;

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
        
        gameState = BasicApplication.getProject().getIntiGS();

        initPlayScreen();

        initGame(gameState);

        Scene newScene = new Scene(playParent);
        stage.setScene(newScene);
        stage.setResizable(true);
        stage.show();
    }

    private static AnchorPane boardPane;
    private void initGame(GameState gameStateInput) {

        if (boardPane == null) {
            boardPane = new AnchorPane();
        }
        boardPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);
        GameBoard gameBoard = gameStateInput.getGameBoard();

        players = gameStateInput.getAllPlayers();

        gameStateInput.addRegistry("currPlayer", players.get(0));

        currPlayer = (Player) gameStateInput.getRegistry("currPlayer");

        initPlayerTurnIndicator();

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
        initPlayers(players);

        ArrayList<Deck> decks = gameStateInput.getAllDecks();
        ArrayList<Button> buttons = gameStateInput.getAllButtons();

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

        initCards(gameStateInput);
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
        for (int i = 0; i < players.size(); i++) {
            initGamePiece(players.get(i).getGamePieces(), i);
        }

        // for each player
        // set player info
        // TODO: add player stuff to inventory (later)
    }

    private void initButtons(GameState gameState) {
        ArrayList<Button> buttons = gameState.getAllButtons();
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
            label.setCenterShape(true);
            container.setMargin(button.getParent(), new Insets(10, 10, 20, 10));
        });
        buttonPane.setContent(container);
        buttonPane.setStyle("-fx-border-color: black");
    }

    private void initCards(GameState gameState) {
        ArrayList<Card> cards = gameState.getAllCards();
        cards.forEach(c -> {
            String event = c.getOnPlay();
            Label label = new Label(c.getLabel());
            c.setParent(label);
            setStyle(label, "18", c.getColorString(), c.getText().length() * 12, c.getHeight());
            c.getParent().setOnMouseClicked(e -> {
                if (!event.equals("")) {
                    if (c.getEnabled()) {
                        interpreter.interpretEvent(gameState.events.get(event), gameState);
                    }
                }
            });
        });
    }
    private void addToInventory(GameObject object) {
        Node inventoryObject = object.getParent();
        currPlayer.getInventory().add(object);
        inventoryContainer.getChildren().addAll(inventoryObject);
        inventoryContainer.setMargin(inventoryObject, new Insets(10, 10, 10, 10));
    }

    public void fillInventory(ArrayList<GameObject> inventory) {
        currPlayer = (Player) gameState.getRegistry("currPlayer");
        if (inventory == null) { return; }
        inventoryContainer = new HBox();
        inventoryContainer.setAlignment(Pos.CENTER);
        inventoryContainer.setSpacing(-10);
        inventory.forEach(c -> {
            addToInventory(c);
        });
        inventoryPane.setContent(inventoryContainer);
        inventoryContainer.setStyle("-fx-border-color: black; -fx-background-color: " + GlobalCSSValues.secondary);
    }

    private void initGamePiece(ArrayList<Gamepiece> gamePieces, int i) {
        gamePieces.forEach(gamePiece -> {
            drawPiece(gamePiece, i);
        });
    }

    private void drawPiece(Gamepiece gamePiece, int i) {
        Tile parent = gamePiece.getLocation();
        if (parent == null) { return; }
        int numPlayers = players.size();
        double rows = Math.ceil(Math.sqrt(numPlayers));
        double radius = (((parent.getWidth() / rows) - (rows + 1)) / 2);
        Circle gp = new Circle(radius, Color.WHITE);
        gp.setUserData(gamePiece);
        gamePiece.setParent(gp);
        boardPane.getChildren().add(gp);
        if (players.get(0).getGamePieces().size() == 1) {
            double shift = (2 * (i % rows) + 1) * (radius + 2 * ((i % rows) + 1));
            gp.setLayoutX(parent.getXPos() + shift);
            gp.setLayoutY(parent.getYPos() + shift);
        } else {
            gp.setRadius(parent.getWidth() / 2 - 4);
            gp.setLayoutX((parent.getXPos() + parent.getWidth()) / 2 + 2);
            gp.setLayoutX((parent.getYPos() + parent.getHeight()) / 2 + 2);
        }
        Display.getDisplay().updatePiece(gamePiece);
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
                "-fx-border-color: #000000;");
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

    private void rollDice(MouseEvent e, ArrayList<Die> dice) {
        Label source = (Label) e.getSource();
        Die die = dice.get(0);
        LiteralNode<String> name = new LiteralNode<>("currPlayer");
        int roll = die.roll();
        LiteralNode<Integer> value = new LiteralNode<>(roll);

        Display.getDisplay().print("You rolled: " + roll);

        MoveByNode move = new MoveByNode();
        move.setOperand(name, 0).setOperand(value, 1);
        ArrayList<org.RuleEngine.nodes.Node> moveNodes = new ArrayList<>();
        moveNodes.add(move);
        interpreter.interpretEvent(moveNodes, gameState);
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

    public void mainMenuFromPlay(Stage stage) {
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

    public void displayPopup(MouseEvent event) {
        Popup popup = new Popup(stage);
        popup.displaySettingsPopup();
    }

    public void initDarken(Label label) {
        label.setOnMouseEntered(e -> {
            if (label.getUserData() instanceof Button) {
                    if (((Button)label.getUserData()).getEnabled()) {
                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setBrightness(-0.2);
                        label.setEffect(colorAdjust);
                    }
            } else if (label.getUserData() instanceof Button) {
                if (((Button)label.getUserData()).getEnabled()) {
                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setBrightness(-0.2);
                    label.setEffect(colorAdjust);
                }
            } else {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.2);
                label.setEffect(colorAdjust);
            }
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

    public AnchorPane getPlayParent() { return playParent; }
    public AnchorPane getBoardPane() { return boardPane; }
    public Label getPlayerTurnIndicator() { return playerTurnIndicator; }
}

