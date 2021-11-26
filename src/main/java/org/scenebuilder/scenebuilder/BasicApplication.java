package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.objects.*;
import org.scenebuilder.scenebuilder.dummy.*;

import java.util.ArrayList;
import java.util.List;

public class BasicApplication extends Application {

    private static SettingsObject settingsObject = new SettingsObject();

    private static ArrayList<DummyGame> newGames = new ArrayList<>();
    private static ArrayList<DummyGame> savedGames = new ArrayList<>();

    private static DummyGame selectedGame;
    private static SetupData setupData;

    public static void loadNewGames() {

        // do stuff to get list of playable games (on start) from Persistent Data team
        // todo

        DummyGame game1 = createDummyGame("Game 1", "Rectangle");
        DummyGame game2 = createDummyGame("Game 2", "Circle");

        newGames.clear();
        newGames.add(game1);
        newGames.add(game2);
        newGames.add(game1);
    }
    public static void loadSavedGames() {

        // do stuff to get list of saved games
        // todo

        DummyGame game1 = createDummyGame("Game 1", "Rectangle");
        DummyGame game2 = createDummyGame("Game 2", "Circle");

        savedGames.clear();
        savedGames.add(game1);
        savedGames.add(game2);
    }
    private static DummyGame createDummyGame(String gameName, String gameShape) {

        ArrayList<DummyPlayer> players = new ArrayList<>();
        DummyPlayer player1 = new DummyPlayer("Player 1", Color.AQUAMARINE, new ArrayList<DummyGameToken>(), new DummyInventory("Inventory 1", new ArrayList<GameObject>()), true);
        player1.addToken(new DummyGameToken("Token 1", "Square"));

        ArrayList<DummyTile> tiles = new ArrayList<>();

        double tileWidth = 100;
        double tileHeight = 100;

        double tileX = 0;
        double tileY = 0;

        boolean red = true;
        for (int i = 1; i < 57; i++) {

            if (red) {
                tiles.add(new DummyTile("Tile 1", "Rectangle", Color.RED, tileWidth, tileHeight, tileX, tileY, new ArrayList<DummyTile>()));
                red = false;
            } else {
                tiles.add(new DummyTile("Tile 1", "Rectangle", Color.BLACK, tileWidth, tileHeight, tileX, tileY, new ArrayList<DummyTile>()));
                red = true;
            }
            if (i%8 == 0) {
                red = !red;
                tileY += 100;
                tileX = 0;
            } else {
                tileX += 100;
            }
        }

        ArrayList<Deck> decks = new ArrayList<>();
        ArrayList<DummyCard> cards = new ArrayList<>();
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        Deck deck3 = new Deck();
        Deck deck4 = new Deck();
        deck1.addCard(new Card(), 52);
        deck2.addCard(new Card(), 52);
        deck3.addCard(new Card(), 52);
        deck4.addCard(new Card(), 52);
        decks.add(deck1);
        decks.add(deck2);
        decks.add(deck3);
        decks.add(deck4);


        ArrayList<Die> dice = new ArrayList<Die>();
        ArrayList<Spinner> spinners = new ArrayList();

        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();
        Die die4 = new Die();
        Die die5 = new Die();
        Die die6 = new Die();
        Die die7 = new Die();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);
        dice.add(die7);

        Spinner spinner1 = new Spinner();
        spinner1.setNumCategories(5);
        List<Double> spinnerWeight = new ArrayList();
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinners.add(spinner1);

        ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
        DummyGameToken gameToken = new DummyGameToken("Token 1", "Square");
        gameTokens.add(gameToken);

        DummyGamestate gamestate = new DummyGamestate(players, tiles, decks, dice, spinners, gameTokens);

        DummyGameBoard gameBoard = new DummyGameBoard(gameName, gameShape, 800, 700, 10, 10, tiles);

        DummyGameRules gameRules = new DummyGameRules();

        return new DummyGame(gameBoard, gameRules, gamestate);
    }

    // setters
    public static void setSelectedGame(DummyGame game) {
        selectedGame = game;
    }
    public static void setSetupData(SetupData data) {
        setupData = data;
    }
    public static void setSettingsObject(SettingsObject obj) { settingsObject = obj; }

    // getters
    public static ArrayList<DummyGame> getNewGames() {
        return newGames;
    }
    public static ArrayList<DummyGame> getSavedGames() {
        return savedGames;
    }
    public static DummyGame getSelectedGame() {
        return selectedGame;
    }
    public static SetupData getSetupData() {
        return setupData;
    }
    public static SettingsObject getSettingsObject() { return settingsObject; }

    public static int[] calculateScreenDimensions() {

        int[] screenDimensions = new int[2];

        Rectangle2D dims = Screen.getPrimary().getBounds();
        screenDimensions[0] = (int)dims.getWidth();
        screenDimensions[1] = (int)dims.getHeight();

        return screenDimensions;
    }
    public static void initScreenStyle(Stage stage) {

        if(settingsObject.getIsFullScreen() == true) {
            stage.initStyle(StageStyle.UNDECORATED); // remove title bar
        } else {
            stage.initStyle(StageStyle.DECORATED); // title bar intact
        }
    }
    public static void initScreenDimensions(Stage stage) {

        //stage.setResizable(false);

        if(settingsObject.getIsFullScreen() == true) {
            stage.setMaximized(true);
            int[] screenDimensions = calculateScreenDimensions();
            settingsObject.setWindowSize(screenDimensions);
        } else {
            stage.initStyle(StageStyle.DECORATED);

            int[] screenDimensions = settingsObject.getWindowSize();
            stage.setWidth(screenDimensions[0]);
            stage.setHeight(screenDimensions[1]);

            // theoretically keep the stage from becoming unuseable by going off the monitor
            int[] maxScreenDimensions = calculateScreenDimensions();
            stage.setMaxWidth(maxScreenDimensions[0]);
            stage.setMaxHeight(maxScreenDimensions[1]);
        }
    }

    public static Stage updateStage(Stage stage) {

        stage.close();

        Stage newStage = new Stage();

        initScreenStyle(newStage);
        initScreenDimensions(newStage);

        return newStage;
    }

    @Override
    public void start(Stage stage) {

        // calculate default screen dimensions
        //int[] screenDimensions = calculateScreenDimensions();
        //System.out.println("Your Screen Dimensions: " + screenDimensions[0] + " x " + screenDimensions[1]);

        stage = updateStage(stage);

        // initialize controller and set initial scene
        MainController controller = new MainController();
        controller.initialize(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}