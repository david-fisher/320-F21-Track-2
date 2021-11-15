package org.scenebuilder.scenebuilder;
import javafx.geometry.Insets;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import org.objects.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.event.*;
import javafx.util.Duration;
import org.objects.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class PlayFXMLController {

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


    private Stage stage;
    private SetupData setupData;
    private DummyGame activeGame;

    @FXML
    public void initialize() {


        // load relevant data
        setupData = BasicApplication.getSetupData();
        activeGame = BasicApplication.getSelectedGame();

        initGame(activeGame);

        playSettings.setImage(new Image("https://images-ext-1.discordapp.net/external/C1VkLgkVceGoEsJogTZ4Nfjo4W-cnZ2GF6FR-XFnIzk/https/cdn-icons-png.flaticon.com/512/61/61094.png?width=375&height=375",
                40, 40, true, true));
    }

    private void initGame(DummyGame game) {
        DummyGameBoard gameBoard = game.getGameBoard();
        DummyGamestate gameState = game.getInitialGamestate();

        AnchorPane boardPane = new AnchorPane();
        boardPane.setPrefHeight(gameBoard.getHeight());
        boardPane.setPrefWidth(gameBoard.getWidth());
        playParent.getChildren().add(boardPane);

        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double playWidth = screenDimensions.getWidth();
        double playHeight = screenDimensions.getHeight();

        playParent.setLeftAnchor(boardPane, (playWidth - gameBoard.getWidth() - 140)/2);
        playParent.setTopAnchor(boardPane, (playHeight - gameBoard.getHeight() - 168)/2);

        initBoard(gameBoard, boardPane);
        initTiles(gameBoard.getTiles(), boardPane);
        initDecks(gameState.getDecks());

        //initRNG(gameState.getRNG());

        initPlayers(gameState.getPlayers());

    }

    private void initBoard(DummyGameBoard gameBoard, AnchorPane boardPane) {
        Shape board;
        double width = gameBoard.getWidth();
        double height = gameBoard.getHeight();

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

    private void initTiles(ArrayList<DummyTile> tiles, AnchorPane boardPane) {

        tiles.forEach(t -> {
            Shape tile;
            double width = t.getWidth();
            double height = t.getHeight();

            if (t.getShape().equals("Rectangle")) {
                tile = new Rectangle(width, height);
            } else {
                tile = new Circle(width / 2);
            }
            tile.setUserData(t);
            tile.setLayoutX(t.getXPos());
            tile.setLayoutY(t.getYPos());
            boardPane.getChildren().addAll(tile);
        });
        // for each tile
        // create tile
        // set tile values
        // add tile to anchorPane
    }

    private void initDecks(ArrayList<DummyDeck> decks) {
        initializeDeckDrawer(decks, decksPane);

        // for each deck
        // create deck node
        // set deck node values
        // add deck node to drawer
    }

    private void initRNG(ArrayList<Die> dice, ArrayList<Spinner> spinners) {
        initializeRNGDrawer(dice, spinners, rngPane);

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

    //A method to add all the decks to the deck slider
    private static void initializeDeckDrawer(ArrayList<DummyDeck> decks, ScrollPane decksPane) {

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
            });
            container.getChildren().addAll(deck);
            container.setMargin(deck, new Insets(10, 10, 20, 10));
        });
        decksPane.setContent(container);
        decksPane.setStyle("-fx-border-color: black");
        //decksPane.toFront();
    }

    public static void initializeRNGDrawer(ArrayList<Die> dice, ArrayList<Spinner> spinners, ScrollPane rngPane) {

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
        if (parent.getId().equals("buttonDecks")) {
            tab = decksPane;
        } else {
            tab = rngPane;
        }
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

