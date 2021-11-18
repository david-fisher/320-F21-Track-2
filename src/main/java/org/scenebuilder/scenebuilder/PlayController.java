package org.scenebuilder.scenebuilder;

import javafx.animation.*;
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
import org.objects.Die;
import org.objects.Spinner;

import java.io.IOException;
import java.util.ArrayList;

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
    private HBox sptiHBox;
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

    private Stage stage;
    private SetupData setupData;
    private DummyGame activeGame;

    @FXML
    public void initialize(Stage stage) {

        super.initialize(stage);


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
        DummyGamestate gameState = game.getInitialGamestate();

        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double playWidth = screenDimensions.getWidth();
        double playHeight = screenDimensions.getHeight();

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
        initDecks(gameState.getDecks());

        //initRNG(gameState.getRNG());

        initPlayers(gameState.getPlayers());

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

    private void initTiles(ArrayList<DummyTile> tiles, AnchorPane boardPane, DummyGameBoard gameBoard) {
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

    private void initDecks(ArrayList<DummyDeck> decks) {
        fillDeckDrawer(decks, decksPane);

        // for each deck
        // create deck node
        // set deck node values
        // add deck node to drawer
    }

    private void initRNG(ArrayList<Die> dice, ArrayList<Spinner> spinners) {
        fillRNGDrawer(dice, spinners, rngPane);

        // for each RNG
        // create RNG node
        // set RNG node values
        // add RNG node to drawer
    }

    private void initPlayers(ArrayList<DummyPlayer> players) {

        players.forEach(p -> {
            initGamePiece(p.getGameTokens().get(0)); // todo, get specific game piece by reference
            //fill inventory
        });

        // for each player
        // set player info
        // add player stuff to inventory (later)
    }

    private void initGamePiece(DummyGameToken gamePiece) {
        // for each player
        // for each piece
        // get piece
        // draw piece at its location
        // set other info..?
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

    private void initDeckLabel() {
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
        playParent.setTopAnchor(decksLabel, 75.0);
        //tabsVBox.setMargin(decksLabel, new Insets(2, 0, 10, 0));
    }

    private void initRNGLabel() {
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

        playParent.getChildren().addAll(rngLabel);

        playParent.setRightAnchor(rngLabel, 0.0);
        playParent.setTopAnchor(rngLabel, 293.0);

        //tabsVBox.getChildren().addAll(rngLabel);
        //tabsVBox.setMargin(rngLabel, new Insets(2, 0, 10, 0));
    }

    private void initInventoryLabel() {
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
        playParent.setTopAnchor(inventoryLabel, 512.0);
        //tabsVBox.getChildren().addAll(inventoryLabel);
        //tabsVBox.setMargin(inventoryLabel, new Insets(2, 0, 10, 0));
    }

    private void initializeDeckDrawer() {
        decksPane = new ScrollPane();
        decksPane.setStyle("-fx-background-color: GREY;");
        decksPane.setId("decksDrawer");
        decksPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        decksPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        decksPane.setPrefWidth(355);
        decksPane.setPrefHeight(209);
        playParent.setRightAnchor(decksPane, -215.0);
        playParent.setTopAnchor(decksPane, 75.0);
        playParent.getChildren().addAll(decksPane);
    }

    private void initializeRNGDrawer() {
        rngPane = new ScrollPane();
        rngPane.setStyle("-fx-background-color: GREY;");
        rngPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rngPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        playParent.setRightAnchor(rngPane, -215.0);
        playParent.setTopAnchor(rngPane, 293.0);
        rngPane.setPrefWidth(355);
        rngPane.setPrefHeight(209);
        playParent.getChildren().addAll(rngPane);
    }

    private void initializeInventoryDrawer() {
        inventoryPane = new ScrollPane();
        inventoryPane.setStyle("-fx-background-color: GREY;");
        inventoryPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        inventoryPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        playParent.setRightAnchor(inventoryPane, -215.0);
        playParent.setTopAnchor(inventoryPane, 512.0);
        inventoryPane.setPrefWidth(355);
        inventoryPane.setPrefHeight(209);
        playParent.getChildren().addAll(inventoryPane);
    }

    //A method to add all the decks to the deck slider
    private void fillDeckDrawer(ArrayList<DummyDeck> decks, ScrollPane decksPane) {
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
                Card topCard = d.drawTop();
                ArrayList<GameObject> tempInventory = new ArrayList<GameObject>();
                tempInventory.add(topCard);

                //initializeInventoryDrawer();

            });
            container.getChildren().addAll(deck);
            container.setMargin(deck, new Insets(10, 10, 20, 10));
        });
        decksPane.setContent(container);
        decksPane.setStyle("-fx-border-color: black");

        //decksPane.toFront();
        //tabsVBox.getChildren().addAll(decksPane);
    }

    public static void fillRNGDrawer(ArrayList<Die> dice, ArrayList<Spinner> spinners, ScrollPane rngPane) {
        HBox container = new HBox();
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);

        dice.forEach(d -> {
            Rectangle die = new Rectangle(100, 100);
            die.setUserData(d);
            die.setFill(new ImagePattern(new Image(d.getIcon())));
            die.setOnMouseClicked(e -> {
                // roll this die if you can
            });
            container.getChildren().addAll(die);
        });

        spinners.forEach(d -> {
            Circle spinner = new Circle(100);
            spinner.setUserData(d);
            spinner.setFill(new ImagePattern(new Image(d.getIcon())));
            spinner.setOnMouseClicked(e -> {
                // spin this spinner if you can
            });
            container.getChildren().addAll(spinner);
        });
        rngPane.setContent(container);
        rngPane.setStyle("-fx-border-color: BLACK;");
    }

    private static void fillInventoryDrawer(ArrayList<DummyInventory> inventory, ScrollPane inventoryPane) {
//        HBox container = new HBox();
//        inventoryPane = new ScrollPane();
//        container.setAlignment(Pos.CENTER);
//        container.setSpacing(-10);
//        inventory.forEach(d -> {
//            double width = d.getWidth() == 0 ? 100 : d.getWidth();
//            double height = d.getHeight() == 0 ? 170 : d.getHeight();
//            Rectangle deck = new Rectangle(width, height);
//            deck.setUserData(d);
//
//            if(d.getIcon() != null) {
//                deck.setFill(new ImagePattern(new Image(d.getIcon())));
//            } else {
//                deck.setFill(Color.RED);
//            }
//            deck.setOnMouseClicked(e -> {
//                //Open this deck if you can // todo
//            });
//            container.getChildren().addAll(deck);
//            container.setMargin(deck, new Insets(10, 10, 20, 10));
//        });
//        decksPane.setContent(container);
//        decksPane.setStyle("-fx-border-color: black");
//        //decksPane.toFront();
    }

    public void initializePlayScreen() {
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double playWidth = screenDimensions.getWidth();
        double playHeight = screenDimensions.getHeight();

        playParent = new AnchorPane();
        playParent.setPrefWidth(playWidth);
        playParent.setPrefHeight(playHeight);

        //initSettingsAndPlayerIndicatorHBox();
        initPlayerTurnIndicator();
        initSettings();
        initializeDeckDrawer();
        initDeckLabel();
        initializeRNGDrawer();
        initRNGLabel();
        initializeInventoryDrawer();
        initInventoryLabel();
    }
    @FXML
    public void exitFromPlay(ActionEvent event, Stage baseStage) throws IOException {
        switchScene(event, "mainFXML.fxml", baseStage);
    }

    @FXML
    public void switchScene(ActionEvent event, String nextScene, Stage baseStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        if (baseStage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = baseStage;
        }
        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
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
        public void setButtonSize(Button button, float prefWidth, float prefHeight, int fontSize) {
            button.setPrefWidth(prefWidth);
            button.setPrefHeight(prefHeight);

            button.setMinWidth(button.getPrefWidth());
            button.setMaxWidth(button.getPrefWidth());
            button.setMinHeight(button.getPrefHeight());
            button.setMaxHeight(button.getPrefHeight());

            button.setStyle("-fx-font-size: "+fontSize+"; -fx-font-family: serif; -fx-background-color: linear-gradient(to top, #D3D3D3, #FFFFFF); -fx-border-color: #000000; -fx-background-insets: 1; -fx-border-radius: 4;");
        }
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

            Button yes = new Button("Yes");
            setButtonSize(yes, 70, 30, 15);
            Button no = new Button("No");
            setButtonSize(no, 70, 30, 15);

            //Change these actions to actually handle restarting
            yes.setOnAction(e-> {
                popupWindow.close();
                parentPopup.close();
            });
            no.setOnAction(e->popupWindow.close());

            buttons.getChildren().addAll(yes);
            buttons.getChildren().addAll(no);

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

            Button yes = new Button("Yes");
            setButtonSize(yes, 70, 30, 15);
            Button no = new Button("No");
            setButtonSize(no, 70, 30, 15);

            yes.setOnAction(e-> {
                popupWindow.close();
                parentPopup.close();
                try {
                    exitFromPlay(e, baseStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            no.setOnAction(e->popupWindow.close());

            buttons.getChildren().addAll(yes);
            buttons.getChildren().addAll(no);

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
            Button saveButton = new Button("Save");
            setButtonSize(saveButton, 170, 80, 40);
            Button exitButton = new Button("Exit");
            setButtonSize(exitButton, 170, 80, 40);
            Button restartButton = new Button("Restart");
            setButtonSize(restartButton, 170, 80, 40);
            saveButton.setOnAction(e->{
                saved = true;
                System.out.println("Save");
            });
            exitButton.setOnAction(e->{
                if (!saved) {
                    displayExitWithoutSave(baseStage, popupWindow);
                } else {
                    popupWindow.close();
                    try {
                        exitFromPlay(e, baseStage);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            restartButton.setOnAction(e->{
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

