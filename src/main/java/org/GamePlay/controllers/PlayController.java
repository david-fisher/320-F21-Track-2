package org.GamePlay.controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.GameEditor.application.GameBoard;
import org.GameObjects.objects.Button;
import org.GameObjects.objects.Spinner;
import org.RuleEngine.nodes.LiteralNode;
import org.RuleEngine.nodes.MoveByNode;
import org.GamePlay.BasicApplication;
import org.GamePlay.Display;
import org.GamePlay.GlobalCSSValues;
import org.RuleEngine.engine.*;
import org.GameObjects.objects.*;

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

    double scale;

    int numDrawers;

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
        //boardPane.setStyle("-fx-background-color: " + GlobalCSSValues.background);
        org.GameEditor.application.GameBoard gameBoard = gameStateInput.getGameBoard();

        players = gameStateInput.getAllPlayers();

        gameStateInput.addRegistry("currPlayer", players.get(0));

        currPlayer = (Player) gameStateInput.getRegistry("currPlayer");

        calculateScale(gameStateInput);

        double boardWidth = gameBoard.getWidth() * scale;
        double boardHeight = gameBoard.getHeight() * scale;

        playParent.getChildren().add(boardPane);

        playParent.setLeftAnchor(boardPane, (playWidth - boardWidth - 120)/2);
        playParent.setTopAnchor(boardPane, (playHeight - boardHeight)/2);
        playParent.setBottomAnchor(boardPane, (playHeight - boardHeight)/2);

        initBoard(gameBoard, boardPane);
        initTiles(gameState.getAllTiles(), boardPane, gameBoard);
        initPlayers(players);

        ArrayList<Deck> decks = gameStateInput.getAllDecks();
        ArrayList<Button> buttons = gameStateInput.getAllButtons();

        numDrawers = 0;
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

        initPlayerTurnIndicator();
        initCards(gameStateInput);
        //TODO: Have some indicator for whether inventory is needed
        if (true) {
            initInventoryDrawer(numDrawers);
            initInventoryLabel(numDrawers);
            fillInventory(currPlayer.getInventory());
        }

    }

    private void calculateScale(GameState gameState) {
        org.GameEditor.application.GameBoard gameBoard = gameState.getGameBoard();
        double scaleWidth = (playWidth - 120) / gameBoard.getWidth();
        double scaleHeight = playHeight / gameBoard.getHeight();
        scale = (scaleHeight >= scaleWidth ? scaleWidth : scaleHeight) * 0.88;

    }
    private void initBoard(org.GameEditor.application.GameBoard gameBoard, AnchorPane boardPane) {
        Rectangle board;
        double width = gameBoard.getWidth();
        double height = gameBoard.getHeight();

        double boardWidth = scale * width;
        double boardHeight = scale * height;

        //Set the boardPane's height and width so that it will not overlap with other elements on smaller screens
        boardPane.setPrefWidth(boardWidth);
        boardPane.setPrefHeight(boardHeight);

        board = new Rectangle(width, height);

        board.setWidth(boardWidth);
        board.setHeight(boardHeight);

        boardPane.getChildren().add(board);
//
//        double widthPadding = (playWidth - 120 - width) / 2;
//        double heightPadding = (playHeight - height) / 2;
//        boardPane.setLeftAnchor(board, widthPadding);
//        boardPane.setTopAnchor(board, heightPadding);
//        boardPane.setRightAnchor(board, 0.0);
//        boardPane.setBottomAnchor(board, 0.0);


        // create board anchorPane
        // set anchorPane values
    }

    private void initTiles(ArrayList<Tile> tiles, AnchorPane boardPane, org.GameEditor.application.GameBoard gameBoard) {
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
            tile.setLayoutX(t.getTileXLocation() * scale * gameBoard.getCellWidth() + 1);
            tile.setLayoutY(t.getTileYLocation() * scale * gameBoard.getCellHeight() + 1);
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
            Style.setStyle(label, "18", button.getColorString(), GlobalCSSValues.text, button.getText().length() * 12, button.getHeight());
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
            Style.setStyle(label, "18", c.getColorString(), GlobalCSSValues.text,c.getText().length() * 12, c.getHeight());
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
        if (inventory.size() == 0) { return; }
        initInventoryLabel(numDrawers);
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
        boolean singlePiece = players.get(0).getGamePieces().size() == 1;
        int rows = (int) Math.ceil(Math.sqrt(numPlayers));
        double tileWidth;
        tileWidth = parent.getShape().equals("Rectangle")
                ? ((Rectangle) parent.getParent()).getWidth()
                : ((Circle) parent.getParent()).getRadius() * 2;
        double radius = singlePiece ? (((tileWidth / rows) - 8 * (rows + 1)) / 2) : tileWidth / 2 - 16;
        Circle gp = new Circle(radius, gamePiece.getColor());
        gp.setUserData(gamePiece);
        gamePiece.setParent(gp);
        boardPane.getChildren().add(gp);
        System.out.println("Baseline:" + gp.getBaselineOffset());

        if (singlePiece) {
            double shiftX = (2 * (i % rows) + 1) * radius + (i % rows + 1) * (tileWidth - rows * radius * 2) / (rows + 1);
            double shiftY = (2 * (i / rows) + 1) * radius + (i / rows + 1) * (tileWidth - rows * radius * 2) / (rows + 1);
            gp.setLayoutX(parent.getParent().getLayoutX() + shiftX);
            gp.setLayoutY(parent.getParent().getLayoutY() + shiftY);
        } else {
            gp.setLayoutX((parent.getParent().getLayoutX() + tileWidth) / 2 + 8);
            gp.setLayoutY((parent.getParent().getLayoutY() + parent.getParent().getBoundsInParent().getHeight()) / 2 + 8);
        }
//        Display.getDisplay().updatePiece(gamePiece);
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
        playerTurnIndicator.setText("" + currPlayer.getLabel() + "'s \n Turn");

        playerTurnIndicator.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: " + Math.log10(200 / currPlayer.getLabel().length()) * 12 +
                "; -fx-font-color: BLACK; " +
                "-fx-border-color: #000000;");
        playerTurnIndicator.setId("playerTurnIndicator");
        playerTurnIndicator.setWrapText(true);
        playerTurnIndicator.setLineSpacing(5);
        playerTurnIndicator.setTextAlignment(TextAlignment.CENTER);
        playerTurnIndicator.setAlignment(Pos.CENTER);
        playerTurnIndicator.setPadding(new Insets(2, 2, 2, 2));

        playerTurnIndicator.setPrefWidth(90);

        playerTurnIndicator.setPrefHeight(90);

        playParent.getChildren().addAll(playerTurnIndicator);
        playParent.setRightAnchor(playerTurnIndicator, 70.0);
        playParent.setTopAnchor(playerTurnIndicator, 10.0);
    }

    private void initDeckLabel(int numDrawers) {
        decksLabel = new Label();
        initLabel(decksLabel, "Decks", "decksLabel");
        playParent.setTopAnchor(decksLabel, (playHeight / 5) + 175 - 50 * Math.log(Math.pow(10, numDrawers - 1)));
    }

    private void initButtonLabel(int numDrawers) {
        buttonLabel = new Label();
        initLabel(buttonLabel, "Buttons", "buttonLabel");
        playParent.setTopAnchor(buttonLabel, (playHeight / 5) + 175 - 20 * Math.log(Math.pow(10, (numDrawers - 1))) * Math.log(Math.pow(10, (3 - numDrawers))));
    }

    private void initInventoryLabel(int numDrawers) {
        inventoryLabel = new Label();
        initLabel(inventoryLabel, "" + currPlayer.getLabel() + "'s \n Inventory" , "inventoryLabel");
        inventoryLabel.setTextAlignment(TextAlignment.CENTER);
        inventoryLabel.setWrapText(true);
        playParent.setTopAnchor(inventoryLabel, (playHeight / 5) + 175 + 50 * Math.log(Math.pow(10, numDrawers - 1)));
    }

    private void initLabel(Label label, String text, String id) {
        label.setText(text);
        label.setStyle("-fx-font-family: Serif; " +
                "-fx-font-size: " + Math.log10(280 / currPlayer.getLabel().length()) * 16 +
                "; -fx-background-color: " + GlobalCSSValues.buttonBackground +
                "; -fx-border-color: BLACK;");
        label.setTextFill(Color.valueOf(GlobalCSSValues.buttonText));
        label.setLineSpacing(3);
        label.setId(id);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);

        label.setPrefWidth(140);

        label.setPrefHeight(209);

        label.setOnMouseClicked(e -> {
            slideOut(e);
        });
        Style.initDarken(label);

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

        initSettings();
    }

    public void exitFromPlay() {
        Platform.exit();
    }

    public void clearPlayParent() {
        playParent = new AnchorPane();
        boardPane = new AnchorPane();
    }

    public void clearParents() {
        gameState.getAllTiles().forEach(t -> {
            t.setParent(null);
        });
        gameState.getAllGamePieces().forEach(gp -> {
            gp.setParent(null);
        });
        gameState.getAllCards().forEach(c -> {
            c.setParent(null);
        });
        gameState.getAllButtons().forEach(b -> {
            b.setParent(null);
        });
    }

    public void mainMenuFromPlay(Stage stage) {
        MainController controller = new MainController();
        controller.initialize(stage);
    }

    public void restartPlay(Stage stage) {
        Savable.intitDB();
        PlayController controller = new PlayController();
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

    public AnchorPane getPlayParent() { return playParent; }
    public AnchorPane getBoardPane() { return boardPane; }
    public Label getPlayerTurnIndicator() { return playerTurnIndicator; }
    public Stage getPlayStage() { return stage; }
}

