package org.GamePlay;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.RuleEngine.engine.GameState;
import org.GameObjects.objects.*;
import org.GamePlay.controllers.MainController;

import java.util.ArrayList;
import java.util.List;

public class BasicApplication extends Application {

    private static SettingsObject settingsObject = new SettingsObject();

    //TODO: Phase these out
    private static ArrayList<GameState> newGames = new ArrayList<>();
    private static ArrayList<GameState> savedGames = new ArrayList<>();

    //TODO: selected game could be replaced by Project
    private static GameState selectedGame;
    private static SetupData setupData;

    private static Project project;

    public static void loadNewGames() {
        // do stuff to get list of playable games (on start) from Persistent Data team
        // todo

        GameState game1 = createGame("All Drawers", "Rectangle", true, true, true);
        GameState game2 = createGame("RNG and Inventory", "Rectangle", false, true, true);
        GameState game3 = createGame("Inventory Only", "Circle", false, false, true);

        newGames.clear();
        newGames.add(game1);
        newGames.add(game2);
        newGames.add(game3);
        System.out.println(newGames);
    }
    public static void loadSavedGames() {

        // do stuff to get list of saved games
        // todo

        GameState game1 = createGame("All Drawers", "Rectangle", true, true, true);
        GameState game2 = createGame("RNG and Inventory", "Rectangle", false, true, true);
        GameState game3 = createGame("Inventory Only", "Rectangle", false, false, true);

        savedGames.clear();
        savedGames.add(game1);
        savedGames.add(game2);
        savedGames.add(game3);
        System.out.println(savedGames);
    }
    private static GameState createGame(String gameName, String gameShape, boolean decksOn, boolean rngOn, boolean inventoryOn) {

        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("David", Color.PURPLE, new ArrayList<Gamepiece>(), new ArrayList<GameObject>(), true);
        Gamepiece gp = new Gamepiece();
        gp.setColor(Color.WHITE);
        player1.addPiece(new Gamepiece());
        players.add(player1);
//        System.out.println("Basic screen: " + player1.getGamePieces().get(0).getColor().toString());

        ArrayList<Tile> tiles = new ArrayList<>();

        double tileWidth = 100;
        double tileHeight = 100;

        int tileX = 0;
        int tileY = 0;

        boolean red = true;
        Tile previous = new Tile();
        for (int i = 1; i < 57; i++) {
            Tile tile = new Tile();

            tile.setHeight(tileHeight);
            tile.setWidth(tileWidth);
            if (red) {
                tile.setColor(Color.RED);
                tile.setLabel("Tile 1");
                tile.setShape("Rectangle");
                tile.setXPos(tileX);
                tile.setYPos(tileY);
                tiles.add(tile);
                red = false;
            } else {
                tile.setColor(Color.BLACK);
                tile.setLabel("Tile 1");
                tile.setShape("Rectangle");
                tile.setXPos(tileX);
                tile.setYPos(tileY);
                tiles.add(tile);
                red = true;
            }
            if (i%8 == 0) {
                red = !red;
                tileY += 100;
                tileX = 0;
            } else {
                tileX += 100;
            }
            if (i == 1) {
                previous = tile;
            } else {
                previous.addConnect(tile);
                previous = tile;
            }
        }
        tiles.get(0).addConnect(tiles.get(1));
        tiles.get(0).addConnect(tiles.get(8));
        tiles.get(7).addConnect(tiles.get(6));
        tiles.get(7).addConnect(tiles.get(15));
        tiles.get(48).addConnect(tiles.get(40));
        tiles.get(48).addConnect(tiles.get(49));
        tiles.get(55).addConnect(tiles.get(54));
        tiles.get(55).addConnect(tiles.get(47));
        for (int i = 1; i < tiles.size() - 1; i++) {
            if (i == 7 || i == 48) {
                continue;
            }
            Tile tile = tiles.get(i);
            if (i % 8 == 0 || i % 8 == 7) {
                tile.addConnect(tiles.get(i + 8));
                tile.addConnect(tiles.get(i - 8));
            } else {
                tile.addConnect(tiles.get(i + 1));
                tile.addConnect(tiles.get(i - 1));
            }
        }
        ArrayList<Deck> decks = new ArrayList<>();
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
//        Die die2 = new Die();
//        Die die3 = new Die();
//        Die die4 = new Die();
//        Die die5 = new Die();
//        Die die6 = new Die();
//        Die die7 = new Die();
        dice.add(die1);
//        dice.add(die2);
//        dice.add(die3);
//        dice.add(die4);
//        dice.add(die5);
//        dice.add(die6);
//        dice.add(die7);

        Spinner spinner1 = new Spinner();
        spinner1.setNumCategories(5);
        List<Double> spinnerWeight = new ArrayList();
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinnerWeight.add(0.2);
        spinners.add(spinner1);

        ArrayList<Button> buttons = new ArrayList<>();
        Button button = new Button();
        button.setText("Roll Dice");
        button.setColorString("#FFFFFF");
        button.setWidth(100.0);
        button.setHeight(50.0);
        buttons.add(button);

        Button button1 = new Button();
        button1.setText("Draw From Deck");
        button1.setColorString("#FFFFFF");
        button1.setWidth(100.0);
        button1.setHeight(50.0);
        buttons.add(button1);


        ArrayList<Token> gameTokens = new ArrayList<>();
        Token gameToken = new Token();
        gameToken.setLabel("Token 1");
        gameToken.setShape("Rectangle");
        gameTokens.add(gameToken);

        GameState gameState = new GameState(); //players, tiles, decks, dice, spinners, gameTokens
        gameState.setAllTiles(tiles);
        if (decksOn) {
            gameState.setAllDecks(decks);
        }
        if (rngOn) {
            gameState.setAllDice(dice);
            gameState.setAllSpinners(spinners);
        }

        gameState.setAllButtons(buttons);

        gameState.setAllPlayers(players);
        gameState.setAllTokens(gameTokens);
        GameBoard gameBoard = new GameBoard(gameName, gameShape, 800, 700, 10, 10, tiles);

        gameState.setGameBoard(gameBoard);
        gameState.getGameBoard().setBoardID(gameName);
        return gameState;
    }

