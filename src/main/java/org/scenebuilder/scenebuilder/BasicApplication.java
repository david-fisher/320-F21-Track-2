package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.objects.Tile;

import java.util.ArrayList;
import java.util.Set;

public class BasicApplication extends Application {

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
        DummyPlayer player1 = new DummyPlayer("Player 1", Color.AQUAMARINE, new ArrayList<DummyGameToken>(), new DummyInventory("Inventory 1"), true);
        player1.addToken(new DummyGameToken("Token 1", "Square"));

        ArrayList<DummyTile> tiles = new ArrayList<>();
        tiles.add(new DummyTile("Tile 1", "Square", Color.RED, 100, 100, 50, 50, new ArrayList<DummyTile>()));
        tiles.add(new DummyTile("Tile 2", "Rectangle", Color.OLDLACE, 150, 100, 200, 100, new ArrayList<DummyTile>()));

        ArrayList<DummyDeck> decks = new ArrayList<>();
        ArrayList<DummyCard> cards = new ArrayList<>();
        for(int i = 0; i < 52; ++i) {
            cards.add(new DummyCard("Card " + i));
        }
        DummyDeck deck = new DummyDeck("Deck 1", cards);
        decks.add(deck);

        ArrayList<DummyRNG> rng = new ArrayList<>();

        ArrayList<DummyGameToken> gameTokens = new ArrayList<>();
        DummyGameToken gameToken = new DummyGameToken("Token 1", "Square");
        gameTokens.add(gameToken);

        DummyGamestate gamestate = new DummyGamestate(players, tiles, decks, rng, gameTokens);

        DummyGameBoard gameBoard = new DummyGameBoard(gameName, gameShape, 800, 800, 10, 10, tiles);

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

    @Override
    public void start(Stage stage) throws Exception {

        // load fxml file (which specifies the controller)
        Parent root = FXMLLoader.load(getClass().getResource("mainFXML.fxml"));

        // create new instance of the controller class
        // inject all fx:id tagged objects from fxml file
        // and marked with @FXML annotation in controller

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.setMaximized(true);

        System.out.println("My Screen Dimensions:");
        System.out.println(width);
        System.out.println(height);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}