    // setters
    public static void setSelectedGame(GameState game) {
        selectedGame = game;
    }
    public static void setSetupData(SetupData data) {
        setupData = data;
    }
    public static void setSettingsObject(SettingsObject obj) { settingsObject = obj; }

    // getters
    public static ArrayList<GameState> getNewGames() {
        return newGames;
    }
    public static ArrayList<GameState> getSavedGames() {
        return savedGames;
    }
    public static GameState getSelectedGame() {
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

        if(settingsObject.getIsFullScreen() == true) {
            stage.setMaximized(true);
        } else {

            // theoretically keep the stage from becoming unuseable by going off the monitor
//            int[] maxScreenDimensions = calculateScreenDimensions();
//            stage.setMaxWidth(maxScreenDimensions[0]);
//            stage.setMaxHeight(maxScreenDimensions[1]);
        }
    }

    public static Stage restartStage(Stage stage) {

        stage.close();

        Stage newStage = new Stage();

        initScreenStyle(newStage);
        initScreenDimensions(newStage);

        return newStage;
    }
    public static Stage updateStage(Stage stage) {

        initScreenDimensions(stage);

        return stage;
    }
    public static void setProject(Project p) { project = p; }
    public static Project getProject() { return project; }

    @Override
    public void start(Stage stage) {

        // calculate default screen dimensions
        //int[] screenDimensions = calculateScreenDimensions();
        //System.out.println("Your Screen Dimensions: " + screenDimensions[0] + " x " + screenDimensions[1]);

        //stage = updateStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);

        // initialize controller and set initial scene
        MainController controller = new MainController();
        controller.initialize(stage);
    }

    public static void main(String[] args) {

        // stuff done before the app launches
        GlobalCSSValues.initDefaultPalette();

        launch();
    }

}